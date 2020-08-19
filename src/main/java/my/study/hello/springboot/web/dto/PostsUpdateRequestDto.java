package my.study.hello.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Size(min = 2)
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
