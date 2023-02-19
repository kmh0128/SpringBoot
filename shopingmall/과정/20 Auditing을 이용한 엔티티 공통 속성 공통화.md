지금까지 설계한 Item, Order, OrderItem 에티티를 보면 등록시간(regtime) 수정시간(updateTime) 멤버변수가 공통으로 들어가 있다.

여기서 Spring Auditing 기능은 엔티티가 저장 또는 수정될 때 자동으로 등록일,수정일,등록자,수정자를 입력해줍니다.

Audit의 사전적 정의는 감시하다 입니다. 

즉 엔티티의 생성과 수정을 감시하고 있는 것입니다.

이런 공통 멤버 변수들을 추상 클래스로 만들고 해당 추상 클래스를 상속받는 형태로 에티티를 리팩토링 하겠습니다.

현재 로그인한 사용자의 정보를 등록자와 수정자로 지정하기 위해서 AuditorAware 인터페이스를 구현할 클래스를 생성합니다

AuditorAwareImpl
===

    import org.springframework.data.domain.AuditorAware;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import java.util.Optional;

    public class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = "";
            if(authentication != null){
                userId = authentication.getName();//1 현재 로그인 한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 나눈다.
            }
            return Optional.of(userId);
        }

    }


Auditing 기능을 사용하기 위해서 Config 파일을 생성하겠습니다.

AuditConfig
====

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.domain.AuditorAware;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

    @Configuration
    @EnableJpaAuditing//1
    public class AuditConfig {

        @Bean
        public AuditorAware<String> auditorProvider() {//2
            return new AuditorAwareImpl();
        }

    }

1 -> JPA의 Auditing 기능을 활성화합니다.

2 -> 등록자와 수정자를 처리해주는 AuditorAware을 빈으로 등록합니다.

이어서 보통 테이블에 등록일, 수정일, 등록자, 수정자를 모두 다 넣어 주지만 어떤 테이블은 등록자, 수정자를 넣지 않는 테이블도 있을수 있다.

그런 테이블은 BaseTimeEntity만 상속받을 수 있도록 BaseTimeEntity 클래스를 생성합니다.

BaseTimeEntity
===

    import lombok.Getter;
    import lombok.Setter;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

    import javax.persistence.Column;
    import javax.persistence.EntityListeners;
    import javax.persistence.MappedSuperclass;
    import java.time.LocalDateTime;

    @EntityListeners(value = {AuditingEntityListener.class})//1
    @MappedSuperclass//2
    @Getter @Setter
    public abstract class BaseTimeEntity {

        @CreatedDate//3
        @Column(updatable = false)
        private LocalDateTime regTime;

        @LastModifiedDate//4
        private LocalDateTime updateTime;

    }   

1 Auditing을 적용하기 위해서 @EntityListeners 어노테이션을 추가합니다.

2 공통 매핑 정보가 필요할 때 사용하는 어노테이션으로 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공 합니다.

3 엔티티가 생성되어 저장될 때 시간을 자동으로 저장합니다

4 엔티티 값을 변경할 때 시간을 자동으로 저장합니다.

Member
===

    @Entity
    @Table(name="member")
    @Getter @Setter
    @ToString
    public class Member extends BaseEntity {
    -코드생략-
    }

