package ua.training.controller.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UriParserTest {

    @Test
    public void shouldReturnIndex() {
        int expected = 2;

        int actual = UriParser.getIndexFromUri("test/uri/index/2");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCommandName() {
        String expected = "user/command";
        String uri = "cinema/cinema/user/command/32";

        String actual = UriParser.getCommandNameFromUri(uri);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCommandNameWhenThereIsNoIndex() {
        String expected = "user/command";
        String uri = "cinema/cinema/user/command";

        String actual = UriParser.getCommandNameFromUri(uri);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAccessLabel() {
        String expected = "user";
        String uri = "cinema/cinema/user/command/32";

        String actual = UriParser.getAccessLabel(uri);

        assertEquals(expected, actual);
    }
}