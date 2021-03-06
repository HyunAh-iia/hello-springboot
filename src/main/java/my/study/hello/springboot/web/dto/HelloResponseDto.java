package my.study.hello.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // create get methods of whole fields
@RequiredArgsConstructor // create constructor with whole final fields(No init.)
                         // and create constructor with @Nullable fields
//@AllArgsConstructor - create e constructor with whole fields
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
