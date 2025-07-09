package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface PropertyConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("bookingApiTestDataFilePath")
    String bookingApiTestData();

    @Key("numberOfFilesToAttachOnEmail")
    int numberOfFilesToAttachOnEmail();
}
