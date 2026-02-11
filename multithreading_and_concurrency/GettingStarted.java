package multithreading_and_concurrency;

import java.util.List;

// ways to create threads

// 1 Extending the thread class
// think it like defining a worker that executes the task

class Task extends Thread {
    private String task;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("inside the thread " + Thread.currentThread().getName() + " runnign the task " + task);
    }

}

// 2 Implementing the Runnable interface
// think it like defining the work to do
// more useful

class RunnableTask implements Runnable {
    private String task;

    public RunnableTask(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(
                String.format("RunnableTask %s is running on the thread(%s)", task, Thread.currentThread().getName()));
    }

}

public class GettingStarted {
    public static void main(String[] args) {
        GettingStarted gettingStarted = new GettingStarted();

        gettingStarted.testThreadsUsingMethod1();
        gettingStarted.testThreadsUsingMethod2();
        GettingStarted.runVsStartMethod();
    }

    // by extending the thread class
    public void testThreadsUsingMethod1() {
        Task task1 = new Task("make tea");
        Task task2 = new Task("make coffee");
        Task task3 = new Task("wash the rice and boil them");
        Task task4 = new Task("wash the clothes");

        List.of(task1, task2, task3, task4).forEach(task -> task.start());

        System.out.println("completed");
    }

    // by implementing the runnable interface
    public void testThreadsUsingMethod2() {
        Thread job1 = new Thread(new RunnableTask("job 1"));
        Thread job2 = new Thread(new RunnableTask("job 2"));
        Thread job3 = new Thread(new RunnableTask("job 3"));
        Thread job4 = new Thread(new RunnableTask("job 4"));

        List.of(job1, job2, job3, job4).forEach(Thread::start);

        System.out.println("example with runnables completed");

    }

    /**
     * start() → creates a new thread, then calls run() inside it
     * 
     * run() → normal method call, no new thread
     */

    public static void runVsStartMethod() {
        Thread job = new Thread(new RunnableTask("random job"));
        job.start(); // started on new Thread
        job.run(); // running on current main thread
    }

}
