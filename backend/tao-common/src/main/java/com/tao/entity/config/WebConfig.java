package com.tao.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebConfig extends com.tao.entity.config.AppConfig {
    @Value("${spring.mail.username:}")
    public String sendUserName;
    @Value("admin.account")
    public String adminAccount;

    public String getAdminAccount() {
        return adminAccount;
    }

    public String getSendUserName() {
        return sendUserName;
    }
}
