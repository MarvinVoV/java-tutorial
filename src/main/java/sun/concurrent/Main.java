/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hufeng on 16/10/19.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // init thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ServiceExecutor executor = new DefaultServiceExecutor(executorService);

        // usage demo
        ServiceInvoke<String> invoke = new ServiceInvoke<>(new ServiceCallback<String>() {
            @Override
            public String execute() {
                return "callback executed.";
            }
        });
        Future<String> result = executor.execute(invoke);
        System.out.println(result.get());

        // shutdown thread pool
        executorService.shutdown();
        if (!executorService.isTerminated()) {
            executorService.shutdownNow();
        }

    }
}
