test 코드
===

참고자료
====

스프링부트3 백엔드 개발자 되기 자바편 저자 신선영


테스트코드란
====

test 디렉토리에서 작업하고 여러가지 패턴이 있다.

이 책에 나와 있는 패턴은 given-when-then 패턴이다.

given은 테스트 실행을 준비하는 단계

when은 테스트를 진행하는 단계

then은 테스트 결과를 검증하는 단계입니다.

ex)새로운 메뉴를 저장한다
===

  @DisplayName
  @Test
  public void saveMenuTest() {
  //메뉴 저장을 위한 준비과정
  final String name = "아메리카노";
  final int price = 2000;

  final Menu americano = new Menu(name, price);

  //when -> 실제의 메뉴를 저장
  final long saveId = menuService.save(americano);

  //then 메뉴가 잘 추가되었는지 검증
  final Menu saveMenu = menuService.findById(saveId).get();
  assertThat(savedMenu.getName()).isEqualTo(name);
  assertThat(savedMenu.getPrice()).isEqaulTo(Price);
  }


스프링부트 스타터 테스트 목록
=====

JUnit: 자바프로그래밍 언어용 단위 테스트 프레임워크

Spring Test & Spring Boot Test : 스프링 부트 애플리케이션을 위한 통합 테스트 지원

AssertJ: 검증문인 어설션을 작성하는데 사용되는 라이브러리

Hamcrest : 표현식을 이해하기 쉽게 만드는데 사용되는 Matcher 라이브러리

JSONassert : JSON 어셜션 라이브러리

JsonPath : JSON 데이터에서 특정 데이터를 선택하고 검색하기 위한 라이브러리

이중에서는 JSON과 AssertJ가 가장많이 사용되고 있다.

JUnit이란?
====

Junit은 자바 언어를 위한 단위 테스트 프레임워크다

단위 테스트란, 작성한 코드가 의도대로 작동하는지 작은 단위로 검증하는것 입니다.

단위는 보통 메서드

JUnit 사용시 단위 테스트를 작성하고 테스트 하는데 도움을 준다.

특징
===

테스트 방식을 구분할 수 있는 애너테이션을 제공해준다.

@Test 애너테이션으로 메서드를 호출할 때마다 새 인스턴스를 생성, 독립테스트 가능

예상 결과를 검증하는 어설션 메서드를 제공한다.

사용 방법이 단순하다

이로인하여 테스트 코드 작성 시간이 적다

자동 실행 자체 결과를 확인하고 즉각적인 피드백을 제공한다.

Junit 단위 테스트 만들기
===

src -> test -> java 폴더에 JUnitTest.java 파일을 생성

    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;

    public class JUnitTest {

        @DisplayName("1 + 2는 3이다")//테스트 이름을 명시하는 애너테이션
        @Test//테스트 메서드 -> 테스트를 수행하는 메서드 -> 이걸 붙인 메서드는 테스트를 실행하게된다.
        public void junitTest() {
            int a = 1;
            int b = 2;
            int sum = 3;

            Assertions.assertEquals(a + b, sum);//sum과 a +  b 가 같은지를 확인한다.
        }

테스트 결과
===

![결과 테스트 1](https://github.com/kmh0128/SpringBoot/assets/100178951/e97df52a-90a3-49a0-ac8b-1d8d351d54d4)

실패하는 코드
===

반대하는 경우 실패하는 코드의 경우

    @DisplayName("1 + 3는 4이다")
    @Test
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        Assertions.assertEquals(a + b, sum);
    }

결과
==

![실패시 전체가 실패](https://github.com/kmh0128/SpringBoot/assets/100178951/c8b8432d-a4dd-48ae-ae9e-390e0c3e81e6)

하나라도 실패하면 클래스 전체가 실패하게 된다.


테스트 애너테이션
===

ex) 코드
===

JUnitCycleTest.java 

















