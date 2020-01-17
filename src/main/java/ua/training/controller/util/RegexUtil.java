package ua.training.controller.util;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    private static final String BUNDLE_NAME = "regex";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Method retrieves regex by regexCode and checks if input matches the regex.
     *
     * @param regexCode code of regex
     * @param input input to be checked
     * @return result of check
     */
    public static boolean matches(String regexCode, String input) {
        if (regexCode == null || input == null) {
            return false;
        }
        String regex = bundle.getString(regexCode);
        return input.matches(regex);
    }
}
