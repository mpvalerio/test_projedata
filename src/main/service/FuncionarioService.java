package service;
import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Regras de negócio relacionadas à manipulação de funcionários.  Esta
 * classe centraliza a criação da lista inicial, bem como operações de
 * remoção, aumento salarial, agrupamento, ordenação e cálculos
 * agregados.
 */
public class FuncionarioService {

    /**
     * Cria a lista inicial de funcionários conforme especificado no
     * enunciado.  Os dados incluem nome, data de nascimento,
     * salário e função.  A lista retornada pode ser modificada pelos
     * métodos desta classe.
     *
     * @return uma lista mutável de funcionários
     */
    public List<Funcionario> criarFuncionariosIniciais() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario(
                "Maria",
                LocalDate.of(2000, 10, 18),
                new BigDecimal("2000.00"),
                "Operador"
        ));
        funcionarios.add(new Funcionario(
                "João",
                LocalDate.of(1990, 5, 12),
                new BigDecimal("2284.00"),
                "Operador"
        ));
        funcionarios.add(new Funcionario(
                "Caio",
                LocalDate.of(1961, 5, 2),
                new BigDecimal("9836.00"),
                "Coordenador"
        ));
        funcionarios.add(new Funcionario(
                "Miguel",
                LocalDate.of(1988, 10, 14),
                new BigDecimal("19119.00"),
                "Diretor"
        ));
        funcionarios.add(new Funcionario(
                "Alice",
                LocalDate.of(1995, 1, 5),
                new BigDecimal("2234.00"),
                "Recepcionista"
        ));
        funcionarios.add(new Funcionario(
                "Heitor",
                LocalDate.of(1999, 11, 19),
                new BigDecimal("1582.72"),
                "Gerente"
        ));
        funcionarios.add(new Funcionario(
                "Arthur",
                LocalDate.of(1993, 3, 31),
                new BigDecimal("4071.84"),
                "Diretor"
        ));
        funcionarios.add(new Funcionario(
                "Laura",
                LocalDate.of(1994, 7, 8),
                new BigDecimal("3017.45"),
                "Gerente"
        ));
        funcionarios.add(new Funcionario(
                "Heloísa",
                LocalDate.of(2003, 12, 24),
                new BigDecimal("1412.89"),
                "Eletricista"
        ));
        funcionarios.add(new Funcionario(
                "Helena",
                LocalDate.of(1999, 10, 2),
                new BigDecimal("3256.88"),
                "Estagiário"
        ));
        return funcionarios;
    }

    /**
     * Remove o primeiro funcionário que possuir o nome indicado.  A
     * comparação ignora diferenciação de maiúsculas e minúsculas.  Se
     * nenhum funcionário com o nome fornecido for encontrado, nada
     * acontece e um {@link Optional#empty()} é retornado.
     *
     * @param funcionarios a lista de funcionários a ser modificada
     * @param nome         o nome do funcionário a remover
     * @return um Optional contendo o funcionário removido, se ele
     *     existir
     */
    public Optional<Funcionario> removerPorNome(List<Funcionario> funcionarios, String nome) {
        Iterator<Funcionario> it = funcionarios.iterator();
        while (it.hasNext()) {
            Funcionario f = it.next();
            if (f.getNome().equalsIgnoreCase(nome)) {
                it.remove();
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    /**
     * Aplica um aumento percentual a todos os funcionários na lista.
     * O percentual deve ser fornecido como valor inteiro (por
     * exemplo, 10 para aumentar em 10%).  O método modifica a lista
     * recebida, ajustando os salários no próprio objeto
     * {@link Funcionario}.
     *
     * @param funcionarios a lista de funcionários cujos salários serão ajustados
     * @param percentual   o percentual de aumento (por exemplo, 10 para 10%)
     */
    public void aplicarAumentoPercentual(List<Funcionario> funcionarios, BigDecimal percentual) {
        if (percentual == null) {
            throw new IllegalArgumentException("Percentual não pode ser nulo");
        }
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));
        for (Funcionario f : funcionarios) {
            BigDecimal novo = f.getSalario().multiply(fator);
            // Arredonda para 2 casas decimais
            novo = novo.setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novo);
        }
    }

    /**
     * Agrupa os funcionários por função, retornando um mapa no qual a
     * chave é a função e o valor é a lista de funcionários que a
     * exercem.
     *
     * @param funcionarios a lista a ser agrupada
     * @return um {@link Map} com a função como chave e a lista de
     *     funcionários como valor
     */
    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    /**
     * Filtra os funcionários que fazem aniversário nos meses
     * fornecidos.  Os meses devem ser passados como números de 1 a 12.
     *
     * @param funcionarios a lista de funcionários a filtrar
     * @param meses        conjunto de meses (1 a 12) a considerar
     * @return uma lista dos funcionários cujas datas de nascimento
     *     possuem o mês especificado
     */
    public List<Funcionario> aniversariantesNosMeses(List<Funcionario> funcionarios, Set<Integer> meses) {
        if (meses == null || meses.isEmpty()) {
            return Collections.emptyList();
        }
        return funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    /**
     * Determina o funcionário mais velho da lista (aquele com a
     * menor data de nascimento).  Se a lista estiver vazia, retorna
     * um Optional vazio.
     *
     * @param funcionarios a lista a ser examinada
     * @return um Optional contendo o funcionário mais velho, se
     *     existir
     */
    public Optional<Funcionario> funcionarioMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    /**
     * Calcula a idade completa, em anos, de uma pessoa na data
     * especificada.  Utiliza {@link Period#between(LocalDate, LocalDate)}.
     *
     * @param nascimento    data de nascimento
     * @param dataReferencia data para a qual a idade deve ser calculada
     * @return a idade em anos
     */
    public int idadeEmAnos(LocalDate nascimento, LocalDate dataReferencia) {
        return Period.between(nascimento, dataReferencia).getYears();
    }

    /**
     * Retorna uma nova lista contendo os funcionários ordenados
     * alfabeticamente pelo nome.  A lista original não é modificada.
     *
     * @param funcionarios a lista a ser ordenada
     * @return uma lista ordenada por nome
     */
    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    /**
     * Soma todos os salários dos funcionários da lista.  Se a lista
     * estiver vazia, retorna zero.
     *
     * @param funcionarios a lista a ser somada
     * @return o total dos salários
     */
    public BigDecimal somarSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcula quantos salários mínimos o funcionário recebe, de forma
     * aproximada (duas casas decimais).  O resultado é obtido pela
     * divisão do salário do funcionário pelo salário mínimo e
     * arredondado com {@link RoundingMode#HALF_UP}.
     *
     * @param funcionario   o funcionário a ser analisado
     * @param salarioMinimo o valor do salário mínimo
     * @return a quantidade de salários mínimos recebidos
     */
    public BigDecimal salariosMinimosQueRecebe(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario()
                .divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }
}