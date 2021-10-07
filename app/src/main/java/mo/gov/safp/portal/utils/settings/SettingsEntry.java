package mo.gov.safp.portal.utils.settings;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import mo.gov.safp.portal.base.CustomApplication;
import mo.gov.safp.portal.utils.SharedPrefsUtils;

public abstract class SettingsEntry<T> implements SharedPrefsUtils.Entry<T> {

    private final int mKeyResId;
    private final int mDefaultValueResId;

    public SettingsEntry(@StringRes int keyResId, int defaultValueResId) {
        mKeyResId = keyResId;
        mDefaultValueResId = defaultValueResId;
    }

    @NonNull
    @Override
    public String getKey() {
        return CustomApplication.getInstance().getString(mKeyResId);
    }

    protected int getDefaultValueResId() {
        return mDefaultValueResId;
    }

    @Nullable
    public abstract T getValue();

    public abstract void putValue(@Nullable T value);

    public void remove() {
        SharedPrefsUtils.remove(this);
    }
}