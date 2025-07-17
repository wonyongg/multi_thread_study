package section3;

import java.math.BigInteger;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        // 연산이 매우 김
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000")));

        // Daemon 스레드를 true로 함으로써 메인 스레드가 끝나면 애플리케이션을 종료시킴.
        // Daemon 스레드란, 백그라운드에서 돌아가는 보조적인 스레드로
        // 메인 작업이 끝났을 때, 백그라운드 작업도 자동으로 종료되길 바랄 때 사용.
        thread.setDaemon(true);
        thread.start();

        // 메인 스레드가 잠들어있는 10초 안에 연산이 끝나지 않으면 메인 스레드 종료와 동시에 애플리케이션도 종료되는 것
        Thread.sleep(10000);
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;


        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        // base^power 제곱근 구하는 함수
        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) < 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }
}
