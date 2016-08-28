package sun.tutorial.generic;

/**
 * Created by yamorn on 2016/7/22.
 */
public class NodeTest {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;      // A raw type - compiler throws an unchecked warning

        n.setData("Hello");
        Integer x = mn.data;  // Causes a ClassCastException to be thrown.


        // After type erasure, this code becomes
        /*

            MyNode mn = new MyNode(5);
            Node n  = (MyNode)mn;
            n.setData("hello");
            Integer x = (String)mn.data;
         */


    }

}
