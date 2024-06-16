DTO를 데이터베이스에 저장하기
===

데이터베이스와 JPA
===

테이터베이스
---

데이터 베이스란 데이터를 관리하는 창고입니다.

줄여서 DB라고도 한다.

엑셀 파일과 비슷하다.

엑셀 파일은 각 셀에 데이터를 저장한다.

DB도 모든 데이터가 행과 열로 구성된 테이블(table)에 저장해 관리한다.

테이블 예 사진
---

![릴레이션_구조 (1)](https://github.com/kmh0128/SpringBoot/assets/100178951/139a630b-0c80-4e11-8328-80ca36d9b589)


h2 데이터베이스의 사용과 JPA
---

처음에 프로젝트를 만들때 의존성 주입을 해놓은 db

DB는 SQL언어를 사용하지만 JPA를 사용하면 자바로도 명령을 내릴수가 있다.

JPA(Java Persistence API)란 자바 언어로 DB에 명령을 내리는 도구로, 데이터를 객체 지향적으로 관리할 수 있게 해 줍니다.

JPA의 핵심 도구로는 엔티티(Entity)와 리파지토리(repository)가 있습니다.

엔티티
--

자바 객체를 DB가 이해할 수 있게 만든 것으로, 이를 기반으로 테이블이 만들어집니다.

리파지토리
--

엔티티가 DB 속 테이블에 저장 및 관리될 수 있게 하는 인터페이스입니다.

DTO를 엔티티로 변환하기
===

DTO를 엔티티로 변환한다.

ArticleController
===

    public class ArticleController {

        //중략

        @PostMapping("/articles/create")//URL 요청접수
        public String createArticle(ArticleForm form) {//폼 데이터를 DTO로 받기
            System.out.println(form.toString());//DTO에 폼 데이터가 잘 담겼는지 확인
            //1.DTO를 엔티티로 변환
            Article article = form.toEntity();
            System.out.println(article.toString());//DTO가 엔티티로 잘 변환되는지 확인 출력
            //2. 리파지토리로 엔티티를 DB에 저장
            return "";


        }
    }

DTO를 변환시키는 코드에서 Article과 toEntity()에 빨간색 오류가 표시가 되는데 articel 클래스와 toEntity()

메서드를 만들지 않았기때문입니다.

Article 클래스
===

![엔티티 패키지를 생성한후 article 클래스 만들기](https://github.com/kmh0128/SpringBoot/assets/100178951/03c64674-ff40-4c75-b14c-b6947684ff31)

빨간색 오류가난 Article 클래스에 마우스를 올리고 조금 기다리면 Article 클래스를 만들 수 있는 링크가 뜹니다.

Create class 'Article'을 클릭후 or Alt + Enter 클릭후 목록에서 Create class 'Article' 기다립니다.

![엔티티 수정](https://github.com/kmh0128/SpringBoot/assets/100178951/7c74daf3-3bd7-4147-b8a0-008d1cea1223)

Destination package에서 controller 부분을 entity로 수정하고 ok클릭

![클래스 생성확인](https://github.com/kmh0128/SpringBoot/assets/100178951/554800b5-6f35-4a70-a431-74de14027ef4)

entity 패키지까지 생성되고 내부에 Article 클래스가 만들어진 것을 확인할 수 있습니다.

@Entity
---

JPA에서 제공하는 어노테이션으로, 이 어노테이션이 붙은 클래스를 기반으로 DB에 테이블이 생성된다.

테이블 이름은 클래스 이름과 동일하게 생성된다.

@Column
---

DTO 코드를 작성할 때와 같이 title, content 필드를 선언합니다.

두 필드가 DB 테이블 각 열(column)과 연결되게 해주는 어노테이션입니다.

@ID
--

엔티티의 대표값을 지정해주는 어노테이션

@GeneratedValue
--

자동 생성 기능(숫자가 자동으로 매겨짐)

entity/Article
===

        public class Article {
            @Id//엔티티의 대표값 지정
            @GeneratedValue//자동 생성 기능 추가(숫자가 자동으로 매겨진다)
            private Long id;

            @Column
            private String title;

            @Column
            private String content;

            public Article(Long id, String title, String content) {//Article 메서드 추가
                this.id = id;
                this.title = title;
                this.content = content;
            }

            @Override
            public String toString() {//toString 메서드 추가
                return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
            }
        }

content 필드 아래 여백의 공간에 마우스 오른쪽 Genrate -> Constructor를 선택 Ctrl을 누른 채 id:Long,title:String, content:String을 모두 선택한후 ok를 클릭

이어서 toSting()메서드도 추가합니다. 생성자 아래에서 마우스 오른쪽 버튼을 누르고 이어서 Generate-> toString()을 선택 위와 같은 항목을 선택하고 ok를 눌러줍니다.

Article에 있던 오류가 사라집니다

Generate 예시
---

![generate](https://github.com/kmh0128/SpringBoot/assets/100178951/ed50f96d-4233-433f-a97a-331ff5463934)

위에 있던 오류가 사라져서 엔티티 타입이 인식된다는 뜻입니다.

entity 패키지의 Article 클래스가 임포트된 것도 확인할 수 있습니다.

controller/ArticleController.java
---

        import com.example.firstproject.entity.Article;
        //중략
        //DTO를 엔티티로 변환
        Article article = form.toEntity();
toEntity() 메서도 추가하기
---

toEntity() 메서드는 DTO인 form 객체를 엔티티 객체로 변환하는 역할을 합니다.

ArticleController.java -> toEntity()에 마우스를 올리고 잠시 기다리면 해당 메서드를 만들어주는 링크가 뜹니다.

Create method 'toEntity' in 'ArticleForm' 클릭하면

ArticleForm
---

        public Article toEntity() {        
        }

해당 코드가 완성

이어서 DTO 개체를 엔티티로 반환하기위에 retrun new Article();

전달값은 Article 클래스의 생성자 형식에 맞게 작성

Aritcle.java 생성자를 확인해 보면 id, title, content를 매개변수로 받고 있다.

ArticleForm 객체에 id 정보는 없으므로 첫 번째 전달값은 null 두번 째 전달값은 title 세 번째 전달값은 content를 입력합니다.

entity/Article
---

        public Article(Long id, String title, String content) {
                this.id = id;
                this.title = title;
                this.content = content;
            }

        
dto/ArticleForm
---

        public Article toEntity() {
        //중략
                return new Article(null, title, content);
            }
        }


ArticleController에 가보면 toEntity() 메서드의 오류 표시가 사라진 것을 확인할 수 있습니다.

DTO를 엔티티로 변환하는 과정이었습니다.

리파지토리로 엔티티를 DB에 저장하기
===

1 articleRepository.save() 메서드를 호출해 article 엔티티를 저장합니다.

save()메서드는 저장된 엔티티를 반환합니다.

이를 Article 타입의 saved라는 객체에 받아 옵니다.

articleRepository부분에 빨간색 오류 표시가 뜹니다.

이런경우 articleRepository 객체를 선언하지 않고 사용했기 때문입니다.

2 필드 선언부에 ArticleRepository 객체를 선언합니다.

controller/ArticleController
---

        @Controller
        public class ArticleController {

            @Autowired//스프링부터가 미리 생성해 놓은 리파지터리 객체 주입(DI)
            private ArticleRepository articleRepository;//articleRepository 객체선언

            @GetMapping("/articles/new")
            public String newArticlesForm() {
                return "articles/new";
            }

            @PostMapping("/articles/create")//URL 요청접수
            public String createArticle(ArticleForm form) {//폼 데이터를 DTO로 받기
                System.out.println(form.toString());//DTO에 폼 데이터가 잘 담겼는지 확인
                //DTO를 엔티티로 변환
                Article article = form.toEntity();
                //2. 리파지토리로 엔티티를 DB에 저장
                Article saved = articleRepository.save(article);//artcle 엔티티를 저장해 saved 객체에 반환
                return "";


            }
        }
위의 코드를 작성시 save() 메서드가 빨간색으로 표시되고 인식되지 않았습니다.

필드 선언부에 ArticleRepository도 마찬가지입니다.

해당 리파지터리가 아직 생성되지 않았기 때문입니다.

리파지토리 만들기
===

1-> com.example.firstproject에서 마우스 오른족 new -> package 선택하고

끝에 repository로 추가해 짓습니다.

2-> repository 패키지가 생성되면 class를 생성 이번에는 interface를 선택하고 이름을 ArticleRepository라고 입력

3 -> 편집기가 열리면서 ArticleRepository가 repository 패키지 안에 생성된 것을 확인할 수 있습니다.

리파지터리는 사용자가 직접 구현할 수도 있지만 JPA에서 제공하는 리파지터리 인터페이스를 활용해서 만들 수도 잇습니다.

ArticleRepository 다음에 extends Crud를 입력하면 펼침 목록이 나타나는데 그중에서 CrudRepository<T, ID>를 선택합니다.

이는 CrudRepository라는 인터페이스를 상속받는 명령입니다.

![리포지토리 목록](https://github.com/kmh0128/SpringBoot/assets/100178951/31fc91a5-5051-4715-812f-d49d4233ab02)

4 위와 같은 CrudRepository는 JPA에서 제공하는 이터페이스로 이를 상속해 엔티티를 관리(생성, 조회,수정,삭제)할 수 있습니다.

CrudRepository에 홑화살괄호(<>)를 붙이고 그안에 다음과 같은 제네릭 요소를 받습니다

Aritcle -> 관리 대상 엔티티의 클래스 타입입니다. 여기서는 Article 입니다.

Long -> 관리 대상 엔티티의 대표값 타입입니다.

Article.java 파일에 가 보면 id를 대푯값으로 지정했습니다.

id의 타입은 Long 이므로 Long을 입력합니다.

repository/ArticleRepository.java
---

![리퍼지토리](https://github.com/kmh0128/SpringBoot/assets/100178951/42b09f3d-d36c-44f0-ae60-cac8ebf46cdb)

        //CrudRepository  패키지 자동 임포트
        import org.springframework.data.repository.CrudRepository;

위 코드까지 작성하면 ArticleRepository는 CrudRepository가 제공하는 기능을 별도 정의 없이 그대로 사용할 수 있습니다.

DB에 데이터를 생성하고(Create),읽고(Read),수정하고(Update),삭제하는(Delete) 기본 동작을 추가 코드로 구현할 필요 없이 CrudRepository에서 상속받아서 사용할 수 있습니다.




참고자료
---

http://wiki.hash.kr/index.php/%ED%85%8C%EC%9D%B4%EB%B8%94_(%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4)
