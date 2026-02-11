package multithreading_and_concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * @point Threads in java are running in same memory space, hence it is easy to
 *        communicate between two threads
 * @point since all threads are sharing the same memory, it is possible that any
 *        two or more threads are using same object or performing operations on
 *        the same object (variable or methods)
 * @problem one thread read data from the shared object and one stores data into
 *          it, then there can be synchronization problem if the second thread
 *          has not finished storing the data before first thread reads it
 * @solution the keyword "synchronized" is used by which the methods or blocks
 *           of statements can be made protected from the simultaneous access.
 * @important synchronized does 2 things
 * @li 1. Atomicity (only one thread can enter)
 * @li 2. Visibility (writes are seen by other threads)
 * 
 */

class Account {
    public int balance;
    public int accountNo;

    public void displayBalance() {
        System.out.println("Account No: " + accountNo + " Balance: " + balance);
    }

    synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(amount + " is deposited");
        displayBalance();
    }

    synchronized void withdraw(int amount) {
        balance -= amount;
        System.out.println(amount + " is withdrawn");
        displayBalance();
    }
}

class TransactionDeposit implements Runnable {
    private Account account;
    private int amount;

    public TransactionDeposit(Account account, int amountToDeposit) {
        this.account = account;
        this.amount += amountToDeposit;
        new Thread(this).start();
    }

    @Override
    public void run() {
        account.deposit(amount);
    }
}

class TransactionWithdraw implements Runnable {
    private Account account;
    private int amount;

    public TransactionWithdraw(Account account, int amountToDeposit) {
        this.account = account;
        this.amount += amountToDeposit;
        new Thread(this).start();
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}

class Counter implements Runnable {
    private static int count = 0;

    void increment() {
        System.out.println("current count is : " + count); // observe this print statement
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        increment();
    }

}

class SynchronizedCounter implements Runnable {
    private int count = 0;

    // this method will never run for the same object instance simultaneously.
    synchronized void increment() {
        System.out.println("current count is : " + count); // observe this print statement
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        increment();
    }

}

public class Synchronization {
    public static void main(String[] args) {
        // test without suncronisation

        testWithoutSync();

        // test with syncronization counter

        testWithSync();

        // banking transaction example
        Account ABC = new Account();
        ABC.balance = 1000;
        ABC.accountNo = 111;
        TransactionDeposit t1;
        TransactionWithdraw t2;
        t1 = new TransactionDeposit(ABC, 500);
        t2 = new TransactionWithdraw(ABC, 900);
    }

    /**
     * @test without synchronization
     */

    static void testWithoutSync() {
        Counter counter = new Counter();
        List<Thread> threadsList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread countertThread = new Thread(counter);
            threadsList.add(countertThread);
        }

        for (Thread thread : threadsList) {
            thread.start();
        }
    }

    /**
     * @test with synchronization
     */

    static void testWithSync() {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        List<Thread> syncThreadsList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Thread sycnThread = new Thread(synchronizedCounter);
            syncThreadsList.add(sycnThread);
        }

        for (Thread thread : syncThreadsList) {
            thread.start();
        }
    }
}