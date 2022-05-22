import threads.Finisher;
import threads.MyCustomCallable;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {


    public static void main(String[] args) {

        //9A
//        MyCustomRunnable myCustomRunnable = new MyCustomRunnable();
//
//        Thread thread1 = new Thread(myCustomRunnable);
//        Thread thread2 = new Thread(myCustomRunnable);
//
//        thread1.start();
//        thread2.start();

        //9B

//        Thread scorpionThread = new Thread(new Scorpion());
//        Thread subZeroThread = new Thread(new SubZero());
//        scorpionThread.start();
//        subZeroThread.start();


        //9C - Anonymous Method

//        MyCustomRunnable scorpion = new MyCustomRunnable();
//
//        scorpion.finisher = new Finisher() {
//            @Override
//            public void finishHim() {
//                System.out.println("Scorpion Attack SubZero with Chain Reaction");
//            }
//        };
//
//
//        MyCustomRunnable subZero = new MyCustomRunnable();
//        subZero.finisher = new Finisher() {
//            @Override
//            public void finishHim() {
//                System.out.println("subZero Attacks Scorpion with Frozen in Time");
//            }
//        };


//        Thread scorpionThread = new Thread(scorpion);
//        Thread subZeroThread = new Thread(subZero);
//        scorpionThread.start();
//        subZeroThread.start();

//      9D - Lambda:
//        MyCustomRunnable scorpion = new MyCustomRunnable();
//
//        scorpion.finisher = () -> System.out.println("Scorpion Attack SubZero with Chain Reaction");
//
//
//        MyCustomRunnable subZero = new MyCustomRunnable();
//        subZero.finisher = () -> System.out.println("subZero Attacks Scorpion with Frozen in Time");
//
//        Thread scorpionThread = new Thread(scorpion);
//        Thread subZeroThread = new Thread(subZero);
//        scorpionThread.start();
//        subZeroThread.start();

        //Task9 E
        MyCustomCallable myCustomCallable = new MyCustomCallable();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Double> doubleValues;
        try {
            doubleValues = executor.submit(myCustomCallable);
            System.out.println(doubleValues.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }

        //Task 9 F

        MyCustomCallable scorpionFinisherCallable = new MyCustomCallable();
        scorpionFinisherCallable.finisherList.add(() -> System.out.println("You are next"));
        scorpionFinisherCallable.finisherList.add(() -> System.out.println("Chain Reaction"));


        MyCustomCallable subZeroFinisherCallable = new MyCustomCallable();
        subZeroFinisherCallable.finisherList.add(() -> System.out.println("Ice-Cutioner"));
        subZeroFinisherCallable.finisherList.add(() -> System.out.println("Ice-Frozen in Time"));


        try {
            if (doubleValues.get() >= 0.0 && doubleValues.get() >= 0.5) {
                scorpionFinisherCallable.finisherList.get(new Random().nextInt(scorpionFinisherCallable.finisherList.size())).finishHim();

            } else {
                subZeroFinisherCallable.finisherList.get(new Random().nextInt(subZeroFinisherCallable.finisherList.size())).finishHim();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
