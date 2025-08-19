package br.com.empresa.app;

import br.com.empresa.model.Funcionario;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.util.FormatadorUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Principal {

    private static void imprimirLista(List<Funcionario> funcionarios) {
        funcionarios.forEach(f -> System.out.printf(" - %s | Nascimento: %s | Função: %s | Salário: %s%n",
                f.getNome(),
                FormatadorUtil.formatarData(f.getDataNascimento()),
                f.getFuncao(),
                FormatadorUtil.formatarValor(f.getSalario())
        ));
    }

    public static void main(String[] args) {
        var service = new FuncionarioService();
        var funcionarios = service.criarFuncionariosIniciais();

        System.out.println("===== 3.1 Funcionários inseridos =====");
        imprimirLista(funcionarios);

        System.out.println();
        System.out.println("===== 3.2 Remover \"João\" =====");
        service.removerPorNome(funcionarios, "João").ifPresentOrElse(
                f -> System.out.println("Removido: " + f.getNome()),
                () -> System.out.println("João não encontrado")
        );

        System.out.println();
        System.out.println("===== 3.3 Funcionários (formatados) =====");
        imprimirLista(funcionarios);

        System.out.println();
        System.out.println("===== 3.4 Aumento de 10% =====");
        service.aplicarAumentoPercentual(funcionarios, new BigDecimal("10"));
        imprimirLista(funcionarios);

        System.out.println();
        System.out.println("===== 3.5 / 3.6 Agrupar e imprimir por função =====");
        Map<String, List<Funcionario>> porFuncao = service.agruparPorFuncao(funcionarios);
        porFuncao.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println("Função: " + entry.getKey());
                    imprimirLista(entry.getValue());
                });

        System.out.println();
        System.out.println("===== 3.8 Aniversariantes (Outubro e Dezembro) =====");
        var meses = new HashSet<>(Arrays.asList(10, 12));
        var aniversariantes = service.aniversariantesNosMeses(funcionarios, meses);
        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum aniversariante em outubro/dezembro.");
        } else {
            imprimirLista(aniversariantes);
        }

        System.out.println();
        System.out.println("===== 3.9 Funcionário mais velho =====");
        service.funcionarioMaisVelho(funcionarios).ifPresent(maisVelho -> {
            int idade = service.idadeEmAnos(maisVelho.getDataNascimento(), LocalDate.now());
            System.out.printf("Nome: %s | Idade: %d%n", maisVelho.getNome(), idade);
        });

        System.out.println();
        System.out.println("===== 3.10 Lista por ordem alfabética =====");
        var ordenados = service.ordenarPorNome(funcionarios);
        imprimirLista(ordenados);

        System.out.println();
        System.out.println("===== 3.11 Total dos salários (após +10%%) =====");
        var total = service.somarSalarios(funcionarios);
        System.out.println("Total: " + FormatadorUtil.formatarValor(total));

        System.out.println();
        System.out.println("===== 3.12 Salários mínimos (R$ 1.212,00) por funcionário =====");
        var salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            var qtde = service.salariosMinimosQueRecebe(f, salarioMinimo);
            System.out.printf(" - %s: %s salários mínimos%n", f.getNome(), FormatadorUtil.formatarValor(qtde));
        });
    }
}
