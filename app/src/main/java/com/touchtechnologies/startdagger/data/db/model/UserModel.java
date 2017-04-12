package com.touchtechnologies.startdagger.data.db.model;



import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;


/**
 * Created by Jannrong on 13/10/2559.
 */

@RealmClass
public class UserModel implements RealmModel {

   public static final String SIGNUP_SOURCE_FACEBOOK = "facebook";
   public static final String SIGNUP_SOURCE_SYSTEM = "member";

    private String id;
    private String userId;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String avatar;
    private String identifier;
    private String profileUrl;
    private String activeStatus= "active";
    private String accessToken;
    private String displayName;
    private String accesstokenid;
    private String tokenIdentifier;
    private boolean loggedin;
    private String birthDate;
    private String social;
    private String loginWith;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getDisplayName() {
        return firstName+" "+ lastName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTokenIdentifier() {
        return tokenIdentifier;
    }

    public void setTokenIdentifier(String tokenIdentifier) {
        this.tokenIdentifier = tokenIdentifier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccesstokenid() {
        return accesstokenid;
    }

    public void setAccesstokenid(String accesstokenid) {
        this.accesstokenid = accesstokenid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setLoginWith(String loginWith) {
        this.loginWith = loginWith;
    }

    public String getLoginWith() {
        return loginWith;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }


}
