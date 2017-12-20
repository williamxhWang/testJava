package com.william.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestMyJava {

    static class Sum implements Callable<String> {
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            System.out.println(Thread.currentThread().getName() + " : " + acc);
            return "hello world  "+acc;
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Sum> test=new ArrayList<Sum>();
        test.add(new Sum(0, 10));
        test.add(new Sum(0, 1_000));
        test.add(new Sum(0, 1_000_000));
        List<Future<String>> results = executor.invokeAll(test);
        executor.shutdown();

        for (Future<String> result : results) {
            System.out.println(result.get());
        }
    }

}
