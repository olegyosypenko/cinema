package ua.training.controller.util;


import org.apache.log4j.Logger;

public class UriParser {
    private static Logger logger = Logger.getLogger(UriParser.class);
    public static int getIndexFromUri(String uri) {
        String[] uriParts = uri.split("/");
        String indexPart = uri.split("/")[uriParts.length - 1];
        logger.info("index: " + indexPart);
        return Integer.parseInt(indexPart);
    }

    public static String getCommandNameFromUri(String uri) {
        String result = uri.replaceFirst(".*/servlet/", "");
        result = result.replaceFirst("/[0-9-]+", "");
        return result;
    }

    public static String getAccessLabel(String uri) {
        String result = uri.replaceFirst(".*/servlet/", "");
        result = result.replaceAll("/.*", "");
        return result;
    }
}
