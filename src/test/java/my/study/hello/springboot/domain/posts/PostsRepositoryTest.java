package my.study.hello.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // if you use @SpringBootTest with default option, automatically run H2 database
public class PostsRepositoryTest {

    @Autowired // get beans from spring
    PostsRepository postsRepository;

    @After // run the method after finish unit tests
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savedPost_ListAll() {
        // given
        String title = "title of a post";
        String content = "content of a post";

        postsRepository.save(Posts.builder() // .save : upsert query
                .title(title)
                .content(content)
                .author("kha2464@gmail.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
