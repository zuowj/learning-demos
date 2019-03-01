package cn.zuowenjun.cloud.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()//如果有前缀，则可以设置prefix=XXX
public class RemoteConfigProperties {

    private String demoConfigProfileEnv;
    private Zuowenjun zuowenjun;

    public String getDemoConfigProfileEnv() {
        return demoConfigProfileEnv;
    }

    public void setDemoConfigProfileEnv(String demoConfigProfileEnv) {
        this.demoConfigProfileEnv = demoConfigProfileEnv;
    }

    public Zuowenjun getZuowenjun() {
        return zuowenjun;
    }

    public void setZuowenjun(Zuowenjun zuowenjun) {
        this.zuowenjun = zuowenjun;
    }

    public static class Zuowenjun {

        private String site;
        private String skills;
        private String motto;


        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

    }

}
