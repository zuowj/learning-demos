package cn.zuowenjun.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableZuulProxy
@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication
public class ZuulapigatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulapigatewayApplication.class, args);
    }

    /**
     * 实现从config server获取配置数据并映射到ZuulProperties中
     * @return
     */
    @Bean
    @Primary
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

    /**
     * 实现自定义serviceId到route的映射（如正则映射转换成route）
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        //实现当serviceId符合：服务名-v版本号,则转换成：版本号/服务名，如：testservice-v1-->1/testservice
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }

}
