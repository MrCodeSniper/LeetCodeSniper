package mo.gov.safp.portal.softtoken.user;

import android.accounts.Account;

import androidx.annotation.NonNull;

import mo.gov.safp.portal.utils.SharedPrefsSettings;


public final class UserManager {

    private static volatile UserManager instance;

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    // 用户类型
    public enum UserType {
        ACCOUNT, MOBILE;
    }

    // 一戶通帳戶
    private Account mAccount;          //一戶通帳戶帳戶
    private String mAuthTokenType;     //一戶通帳戶類型

    private UserManager() {
        // 初始化账号
        initUser();
    }

    // 初始化驗證數據：獲取帳戶信息或手機驗證信息
    private void initUser() {
        String activeType = SharedPrefsSettings.ACTIVE_ACCOUNT_TYPE.getValue();
        String activeName = SharedPrefsSettings.ACTIVE_ACCOUNT_NAME.getValue();

    }

    private void resetUser() {
        this.mAccount = null;
        this.mAuthTokenType = null;
    }


    // 帳號是否登入
    public boolean isLogin() {
        return true;
    }

    // 個人帳戶
    public boolean isPersonalType() {
        if (mAccount == null) {
            return false;
        }
        return AccountConsts.AccountType.PERSONAL.getType().equals(mAccount.type);
    }

    // 公營實體帳戶
    public boolean isPublicEntityType() {
        if (mAccount == null) {
            return false;
        }
        return AccountConsts.AccountType.GOV_ENTITY.getType().equals(mAccount.type);
    }

    // 私營實體帳戶
    public boolean isPrivateEntityType() {
        if (mAccount == null) {
            return false;
        }
        return AccountConsts.AccountType.CORP_ENTITY.getType().equals(mAccount.type);
    }

    public Account getAccount() {
        return mAccount;
    }

    public @NonNull
    AccountConsts.AccountType getAccountType() {
        if (UserManager.getInstance().isPublicEntityType()) {
            return AccountConsts.AccountType.GOV_ENTITY;
        } else if (UserManager.getInstance().isPrivateEntityType()) {
            return AccountConsts.AccountType.CORP_ENTITY;
        }
        return AccountConsts.AccountType.PERSONAL;
    }


    public UserType getUserType() {
        if (isLogin()) {
            return UserType.ACCOUNT;
        }
        return UserType.ACCOUNT;
    }


}
