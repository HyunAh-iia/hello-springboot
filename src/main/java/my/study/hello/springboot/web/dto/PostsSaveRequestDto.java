package my.study.hello.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.study.hello.springboot.domain.posts.Posts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    @Size(min = 2)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Size(min = 2)
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @NotBlank
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
