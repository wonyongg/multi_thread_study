# 스택 & 힙 메모리 영역
## 스택
- 스택 프레임

## 힙
- 객체
- 클래스 멤버
- static도 클래스 수준에서 정의된 변수라면 힙에 할당

## Object vs References
```java
Object ref1 = new Object();
Object ref2 = ref1;
```
레퍼런스가 힙과 스텍 양쪽에 할당되는경우
객체는 힙에만 할당됨

# 스레드 간 리소스 공유
- 리소스의 종류
  - 변수
  - 데이터 구조
  - 파일
  - 메시지 큐
  - 객체 등