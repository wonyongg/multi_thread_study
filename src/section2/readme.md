# 핵심 내용

## 스레드의 독립 실행과 편입
```java
Thread t = new Thread(() -> {
    System.out.println("Running in: " + Thread.currentThread().getName());
});

// 이건 새로운 스레드를 만드는 것
t.start();

// 이건 그냥 메인 스레드에서 동기적으로 실행됨
t.run(); // 메인 스레드에 '편입'되는 방식
```

## 스레드 간 실행 순서
```java
Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("I'm going for a walk");
    }
});
 
Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("I'm going to swim");
    }
});
        
thread1.start();
thread2.start();
System.out.println("I'm going home");
```
- main 스레드, 스레드 1, 2 모두 각자만의 스레드에서 실행된다.
- start()는 스레드를 비동기적으로 실행시키기 때문이다. 
- 각 스레드는 운영체제의 스케줄러에 따라 언제 실행될지 결정된다. 
- 따라서 출력 순서는 매번 달라질 수 있으며, 예측할 수 없다.

## 스레드의 상속
```java
// 스레드를 상속하여 기능 확장
public class Main {
    public static void main(String [] args) {
        Thread thread = new TaskThread1();
        thread.start();        
    }
    
    public static class TaskThread1 extends Thread {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}

// Runnable 구현
public class Main {
    public static void main(String [] args) {
        Thread thread = new Thread(new Task2());
        thread.start();
    }
 
    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}
```
- 두 방식 모두 스레드를 사용하는 방식이다.
- 일반적으로는 Runnable을 사용한다.
  - 단순 작업을 병렬로 실행하는 경우가 이에 해당한다.
- 스레드 자체를 확장해서 제어하고 싶은 경우에는 상속을 이용한다.
  - 스레드 자체에 여러 기능을 추가하거나, 스레드의 특성을 오버라이드 해야 하는 경우가 이에 해당한다.