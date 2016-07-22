package sun.tutorial.generic;

/**
 * Created by yamorn on 2016/7/22.
 */
public class MyNode extends Node<Integer> {

    public MyNode(Integer data) {
        super(data);
    }

    @Override
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
