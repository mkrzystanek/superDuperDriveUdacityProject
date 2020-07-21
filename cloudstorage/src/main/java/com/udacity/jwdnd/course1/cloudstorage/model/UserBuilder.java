package com.udacity.jwdnd.course1.cloudstorage.model;

public final class UserBuilder {

    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public UserBuilder withUserName(String userName) {
        this.username = userName;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstname = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastname = lastName;
        return this;
    }

    public UserBuilder withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public UserBuilder withUserId(Integer userId) {
        this.userid = userId;
        return this;
    }

    public User build() {
        return new User(userid, username, salt, password, firstname, lastname);
    }
}
