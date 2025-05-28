package section2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main4 {

    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {

        Random random = new Random();

        // 0부터 MAX_PASSWORD -1까지 랜덤 금고 비밀번호 설정
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThead(vault));
        threads.add(new DescendingHackerThead(vault));
        threads.add(new PoliceThead());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * 금고 클래스
     */
    private static class Vault {
        private int password;
        public Vault(int password) {
            System.out.println("Vault password: " + password);
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }
            return this.password == guess;
        }
    }

    /**
     * 해커 스레드 추상 클래스
     */
    private static abstract class HackerThead extends Thread {
        protected Vault vault;

        public HackerThead(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread : " + this.getName());
            super.start();
        }
    }

    /**
     * 0부터 오름차순으로 비밀번호를 해킹하는 해커 스레드
     */
    private static class AscendingHackerThead extends HackerThead {
        public AscendingHackerThead(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password : " + guess);
                    System.exit(0); // 정답시 프로그램 종료
                }
            }
        }
    }

    /**
     * 9999부터 내림차순으로 비밀번호를 해킹하는 해커 스레드
     */
    private static class DescendingHackerThead extends HackerThead {
        public DescendingHackerThead(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD - 1; guess > 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password : " + guess);
                    System.exit(0); // 정답시 프로그램 종료
                }
            }
        }
    }

    /**
     * 경찰 스레드 클래스
     */
    private static class PoliceThead extends Thread {
        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }

                System.out.println(i);
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
