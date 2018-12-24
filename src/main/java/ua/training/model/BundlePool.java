package ua.training.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BundlePool {
    public static BundlePool instance = new BundlePool();
    Map<String, ResourceBundle> bundles;
    private BundlePool() {
        bundles = new HashMap<>();
    }
    public ResourceBundle getBundleByThreadName(String name) {
        return bundles.get(name);
    }
    public void putBundle(String name, ResourceBundle bundle) {
        bundles.put(name, bundle);
    }

}
