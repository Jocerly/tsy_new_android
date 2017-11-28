package com.tsy.base.constants;

/**
 * Created by jay on 2017/11/23.
 */

public final class BasicParams {

    private String versionName = "3.0.1";

    private String versionCode = "0";

    private String appToken = "";

    private String deviceId = "0";

    private String channelName = "Channel_Default";

    public static BasicParams getInstance() {
        return SingletonHolder.instance;
    }

    private BasicParams() {}

    private static class SingletonHolder {
        public static BasicParams instance = new BasicParams();
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
