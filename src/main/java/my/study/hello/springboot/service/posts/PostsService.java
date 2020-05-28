package my.study.hello.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import my.study.hello.springboot.domain.posts.PostsRepository;
import my.study.hello.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}

/* [Note]
 * @Service doesn't need to process a business logic
 * Service only care about a sequence of transactions and domains
 */
