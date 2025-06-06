package section3;

public class Main1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTest());

        thread.start();

        // 스레드 종료 요청
        thread.interrupt();
    }

    private static class BlockingTest implements Runnable {

        @Override
        public void run() {
            try {

                // interrupt 하지 않으면 메인 스레드가 종료되어도 5초 후 종료
                // interrupt 하는 경우 sleep 이라는 블로킹 메서드 사용으로 인해 InterruptedException 예외가 발생하고
                // 아래의 프린트문 출력후 종료
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread.");
            }
        }
    }
}
