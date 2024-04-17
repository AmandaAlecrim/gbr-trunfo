package model.dto;

import io.jsonwebtoken.Claims;

import java.security.Principal;

public class UserPrincipalDTO implements Principal {

    private int id;
    private String nome;
    private String login;

    public UserPrincipalDTO() {
    }

    public UserPrincipalDTO(int id, String nome, String login) {
        this.id = id;
        this.nome = nome;
        this.login = login;
    }

    public static UserPrincipalDTO from(Claims claims){
        UserPrincipalDTO dto = new UserPrincipalDTO(
                claims.get("id", Integer.class),
                claims.get("nome", String.class),
                claims.get("login", String.class)
        );
        return dto;
    }

    @Override
    public String toString() {
        return "UserPrincipalDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
