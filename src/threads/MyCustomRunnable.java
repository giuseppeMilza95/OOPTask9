package threads;

public class MyCustomRunnable implements Runnable {

    public Finisher finisher;
    @Override
    public void run() {

        for (int i = 10; i >= 0; i--) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        finisher.finishHim();


    }
}

