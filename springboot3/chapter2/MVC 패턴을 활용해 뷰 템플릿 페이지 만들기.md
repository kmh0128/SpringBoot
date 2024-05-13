MVC 패턴을 활용해 뷰 템플릿 페이지 만들기
===

1 뷰 템플릿 페이지 만들기
----

프로젝트 탐색기에서 src > main > resources를 펼치면 statci과 templates 디렉터리가 있다.

뷰 템플릿은 templates 디렉터리에 있다.

1-1
---

templates 디렉터치레엇 마우스 오른쪽 버튼 New -> File 선택 파일명은 greetings.mustache로 입력하고 파일 작성합니다.

![greetings 머스테치](https://github.com/kmh0128/SpringBoot/assets/100178951/d70658af-7061-4db7-99d0-680cf22ca09d)

확장자 mustache는 뷰 템플릿을 만드는 도구, 뷰 템플릿 엔진을 의미합니다.

머스테치 파일의 기본 위치는 src > main > resources > templates 입니다.

이 위치에 머스테치 파일을 저장하면 스프링 부트에서 자동으로 로딩합니다.


1-2 
---

머스테치(.mustache) 파일을 지원하는 플러그인을 발견했다고 뜹니다

![머스테치2](https://github.com/kmh0128/SpringBoot/assets/100178951/d084894d-91e4-444a-8ee5-cd1edb90be28)

사진에는 설치창에 가려지지만 본문 위쪽바에 뜹니다.

그게 아니라면 file -> setting -> plugin -> 오른쪽탭 marketplace 탭을 클릭하고 mustache를 검색 -> handlebars/Mustache를 

선택하고 인스톨을 클릭합니다 설치가 끝나면 ok를 눌러주면 다운이 완료됩니다.

1-3
---

mustache 파일로 변경이 완료되어도 본문에는 아무것도 없습니다.

![doc 입력 tab](https://github.com/kmh0128/SpringBoot/assets/100178951/6be363df-d240-4945-b3e5-06bb9f6618ef)

doc을 입력해주고 tab버튼을 눌러주면,

![tab2](https://github.com/kmh0128/SpringBoot/assets/100178951/7b29c3b6-6394-4c6e-91b8-9f2047cdcf6d)

해당화면처럼 바뀝니다.

2 컨트롤러 만들고 실행하기
====

2-1 패키지 만들기 
----

com.example.firstproject에서 마우스 오른쪽 버튼 누르고 메뉴 new -> package 를 선택합니다.

패키지명은 기본으로 입력된 패키지명 뒤에 controller를 추가해 com.example.firstproject.controller로 설정

![컨트롤러 패키지](https://github.com/kmh0128/SpringBoot/assets/100178951/5d211c96-8403-4e36-b867-8b87c5374c3b)

controller 패키지에 자바 클래스를 생성

클래스 명은 FirstController로 설정합니다.

2-2 FirstController
----

    package com.example.firstproject.controller;

    import org.springframework.stereotype.Controller;


    @Controller//컨트롤러 선언
    public class FirstController {
        
        public String niceToMeetyou() {          
            return "greetings";//메서드 작성, greetings.mustache 파일 반환 
        }
    }


어노테이션(annotation)이란 소스 코드에 추가해 사용하는 메타 데이터의 일종입니다.

메타 데이터는 프로그램에서 처리해야 할 데이터가 아니라 컴파일 및 실행 과정에서 코드를 어떻게 처리해야 할지 알려 주는 추가정보입니다.

자바에서 어노테이션은 @을 붙여서 사용합니다.

return "greetings";
---

niceToMeetYou() 메서드로 greetings.mustache 페이지를 반환하려면 파일이름인 greetings만 반환값으로 적어 주면된다.

return "greetings";로 적어 주면 서버가 알아서 templates 디렉터리에서 greetings.mustache 파일을 찾아 웹 브라우저로 전송합니다.


웹 브라우저 접속
----

서버를 실행시키고 접속한다.

![접속 실패](https://github.com/kmh0128/SpringBoot/assets/100178951/cb79e761-78ac-4f62-b898-c99b5ccc222d)

404 Not Found 에러입니다.

더 처리해 줘야할게 있다는 뜻입니다.

다시 FirstController
---

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping; //URL 연결 요청(@GetMapping)으로 자동으로 임포트

        @Controller//컨트롤러 선언
        public class FirstController {

            @GetMapping("/hi")//1,2 -> url 요청접수
            public String niceToMeetyou() {
                model.addAttribute("username", "ㅁㅎ");
                return "greetings";//메서드 작성, greetings.mustache 파일 반환
            }
        }

위 코드는 웹 브라우저 localhost:8080/hi로 접속시 greeting.mustache 파일을 찾아 반환하라는 코드입니다.

컨트롤러를 만들 때 컨트롤러를 선언하고, 반환값으로 보여 줄 페이지의 이름만 따 서 적은 다음(return "greetings";)

URL 요청을 접수해야(@GetMapping("/hi")) 제대로 나타나게 됩니다.


다시 웹페이지
---

![오류2](https://github.com/kmh0128/SpringBoot/assets/100178951/98c58a6d-9556-4e4a-8f9f-a688d70d29a7)

한글 깨짐현상이 발생했습니다.

해결방법은 src > main > resources > application.properties 파일을 열고 코드를 추가합니다.

        server.servlet.encoding.force=true

 HTTP 요청과 응답의 인코딩을 내가 지정한 charset으로 설정해줘서 한글로 바꿔주게됩니다.


 참고자료
 ---

저자 홍팍님

책 제목:스프링부트3 자바 백엔드 개발 입문

 https://velog.io/@codingpotato/Spring-Boot-2.7-Mustache-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%ED%95%9C%EA%B8%80%EC%9D%B4-%EA%B9%A8%EC%A7%80%EB%8A%94-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0%EB%B2%95
