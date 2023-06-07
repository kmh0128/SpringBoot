Thymeleaf 페이지 레이아웃
====

보통 웹사이트를 만들려면 header, footer, menu 등 공통적인 페이지 구성 요소들이 있다. 

이런 영역들을 각각의 페이지마다 같은 소스코드를 넣는다면 변경이 일어날 때마다 이를 포함하고 있는 모든 페이지를 수정해야 한다. 

Thymeleaf의 페이지 레이아웃 기능을 사용한다면 공통 요소 관리를 쉽게 할 수 있다.

공통적으로 적용되는 레이아웃을 미리 만들어 놓고 현재 작성중인 페이지만 레이아웃에 끼워 넣으면 여러페이지에 공통적으로 적용가능합니다.


Thymeleaf layout Dialect dependency 추가하기
===


pom.xml에 Thymeleaf Layout Dialect 의존성을 추가하기


      <dependency>
			  <groupId>nz.net.ultraq.thymeleaf</groupId>
			  <artifactId>thymeleaf-layout-dialect</artifactId>
			  <version>3.1.0</version>
		  </dependency>

3.1버전으로 사용중

thymeleaf-layout-dialect 라이브러리가 설치가 완료시 templates 아래에 fragments 폴더를 생성후

footer.html, header.html 파일을 생성한다.

마찬가지로 templates 폴더 아래에 layouts 폴더를 만들고 layout1.html 파일을 생성한다.
![파일생성](https://user-images.githubusercontent.com/100178951/205145420-fd517716-9648-4c47-b899-1f14873d3238.jpg)


footer.html
===

        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
          <div th:fragment="footer"> //1
            footer 영역 입니다.
          </div>
        </html>

1. 다른 페이지에 포함시킬 영역을 th:fragment로 선언해줍니다. footer 영역을 fragment로 만들겠습니다.

header.html
===

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <div th:fragment="header">//2
        header 영역 입니다.
    </div>
    </html>
    
    2.마찬가지로 다른 페이지에 포함시킬 영역을 th:fragment로 선언해주고, header영역을 fragment로 만들어준다.
    
layout1.html
====
    
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org"
              xmlns:layout="http://www.ultraq.net.nz/thymelaf/layout"> //1
        <head>
            <meta charset="UTF-8">
            <title>Title</title>

            <th:block layout:fragment="script"></th:block>
            <th:block layout:fragment="css"></th:block>
        </head>
        <body>
            <div th:replace="fragments/header::header"></div> //2
            
            <div layout:fragment="content"></div> //3
            
            <div th:replace="fragments/footer::footer"></div> //4
        </body>
        </html>
        
1.layout 기능을 사용하기 위해서 html 태그에 layout 네임스페이스를 추가한다.

2.th:replace 속성은 해당 속성이 선언된 html 태그를 다른 html 파일로 치환하는 것으로 이해하면 좋다. 

fragments 폴더 아래의 header.html 파일의 “th:fragment=header” 영역을 가지고 온다.

3.layout에서 변경되는 영역을 fragment로 설정한다. 앞으로 쇼핑몰을 만들면서 만들 페이지는 이 영역으로 들어간다.

4.header 영역과 마찬가지로 fragments 폴더 아래의 footer.html 파일의 “th:fragment=footer” 영역을 가지고 온다.


thymeleafEx07.html
===

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org"
          xmlns:layout="http://www.ultraq.net.nz/thymelaf/layout"   1
          layout:decorate="~{layouts/layout1">

        <div layout:fragment="content">   2
            본문 영역 입니다.
        </div>
    </html>

1.layouts 폴더 아래에 있는 layout1.html을 적용하기 위해서 네임스페이스를 추가합니다.

2.layout1.html 파일의 <div layout:fragment="content"> 영역에 들어가는 영역입니다.
  
컨트롤러 클래스 작성
====
    @GetMapping(value = "/ex07")
    public String thymeleafExample07() {
        return "thymeleafEx/thymeleafEx07";  
    }
  }  
  
  
//참고자료: 백견불여일타 스프링부트와 쇼핑몰 with JPA 저자:변구훈님
