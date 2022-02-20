package com.bik.smalldata;

import com.bik.smalldata.database.DatabaseOperations;
import com.bik.smalldata.filereader.FileReaderApp;
import com.bik.smalldata.filewriter.FileWriterApp;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <h1>Data Analysis with Java</h1>
 * <p>
 *  This program demonstrates reading data files and loading them in
 *  application and analyzing them as required.
 * </p>
 * @author bikrampaudel
 * @since 2021-09-04
 */
public class Main
{
    private static final int NO_OF_THREADS = 3;
    private static ExecutorService executor = Executors.newFixedThreadPool(NO_OF_THREADS);

    /**
     *
     * @param args
     */
    public static void main(String... args)
    {
        System.out.println("Running Data Analysis Application Written in Java");
        List<Runnable> tasks = List.of(
                new FileWriterApp(),
                new FileReaderApp(),
                new DatabaseOperations()
        );
        for(Runnable task: tasks)
        {
            executor.submit(task);
        }
        while (!executor.isTerminated())
        {
            System.out.println("Task are still running.");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop(executor);
        }

    }




    private static void stop(ExecutorService service)
    {
        try {
            System.out.println("Shutting down ExecutorService");
            service.shutdown();
            service.awaitTermination(5, TimeUnit.MINUTES);
        }
        catch (InterruptedException e) {
            System.err.println("Shutdown interrupted");
            e.printStackTrace();
        }
        finally {
            if (!service.isTerminated()) {
                System.err.println("Shutting down unfinished tasks");
            }
            service.shutdownNow();
            System.out.println("Shutdown Completed");
        }
    }
}