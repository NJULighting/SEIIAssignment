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
     * Check whether name's valid. The name's length should be between 1 and 15(included)
     * @param name name to be checked
     * @return true if only contains number, Chinese character, letters and the length is between 1 and 15
     */
    public static boolean validName(String name) {
        // Check emptiness
        return !name.trim().isEmpty() && matchRegex(name, "[A-Za-z0-9\u4e00-\u9fa5]{1,15}");
    }

    /**
     * Valid ID should only contains number or letters, and the length of the id must larger than 6
     * @param id if to be checked
     * @return true is not empty and only contains number and letter
     */
    public static boolean validID(String id) {
        // TODO: 2017/11/30 Add checking length of id
        return !id.trim().isEmpty() && matchRegex(id, "[a-zA-z0-9]{6,}");
    }

    private static boolean matchRegex(String target, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }
}
