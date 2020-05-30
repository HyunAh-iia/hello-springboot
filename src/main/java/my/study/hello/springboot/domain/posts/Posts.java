package my.study.hello.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // lombok
@NoArgsConstructor // lombok - make default constructor
@Entity // JPA annotation - shows the class is linked with tables
public class Posts {

    @Id // shows the field is a pk key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk generate rule
    private Long id;

    @Column(length = 500, nullable = false) // @Column is default annotation, use if you want to add more options
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // create a builder pattern class
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;

        /* [Note] No update query
         * Dirty checking of JPA ==> https://jojoldu.tistory.com/415
         */
    }
}

/* [Note]
 * Do not create Setter methods in Entity class
 * 1. if you need to change value of column, make a method which shows purpose and objective clearly
 *
 * 2. if you want to insert data into a database, fulfill data into a constructor and then insert into a database
 * I'm using builder class which @Builder provides instead of constructors
 * Advantage of @Builder is to shows what field should fulfilled.
 *   - constructor : if you switch position of a and b, you don't know until you run the code.
 *   public Example(String a, String b) {
 *      this.a = a;
 *      this.b = b;
 *   }
 *   Example(b, a);
 *
 *   - @Builder
 *   Example.builder()
 *      .a(a)
 *      .b(b)
 *      .build();
 *
 */