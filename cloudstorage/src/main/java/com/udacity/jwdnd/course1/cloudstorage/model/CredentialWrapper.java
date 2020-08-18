package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialWrapper {
    private Credentials credentials;
    private String decryptedPassword;

    public CredentialWrapper(Credentials credentials, String decryptedPassword) {
        this.credentials = credentials;
        this.decryptedPassword = decryptedPassword;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }
}
