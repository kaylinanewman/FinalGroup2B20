package utils;

import java.util.ArrayList;

public class Constants {
    public static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    public static final int EXPLICIT_WAIT = 20;
    public static final int IMPLICIT_WAIT = 10;
    public static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/batch18excel.xlsx";
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/screenshots/";
    public static final String USERNAME_FIELD = "txtUsername";
    public static final String PASSWORD_FIELD = "txtPassword";
    public static final String LOGIN_BUTTON = "btnLogin";
    public static final String MY_INFO_MENU = "#menu_pim_viewMyDetails b";
    public static final String CHANGE_PHOTO_OPTION = "//*[@id=\"empPic\"]";
    public static final String CHOOSE_FILE_OPTION = "//*[@id=\"photofile\"]";
    public static final String UPLOAD_BUTTON = "//*[@id=\"btnSave\"]";
    public static final String SUCCESS_MESSAGE = "//div[contains(@class, 'message') and contains(@class, 'success')]\n";
    public static final String ERROR_MESSAGE = "//div[@class='message warning fadable']\n";
    public static final String PROFILE_PICTURE = "//*[@id=\"empPic\"]";
    public static final String BASE_PATH = "src/test/resources/images/";


    public static ArrayList<String> allNames = new ArrayList<>();
    public static String allDependentsNameXPath = "//table/tbody/tr/td[2]";
}


