package constants;

import helpers.PropertiesHelper;

public class ConfigData {
    static {
        PropertiesHelper.loadAllFile();
    }

    public static String URL = PropertiesHelper.getValue("url");
    public static String EMAIL = PropertiesHelper.getValue("email");
    public static String PASSWORD = PropertiesHelper.getValue("password");
}
