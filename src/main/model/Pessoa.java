package model;
import java.time.LocalDate;

/**
 * Representa uma pessoa com nome e data de nascimento.  Esta classe
 * serve como base para outras entidades, como {@link Funcionario}.
 */
public class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    /**
     * Constrói uma nova pessoa.
     *
     * @param nome           o nome da pessoa
     * @param dataNascimento a data de nascimento da pessoa
     */
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a data de nascimento da pessoa.
     *
     * @return a data de nascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}