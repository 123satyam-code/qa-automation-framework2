package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioData = new HashMap<>();

    /* ================== SET DATA ================== */
    public void set(String key, Object value) {
        scenarioData.put(key, value);
    }

    /* ================== GET DATA ================== */
    public Object get(String key) {
        return scenarioData.get(key);
    }

    /* ================== GET WITH TYPE SAFETY ================== */
    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(scenarioData.get(key));
    }

    /* ================== CLEAR ================== */
    public void clear() {
        scenarioData.clear();
    }
}
