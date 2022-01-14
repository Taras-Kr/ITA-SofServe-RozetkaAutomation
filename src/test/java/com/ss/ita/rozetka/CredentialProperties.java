package com.ss.ita.rozetka;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialProperties {
    private final FileInputStream fileInputStream;
    private final Properties properties;

    public CredentialProperties() {
        try {
            this.fileInputStream = new FileInputStream("src/test/resources/credential.properties");
            this.properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public String getFacebookEmail() { return properties.getProperty("facebook_email"); }
    public String getFacebookPassword() { return properties.getProperty("facebook_password"); }
}
