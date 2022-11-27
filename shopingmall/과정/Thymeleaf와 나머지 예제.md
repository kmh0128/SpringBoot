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
                                                    

                                                    
                                                    
                                                    
                                                    
참고서적 : 백타불여일타 스프링부트와 쇼핑물 with Jpa 저자: 변구훈님
