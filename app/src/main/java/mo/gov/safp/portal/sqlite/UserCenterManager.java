package mo.gov.safp.portal.sqlite;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.locks.ReentrantLock;

import mo.gov.safp.portal.base.CustomApplication;

public class UserCenterManager {
    private static volatile UserCenterManager INSTANCE = null;
    private LoginResultData resultData;
    private boolean isTokenValid;
    private final ReentrantLock mUserDataLock = new ReentrantLock();
    private final String TAG = "UserCenterManager";
    private final UserInfoRepository mUserRepository;

    //登出结果码
    public final static int RESULT_LOGOUT_FAIL = 0;
    public final static int RESULT_LOGOUT_SUCCESS = 1;
    public final static int RESULT_SWITCH_MAIN = 2;

    private UserCenterManager() {
        mUserRepository = new UserInfoRepository(CustomApplication.getInstance());
    }

    public static UserCenterManager getInstance() {
        if (INSTANCE == null) {
            synchronized (UserCenterManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserCenterManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 清理登录内存缓存数据
     */
    public void clearCache() {
        mUserDataLock.lock();
        try {
            resultData = null;
//            EventManager.send(LoginResultData.class, null);
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 查当前使用账号
     */
    private @Nullable
    LoginResultData findCurrentUserInfo() {
        return mUserRepository.queryCurrentUser();
    }


    /**
     * 取登录数据
     * 先取内存缓存，再读持久化数据
     */
    public LoginResultData getLoginResult() {
        mUserDataLock.lock();
        try {
            if (resultData != null) {
                return resultData;
            }
            resultData = findCurrentUserInfo();
            return resultData;
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 获取身份令牌
     */
    public String getToken() {
        mUserDataLock.lock();
        try {
            getLoginResult();
            if (resultData != null) {
                return resultData.token;
            }
            return "";
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 保存自动刷新Token
     *
     * @param token 新token
     */
    public void setToken(String token) {
        mUserDataLock.lock();
        try {
            getLoginResult();
            if (resultData != null) {
                resultData.token = token;
                updateUserInfo(resultData);
            }
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 是否已登陆
     *
     * @return true 已登陆
     */
    public boolean isLogin() {
        mUserDataLock.lock();
        try {
            getLoginResult();
            return resultData != null && !TextUtils.isEmpty(resultData.token);
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 保存用户信息
     *
     * @param data
     */
    public void saveUserInfo(LoginResultData data) {
        LoginResultData user = mUserRepository.queryById(data.euid);
        if (user == null) {
            mUserRepository.insertUser(data);
        } else {
            data.id = user.id;
            mUserRepository.updateUser(data);
        }
    }

    public void updateUserInfo(LoginResultData data) {
        if (data != null) {
            mUserRepository.updateUser(data);
        }
    }


    /**
     * 根据euid查用户信息
     */
    public LoginResultData getUserInfoById(String euid) {
        return mUserRepository.queryById(euid);
    }

    public void deleteUserInfo(LoginResultData data) {
        mUserRepository.deleteUser(data);
    }

    /**
     * 保存登录成功用户信息
     */
    public void saveLoginUserData(@NotNull LoginResultData data) {
        mUserDataLock.lock();
        try {
            LoginResultData oldUser = UserCenterManager.getInstance().getLoginResult();
            clearCache();
            saveLoginUserData(oldUser, data);
        } finally {
            mUserDataLock.unlock();
        }
    }

    /**
     * 持久化保存登录信息
     */
    private void saveLoginUserData(@Nullable LoginResultData oldUser, LoginResultData current) {
        if (oldUser != null) {
            oldUser.isCurrent = false;
            updateUserInfo(oldUser);
        }
        current.isCurrent = true;
        saveUserInfo(current);
    }

}

