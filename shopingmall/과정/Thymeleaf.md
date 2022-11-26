Thymeleaf
===

타임리프는 사용할때 Thymeleaf 문법을 포함하고 있는 html 파일을 서버 사이드 렌더링을 하지 않고 브라우저에 띄워도 정상적인 화면을 볼수있다.

일단 

controller 패키지
===
controller 패키지를 작성하고 Thymeleaf 예제용 컨트롤러 클래스를 하나 생성

![컨트롤러 클래스 생성](https://user-images.githubusercontent.com/100178951/202190276-24c45fd4-530e-4dd0-9c64-72311806ea29.jpg)


그 후 코드작성


    @Controller
    @RequestMapping(value = "/thymeleaf")//1
    public class ThymeleafExController {

        @GetMapping(value = "/ex01")
        public String thymeleafExample01(Model model){
            model.addAttribute("data", "타임리프 예제 입니다."); //2
            return "thymeleafEx/thymeleafEx01";//3
      }
      
  
1.클라이언트의 요청에 따라 어떤 컨트롤러가 처리할지 매핑하는 어노테이션. 

url에 "/thymeleaf" 경로로 오는 요청을 ThymeleafExController가 처리하도록 합니다.

2.model 객체를 이용해 뷰에 전달한 데이터를 key,value 구조로 넣어줍니다.

3.templates 폴더를 기준으로 뷰의 위치와 이름(thymeleafEx01.html)을 반환합니다.


서버용 Thymeleaf 파일
===

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">  //1
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
    <p th:text="${data}">Hello Thymeleaf!!</p>   //2
    </body>
    </html>
    
  
  1.Thymeleaf 문법을 사용하기 위해서 추가합니다.
  
  2.ThymeleafExController의 model의 data라는 key 값에 담아준 값을 출력한다
  
  이때 사용하는 Thymeleaf의 문법이 "th:text"입니다
  
  결과
  ==
  ![예제 성공](https://user-images.githubusercontent.com/100178951/202191383-5dad779c-85f7-48d6-a0fb-7ce4f31dac0e.jpg)

  2.Spring Boot Devtools
  =====
  
  Spring Boot Devtools에서 제공하는 대표적인 기능 3가지
  
  -Automatic Restart: classpath에 있는 파일이 변경될때 마다 애플리케이션을 자동으로 재시작해주는 기능
  
  개발자가 소스 수정 후 애플리케이션을 재실행하는 과정을 줄일수 있다.
  
  -Live Reaload : 정적 자원(html, css, js)수정 시 새로 고침 없이 바로 적용할 수있다.
  
  -Property Defaults : Thymeleaf는 기본적으로 성능을 향상시키기 위해서 캐싱 기능을 사용합니다.
  
  하지만 개발하는 과정에서 캐싱 기능을 사용한다면 수정한 소스가 제대로 반영되지 않을 수 있기 때문에 cache의 기본 값을 false로 설정할 수 있습니다.
  
  위의 기능의 사용을 위해서
  
  pom.xml에 의존성을 추가합니다.
  
  
  pom.xml 의존성 추가
  ===
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
  
  Intellij를 사용하는 경우
  =====
  
Intellij를 사용하는 경우, IDE 자체에서 추가설정을 해주어야 한다.

Intellij의 file->settings->build,execution,deployment->compiler에 있는 build project automatically를 체크해준다.

Intellij에서 SHIFT+CTRL+A를 누르고 registry를 입력한 뒤, compiler.automake.allow.when.app.running을 체크해준다.

LiveReload 활성화 적용
====

        #Live Reload 기능 활성화
        spring.devtools.livereload.enabled=true



다음은 구글 크롬 웹 스토어에서 LiveReload를 검색해 해당 프로그램을 설치합니다.
![liveReload](https://user-images.githubusercontent.com/100178951/204087377-695d2715-4db3-43b4-96ef-1ce17f6b7944.jpg)

설치 후 확장 프로그램에서 모든 사이트에서 적용가능하도록 옵션으로 활성화 합니다.

property Defaults 설정에서 캐싱기능을 사용중지로 꺼두는데, 실제에서는 분리후 운영환경에서는 캐싱기능 사용 개봘환경에서는 캐싱기능을 꺼두면 된다.

        #Thymeleaf cache 사용 중지
        spring.thymeleaf.cache = false

Thymeleaf예제
====

Dto 클래스를 생성하고 ItemDto 클래스를 생성해줍니다.

여기서 Dto 클래스는 데이터를 주고 받을때 Entity클래스 자체를 반환하면 안되고 데이터 전달용 객체(Data Transfer Object)를 생성해서 사용해야합니다.

이러하면 데이터베이스의 설계를 외부에 노출할 필요도 없으며, 요청과 응답 객체가 항상 엔티티와 같지 않기 때문입니다.


itemDto
===

        @Getter
        @Setter
        public class ItemDto {

            private Long id;

            private String itemNm;

            private Integer price;

            private String itemDetail;

            private String sellStatCd;

            private LocalDateTime regTime;

            private LocalDateTime updateTime;

        }

C


참고자료 : https://velog.io/@gidskql6671/Spring-Devtools-restart%EB%A1%9C-%EC%9E%90%EB%8F%99-%EC%9E%AC%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0

참고서적 : 백타불여일타 스프링부트와 쇼핑물 with Jpa 저자: 변구훈님
