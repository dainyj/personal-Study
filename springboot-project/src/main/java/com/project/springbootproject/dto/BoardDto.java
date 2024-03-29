package com.project.springbootproject.dto;

import com.project.springbootproject.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        return boardEntity;
    }

    @Builder

    public BoardDto(Long id, String writer, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}

/* **Explain**
toEntity()
 - dto에서 필요한 부분을 빌더 패턴을 통해 entity로 만든다
   - 필요한 Entity는 이런 식으로 추가하면 된다

dto는 Controller <> Service <> Repository 간에 필요한 데이터를 캡슐화한 데이터 전달 객체이다
 - 그런데 예제에서 Service에서 Repository 메서드를 호출할 때, Entity를 전달한 이유는 JpaRepository에 정의된
   함수들은 미리 정의되어 있기 때문이다. 그래서 Entity를 전달할 수밖에 없었는데,
   요점은 각 계층에서 필요한 객체 전달은 Entity 객체가 아닌 dto 객체를 통해 주고받는 것이 좋다는 것이다 */