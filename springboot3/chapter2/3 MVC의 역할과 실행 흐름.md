MVC의 역할과 실행 흐름
===

mvc 패턴
===

![image (1)](https://github.com/kmh0128/SpringBoot/assets/100178951/d69e655e-0023-4968-909a-07cb07b7b26d)


MVC 패턴의 흐름은 식당에서 주문하는 과정과 비슷하다.

웨이터가 손님의 주문을 받으면, 

주방장이 요리를 만들고 식재료 담당자가 요리에 쓰일 재료를 주방장에게 전달하는 것과 같이

컨트롤러가 클라이언트의 요청을 받으면, 

뷰가 페이지를 만들고 모델이 페이지에 쓰일 데이터를 뷰에 전달한다.

페이지의 흐름
====

FirstController.java 동작
---

파일의 컨트롤러 선언 @Controller 사용

@GetMapping("/bye") 어노테이션을 추가합니다. //URL의 주소 클라이언트의 요청을 접수하는 주소는 bye가 된다

/bye요청을 처리할 메서드인 seeYouNext()를 만든다

반환값은 요청에 따라 보여 줄 뷰 템플릿 페이지를 적는데, 아직 뷰 템플릿 페이지를 만들지 않았으므로 임의로 return "goodbye";라고 적는다.

    @GetMapping("/bye")//url 요청접수
        public String seeYouNext(Model model){//메서드 작성,모델 객체 받아오기
            model.addAttribute("nickname", "ㄹㅇㄷ");//변수명과 변수값등록
            return "goodbye";//goodbye.mustache 반환

        }

반환값을 만들었으니 컨트롤러는 그대로 사용하기로하고 뷰 템플릿 페이지는 새로 만든다

goodbye.mustache를 templates 디렉토리에 만든다

새 파일을 만들고 doc->쓰고난후 tap버튼을 클릭하면 html 기본코드를 작성

본문에 글을 작성해준다.

![qdqd](https://github.com/kmh0128/SpringBoot/assets/100178951/086a1f38-864c-401e-8183-3c1133b8d528)

사용자 이름을 그때그때 변경하고 싶으면 

![qdqd2](https://github.com/kmh0128/SpringBoot/assets/100178951/2560ea3d-e3c3-4f6d-8b40-67b6e89eb03e)

위 이미지처럼 수정해준다

        public String seeYouNext(Model model){//메서드 작성,1모델 객체 받아오기
            model.addAttribute("nickname", "ㄹㅇㄷ");//2변수명과 변수값등록

뷰 템플릿에서 변수(nickname)을 이용하려면 이 페이지를 반환하는 컨트롤러의 메서드(seeYouNext)에 변수를 등록해야 합니다.

컨트롤러를 돌아와서 다음과 같이 코드를 추가합니다.

1 메서드의 매겨변수로 model 객체를 받아옵니다.

2 modeladdAttribute() 메서드로 등록할 변수명과 변수값을 적어준다.

머스테치에서 이렇게하면 코드에서 변수값을 받을수 있습니다.

![변수명 자유자재](https://github.com/kmh0128/SpringBoot/assets/100178951/6442694c-c250-465a-8ccf-db409d393ad9)

서버를 시작하고 localhost8080/bye에 접속하면 위와 같이 나오게됩니다. 

참조자료
----


https://velog.io/@hznnoy/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%9E%85%EB%AC%B8-2-

https://ai-hong.tistory.com/104
