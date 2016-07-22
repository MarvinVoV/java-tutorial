package sun.tutorial.generic;

/**
 * Created by yamorn on 2016/7/22.
 */
public class Node<T> {

    public T data;

    public Node(T data){
        this.data = data;
    }

    public void setData(T data){
        System.out.println("Node.setData");
        this.data = data;
    }
}
