/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.concurrent;

import java.util.concurrent.Future;

/**
 * Created by hufeng on 16/10/19.
 */
public interface ServiceExecutor {

    public <T> Future<T> execute(ServiceInvoke<T> service);
}
