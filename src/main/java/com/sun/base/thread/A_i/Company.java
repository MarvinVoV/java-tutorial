package com.sun.base.thread.A_i;

/**
 * Created by louis on 2014/11/2.
 */
public class Company implements Runnable {
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.addAmount(1000);
        }
    }
}
