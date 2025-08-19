package model;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Especialização de {@link Pessoa} para representar um funcionário
 * dentro da organização.  Um funcionário possui, além do nome e da
 * data de nascimento, um salário e uma função.
 */
public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    /**
     * Constrói um novo funcionário com os dados fornecidos.
     *
     * @param nome           o nome do funcionário
     * @param dataNascimento a data de nascimento do funcionário
     * @param salario        o salário do funcionário
     * @param funcao         a função exercida pelo funcionário
     */
    public Funcionario(String nome,
                       LocalDate dataNascimento,
                       BigDecimal salario,
                       String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    /**
     * Obtém o salário atual do funcionário.
     *
     * @return o salário
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Altera o salário do funcionário.  Este método não realiza
     * validações; a responsabilidade de cálculo de aumentos está na
     * classe de serviço.
     *
     * @param salario o novo salário a ser definido
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    /**
     * Obtém a função exercida pelo funcionário.
     *
     * @return a função
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Altera a função exercida pelo funcionário.
     *
     * @param funcao a nova função
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}