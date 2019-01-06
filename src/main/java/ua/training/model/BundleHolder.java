package ua.training.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BundleHolder {
    private static Map<String, ResourceBundle> bundles = new HashMap<>();
    private BundleHolder() {}
    public static ResourceBundle getBundle() {
        return bundles.get(Thread.currentThread().getName());
    }
    public static void putBundle(ResourceBundle bundle) {
        bundles.put(Thread.currentThread().getName(), bundle);
    }
}
