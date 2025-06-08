package section5;

import java.math.BigInteger;

/**
 * 디버깅 체크를 한 후 디버깅 모드로 돌려보면
 * 첫번째로 Main 메서드의 stack frame이 만들어지며
 * 한 줄 넘어갈 때마다 지역변수 내용이 stack frame에 저장됨을 확인 할 수 있다.
 * 두번째 sum 메서드 역시 stack frame이 생성되며 메서드 호출이 끝나면 지워짐을 확인할 수 있다.
 * 후입선출 구조를 보여 스택이라 부른다.
 * main 메서드도 호출이 종료되면 텅 빈 스택만 남게 된다.
 */
public class StackFrame {

    public static void main(String[] args) {
        int x = 1; // 디버깅 체크
        int y = 2; // 디버깅 체크
        int result = sum(x, y);
        System.out.println(result);
    }

    private static int sum(int a, int b) { // 디버깅 체크
        return a + b;
    }
}
