package mo.gov.safp.portal.utils.settings;

import android.net.Uri;

import android.text.TextUtils;

import androidx.annotation.BoolRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import mo.gov.safp.portal.base.CustomApplication;
import mo.gov.safp.portal.utils.SharedPrefsUtils;


public interface Settings {

    class StringSettingsEntry extends SettingsEntry<String> {

        public StringSettingsEntry(@StringRes int keyResId, @StringRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        @Override
        public String getDefaultValue() {
            return CustomApplication.getInstance().getString(getDefaultValueResId());
        }

        @Nullable
        @Override
        public String getValue() {
            return SharedPrefsUtils.getString(this);
        }

        @Override
        public void putValue(@Nullable String value) {
            SharedPrefsUtils.putString(this, value);
        }
    }

    class IntegerSettingsEntry extends SettingsEntry<Integer> {

        public IntegerSettingsEntry(@StringRes int keyResId, @IntegerRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        @Override
        public Integer getDefaultValue() {
            return CustomApplication.getInstance().getResources().getInteger(getDefaultValueResId());
        }

        @NonNull
        @Override
        public Integer getValue() {
            return SharedPrefsUtils.getInt(this);
        }

        @Override
        public void putValue(@NonNull Integer value) {
            SharedPrefsUtils.putInt(this, value);
        }
    }

    class LongSettingsEntry extends SettingsEntry<Long> {

        public LongSettingsEntry(@StringRes int keyResId, @StringRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        @Override
        public Long getDefaultValue() {
            return Long.valueOf(CustomApplication.getInstance().getResources().getString(getDefaultValueResId()));
        }

        @NonNull
        @Override
        public Long getValue() {
            return SharedPrefsUtils.getLong(this);
        }

        @Override
        public void putValue(@NonNull Long value) {
            SharedPrefsUtils.putLong(this, value);
        }
    }

    class FloatSettingsEntry extends SettingsEntry<Float> {

        public FloatSettingsEntry(@StringRes int keyResId, @StringRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        @Override
        public Float getDefaultValue() {
            return Float.valueOf(CustomApplication.getInstance().getResources().getString(
                    getDefaultValueResId()));
        }

        @NonNull
        @Override
        public Float getValue() {
            return SharedPrefsUtils.getFloat(this);
        }

        @Override
        public void putValue(@NonNull Float value) {
            SharedPrefsUtils.putFloat(this, value);
        }
    }

    class BooleanSettingsEntry extends SettingsEntry<Boolean> {

        public BooleanSettingsEntry(@StringRes int keyResId, @BoolRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        @Override
        public Boolean getDefaultValue() {
            return CustomApplication.getInstance().getResources().getBoolean(getDefaultValueResId());
        }

        @NonNull
        @Override
        public Boolean getValue() {
            return SharedPrefsUtils.getBoolean(this);
        }

        @Override
        public void putValue(@NonNull Boolean value) {
            SharedPrefsUtils.putBoolean(this, value);
        }
    }

    class UriSettingsEntry extends StringSettingsEntry {

        public UriSettingsEntry(@StringRes int keyResId, @StringRes int defaultValueResId) {
            super(keyResId, defaultValueResId);
        }

        @NonNull
        public Uri getDefaultUriValue() {
            return stringToUri(getDefaultValue());
        }

        @Nullable
        public Uri getUriValue() {
            return stringToUri(getValue());
        }

        @Nullable
        private static Uri stringToUri(@Nullable String string) {
            return !TextUtils.isEmpty(string) ? Uri.parse(string) : null;
        }

        public void putUriValue(@Nullable Uri value) {
            putValue(value != null ? value.toString() : null);
        }
    }
}
