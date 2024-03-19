package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "carta")
public class Carta {

    @Id
    @SequenceGenerator(name = "seq_carta", sequenceName = "seq_carta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carta")
    private int id;
    private String nome;
    private int forca;
    private int inteligencia;
    private int velocidade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro")
    private Calendar dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

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

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
