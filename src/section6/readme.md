# 임계영역(Critical Section)과 Syncronized 키워드

## 임계 영역이란?
```java
int count = 0;

public void increase() {
    count = count + 1;  // 여기가 임계 영역
}
```
- 여러 스레드가 동시에 실행되면 안 되는 코드 영역이다.
- 위 예시의 메서드를 여러 스레드가 동시에 실행하면 count가 이상한 값이 될 수 있다.

## synchronized 키워드
```java
public synchronized void increase() {
    count = count + 1;
}
```
- 임계 영역을 한 번에 한 스레드만 실행하게 만들어주는 키워드이다.
- 위와 같이 `synchronized` 키워드를 붙이면 한 번에 한 스레드만 실행 가능한 메서드가 된다.
- 내부적으로 해당 객체에 락을 건다.

### 특정 블록만 임계 영역으로 만들기
```java
public void increase() {
    synchronized(this) {
        count = count + 1;
    }
}
```
- 메서드 전체가 아닌 딱 필요한 부분만 잠그는 방식이다.

## 주의할 점
- Deadlock(교착상태)에 주의해야 한다.
  - 두 스레드가 서로가 가진 락을 기다리면서 무한정 멈추는 상황이 생길 수 있다.
- 성능 저하를 막기 위해 임계 영역은 최소한의 범위로 잡는 게 좋다.
- 예시
  - 동일한 객체의 다른 두 메서드에 모두 `synchronized` 키워드가 붙어있다고 하자. 
  - 이때 하나의 메서드가 실행된다면 다른 스레드에서 동일한 객체에 동기화된 모든 다른 메서드로 액세스할 수 없게 된다.
  - 두 메서드가 동기화된 상태로 동일한 객체에 속하기 때문이다.
