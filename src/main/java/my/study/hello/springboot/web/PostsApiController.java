package my.study.hello.springboot.web;

import lombok.RequiredArgsConstructor;
import my.study.hello.springboot.service.posts.PostsService;
import my.study.hello.springboot.web.dto.PostsResponseDto;
import my.study.hello.springboot.web.dto.PostsSaveRequestDto;
import my.study.hello.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestsDto) {
        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id]")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}

/* Annotations to get request params
 * @RequestsParam : https://url?param1=1&param2=2, @RequestsParam("param1") int param1
 * @RequestsBody : the Body of HTTP requests
 * @PathVariable : https://url/{idx}, @PathVariable("idx") int idx
 * @ModelAttribute
 */
