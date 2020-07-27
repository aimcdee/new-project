package com.project.utils;

/**
 * 描述:
 * 微信小程序参数
 *
 * @author liangyuding
 * @date 2020-03-30
 */
public enum WeChatKey {
    student("wxa9b239bd2967585f", "6dc8c23b61171c38f4b661b59ad56fbf"),
    ;

    private String appId;
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public String toString() {
        return "WeChatKey{" +
            "appId='" + appId + '\'' +
            ", appSecret='" + appSecret + '\'' +
            '}';
    }

    WeChatKey(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }
}
