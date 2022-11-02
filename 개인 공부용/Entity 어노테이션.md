Entity 어노테이션
===



@Entity
==

@Entity 어노테이션은 JPA를 사용해 테이블과 매핑할 클래스에 붙여주는 어노테이션이다. 이 어노테이션을 붙임으로써 JPA가 해당 클래스를 관리하게 된다.

속성은 name

기능은 -> JPA에서 사용할 엔티티 이름 지정

name을 쓰지 않을 경우 (default) 클래스이름을 엔티티 이름으로 지정

예시
=

    @Entity(name = "user2")
    public class User {}
    
    
-주의사항-

기본 생성자가 꼭 필요

final, enum, interface, innter class에서는 사용 불가

필드(변수)를 final로 선언 불가

@Table
===

@Table 어노테이션은 엔티티와 매핑할 테이블을 지정합니다.


속성	               기능
name	               매핑할 테이블 이름
                     생략시 엔티티 이름(@Entity(name="~") 사용
                   
catalog	             catalog 기능이 있는 DB에서 catalog 매핑

schema	             schema기능이 있는 DB에서 schema 매핑

uniqueContraints	   DDL 생성시 유니크 제약조건 생성

※ 스키마 자동 생성 기능을 사용해 DDL을 만들 때만 사용

예시
=
    @Entity
    @Table(name = "user3")//데이터베이스 이름이 user3으로 세팅된다.
    @Getter
    @Setter
    public class User {
        @Id
        @GeneratedValue
        private Long id;
        private String name;
    }

@Id
===


참고자료:https://cjw-awdsd.tistory.com/46
