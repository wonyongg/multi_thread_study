package section2;

public class Main2 {

    public static void main(String[] args) {

        // (1)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code that will run in new thread
                throw new RuntimeException("[ Intentional Exception ]");
            }
        });

        // (2)
        thread.setName("[ Misbehaving Thread ]");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                + " the erorr is " + e.getMessage());
            }
        });

        thread.start();
    }
}

// (1) thread에서 예외가 호출 되도록 함
// (2) thread에서 예외를 처리하지 않을 경우 핸들러 호출되어 프린트문 출력