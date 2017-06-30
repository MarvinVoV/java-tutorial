/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author hufeng
 * @version $Id: BufferedReaderProcessor, v0.1 2017年06月29日 10:02 PM hufeng Exp $
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader bufferedReader) throws IOException;

}
