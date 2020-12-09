/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.config;

/**
 * @author likailee.llk
 * @version ConfigurationFactory.java 2020/12/09 Wed 2:20 PM likai
 */
public class ConfigurationFactory {
    public static Configuration getConfig() {
        return SingletonConfigurationHolder.INSTANCE;
    }

    private static class SingletonConfigurationHolder {
        private static final Configuration INSTANCE = buildConfiguration();

        private static Configuration buildConfiguration() {
            return new DefaultConfiguration();
        }
    }
}
