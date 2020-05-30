package my.study.hello.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Repository and Entity class should be located same address.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/* [Note]
 * Select Query Framework : querydsl, jooq, MyBatis
 * CUD Query : Using SpringDataJpa
 *
 * recommend "querydsl" as select query framework
 * Why?
 * 1 : stabiliy of type
 * 2 : many companies in Korea using it
 * 3 : many references
 */
