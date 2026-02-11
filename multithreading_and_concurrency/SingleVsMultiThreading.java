package multithreading_and_concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

// lets take a simple class

/**
 * Adder read a file and add all the number from it and write it to new file
 * 
 */
class Adder {

    private String inFile, outFile;

    public Adder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void doAdd() throws Exception {
        int total = 0;
        String line = null;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(inFile));
            while ((line = bufferedReader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
            bufferedReader.close();

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outFile))) {
                bufferedWriter.write("Total: " + total);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

// adder with threading support

class AdderWithRunnable implements Runnable {
    private String inFile, outFile;

    public AdderWithRunnable(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void doAdd() throws Exception {
        int total = 0;
        String line = null;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(inFile));
            while ((line = bufferedReader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
            bufferedReader.close();

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outFile))) {
                bufferedWriter.write("Total: " + total);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // this class now understand how to be run on another thread

    @Override
    public void run() {
        // its now thread resposible to handle all the exceptions
        try {
            doAdd();
            System.out.println(Thread.currentThread().getName() + " finished its work");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

// adder with communication to main thread

class AdderWithCallable implements Callable<Integer> {

    private String inFile;

    public AdderWithCallable(String inFile) {
        this.inFile = inFile;
    }

    public int doAdd() throws IOException {
        int total = 0;
        String line = null;

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(inFile));) {
            while ((line = bufferedReader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }
        return total;

    }

    @Override
    public Integer call() throws IOException {
        System.out.println(Thread.currentThread().getName() + " finished its work");
        return doAdd();
    }

}

public class SingleVsMultiThreading {
    public static void main(String[] args) throws Exception {
        // this is single thread

        String[] inputFiles = { Path.of(SingleVsMultiThreading.class.getPackageName(), "input.txt").toString(),
                Path.of(SingleVsMultiThreading.class.getPackageName(), "input1.txt").toString(),
                Path.of(SingleVsMultiThreading.class.getPackageName(), "input2.txt").toString(), };
        String[] outputFiles = { Path.of(SingleVsMultiThreading.class.getPackageName(), "output.txt").toString(),
                Path.of(SingleVsMultiThreading.class.getPackageName(), "output1.txt").toString(),
                Path.of(SingleVsMultiThreading.class.getPackageName(), "output2.txt").toString(), };

        try {
            // not utilizing the CPU in better way
            // there is waiting going
            // we are doing the work one by one
            // only one file is proces at one time
            for (int i = 0; i < inputFiles.length; i++) {
                Adder adder = new Adder(inputFiles[i], outputFiles[i]);
                adder.doAdd();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        // now lets take advantage of the multithreading in java
        // multithreading is an explicit choice
        // we also need to break the problems into parts
        // must handoff the parts for processing

        // java provides threading abstraction classes
        // or we can also handle them directly (basic threading types)

        // we don't need to handle the Exception here
        // since the background thread is handling the IOException

        Thread[] threads = new Thread[inputFiles.length];
        for (int i = 0; i < inputFiles.length; i++) {
            AdderWithRunnable adderWithRunnable = new AdderWithRunnable(inputFiles[i], outputFiles[i]);

            // this will run each instance of the adder class on the separate thread
            Thread thread = new Thread(adderWithRunnable);
            threads[i] = thread;
            thread.start();
        }

        // lets block now the main thread
        // main thread will waits for threads to complete there task and exit

        // try to run with or without this block and see the output
        for (Thread thread : threads) {
            thread.join(); // blocks waiting for the thread completion
            // if background task is already completed join returns immediately
            // otherwise it will block the execution
        }

        System.out.println(Thread.currentThread().getName() + " finished its work");

        // so while working with Thread and Runnable, we are responsible for handling a
        // lot of details
        // so with these apis it's really easy to misuse threads if you're not familiar
        // with how to do that.

        // Now we can use thread pools (which will take care of a lot of thread
        // management details for us)
        // Thread pool will abstract(hide) a lot of details from us
        // Thread pool provides a queue to put tasks into
        // and then thread pool will take tasks from the queue and assign them off to
        // threads in the pool to do the actual work

        // Executor Service Interface models thread pool behavior
        // can submit tasks and request and wait for pool shutdown
        // Executors class provides methods for creating the thread pools
        // e.g. dyanamically sized pools
        // e.g. size limited pools
        // e.g. pool that schedule tasks for later

        ExecutorService executorService = Executors.newFixedThreadPool(3); // fixed size thread pool, only allow maximum
                                                                           // three threads to run at same time
        for (int i = 0; i < inputFiles.length; i++) {
            AdderWithRunnable adderWithRunnable = new AdderWithRunnable(inputFiles[i], outputFiles[i]);

            // thread pool will take care of the rest
            // i.e. creating thread and starting it
            executorService.submit(adderWithRunnable);
        }
        executorService.shutdown(); // finish all the work that is assigned to the pool
        // don't assign any new work
        // it does not block the main thread

        // to block the main thread we can use
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // creating closer relationships between thread tasks
        // Next we will look at how can one thread get the results of the another
        // thread
        // e.g. main thread wants to get those result back which thread completed
        // not only result it will help the main thread to know the status and error
        // details inside the another thread

        // these things can be done manually
        // (required code to be writting to communicate information using shared
        // resources)

        // Java provides types to achieve this
        // Callable interface
        // - represents a task that can be run on a thread
        // - plus can return results
        // - plus can throw exceptions

        // Thread results are harvested/created using Future interface
        // - Future object : represent results of thread task (may be workdone or
        // exceptions)
        // Future interface key method is get
        // blocks until task is completed
        // returns callback interface result or exceptions

        // let see in action

        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inputFiles.length];

        for (int i = 0; i < inputFiles.length; i++) {
            AdderWithCallable adderWithCallable = new AdderWithCallable(inputFiles[i]);
            results[i] = executorService2.submit(adderWithCallable);
        }

        for (Future<Integer> future : results) {
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) { // Exception raised in the AdderWithCallable
                System.out.println(e.getCause());
                throw e;
            } catch (Exception e) { // Non AddWithCallable exceptions

            }

        }

        executorService2.shutdown();

    }
}
