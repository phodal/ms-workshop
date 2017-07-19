package com.phodal.ms.demoa;

import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Base64;

@RestController
@RefreshScope
class DemoAController {

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping("/name")
    public String name(){
        String authorization = httpRequest.getHeader("Authorization");

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        String[] values = credentials.split(":", 2);
        String username = values[0];

        return username;
    }
}