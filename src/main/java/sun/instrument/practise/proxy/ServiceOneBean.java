package sun.instrument.practise.proxy;

/**
 * Created by yamorn on 8/27/16.
 */
public class ServiceOneBean implements ServiceOne {
    @Override
    public String sayHello() {
        System.out.println("Excuting method sayHello");
        return "hello";
    }
}
