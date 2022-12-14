부트스트랩으로 header,footer영역 수정하기
===

보통 웹 페이지의 상단에는 웹 페이지를 이동할 수 있는 네비게이션 바가 있으며 하단에는 해당 기업의 주소, 전화번호, 이메일 등의 정보를 나타내는 footer 영역이 있다. 

여기서는 부트스트랩으로 네비게이션 바와 푸터 영역을 만들어보도록 하겠다.

서버 개발자로서 애플리케이션을 만들 때 힘든 점은 웹 페이지의 디자인 및 웹 퍼블리싱이다. 

트위터에서 만든 오픈소스인 부트스트랩은 웹사이트를 쉽게 만들 수 있도록 도와주는 HTML, CSS, JS 프레임워크이다. 

부트스트랩에서 제공하는 템플릿 및 컴포넌트 등을 이용하면 웹 페이지를 쉽게 꾸밀 수 있다.


Bootstrap CDN 추가하기
===


부트스트랩을 사용하기 위해서 다운로드한 후 애플리케이션에 추가할 수 있지만, 예제 진행의 편의상 Bootstrap CDN만 추가 후 개발을 진행하도록 하겠다.

CDN(Contents Delivery Network)을 간단히 설명하자면 물리적으로 멀리 떨어져 있는 사용자에게 컨텐츠를 좀 더 바르게 제공하기 위한 서비스를 말한다. 

예를 들어서 한국에서 미국 서버에 있는 css, javascript, 이미지 등의 리소스를 받기 위해서는 어느 정도 시간 지연이 발생한다. 

한국에 같은 리소스를 제공해주는 서버가 있다면 물리적 거리가 가깝기 때문에 좀 더 빠르게 받을 수 있다. 즉, 일종의 캐시 서버를 두어서 컨텐츠를 빠르게 받을 수 있도록 하는 서비스이다.

Bootstrap CDN을 layout1.html의 헤더 영역에 추가하여 해당 리소스를 다운로드해서 사용할 수 있도록 한다.

웹 페이지의 화면을 만드는 일을 해주는 소스코드가 굉장히 많다.


layout.html1
===

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org"
          xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>

        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link th:href="@{/css/layout1.css}" rel="stylesheet">

        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <th:block layout:fragment="script"></th:block>
        <th:block layout:fragment="css"></th:block>

    </head>
    <body>

    <div th:replace="fragments/header::header"></div>

    <div layout:fragment="content" class="content">

    </div>

    <div th:replace="fragments/footer::footer"></div>

    </body>
    </html>
    
    
