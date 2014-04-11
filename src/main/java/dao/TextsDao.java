package dao;

import java.util.ResourceBundle;

public class TextsDao {
    public static String getProperty(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("mail");
        return rb.getString(key);
    }
}
