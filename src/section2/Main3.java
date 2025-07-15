package section2;

public class Main3 {

    public static void main(String[] args) {

        // Runnable을 이용한 스레드 생성 방식으로, Runnable 객체를 Thread에 전달
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

// Thread 클래스를 상속하여 새로운 스레드를 정의하고 실행하는 방법을 실습한 코드