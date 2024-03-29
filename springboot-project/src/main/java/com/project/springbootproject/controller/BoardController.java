package com.project.springbootproject.controller;


//URL을 매핑하고, 비즈니스 로직 함수를 호출하여 view에 뿌려주는 역할을 하는 컨트롤러

import com.project.springbootproject.dto.BoardDto;
import com.project.springbootproject.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/")
    public String list() {
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}

/* **EXPLAIN**
@Controller
 - 컨트롤러임을 명시하는 어노테이션
 - MVC에서 컨트롤러로 명시된 클래스의 메서드들은 반환 값으로 템플릿 경로를 작성하거나, redirect를 해줘야 한다
    - 템플릿 경로는 templates패키지를 기준으로 한 상대 경로이다

 - @RestController는 @Controller와 @ReponseBody를 합쳐놓은 어노테이션이다
    - view 페이지가 필요 없는 API 응답에 어울리는 어노테이션

@AllArgsConstructor
 - Bean 주입 방식과 관련이 있으며, 생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션
   그래서 BoardService 객체를 주입받을 때 @Autowired 같은 특별한 어노테이션을 부여하지 않는다 *참고
 - 그 밖에, @NoArgsConstructor @RequiredArgsConstructor 어노테이션이 있다

@GetMapping / @PostMapping
 - URL을 매핑해주는 어노테이션이며, HTTP Method에 맞는 어노테이션을 작성하면 된다
    list() 메서드는 지금 구현하지 않고, 다음 글에서 구현할 것이다
    dto는 Controller와 Service사이에서 데이터를 주고받는 객체를 의미한다*/