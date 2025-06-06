package section3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main4 {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 343500000000L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);

        // 스레드 리스트 생성
        List<FactorialThread> threads = new ArrayList<>();

        // 각 요소를 개별 스레드에 할당
        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        // 스레드 루프돌며 개별 팩토리얼 계산 멀티스레딩으로 시작
        for (FactorialThread thread : threads) {
            // 메인스레드 종료되면 모두 종료
            thread.setDaemon(true);

            thread.start();

            // 각 스레드의 동작 시간은 2초 제한
            thread.join(2000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " calculated finished.");
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + factorialThread.getResult() + " is still in progress.");
            }
        }
    }

    private static class FactorialThread extends Thread {

        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
