package my.study.hello.springboot.config.auth.dto;

import lombok.Getter;
import my.study.hello.springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
/* [Note] the reason not to use the User entity.
 * read the message below
 * "Faile to convert from type [java.lang.Object] to type [byte[]] for value '~domain~.domain.user.User@~'
 *   ==> it means "Did not implement serialization at the User entity"
 *
 * to solve the problem,
 * 1. do implement serialization at the User entity
 *   ==> the User class is entity, so the class may have relationships with other entities.
 *       ex) if the User entity have child classes, the serialization function should include child classes.
 *       it could occur performance issues and side effects.
 * 2. make a session DTO have serialization function (recommended)
 */
