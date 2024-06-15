package io.github.anaooz.rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String login;
    private String token;
}
