package section2;

public class Main3 {

    public static void main(String[] args) {

        /*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that executes on the new thread
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        });
        */

        Thread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            //Code that executes on the new thread
            System.out.println("Hello from " + this.getName());
        }
    }
}
