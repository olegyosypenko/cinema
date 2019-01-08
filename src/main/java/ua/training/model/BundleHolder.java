package ua.training.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class BundleHolder {
    private static Map<String, ResourceBundle> bundles = new HashMap<>();

    private BundleHolder() {}
    public static ResourceBundle getBundle() {
        ResourceBundle defaultBundle = ResourceBundle.getBundle("mysql_localized", new Locale("uk"));
        return bundles.getOrDefault(Thread.currentThread().getName(), defaultBundle);
    }
    public static void putBundle(ResourceBundle bundle) {
        bundles.put(Thread.currentThread().getName(), bundle);
    }
}
