test 코드
===

참고자료
====

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

























