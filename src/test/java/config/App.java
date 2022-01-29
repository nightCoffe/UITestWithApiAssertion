package config;

import org.aeonbits.owner.ConfigFactory;

public class App {
        public static UserConfig config = ConfigFactory.create(UserConfig.class, System.getProperties());
    }

