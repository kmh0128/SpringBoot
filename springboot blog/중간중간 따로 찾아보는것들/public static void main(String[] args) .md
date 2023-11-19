args 
=======

참고자료
====

https://www.delftstack.com/ko/howto/java/args-java/#google_vignette

개요
===
블로그 만드는 책을 클론코딩 공부를 하던중 args를 표현을 써서 항상 쓰여져 있어서 아무렇지 않게 생각하게 되었다.

궁금해서 찾아보게 되었습니다.



public static void main(String[] args)
===


Public은?
====

접근 제한자인데, 이 접근 제한자에는 public, protected, private, default 총 4종류가있다

public ->모든 곳에서 접근 가능

protected ->같은 패키지안의 클래스나 상속 클래스에서만 접근 가능

private -> 클래스내에서만 접근 가능

default -> 같은 패키지 안에서만 가능

위에서  public이 쓰이는 이유는

main은 프로그램의 모든 메서드의 기본이 되기때문에

모든 곳에서 접근 가능해야하다보니 무조건 public만이 가능합니다.

static은 무엇일까?
===

static	1 프로그램이 실행될 때 저장됨

        2  Garbage Collector가 정리 못함

heap    메소드(연산)이 실행될 떄 저장됨

        Garbage Collector가 정리함


static이 쓰이는 이유
===

그러다보니 이 프로그램의 기본이 되어야하는 함수가

혹여 Garbage Collector로 정리되면 안되기 때문에

static 영역에 메모리 할당을 시켜주는 것

즉, static 영역에 메모리를 할당할 것이다 라고 선언해주는 것이다!
 
즉, 모든 클래스에서 접근 가능한 메소드임을 선언하고 시작하는것!

void
===

void는?
 
void의 의미는 return이 없다는 뜻!

 void대신 int를 쓴다면 int를 return 하고,

String을 쓴다면 String을 return 한다!

 
다만, 메인에서 꼭 void를 쓰는 이유는

메인은 프로그램의 기본이 되는 메소드이기 때문에

메인이 죽으면 다음 단계가 없이 프로그램 전체가 종료된다.

그렇기에 return 값을 가질 수가 없다.

그리고 main은?
 ====

main함수는 자바 프로그램의 시작점이다.

main은 자바가 시작하는 문법 규칙!

그래서 그냥 이름이라고 생각하는게 좋을것같다!

 

 

마지막으로 String[] args는?
==== 

String은 문자열, []는 배열을 뜻하고

즉, args라는 이름의 문자열 배열을 사용하겠다는 뜻이다!

메인 함수에서 이 String[] args를 생략할 수 없는데

그 이유는 프로그램이 시작되는 부분이기 때문에 외부에서 값을 받을 수 있어야하기 때문이다.

그리고 그 값을 문자열 배열로 받아오는것이 String[] args!






