package com.cfysu.jmx;

public class User implements UserMBean{
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getDes(){
        return username + ":" + password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    public int add(int x, int y) {
        return x+y;
    }
}
