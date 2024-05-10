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







