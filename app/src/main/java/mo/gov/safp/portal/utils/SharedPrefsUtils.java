package mo.gov.safp.portal.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import mo.gov.safp.portal.base.CustomApplication;

public class SharedPrefsUtils {

    private final static String MAIN_PREFERENCES_KEY = "mo.gov.smart.common.pref_info";

    private SharedPrefsUtils() {}

    @NonNull
    public static SharedPreferences getSharedPrefs() {
        return CustomApplication.getInstance().getSharedPreferences(MAIN_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    @NonNull
    public static SharedPreferences.Editor getEditor() {
        return getSharedPrefs().edit();
    }

    @Nullable
    public static String getString(@NonNull Entry<String> entry) {
        return getSharedPrefs().getString(entry.getKey(), entry.getDefaultValue());
    }

    public static int getInt(@NonNull Entry<Integer> entry) {
        return getSharedPrefs().getInt(entry.getKey(), entry.getDefaultValue());
    }

    public static long getLong(@NonNull Entry<Long> entry) {
        return getSharedPrefs().getLong(entry.getKey(), entry.getDefaultValue());
    }

    public static float getFloat(@NonNull Entry<Float> entry) {
        return getSharedPrefs().getFloat(entry.getKey(), entry.getDefaultValue());
    }

    public static boolean getBoolean(@NonNull Entry<Boolean> entry) {
        return getSharedPrefs().getBoolean(entry.getKey(), entry.getDefaultValue());
    }

    public static void putString(@NonNull Entry<String> entry, @Nullable String value) {
        getEditor().putString(entry.getKey(), value).apply();
    }

    public static void putInt(@NonNull Entry<Integer> entry, int value) {
        getEditor().putInt(entry.getKey(), value).apply();
    }

    public static void putLong(@NonNull Entry<Long> entry, long value) {
        getEditor().putLong(entry.getKey(), value).apply();
    }

    public static void putFloat(@NonNull Entry<Float> entry, float value) {
        getEditor().putFloat(entry.getKey(), value).apply();
    }

    public static void putBoolean(@NonNull Entry<Boolean> entry, boolean value) {
        getEditor().putBoolean(entry.getKey(), value).apply();
    }

    public static void remove(@NonNull Entry<?> entry) {
        getEditor().remove(entry.getKey()).apply();
    }

    public static void clear() {
        getEditor().clear().apply();
    }

    public interface Entry<T> {

        @NonNull
        String getKey();

        @NonNull
        T getDefaultValue();
    }
}
