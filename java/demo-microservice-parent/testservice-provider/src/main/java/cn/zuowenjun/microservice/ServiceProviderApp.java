package cn.zuowenjun.microservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceProviderApp.class,args);
    }
}
