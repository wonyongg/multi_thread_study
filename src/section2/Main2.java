package section2;

public class Main2 {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code that will run in new thread
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving Thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                + "the erorr is " + e.getMessage());
            }
        });

        thread.start();
    }
}
