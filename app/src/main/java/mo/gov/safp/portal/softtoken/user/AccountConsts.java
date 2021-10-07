package mo.gov.safp.portal.softtoken.user;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mo.gov.safp.portal.BuildConfig;

public abstract class AccountConsts {

    //public static final String ACCOUNT_TYPE = "mo.gov.account";
    //public static final String ACCOUNT_NAME = "Macau Government Service Account";

    public static final String TOKEN_META_TOKEN_TYPE = "token_type";
    public static final String TOKEN_META_REFRESH_TOKEN = "refresh_token";
    public static final String TOKEN_META_SCOPE = "scope";
    public static final String TOKEN_META_EXPIRES_IN = "expires_in";
    public static final String TOKEN_META_TOKEN_CLIENT = "token_client";
    public static final String TOKEN_META_USER_PROFILE = "user_profile";

    public static final String EXTRA_AUTH_ACCOUNT_TYPE = "AUTH_ACCOUNT_TYPE";
    public static final String EXTRA_AUTH_URL = "AUTH_URL";
    public static final String EXTRA_CLIENT_ID = "CLIENT_ID";
    public static final String EXTRA_CLIENT_SECRET = "CLIENT_SECRET";
    public static final String EXTRA_SCOPES = "SCOPES";
    public static final String EXTRA_REDIRECT_URL = "REDIRECT_URL";
    public static final String EXTRA_TOKEN_TYPE = "TOKEN_TYPE";
    public static final String EXTRA_GRANT_TYPE = "GRANT_TYPE";
    public static final String EXTRA_ACTIVE_ACCESS_TOKEN = "ACTIVE_ACCESS_TOKEN"; //已登入的Token
    public static final String EXTRA_RESET = "RESET"; //重置Cookie
    public static final String EXTRA_SKIP_ACCOUNT = "SKIP_ACCOUNT"; //是否跳過account檢查

    public static final String EXTRA_ACCESS_TOKEN = "TOKEN";
    public static final String EXTRA_REFRESH_TOKEN = "REFRESH_TOKEN";

    public static final String EXTRA_AUTH_CODE = "AUTH_CODE";
    public static final String RESULT_AUTH_CODE = "RESULT_AUTH_CODE";

    // token類型(存儲在本地)
    public enum TokenType {

        GOV_PERSONAL_SERVICE("MacauGovService", "Macau Government Service"), // 個人：主tokenType
        CORP_ENTITY_SERVICE("MacauCorpEntityService", "一戶通私營實體"),         // 私營實體
        GOV_ENTITY_SERVICE("MacauGovEntityService", "一戶通公營機構"),         // 公營機構

        GOV_RECRUIT("MacauGovRecruitService", "Macau Government Recruit Service"),
        GOV_FSS("MacauGovFssService", "Macau Government FSS Service"),
        GOV_SSM("MacauGovSsmService", "Macau Government SSM Service"),
        GOV_G2E("G2E", "G2E 公務人員管理及服務平台"),
        GOV_CTT_SEPBOX("CttSepbox", "CTT SEPBox"),
        GOV_DSI("DSI", "現金分享"),
        GOV_DSAJ("MacauGovDsajService", "電子書面報告"),
        GOV_IAM("iam", "市政署 IAM"),
        GOV_FP("FP", "退休基金會"),
        GOV_DSAT("MacauGovDsatService", "交通事務局");

        TokenType(String type, String label) {
            this.type = type;
            this.label = label;
        }

        private String type;
        private String label;

        public String getType() {
            return this.type;
        }

        public String getLabel() {
            return this.label;
        }

        // 根據TokenType 獲取 AccountType
        public static AccountType getAccountType(String str){
            return AccountType.PERSONAL;
        }

        public static TokenType getTokenType(String str){
            for(TokenType type : TokenType.values()){
                if(TextUtils.equals(type.type, str)){
                    return type;
                }
            }
            return null;
        }

        public static boolean isMainTokenType(String str){
            if(TextUtils.equals(GOV_PERSONAL_SERVICE.type, str) || TextUtils.equals(CORP_ENTITY_SERVICE.type, str)
                    || TextUtils.equals(GOV_ENTITY_SERVICE.type, str)){
                return true;
            }
            return false;
        }

        public static String getAuthTokenModule(TokenType tokenType) {
            switch (tokenType) {
                case GOV_RECRUIT:
                    return "Recruit"; //統一招聘
                case GOV_FSS:
                    return "SocialSecurity"; //社保供款
                case GOV_SSM:
                    return "Health"; //醫療衛生
                case GOV_CTT_SEPBOX:
                    return "CttSepbox";
                case GOV_DSI:
                    return "DSI";
                case GOV_DSAJ:
                    return "Dsaj";
                case GOV_IAM:
                    return "iam";
                case GOV_FP:
                    return "Fp";
                case GOV_DSAT:
                    return "MacauGovDsatService";
                default:
                    return "";
            }
        }

        // 非主TokenType
        public static List<TokenType> getSubTokenTypes(){
            List<TokenType> subTokenTypes = new ArrayList<TokenType>();
            for(TokenType tokenType : TokenType.values()){
                subTokenTypes.add(tokenType);
            }
            return subTokenTypes;
        }

        @NonNull
        @Override
        public String toString() {
            return this.type;
        }
    }


    public enum GrantType {

        CODE("authorization_code"),
        PASSWORD("password"),
        REFRESH_TOKEN("refresh_token");

        GrantType(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return this.value;
        }

        @NonNull
        @Override
        public String toString() {
            return this.value;
        }
    }

    // 帳戶類型

    public enum AccountType {

        PERSONAL("Personal", "mo.gov.account"),             //个人
        CORP_ENTITY("Corp-Entity", "mo.gov.account.corp"),  //私營實體
        GOV_ENTITY("Gov-Entity", "mo.gov.account.gov");     //政府實體

        private String label;
        private String type;

        AccountType(String label, String type) {
            this.label = label;
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public String getType() {
            return type;
        }

        public static @NonNull AccountType getAccountType(String label){
            for(AccountType type : AccountType.values()){
                if(TextUtils.equals(type.label, label)){
                    return type;
                }
            }
            return AccountType.PERSONAL;
        }

        public static @NonNull AccountType getAccountTypeByType(String typeStr){
            for(AccountType type : AccountType.values()){
                if(TextUtils.equals(type.type, typeStr)){
                    return type;
                }
            }
            return AccountType.PERSONAL;
        }
    }

    public static @NonNull AccountType getAccountType(String authTokenType) {
        if(TokenType.GOV_ENTITY_SERVICE.type.equals(authTokenType)){
            return AccountType.GOV_ENTITY;
        }else if(TokenType.CORP_ENTITY_SERVICE.type.equals(authTokenType)){
            return AccountType.CORP_ENTITY;
        }
        return AccountType.PERSONAL;
    }

    public static String getAuthTokenLabel(String authTokenType) {
        TokenType tokenType = TokenType.getTokenType(authTokenType);
        if(tokenType != null){
            return tokenType.label;
        }
        return authTokenType;
    }


}
