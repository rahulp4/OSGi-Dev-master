package com.intellizonex.gateway.core;

import java.util.Properties;

public interface Storage {
    public void put(String key, Properties props);
    public Properties get(String key);
}
