package com.sun.base.thread.B_d;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by louis on 2014/11/11.
 */
public class Main {
    public static void main(String[] args) {
        final int rows=10000;
        final int cols=1000;
        final int search=5;
        final int participants=5;
        final int linesParticipant=2000;
        MatrixMock mock=new MatrixMock(rows, cols,search);
        Result result=new Result(rows);
        Grouper grouper=new Grouper(result);

        CyclicBarrier barrier=new CyclicBarrier(participants,grouper);

        Searcher searchers[]=new Searcher[participants];

        for (int i=0; i<participants; i++){
            // Every searching thread searches 2000 rows
            searchers[i]=new Searcher(i*linesParticipant,
                    (i*linesParticipant) + linesParticipant, mock, result, 5,barrier);
            Thread thread=new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");

    }
}
