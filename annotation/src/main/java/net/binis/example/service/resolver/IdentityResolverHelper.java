package net.binis.example.service.resolver;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

public class IdentityResolverHelper {

    public static final String THREAD_NAME = "ThreadName";

    private IdentityResolverHelper() {
        //Do nothing
    }

    public static void withThread(String name, Runnable task) {
        //TODO: Thread pool
        new Thread(() -> {
            MDC.put(THREAD_NAME, name);
            try {
                task.run();
            } finally {
                MDC.clear();
            }
        }).start();
    }

    public static void withThread(IdentityResolver resolver, Runnable task) {
        withThread(resolver.getIdentity(), task);
    }


    public static String getThreadName() {
        var name = MDC.get(THREAD_NAME);
        if (StringUtils.isNotEmpty(name)) {
            return name;
        } else {
            return Thread.currentThread().getName();
        }
    }

}
