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
===

    public class JUnitCycleTest {
        @BeforeAll// 전체 테스트를 시작하기 전에 1회 실행하므로 메소드는 static을 선언해줘야 된다.
        static void beforeAll() {
            System.out.println("@BeforeAll");
        }
        @BeforeEach// 테스트 케이스를 실행하기 전마다 실행
        public void beforeEach() {
            System.out.println("@BeforeEach");
        }

        @Test
        public void test1() {
            System.out.println("test1");
        }

        @Test
        public void test2() {
            System.out.println("test2");
        }

        @Test
        public void test3() {
            System.out.println("test3");
        }

        @AfterAll // 전체 테스트를 마치고 종료하기 전에 1회 실행하므로 메서드는 static으로 선언된다.
        static void afterAll(){
            System.out.println("@AfterAll");
        }

        @AfterEach // 테스트 케이스를 종료하기 전마다 실행
        public void afterEach(){
            System.out.println("@AfterEach");
        }
    }

한번만 실행되는 @BeforeAll, @AfterAll 말고 -> 이둘은 맨처음과 맨끝 클래스정리

    @BeforeEach
    메서드 레벨 설정

    @Test             ->순서대로 테스트개수 만큼 반복된다.
    테스트 실행 

    @AfterEach
    메서드 레벨 정리

코드 결과
===

![테스트 예 결과](https://github.com/kmh0128/SpringBoot/assets/100178951/00cdc3ee-9415-44f2-a32a-f150e11ab539)


애너테이션 의미
===

@BeforeAll -> 전체 테스트를 시작하기 전에 처음으로 한번만 실행한다.

이 애너테이션은 전체 테스트 실행 주기에서 한 번만 호출되어야 하기 때문에 메서드를 static으로 선언

예) 데이터베이스를 연결해야 하거나 테스트 환경을 초기화 할 때 사용합니다.

@BeforeEach -> 테스트 케이스를 시작하기 전에 매번 실행한다.

각 인스턴스에 대해서 메서드를 호출해야 하므로 메서드는 static이 아니어야 한다.

예) 테스트 메서드에서 사용하는 객체를 초기화하거나 테스트에 필요한 값을 미리 넣을 때 사용할수 있다.

@AfterAll

-> 전체 테스트를 마치고 종료하기 전에 한 번만 실행합니다.

예) 데이터베이스 연결을 종료할때나 공통적으로 사용하는 자원을 해제할 때 사용할 수 있다.

이것 또한 전체 주기에서 한 번만 호출되어야하므로 메서드를 static으로 선언한다.

@AfterEach

각 테스트 케이스를 종료하기 전 매번 실행한다.

예) 테스트 이후에 특정 데이터를 삭제해야 하는 경우 사용합니다.

@BeforEach와 마찬가지로 메서드는 static이 나이어야 한다


AssertJ 검증문
===

AssertJ는 JUnit과 함계 사용해 검증문의 가독성을 높여주는 라이브러리입니다.

기대값과 실제 비교값을 명시하여서 비교대상을 명확하게 해준다.

|메서드 이름|설명|
|------|---|
|isEqualTo(A)|A 값과 다른지 검증|
|isNotEqualTo(A)|A 값과 다른지 검증|
|contains(A)|A 값을 포함하는지 검증|
|doesNotContains(A)|A 값을 포함하지 않는지 검증|
|startWith(A)|접두사가 A인지 확인|
|endsWith(A)|접미사가 A인지 확인|
|isEmpty()|비어 있는 값인지 검증|
|isNotEmpty()|비어 있지 않은 값인지 검증|
|isPositive()|양수인지 검증|
|isNegative()|음수인지 검증|
|isGreaterThan(1)|1보다 큰값인지 검증|
|isLessThan(1)|1 보다 작은 값인지 검증|

테스트코드 작성
===

TestController.java 파일을 열고 

클래스 이름위에 마우스 커서를 놓고 alt + enter 누르면 create test가 나타난다.

![create test](https://github.com/kmh0128/SpringBoot/assets/100178951/3f47d147-bb91-48f6-94e4-655bac44ca43)

ok를 클릭하면 TestControllerTest.java 파일이 test/java/패키지 아래에 생성된다.

코드 예제)
====

    @SpringBootTest//테스트용 애플리케이션 컨텍스트 생성
    @AutoConfigureMockMvc// MockMvc 생성 및 자동 구성
    class TestControllerTest {

        @Autowired// 빈에서 객체등록
        protected MockMvc mockMvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        private MemberRepository memberRepository;

        @BeforeEach// 테스트 실행전 메서드
        public void mockMvcSetUp() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();//test를 위한 객체생성
        }

        @AfterEach//테스트 실행 후 실행하는 메서드
        public void cleanUp() {
            memberRepository.deleteAll();//deleteAll() 해당 메서드는 Repository 에 등록되어 있는 메서드로 해당 레포지토리에서 관리 되는 모든 entity 를 삭제합니다.
        }









