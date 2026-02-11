# Multitheading

## Process

- A process is an instance of a program in execution.
- Each process has its own memory space.
- A process can contain multiple threads.
- Processes are isolated from each other.
- Each process starts its own Java Virtual Machine (JVM) when executing a Java program.
- In each process there is at least one thread, called the main thread, which is created by the JVM when the program starts.

## Thread

- A thread is the smallest unit of execution within a program(process).
- Java program always starts with one main thread.

## Thread Life Cycle

A thread can be in one of the following states:

1. New: When a thread is created but not yet started.
2. Runnable: When a thread is ready to run and is waiting for CPU time.
3. Running: When a thread is executing.
4. Blocked/Waiting: When a thread is waiting for a resource or another thread to complete.
5. Terminated: When a thread has completed its execution.

## Creating a Thread

Java provides a variety of different levels of abstraction over the idea of threading. But the most foundational aspects of that provide a very limited abstraction. In other words, the basic threading types in Java are very close to the way threading works in most operating systems. Basically, each thread is started to do a specific task. When that thread finishes that task, the thread terminates. When we're working at this level, we're responsible for any kind of management that has to go on. We're going to be responsible to do any kind of coordination. That's all going to be our responsibility. And the exceptions that occur on a thread are tied to that thread, meaning that each thread is responsible to handle any exceptions that occur on the thread. Now, there are two core types that we use when we're working at this level. One is the Runnable interface. The Runnable interface represents some tasks to be run on a thread. It has exactly one member, the run method. The other type we use a lot at this level is the Thread class. It represents a thread of execution. This class allows you to interact with that thread and affect a thread state. Now, simply creating this as a Thread class does not start the thread running. The thread doesn't start running until we call that thread's start method.

There are two main ways to create a thread in Java:

1. By extending the `Thread` class:
    - worker that executes the task
    - multiple inheritance not possible
    - represent a thread of execution

    ```java
    class MyThread extends Thread {
        public void run() {
            System.out.println("Thread is running");
        }
    }       
    ```

2. By implementing the `Runnable` interface:
    - This approach is preferred when the class needs to extend another class, as Java supports only single inheritance.
    - represent a task to be run on a thread or executed by a thread

    ```java
    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Thread is running");
        }
    }
    ```

## Starting a Thread

To start a thread, you call the `start()` method on a `Thread` object. This method invokes the `run()` method in a new thread of execution.

```java
MyThread thread = new MyThread();
thread.start();

MyRunnable myRunnable = new MyRunnable();
Thread thread2 = new Thread(myRunnable);
thread2.start();        
```

Thread is a class that represents a thread of execution, while Runnable is an interface that represents a task to be run on a thread or executed by a thread.

## Modern way to work with threads

Since Java 5, the `java.util.concurrent` package provides higher-level abstractions for working with threads, such as the `ExecutorService` interface and the `ThreadPoolExecutor` class. These abstractions make it easier to manage and control threads in a more efficient way.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
for (int i = 0; i < 10; i++) {
    executor.submit(new MyRunnable());
}
executor.shutdown();
```

## Race condition

A race condition happens when multiple threads update shared data without proper synchronization, leading to inconsistent or incorrect results.

## Thread Synchronization

when multiple threads try to access a shared data, it may lead to data inconsistency. To avoid this, we use synchronization.
gurantees atomcity, visibility.

```java
    synchronized void increment() {
        count++;
    }
```

This ensures that only one thread can execute the `increment` method at a time, preventing race conditions.

## Volatile

- The `volatile` keyword in Java is used to indicate that a variable's value will be modified by different threads.
- Declaring a variable as `volatile` ensures that its value is always read from and written to the main memory, providing visibility guarantees across threads.

```java
    private volatile boolean flag = true;
```

This ensures that when one thread updates the `flag` variable, other threads will see the updated value immediately.
