package mo.gov.safp.portal.softtoken;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mo.gov.safp.portal.base.CustomApplication;
import mo.gov.safp.portal.softtoken.event.IdentityCode;
import mo.gov.safp.portal.softtoken.event.IdentityEvent;
import mo.gov.safp.portal.softtoken.greendao.GreenDaoManager;
import mo.gov.safp.portal.softtoken.greendao.SoftTokenDao;
import mo.gov.safp.portal.softtoken.opt.AccountDb;
import mo.gov.safp.portal.softtoken.opt.OtpProvider;
import mo.gov.safp.portal.softtoken.opt.OtpSource;
import mo.gov.safp.portal.softtoken.opt.OtpSourceException;
import mo.gov.safp.portal.softtoken.opt.TotpClock;
import mo.gov.safp.portal.softtoken.opt.time.SystemWallClock;
import mo.gov.safp.portal.softtoken.rx.RxBus;
import mo.gov.safp.portal.softtoken.user.UserManager;
import mo.gov.safp.portal.utils.SharedPreferencesUtils;

public class SoftTokenManager {

    static final String LOG_TAG = "SoftTokenManager";

    private static volatile SoftTokenManager instance;

    private static Object objLock = new Object();

    private final AccountDb accountDb;
    private final OtpSource otpProvider;

    private SoftTokenManager(Context context) {
        accountDb = new AccountDb(context);
        otpProvider = new OtpProvider(accountDb, new TotpClock(context, new SystemWallClock()));
    }

    public static SoftTokenManager getInstances() {
        if (instance == null) {
            synchronized (objLock) {
                if (instance == null) {
                    instance = new SoftTokenManager(CustomApplication.getInstance());
                }
            }
        }
        return instance;
    }

    public String getSoftToken(String username) {
        AccountDb.AccountIndex selectedIndex = null;

        for (AccountDb.AccountIndex accountIndex : accountDb.getAccounts()) {
            if (TextUtils.equals(username, getOriginalName(accountIndex))) {
                selectedIndex = accountIndex;
                break;
            }
        }
        if (selectedIndex != null) {
            try {
                return otpProvider.getNextCode(selectedIndex);
            } catch (OtpSourceException e) {
                return "";
            }
        }
        return "";
    }

    //原來的名字
    public String getOriginalName(AccountDb.AccountIndex selectedIndex) {
        return accountDb.getOriginalName(selectedIndex);
    }

    //重新設置名字
    public void resetName(SoftToken softToken, String name) {
        softToken.setDisplayName(name);
        getDao().insertOrReplace(softToken);
    }


    public void delete(SoftToken softToken) {
        if (softToken.getIndex() != null) {
            accountDb.delete(softToken.getIndex());
        }

        getDao().delete(softToken);

        RxBus.getDefault().post(new IdentityEvent(IdentityCode.IdentitySoftTokenDelete));
    }

    public SoftTokenDao getDao() {
        return GreenDaoManager.getInstance().getSession().getSoftTokenDao();
    }

    public static String ISSUSER = "GOV.MO";

    public void addAccount(String secret, String id) {
        if (UserManager.getInstance().isLogin()) {
            String username = "safpuser20";

            List<AccountDb.AccountIndex> list = accountDb.getAccounts();

            //删除原来的记录
            if (list != null && !list.isEmpty()) {
                for (AccountDb.AccountIndex index : list) {
                    if (TextUtils.equals(accountDb.getOriginalName(index), username)) {
                        accountDb.delete(index);
                        break;
                    }
                }
            }

            AccountDb.AccountIndex index = accountDb.add(username, secret, AccountDb.OtpType.TOTP, 0, false, ISSUSER);

            SoftToken mSoftToken = new SoftToken();

            mSoftToken.setDisplayName(username);
            mSoftToken.setAccountType(UserManager.getInstance().getAccountType().toString());
            mSoftToken.setIndex(index);
            mSoftToken.setUsername(username);
            mSoftToken.setTokenId(id);
            mSoftToken.setStatus("VERIFIED");

            GreenDaoManager.getInstance().getSession().getSoftTokenDao().insertOrReplace(mSoftToken);
            RxBus.getDefault().post(new IdentityEvent(IdentityCode.IdentitySoftTokenSuccess));

        }
    }


    //適配舊數據
    public void adapterSoftToken() {
        List<AccountDb.AccountIndex> indexList = accountDb.getAccounts();
        List<SoftToken> mSoftTokenList = getSoftToken();

        if (!mSoftTokenList.isEmpty()) {
            return;
        }

        List<SoftToken> mAdapterSoftToken = new ArrayList<>();

        if (indexList != null && !indexList.isEmpty()) {
            for (AccountDb.AccountIndex index : indexList){

                String mName = getOriginalName(index);
                SoftToken mSoftToken = new SoftToken();

                mSoftToken.setDisplayName(index.getName());
                mSoftToken.setAccountType(" ");
                mSoftToken.setIndex(index);
                mSoftToken.setUsername(TextUtils.isEmpty(mName)?index.getName():mName);
                String id = SharedPreferencesUtils.getValue(CustomApplication.getInstance(), "softToken:" + mSoftToken.getUsername());

                if (!TextUtils.isEmpty(id)) {
                    mSoftToken.setTokenId(id);
                    boolean isStatus = (boolean) SharedPreferencesUtils.getValue(CustomApplication.getInstance(), "softTokenStatus:" + id, false);

                    if (isStatus) {
                        mSoftToken.setStatus("DISABLED");
                    }else {
                        mSoftToken.setStatus("VERIFIED");
                    }
                }
                mAdapterSoftToken.add(mSoftToken);
            }
        }
        if (!mAdapterSoftToken.isEmpty()) {
            getDao().insertOrReplaceInTx(mAdapterSoftToken);
        }
    }


    public List<SoftToken> getSoftToken() {
        return getDao().loadAll();
    }




}
