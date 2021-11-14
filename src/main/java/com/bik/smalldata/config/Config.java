package com.bik.smalldata.config;

public class Config
{
    private String dburl;
    private String dbusername;
    private String dbpassword;

    public Config() {}

    public Config(String dburl, String dbusername, String dbpassword)
    {
        this.dburl = dburl;
        this.dbusername = dbusername;
        this.dbpassword = dbpassword;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }
}
