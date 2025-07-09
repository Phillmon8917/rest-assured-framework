package config;

import org.aeonbits.owner.ConfigCache;

public class PropertyUtil {

    public static PropertyConfig getConfig(){
        return ConfigCache.getOrCreate(PropertyConfig.class);
        /* This method will return PropertyConfig either by creating new instance or by
        * getting it from the cache, saving time. */
    }
}
