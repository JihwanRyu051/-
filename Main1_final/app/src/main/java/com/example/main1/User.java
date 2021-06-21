package com.example.main1;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String idToken; // 고유 토큰 정보
    private String profile;
    private String id;
    private String pw;
    private String userName;
    private Map<String, Game> gMap;

    public User(){}

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Game> getgMap() {
        return gMap;
    }

    public void setgMap(Map<String, Game> gMap) {
        this.gMap = gMap;
    }


}
