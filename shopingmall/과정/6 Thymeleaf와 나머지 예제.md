th:each 예제
===

여러 개의 데이터를 가지고 있는 컬렉션 데이터를 화면에 출력하는 방법을 알아보겠다.


itemDto
====

  @GetMapping(value = "/ex03")
  public String thymeleafExample03(Model model) {
      List<ItemDto> itemDtoList = new ArrayList<>();

      for (int i = 1; i <= 10; i++) {//1
          ItemDto itemDto = new ItemDto();
          itemDto.setItemDetail("상품 상세 설명");
          itemDto.setItemNm("테스트 상품" + i);
          itemDto.setPrice(1000*i);
          itemDto.setRegTime(LocalDateTime.now());

          itemDtoList.add(itemDto);
      }

      model.addAttribute("itemDtoList", itemDtoList);//2
      return "thymeleafEx/thymeleafEx03";


                                                    
1.반복문을 통해 화면에서 출력할 10개의 itemDto 객체를 만들어서 itemDtoList에 넣어준다.
                                                    
2.화면에서 출력할 itemDtoList를 model에 담아서 View에 전달한다.
                                                    

thymeleafEx03.html
========

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>

        <h1>상품 리스트 출력 예제</h1>

        <table border="1">
            <thead>
                <tr>
                    <td>순번</td>
                    <td>상품명</td>
                    <td>상품설명</td>
                    <td>가격</td>
                    <td>상품등록일</td>
                </tr>
            </thead>
            <tbody>
                <tr th:each="itemDto, status: ${itemDtoList}">//1
                    <td th:text="${status.index}"></td>//2
                    <td th:text="${itemDto.itemNm}"></td>
                    <td th:text="${itemDto.itemDetail}"></td>
                    <td th:text="${itemDto.price}"></td>
                    <td th:text="${itemDto.regTime}"></td>
                </tr>
            </tbody>
        </table>

    </body>
    </html>                                                    

  

1.th:each를 이용하면 자바의 for문처럼 반복문을 사용할 수 있다. 
 
  전달받은 itemDtoList에 있는 데이터를 하나씩 꺼내와서 itemDto에 담아준다. 
  
  status에는 현재 반복에 대한 상태 데이터가 존재한다. 변수명은 status 대신 다른 것을 사용해도 된다.

  
  2.현재 순회하고 있는 데이터의 인덱스를 출력한다.  
                                                    
                                                    
th:if, th:unless 예제
======

위에는 반복문이었는데 이번에 알아볼 th:if, th:unless는 조건문입니다.
  
이번 예제는 순번이 짝수이면 ‘짝수’를 출력하고 짝수가 아니라면 ‘홀수’를 출력해주는 예제이다. 자바에서의 if else 조건 처리라고 생각하면 된다.

이전 예제와 동일하게 상품 데이터 10개를 넣어서 뷰에 전달해주겠다.  


ThymeleafExController
====
  
      @GetMapping(value = "/ex04")
      public String thymeleafExample04(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(1000 * i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx04";
      }
    }                                                      

이전에 작성한 코드와 비슷하다. 순번을 처리하는 부분만 다르다.
                                                      
thymeleafEx04.html
====

    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>

    <h1>상품 리스트 출력 예제</h1>

    <table border="1">
        <thead>
        <tr>
            <td>순번</td>
            <td>상품명</td>
            <td>상품설명</td>
            <td>가격</td>
            <td>상품등록일</td>
        </tr>
        </thead>
        <tbody>
        <tr th:each="itemDto, status: ${itemDtoList}">
            <td th:if="${status.even}" th:text="짝수"></td> //1
            <td th:unless="${status.even}" th:text="홀수"></td> //2
            <td th:text="${itemDto.itemNm}"></td>
            <td th:text="${itemDto.itemDetail}"></td>
            <td th:text="${itemDto.price}"></td>
            <td th:text="${itemDto.regTime}"></td>
        </tr>
        </tbody>
    </table>

    </body>
    </html>                                                      
                                                      

1.status에는 현재 반복에 대한 정보가 존재한다. 
  
  인덱스가 짝수일 경우 status.even은 true가 된다. 
  
  즉, 현재 인덱스가 짝수라면 순번에 ‘짝수’를 출력해준다.  
  
2. 현재 인덱스가 짝수가 아닐 경우 즉, 홀수일 경우 순번에 ‘홀수’를 출력해준다.
  
  
  
th:href 예제
===
 
  ThymeleafExController
  ===
  
      @GetMapping(value = "/ex05")
      public String thymeleafExample05() {
          return "thymeleafEx/thymeleafEx05";
      }
    }

  thymeleafEx05.html
  ===
      <!DOCTYPE html>
      <html xmlns:th="http://www.thymeleaf.org">
      <head>
          <meta charset="UTF-8">
          <title>Title</title>
      </head>
      <body>
          <h1>Thymeleaf 링크처리 예제 페이지</h1>
          <div>
              <a th:href="@{/thymeleaf/ex01}">예제1 페이지 이동</a> //1
          </div>
          <div>
              <a th:href="@{http://www.thymeleaf.org}">Thymeleaf 공식 페이지 이동</a> //2
          </div>
      </body>
      </html> 
  
1.클릭 시 이전에 작성했떤 예제1 페이지로 이동한다. 
  
  “th:href=@{이동할 경로}” 형태로 입력한다. 
  
  참고로 스프링 부트에서는 애플리케이션의 루트가 “/”이다. 
  
  만약 애플리케이션의 루트가 “/shop”으로 지정 되었다면 html 파일에 생성되는 이동 경로는 “/shop/thymeleaf/ex01”이다.
  
2.thymeleaf 공식 페이지로 이동한다. 애플리케이션 외부의 사이트에 접근하는 절대 경로를 입력한다.
  
  
참고서적 : 백타불여일타 스프링부트와 쇼핑물 with Jpa 저자: 변구훈님
