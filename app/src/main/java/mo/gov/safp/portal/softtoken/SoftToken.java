package mo.gov.safp.portal.softtoken;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.converter.PropertyConverter;


import java.io.Serializable;

import mo.gov.safp.portal.softtoken.opt.AccountDb;


@Entity
public class SoftToken implements Serializable {
    private static final long serialVersionUID = -5489210907477574647L;

    @Id(autoincrement = true)
    private Long localId; // 本地id 主键

    @Unique
    private String username;

    private String accountType;
    private String status;

    private String displayName;

    private String tokenId;

    @Transient
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isEffective(){
        return  TextUtils.equals("VERIFIED",status);
    }

    @Transient
    private String code;


    @Convert(columnType = String.class, converter = AccountIndexConverter.class)
    private AccountDb.AccountIndex index;

    @Generated(hash = 2009046744)
    public SoftToken(Long localId, String username, String accountType, String status, String displayName,
                     String tokenId, AccountDb.AccountIndex index) {
        this.localId = localId;
        this.username = username;
        this.accountType = accountType;
        this.status = status;
        this.displayName = displayName;
        this.tokenId = tokenId;
        this.index = index;
    }

    @Generated(hash = 429239554)
    public SoftToken() {
    }

    public Long getLocalId() {
        return this.localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AccountDb.AccountIndex getIndex() {
        return this.index;
    }

    public void setIndex(AccountDb.AccountIndex index) {
        this.index = index;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public static class AccountIndexConverter implements PropertyConverter<AccountDb.AccountIndex, String> {

        @Override
        public AccountDb.AccountIndex convertToEntityProperty(String databaseValue) {
            if (TextUtils.isEmpty(databaseValue)) {
                return null;
            }

            return new Gson().fromJson(databaseValue, AccountDb.AccountIndex.class);
        }

        @Override
        public String convertToDatabaseValue(AccountDb.AccountIndex entityProperty) {
            if (entityProperty != null) {
                return new Gson().toJson(entityProperty);
            }
            return null;
        }
    }
}
