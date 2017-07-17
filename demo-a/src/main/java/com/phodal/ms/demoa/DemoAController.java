package com.phodal.ms.demoa;

import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class DemoAController {
    public static void main(String[] args) {
        SpringApplication.run(DemoAController.class, args);
    }
}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/name")
    public String name(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return "A";
    }
}