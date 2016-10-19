/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by hufeng on 16/10/19.
 */
public class DefaultServiceExecutor implements ServiceExecutor{

    private ExecutorService executorService;

    public DefaultServiceExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public <T> Future<T> execute(ServiceInvoke<T> service) {
        return executorService.submit(service);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
