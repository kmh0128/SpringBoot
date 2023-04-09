JPA(Java Persistence API)
====

JPA는 자바 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스의 모음이다. 

그 말은 즉, 실제적으로 구현된것이 아니라 구현된 클래스와 매핑을 해주기 위해 사용되는 프레임워크이다. 

JPA를 구현한 대표적인 오픈소스로는 Hibernate가 있다.

ORM(Object-Relational Mapping)
===

우리가 일반 적으로 알고 있는 애플리케이션 Class와 RDB(Relational DataBase)의 테이블을 매핑(연결)한다는 뜻이며, 

기술적으로는 어플리케이션의 객체를 RDB 테이블에 자동으로 영속화 해주는 것이라고 보면된다.

관계형 데이타베이스(이하 RDB)라고 한다 


장점
==
SQL문이 아닌 Method를 통해 DB를 조작할 수 있어, 개발자는 객체 모델을 이용하여 비즈니스 로직을 구성하는데만 집중할 수 있음.

(내부적으로는 쿼리를 생성하여 DB를 조작함. 하지만 개발자가 이를 신경 쓰지 않아도됨)

Query와 같이 필요한 선언문, 할당 등의 부수적인 코드가 줄어들어, 각종 객체에 대한 코드를 별도로 작성하여 코드의 가독성을 높임

객체지향적인 코드 작성이 가능하다. 오직 객체지향적 접근만 고려하면 되기때문에 생산성 증가

매핑하는 정보가 Class로 명시 되었기 때문에 ERD를 보는 의존도를 낮출 수 있고 유지보수 및 리팩토링에 유리

예를들어 기존 방식에서 MySQL 데이터베이스를 사용하다가 PostgreSQL로 변환한다고 가정해보면, 새로 쿼리를 짜야하는 경우가 생김. 이런 경우에 ORM을 사용한다면 쿼리를 수정할 필요가 없음



단점
==
프로젝트의 규모가 크고 복잡하여 설계가 잘못된 경우, 속도 저하 및 일관성을 무너뜨리는 문제점이 생길 수 있음

복잡하고 무거운 Query는 속도를 위해 별도의 튜닝이 필요하기 때문에 결국 SQL문을 써야할 수도 있음

학습비용이 비쌈


동작원리
===
![images_codren_post_3eaacc52-8165-469f-8976-ddae78508d7f_image](https://user-images.githubusercontent.com/100178951/199136188-3b2f498c-15df-4a2f-abe1-43ea2430ae75.png)

Entity
==
데이터베이스의 테이블에 대응하는 클래스

데이터베이스에 item 테이블 ⟷ Item.java 클래스

@Entity 어노테이션 붙은 클래스를 JPA 가 관리


Entity Manager Factory
==

엔티티 매니저 인스턴스를 관리하는 주체

Application 실행 시 한 개만 만들어짐

사용자로부터 요청이 오면 엔티티 매니저를 생성

Entity Manager
==

Persistence Context 에 접근하여 DB 작업을 제공하는 객체

내부적으로 DB Connection 을 이용해서 DB 에 접근

Persistence Context(영속성 컨텍스트)
===

Entity 를 영구 저장하도록 지원하는 환경으로써 엔티티 매니저를 통해 접근 가능함

영속성 컨텍스트 사용시 이점
==
![images_codren_post_3eaacc52-8165-469f-8976-ddae78508d7f_image](https://user-images.githubusercontent.com/100178951/199139240-1b74276e-37db-4fec-8942-7870fa58362d.png)

JPA는 영속성 컨텍스트라는 중간 계층을 만들었기 때문에 애플리케이션과 데이터베이스 사이에 만들어져있고, 장점은 중간계층을 만듬으로써 버퍼링, 캐싱을 할수있는 장점 형성

1차 캐시

- 영속성 컨텍스트 내에 저장되는 캐시로 Map<KEY,VALUE>로 저장됨
- 
- entityManager.find() 메소드 호출 시 1차 캐시 조회
- 
- 존재할 경우 반환, 없으면 DB 조회 후 1차 캐시에 저장 및 반환


동일성 보장
- 하나의 트랜잭션에서 같은 키값으로 같은 엔티티 조회를 보장받음

![images_codren_post_a8ad8406-b581-4a45-a964-1636a47754e4_image](https://user-images.githubusercontent.com/100178951/199139429-b75ae157-d084-4a66-9894-e0cbef18d016.png)

트랜잭션을 지원하는 쓰기 지연
- entityManager.persist() 호출하면 1차 캐시에 저장과 동시에 지연 SQL 저장소에 저장
- 
- Commit 호출 시 쌓인 SQL문들이 flush() 되면서 DB 에 반영
- 
- 한 번에 처리하기 때문에 성능 이점

변경 감지
- JPA 는 1차 캐시에 DB 에서 처음 불러온 엔티티의 스냅샷을 저장
- 
- 스냅샷과 비교하여 변경 내용이 있따면 UPDATE SQL 수행
- 

엔티티 생명주기(Entity Life Cycle)
==
![images_codren_post_d17715aa-9de8-46ab-813d-128712c04f33_image](https://user-images.githubusercontent.com/100178951/199139119-0828aa6d-db5b-4a5c-bba8-43032799314a.png)


비영속 (new)
- new 키워드를 통해 생성된 상태로 아직 영속성 컨텍스트에 저장되지 않음

영속 (managed)
- 엔티티가 영속성 컨텍스트에 저장되어 관리되는 상태
- 아직 DB 에 저장된 상태 X, 트랜잭션 Commit 후에 DB 에 반영

준영속 상태 (detached)
- 영속성 컨텍스트에 엔티티가 저장되었다가 분리된 상태

삭제 상태 (removed)
- 영속성 컨텍스트와 데이터베이스에서 삭제된 상태

영속성 컨텍스트 저장후 데이터베이스에 반영되는 코드
===
    Item item = new Item();		// 1 영속성 컨텍스트에 저장할 상품 엔티티를 생성 new 키워드를 통해 생성해서 아직까진 영속성 컨텍스트랑 연관 없음
    item.setItemNm("테스트 상품");	

    EntityManager em = entityManagerFactory.createEntityManager();	// 2 엔티티 팩토리로부터 엔티티 매니저 생성
    EntityTransaction transaction = em.getTransaction();		// 3 엔티티매니저는 데이터 변경 시 무결성을 위해 트랜잭션 시작
	
    transaction.begin();		
    em.persist(item);		// 4 영속성 컨텍스트에 저장된 상태, 아직 DB에 INSERT SQL 보내기 전
    transaction.commit();		// 5 트랜잭션을 DB에 반영, 이 때 실제로 INSERT SQL 수행

    em.close();			// 6 엔티티 매니저와 엔티티 매니저 팩토리 자원을 close() 호출로 반환


참고자료 : https://dbjh.tistory.com/77  

https://velog.io/@codren/JPA-%EA%B0%9C%EB%85%90-%EB%B0%8F-%EC%9B%90%EB%A6%AC

참조도서 : 백견불여일타 스프링부트와 쇼핑몰  with JPA
