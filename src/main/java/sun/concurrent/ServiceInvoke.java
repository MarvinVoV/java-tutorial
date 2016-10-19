/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by hufeng on 16/10/19.
 */
public class ServiceInvoke<T> implements Callable<T> {

    private ServiceCallback<T> serviceCallback;

    public ServiceInvoke(ServiceCallback<T> serviceCallback) {
        this.serviceCallback = serviceCallback;
    }

    @Override
    public T call() throws Exception {
        return serviceCallback.execute();
    }

    public ServiceCallback<T> getServiceCallback() {
        return serviceCallback;
    }

    public void setServiceCallback(ServiceCallback<T> serviceCallback) {
        this.serviceCallback = serviceCallback;
    }
}
