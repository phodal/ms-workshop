package com.phodal.ms.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;


public class AuthFilter extends ZuulFilter{
    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        String header = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        return header != null && header.toLowerCase().startsWith("basic");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();

        String authHeader = request.getHeader("Authorization");

        ctx.addZuulRequestHeader("Authorization", authHeader);

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
