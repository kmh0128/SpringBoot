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

