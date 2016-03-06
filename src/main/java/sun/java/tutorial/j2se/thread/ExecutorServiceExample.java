package sun.java.tutorial.j2se.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by sunyamorn on 3/6/16.
 */
public class ExecutorServiceExample {
    private static final int THREAD_NUM = 10;

    public static void main(String[] args) {
        List<Future<Integer>> list = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);

        for(int i = 0; i< 500;i++){
            Callable<Integer> worker = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return new Random().nextInt()*10;
                }
            };
            Future<Integer> submit = executor.submit(worker);
            list.add(submit);
        }

        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        while(!executor.isTerminated()){
            System.out.println("wait executor to shutdown.");
        }
//        executor.awaitTermination()
        for(Future<Integer> future :list){
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
