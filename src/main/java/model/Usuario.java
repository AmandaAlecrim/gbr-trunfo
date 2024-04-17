package model;

import jakarta.persistence.*;
import model.dto.UserPrincipalDTO;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Usuario() {
    }

    public static Usuario from(UserPrincipalDTO dto) {
        Usuario user = new Usuario();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setLogin(dto.getLogin());
        return user;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
