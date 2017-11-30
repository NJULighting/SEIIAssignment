package nju.lighting.bl.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/11/30.
 * Description:
 * @author Liao
 */
public class NameChecker {
    /**
     * Check whether name's valid
     * @param name name to be checked
     * @return true if only contains number, Chinese character, letters
     */
    public static boolean validName(String name) {
        // Check emptiness
        if (name.trim().isEmpty()) return false;

        // Check whether contains illegal character
        String regex = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
}
