package sun.groovy;

/**
 * Created by marvin on 2017/09/06.
 */

public class MyCallback implements TestCallback {
    @Override
    public String callback() {
        return "hello";
    }
}
