package my.study.hello.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // makes entity classes which is inherited the class
                  // to recognize fields(createdData, modifiedData) of the class as their columns.
@EntityListeners(AuditingEntityListener.class) // makes to include Auditing function at BaseTimeEntity Class
public abstract class BaseTimeEntity {

    @CreatedDate // Auto
    private LocalDateTime createdDate;

    @LastModifiedDate // Auto
    private LocalDateTime modifiedDate;
}

/* [Note]
 * BaseTimeEntity Class will be a super class of whole entites.
 * It manages createdDate, modifiedDate of whole entites.
 */