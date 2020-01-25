package ua.training.controller.util;


import org.apache.log4j.Logger;

public class UriParser {
    private static Logger logger = Logger.getLogger(UriParser.class);

    public static int getIndexFromUri(String uri) {
        String indexPart = uri.replaceAll(".*/(\\d*)$", "$1");
        logger.info("index: " + indexPart);
        return Integer.parseInt(indexPart);
    }

    public static String getCommandNameFromUri(String uri) {
        return uri.replaceAll(".*/cinema/([a-z]*(/[a-z-]*)?)(/[0-9-]+)?", "$1");
    }

    public static String getAccessLabel(String uri) {
        return uri.replaceAll(".*/cinema/(\\w*)/.*", "$1");
    }
}
