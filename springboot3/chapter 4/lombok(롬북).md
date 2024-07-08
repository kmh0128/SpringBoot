lombok
===

롬북이란 일종의 코드를 간소화해주는 라이브러리이다

getter(), setter(), constructor(), toString()과 같은 필수 메서드를 사용하기 마련인데, 이를 매번 작성하는것은 번거로운 작업입니다.

이러한 반복을 줄여주기 위해 나온것이 롬북(lombok)입니다.

롬북을 이용하면 필수 코드를 간결하게 작성할 수 있습니다.

또한 로깅 기능을 통해 println문을 개선할 수 있습니다.

로깅(logging)이란
---

프로그램의 수행 과정을 기록으로 남기는 것을 말한다.

자동차의 블랙박스와 같은 기능


4-2 롬북을 활용해 리팩터링
===

리팩터링(refactoring)이란 코드의 기능을 변함이 없이 코드의 구조 또는 성능을 개선하는 작업을 말합니다.

4-2-1 롬북 설치하기
---

build.gradle 파일 클릭

![롬북 코드 추가](https://github.com/kmh0128/SpringBoot/assets/100178951/da1edae1-bd4c-4e1f-81ab-f1926b0ee852)

이와 같은 코드를 추가해주고 오른쪽 상단 편집기 코끼리 모양의 새로고침을 클릭하면 롬북 관련 라이브러리를 인터넷에서 자동으로 다운로드합니다.

![롬북 포함 확인](https://github.com/kmh0128/SpringBoot/assets/100178951/d2c2a73c-203b-40ab-a9a2-307fdf6e3eb4)

오른쪽 GRADLE 탭에서 롬북포함을 확인

4-2-2 DTO 리팩토링
---

리팩터링(refactoring)이란 코드의 기능에는 변함이 없이 코드의 구조 또는 성능을 개선하는 작업을 말합니다.

dto, entity, repository 등도 작성한것을 간소화해보겠습니다.

DTO
---

com.example.firstproject > dto > ArticleForm을 엽니다.

코드를 보면 ArticleForm() 생성자와 toString() 메서드가 있습니다.

ArticleForm() 생성자 코드를 간소화 해보겠습니다.

1-> ArticleForm() 생성자 코드 전체를 지웁니다.

2-> ArticleForm 클래스 위에 @AllArgsConstructor 어노테이션을 추가합니다.

이렇게 하면 클래스 안쪽의 모든 필드, title과 content를 매개변수로 하는 생성자가 자동으로 만들어집니다.

앞에서 만든 생성자 코드 네줄이 어노테이션 하나로 대체됩니다.

@AllArgsConstructor -> 필드에 쓴 모든생성자만 만들어줌

@NoArgsConstructor -> 기본 생성자를 만들어줌

dto/ArticleForm.java
===

    import lombok.AllArgsConstructor;

    @AllArgsConstructor//새 어노테이션 추가
    @ToString//toString() 메서드를 사용하는 것과 같은 효과가 납니다.
    public class ArticleForm {
        private String title; // 제목을 받을 필드
        private String content; //내용을 받을 필드

        //아래 생성자 전부 삭제
        //public ArticleForm(String title, String content) {//생성자 추가 빈공간에 오른쪽 마우스 Generate -> Constructor 선택 ctrl 누른채로 전부 선택 ok
            //this.title = title;
            //this.content = content;

2 toString() 메서드 간소환
----

2-1 toString() 메서드 코드 전체를 지웁니다.

2-2 @ToString 어노테이션을 추가합니다.

이렇게 하면 toString( 메서드를 사용하는 것과 같은 효과가 나타납니다.


dto/ArticleForm.java
===

    import lombok.ToString;//ToString 패키지 자동 임포트

    @AllArgsConstructor//새 어노테이션 추가
    @ToString//toString() 메서드를 사용하는 것과 같은 효과가 납니다.
    public class ArticleForm {
        private String title; // 제목을 받을 필드
        private String content; //내용을 받을 필드


        //아래 메서드 전체 삭제
        @Override
        public String toString() {
            return "ArticleForm{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        } 

    }


3 동작확인 
---

이제 제대로 동작하는지 확인해 보겠습니다.

서버를 실행후 localhost:8080/articles/new에 접속하고 제목과 내용을 입력후 [Submit] 버튼을 클릭하고 인테리제이 실행창의 [Run] 탭에 가보면 

결과가 잘 출력되는 것을 확인해봅니다.

![입력확인](https://github.com/kmh0128/SpringBoot/assets/100178951/7e8c5251-29cf-4d52-8098-8caaa5409b22)


4 폼 데이터로 전송한 내용이 DB에도 잘 들어갔는지 확인해 보겠습니다.

localhost:8080/h2-console 접속 -> JDBC URL은 서버를 켤 때마다 바뀌니 새로 입력해줘야합니다 [Run]탭으로 와서 ctrl + f 눌러서 jdbc를 검색

검색결과 후 JDBC URL에 붙여 넣고 [Connect] 버튼을 클릭

![db확인](https://github.com/kmh0128/SpringBoot/assets/100178951/30f9750f-747b-4142-9804-4f645ce0dfdc)

그리고 좌측 ARTICLE 테이블 클릭후 RUN 탭 눌러 실행해 보면 데이터가 아래 잘 저장된 것을 확인할 수 있습니다.







