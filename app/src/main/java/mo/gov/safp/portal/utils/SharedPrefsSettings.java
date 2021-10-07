package mo.gov.safp.portal.utils;


import mo.gov.safp.portal.R;
import mo.gov.safp.portal.utils.settings.Settings;

public interface SharedPrefsSettings {

    // App升級
    Settings.StringSettingsEntry APP_UPGRADE = new Settings.StringSettingsEntry(R.string.pref_key_app_upgrade, R.string.pref_default_value_empty_string);
    // 當前登入帳戶
    Settings.StringSettingsEntry ACTIVE_ACCOUNT_NAME = new Settings.StringSettingsEntry(R.string.pref_key_active_account_name, R.string.pref_default_value_empty_string);
    // 當前登入帳戶類型
    Settings.StringSettingsEntry ACTIVE_ACCOUNT_TYPE = new Settings.StringSettingsEntry(R.string.pref_key_active_account_type, R.string.pref_default_value_empty_string);

    // 電話驗證資料
    Settings.StringSettingsEntry MOBILE_PROFILE = new Settings.StringSettingsEntry(R.string.pref_key_mobile_profile, R.string.pref_default_value_empty_string);

    // Web白名單
    Settings.StringSettingsEntry WEB_WHITELIST = new Settings.StringSettingsEntry(R.string.pref_key_web_whitelist, R.string.pref_default_value_empty_string);
    // Euid 白名單
    Settings.StringSettingsEntry ACCOUNT_EUIDLIST = new Settings.StringSettingsEntry(R.string.pref_key_account_euidlist, R.string.pref_default_value_empty_string);

    // App 主題
    Settings.StringSettingsEntry APP_THEME = new Settings.StringSettingsEntry(R.string.pref_key_app_theme, R.string.pref_default_value_empty_string);
    // App 設置
    Settings.StringSettingsEntry APP_SETTING = new Settings.StringSettingsEntry(R.string.pref_key_app_setting, R.string.pref_default_value_empty_string);

}
