package multithreading_and_concurrency;

class CounterForRaceCondtion {
    int count = 0;

    void increment() {
        count++;
    }
}

public class TestRaceConditon {
    public static void main(String[] args) throws Exception {
        CounterForRaceCondtion c = new CounterForRaceCondtion();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                c.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                c.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        System.out.println(c.count);
        t2.join();

        System.out.println(c.count);
    }
}
