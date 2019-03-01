package cn.zuowenjun.cloud.Controller;

import cn.zuowenjun.cloud.model.RemoteConfig;
import cn.zuowenjun.cloud.model.RemoteConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/demo")
public class DemoController extends RemoteConfig {


    @Autowired
    private Environment environment;

    @Autowired
    private RemoteConfigProperties remoteConfigProperties;

    @RequestMapping("/config/byval")
    public Object getRemoteConfigByValue(){
        Map<String,Object> model=new HashMap<>();
        model.put("getMode","@Value");

        Map<String,String> remoteCgfMap=new HashMap<>();
        remoteCgfMap.put("profileEnv", this.profileEnv);
        remoteCgfMap.put("zwjSite", this.zwjSite);
        remoteCgfMap.put("zwjSkills",this.zwjSkills);
        remoteCgfMap.put("zwjMotto", this.zwjMotto);

        model.put("remoteConfig",remoteCgfMap);


        return model;
    }

    @RequestMapping("/config/byprops")
    public Object getRemoteConfigByPropertiesBean(){
        Map<String,Object> model=new HashMap<>();
        model.put("getMode","Properties Bean");
        model.put("remoteConfig",remoteConfigProperties);
        return model;
    }

    @RequestMapping("/config/byenv")
    public  Object getRemoteConfigByEnv(){
        Map<String,Object> model=new HashMap<>();
        model.put("getMode","Environment");

        Map<String,String> remoteCgfMap=new HashMap<>();
        remoteCgfMap.put("profileEnv", environment.getProperty("demo-config-profile-env"));
        remoteCgfMap.put("zwjSite", environment.getProperty("zuowenjun.site"));
        remoteCgfMap.put("zwjSkills", environment.getProperty("zuowenjun.skills"));
        remoteCgfMap.put("zwjMotto", environment.getProperty("zuowenjun.motto"));

        model.put("remoteConfig",remoteCgfMap);
        return  model;
    }


}
