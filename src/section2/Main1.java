package section2;

public class Main1 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code that will run in new thread
                System.out.println("We are in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Worker Thread ");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(1000);
    }
}

// main 스레드와는 별개로 별도의 thread를 생성하여 비동기적으로 수행함을 확인