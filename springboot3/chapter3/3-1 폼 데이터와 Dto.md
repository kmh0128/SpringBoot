폼 데이터란 
===

폼 데이터(form data)란 HTML 요소인 <form>태그에 실려 전송되는 데이터를 말합니다.

인터넷 게시판에 글을 쓰고 전송 버튼을 누르면 게시글이 올라가는데, <form> 태그는 이렇게 웹 브라우저에서 서버로 데이터를 전송할 때 사용합니다.

예
----

![hjkwon-140328-form_-01](https://github.com/kmh0128/SpringBoot/assets/100178951/456a6d7f-cb9f-4513-9250-97ebfb7d6465)

form 태그는 택배에 비유할 수 있다.

택배를 보낼 때 송장에 수령자와 배송 형태(익일, 새벽)을 적는다.

<form> 태그도 데이터를 전송할 때 어디로(where, 어떻게(how), 보낼지 등을 적어서 보낸다.

<form>태그에 실어 보낸 데이터는 서버의 컨트롤러가 객체에 담아 받습니다.

이 객체를 DTO(Data transfer Object)라고 합니다.

DTO로 받은 객체는 최종적으로 데이터베이스(DB)에 저장됩니다.


폼 데이터를 DTO로 받기
===

입력 폼 만들기
---

뷰 템플릿 페이지에 입력 폼을 만든다.

templates 디렉토리에 작성 -> 이러한 뷰 템플릿 페이지는 간단히 뷰 페이지라고도 한다.






참고자료
---

저자 홍팍님

책 제목:스프링부트3 자바 백엔드 개발 입문

https://jjangadadcodingdiary.tistory.com/entry/Spring-Spring-Framework%EC%97%90%EC%84%9C%EC%9D%98-PostMapping%EA%B3%BC-GetMapping-REST-API%EC%9D%98-%ED%95%B5%EC%8B%AC-%EC%9A%94%EC%86%8C

https://www.nextree.co.kr/p8428/
