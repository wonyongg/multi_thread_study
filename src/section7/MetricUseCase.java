package section7;

import java.util.Random;

public class MetricUseCase {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
        BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinter.start();
    }

    /**
     *  1초마다 평균 실행 시간을 출력
     */
    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                // get 메서드는 synchronized 되지 않아 병렬 수행되므로 BusinessLogic의 수행에 영향을 주지 않음
                double currentAverage = metrics.getAverage();

                System.out.println("Current Average is " + currentAverage);
            }
        }
    }

    /**
     * 실제 "업무 로직"을 수행하며, 실행 시간(몇 밀리초 걸렸는지)을 측정해서 Metrics 객체에 전달
     */
    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {

            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    /**
     * 여러 스레드가 평균값을 안전하게 읽고,
     * addSample()은 synchronized로 평균 계산을 원자적으로 처리하는 클래스
     */
    public static class Metrics {
        private long count = 0; // 몇 개의 샘플을 받았는지 저장

        // 여러 스레드가 읽을 때 최신 값을 보장
        private volatile double average = 0.0; //  현재까지의 평균 실행 시간

        // 원자적 연산이 아닌 메서드이므로 synchronized 키워드를 사용해 동시 실행을 막음
        public synchronized void addSample(long sample) {

            // 평균과 개수를 곱해 총합을 구함
            double currentSum = average * count;

            // 카운트 + 1
            count++;

            // 다시 평균 계산, volatile로 인해 원자적 작업 보장
            average = (currentSum + sample) / count;
        }

        // get 메서드 자체는 동시성 보장을 함,
        // 그러나 average 자체는 double이므로 volatile 키워드를 이용해 최신화 보장을 해야함
        public double getAverage() {
            return average;
        }
    }
}
