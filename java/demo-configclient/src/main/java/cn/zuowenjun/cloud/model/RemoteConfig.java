package cn.zuowenjun.cloud.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;


public abstract class RemoteConfig {

    @Value("${demo-config-profile-env}")
    public String profileEnv;

    @Value("${zuowenjun.site}")
    public String zwjSite;

    @Value("${zuowenjun.skills}")
    public String zwjSkills;

    @Value("${zuowenjun.motto}")
    public String zwjMotto;
}
