package section2;

import java.util.ArrayList;
import java.util.List;

public class MainCodingTest {

    private final List<Runnable> tasks;

    public MainCodingTest(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * 먼저, for 문을 활용해 각 Runnable(작업)을 Thread 객체로 감싼 뒤 리스트에 담는다.
     * 그런 다음 두번째 for 문을 통해 각 스레드를 start()해서 실제로 실행을 시작한다.
     * 이로 인해 각 작업이 별도의 스레드에서 동시에 수행된다.
     */
    public void executeAll() {
        List<Thread> threads = new ArrayList<>();

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}