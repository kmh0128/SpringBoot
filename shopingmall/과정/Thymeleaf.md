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

  
