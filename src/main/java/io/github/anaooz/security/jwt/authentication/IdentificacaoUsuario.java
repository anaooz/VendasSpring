package io.github.anaooz.security.jwt.authentication;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificacaoUsuario {
    private String id;
    private String login;
    private List<String> permissoes;

    public List<String> getPermissoes() {
        if(permissoes == null) {
            permissoes = new ArrayList<>();
        }

        return permissoes;
    }
}
