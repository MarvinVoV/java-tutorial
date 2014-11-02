package com.sun.base.thread.A_j;

/**
 * Created by louis on 2014/11/3.
 */
public class Cinema {
    private long vacanciesCinema1;
    private long vacanciesCinema2;

    private final Object controlCinema1,controlCinema2;
    public Cinema(){
        controlCinema1=new Object();
        controlCinema2=new Object();
        vacanciesCinema1=20;
        vacanciesCinema2=20;
    }

    // It uses the controlCinema1 object to control the access to
    // the synchronized block of code
    public boolean sellTickets1 (int number) {
        synchronized (controlCinema1) {
            if (number<vacanciesCinema1) {
                vacanciesCinema1-=number;
                return true;
            } else {
                return false;
            }
        }
    }

    // It uses the controlCinema2 object to control the access to
    // the synchronized block of code
    public boolean sellTickets2 (int number){
        synchronized (controlCinema2) {
            if (number<vacanciesCinema2) {
                vacanciesCinema2-=number;
                return true;
            } else {
                return false;
            }
        }
    }
    // It uses the controlCinema1 object to control the access to
    // the synchronized block of code
    public boolean returnTickets1 (int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1+=number;
            return true;
        }
    }
    // It uses the controlCinema2 object to control the access to
    // the synchronized block of code
    public boolean returnTickets2 (int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2+=number;
            return true;
        }
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }
    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

}
