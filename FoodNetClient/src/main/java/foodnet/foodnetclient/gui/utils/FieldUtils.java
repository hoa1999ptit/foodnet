/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author Kushtrim Hajrizi
 */
public abstract class FieldUtils {
    
    private static Pattern timePattern = Pattern.compile("[0-9]{2}:[0-9]{2}");
    private static Pattern phonePattern = Pattern.compile("\\+[0-9]{11}");
        
    public static boolean fieldsEmpty(TextInputControl... inputs) {
        for (TextInputControl i: inputs)
            if (i.getText().trim().isEmpty())
                return true;
        return false;
    }
    
    public static Date checkTimeFormat(TextField tf) {
        String timeStr = tf.getText().trim();
        Matcher matcher = timePattern.matcher(timeStr);
        matcher.find();
        if (matcher.matches()) {
            System.out.println("Matches");
            String splitted[] = timeStr.split(":");
            int hours = Integer.parseInt(splitted[0]);
            int minutes = Integer.parseInt(splitted[1]);
            if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
                return getDate(hours, minutes);
            }
        }
        return null;
    }
    
    private static Date getDate(int hours, int minutes) {
        Calendar c = Calendar.getInstance();
        c.set(0, 0, 0, hours, minutes, 0);
        return c.getTime();
    }
    
    public static boolean validPhoneNumber(TextField phoneField) {
        Matcher matcher = phonePattern.matcher(phoneField.getText().trim());
        matcher.find();
        return matcher.matches();
    }
}
