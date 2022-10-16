Repository 설계
===

![com da da repository](https://user-images.githubusercontent.com/100178951/196034734-5973e53c-a331-40ef-941a-7caae6c56e3f.jpg)

com.da.da 패키지 아래에 repository 패키지를 만들고 난 후 AvatarRepository 인터페이스 작성합니다.




    package com.da.da.repository;
  
    import com.da.da.entity.Avatar;
    import org.springframework.data.jpa.repository.JpaRepository;
  
    public interface AvatarRepository extends JpaRepository<Avatar, Long>{
  
    }


JpaRepository를 상속받는 AvatarRepository를 작성한다.

JpaRepository는 2개의 제네릭 타입을 사용하는데, 첫 번째에는 엔티티 타입 클래스를 넣어주고 두번째는 기본기 타입을 넣어준다.

Avatar 클래스는 기본키 타입이 Long이므로 Long을 넣어준다.

JpaRepository는 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의돼 있다.

몇가지 예시
=====

    import org.springframework.data.jpa.repository.JpaRepository; 

    import domain.Member; 

    public interface SampleRepository extends JpaRepository<Member, Integer> {

    }






findAll() 메소드
=====
Member 테이블에서 레코드 전체 목록을 조회

List<Member> 객체가 리턴

 

findById(id)
=====
  
Member 테이블에서 기본키 필드 값이 id인 레코드를 조회

Optional<Member> 타입의 객체가 리턴

이 객체의 get 메서드를 호출하면 Member 객체가 리턴 예) Member m = memberRepository.findById(id).get();

 

save(member)
========

Member 객체를 Member 테이블에 저장

객체의 id(기본키) 속성값이 0이면 INSERT / 0이 아니면 UPDATE


saveAll(memberList)
=========

Member 객체 목록을 Member 테이블에 저장

 

delete(member)
=======  

Member 객체의 id(기본키) 속성값과 일치하는 레코드를 삭제

 

deleteAll(memberList)
======  

Member 객체 목록을 테이블에서 삭제

 

count()
======
Member 테이블의 전체 레코드 수를 리턴


exists(id)
======
Member 테이블에서 id에 해당하는 레코드가 있는지 true/false를 리턴

 

flush()
======
지금까지 Member 테이블에 대한 데이터 변경 작업들이 디스크에 모두 기록
  
  
  

  
  
  
참고자료: https://frogand.tistory.com/m/22

백견이불여일타 스프링부트와 쇼핑몰 With JPA
