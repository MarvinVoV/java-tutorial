package com.sun.base.thread.A_i;

/**
 * Created by louis on 2014/11/2.
 */
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        final Thread companyThread = new Thread(company);

        final Bank bank = new Bank(account);
        final Thread bankThread = new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());
        companyThread.start();
        bankThread.start();

        // Monitor bankThread and companyThread's states
        StatusMonitor statusMonitor = new StatusMonitor(new Thread[]{companyThread, bankThread});
        Thread monitor = new Thread(statusMonitor);
        monitor.start();

        //Waiting for threads finished
        try {
            companyThread.join();
            bankThread.join();
            //Ending monitor
            monitor.interrupt();
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
