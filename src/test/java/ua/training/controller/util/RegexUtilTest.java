package ua.training.controller.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexUtilTest {

    @Test
    public void shouldReturnFalseIfRegexCodeIsNull() {
        boolean actual = RegexUtil.matches(null, "login");

        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseIfInputIsNull() {
        boolean actual = RegexUtil.matches("login.regex", null);

        assertFalse(actual);
    }

    @Test
    public void shouldReturnTrueIfInputMatchesRegex() {
        boolean actual = RegexUtil.matches("password.regex", "testPassword");

        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseIfInputDoesNotMatchesRegex() {
        boolean actual = RegexUtil.matches("login.regex", "test Login");

        assertFalse(actual);
    }
}