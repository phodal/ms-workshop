package com.phodal.ms.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CallDependencyService extends HystrixCommand<String> {

    protected CallDependencyService() {
        super(HystrixCommandGroupKey.Factory.asKey("Demo"));
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException("failed!");
    }

    @Override
    protected String getFallback() {
        System.out.println("Events (so far) in Fallback: " + getExecutionEvents());
        return getFailedExecutionException().getMessage();
    }

}
