package com.petstore.rest_assured.support;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) { data.put(key, value); }
    public Object get(String key) { return data.get(key); }
    public Long getLong(String key) { Object v = data.get(key); return v == null ? null : Long.valueOf(v.toString()); }
}
