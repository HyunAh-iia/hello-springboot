package my.study.hello.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.study.hello.springboot.domain.posts.Posts;
import my.study.hello.springboot.domain.posts.PostsRepository;
import my.study.hello.springboot.web.dto.PostsListResponseDto;
import my.study.hello.springboot.web.dto.PostsResponseDto;
import my.study.hello.springboot.web.dto.PostsSaveRequestDto;
import my.study.hello.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // = .map(posts -> new PostsResponseDto(posts))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.deleteById(id);
    }
}

/* [Note]
 * @Service doesn't need to process a business logic
 * Service only care about a sequence of transactions and domains
 */
