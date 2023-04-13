Repository
====

![리포지토리](https://user-images.githubusercontent.com/100178951/199482243-1016add7-4a54-424e-bd06-e51cf70e92a6.jpg)

shop 패키지 아래에 repository 패키지 작성후 ItemRepository 인터페이스 생성합니다.

    public interface ItemRepository extends JpaRepository<Item, Long> {
    
    }

테스트 코드
===
테스트코드를 작성하기 위해서 h2데이터베이스를 사용하도록 resources 패키지 아래에 application-test.properties 파일을 생성합니다

테스트 환경을 위해 별도의 properties를 작성 H2 데이터베이스는 인메모리 데이터 베이스 기능을 제공한다.

애플리케이션이 종료되면 데이터베이스에 저장된 데이터 삭제 또한 가볍고 빠르기때문에 개발할 때 테스트용 데이터 베이스로 많이 사용

![properties](https://user-images.githubusercontent.com/100178951/199483398-5b3dbc32-dc6a-4355-8359-2cf6982261b4.jpg)

확장자 입력
![내용](https://user-images.githubusercontent.com/100178951/199483466-0c0d34e0-28d9-4370-990c-36c00b714bb4.jpg)


    #Datasource 설정
    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.url=jdbc:h2:mem:test
    spring.datasource.username=sa
    spring.datasource.password=

    #H2 데이터베이스 방언 설정
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


테스트 코드 작성
===

ItemRepository 인터페이스에서 마웃스 우클릭 go -> to -> Test버튼 create Test 클릭 junit5 버전을 자동으로 설정해주니 ok를 눌러준다.

ItemRepositoryTest
===

    @SpringBootTest//통합테스트를 위해 스프링부트에서 지원하는 어노테이션 실제 구동처럼 모든 Bean을 어플리케이션에 등록해서 실제 기동시 느릴수 있다.
    @TestPropertySource(locations="classpath:application-test.properties")//1
    class ItemRepositoryTest{


        @Autowired//2
        ItemRepository itemRepository;

        @Test//3
        @DisplayName("상품 저장 테스트")//4
        public void createItemTest() {
            Item item = new Item();
            item.setItemNm("테스트 상품");
            item.setPrice(10000);
            item.setItemDetail("테스트 상품 상세 설명");
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
            System.out.println(savedItem.toString());
        }


1. 테스트 코드 실행시에만 application.properties 보다 application.properties-test 
 
  테스트 코드 실행 시 application.properties 와 겹치는 부분은 우선순위를 갖도록 함
  
2.ItemRepository를 사용하기 위해서  @Autowired 어노테이션을 이용하여 Bean을 주입합니다.

3. 테스트할 메소드위에 선언

4. juit5에 추가된 어노테이션으로 테스트 코드 실행시 @DisplayName에 지정한 테스트명이 노출된다.
 
 테스트 결과
 ===
 ![테스트1](https://user-images.githubusercontent.com/100178951/199485049-0b13b5b3-7dc0-46ed-8fb9-1392dc921e89.jpg)


참고자료: 백견이불여일타 스프링부트와 쇼핑몰 with JPA
