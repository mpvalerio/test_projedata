package service;
import model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para as principais operações da classe
 * {@link FuncionarioService}.  Estes testes utilizam a lista de
 * funcionários construída por {@link FuncionarioService#criarFuncionariosIniciais()} e
 * garantem que cada regra de negócio se comporte conforme o
 * esperado.
 */
public class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> funcionarios;

    @BeforeEach
    public void setUp() {
        service = new FuncionarioService();
        funcionarios = service.criarFuncionariosIniciais();
    }

    @Test
    public void testCriarFuncionariosIniciais() {
        // Deve criar exatamente 10 funcionários
        assertEquals(10, funcionarios.size());
        // Verifica alguns nomes específicos
        assertTrue(funcionarios.stream().anyMatch(f -> f.getNome().equals("Maria")));
        assertTrue(funcionarios.stream().anyMatch(f -> f.getNome().equals("João")));
        assertTrue(funcionarios.stream().anyMatch(f -> f.getNome().equals("Caio")));
    }

    @Test
    public void testRemoverPorNomeRemoveCorretamente() {
        Optional<Funcionario> removido = service.removerPorNome(funcionarios, "João");
        assertTrue(removido.isPresent());
        assertEquals("João", removido.get().getNome());
        // A lista deve diminuir de tamanho
        assertEquals(9, funcionarios.size());
        // João não deve mais estar presente
        assertFalse(funcionarios.stream().anyMatch(f -> f.getNome().equalsIgnoreCase("João")));
    }

    @Test
    public void testAplicarAumentoPercentual() {
        // Copia salário do primeiro funcionário
        Funcionario maria = funcionarios.stream()
                .filter(f -> f.getNome().equals("Maria"))
                .findFirst()
                .orElseThrow();
        BigDecimal salarioOriginal = maria.getSalario();
        service.aplicarAumentoPercentual(funcionarios, new BigDecimal("10"));
        BigDecimal esperado = salarioOriginal.multiply(new BigDecimal("1.10")).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(0, esperado.compareTo(maria.getSalario()));
        // Todos os salários devem ter sido atualizados
        BigDecimal somaApos = service.somarSalarios(funcionarios);
        BigDecimal somaEsperada = new BigDecimal("53696.26");
        assertEquals(0, somaEsperada.compareTo(somaApos));
    }

    @Test
    public void testAgruparPorFuncao() {
        Map<String, List<Funcionario>> mapa = service.agruparPorFuncao(funcionarios);
        assertEquals(7, mapa.size());
        assertEquals(2, mapa.get("Operador").size());
        assertEquals(1, mapa.get("Coordenador").size());
        assertEquals(2, mapa.get("Diretor").size());
        assertEquals(1, mapa.get("Recepcionista").size());
        assertEquals(2, mapa.get("Gerente").size());
        assertEquals(1, mapa.get("Eletricista").size());
        assertEquals(1, mapa.get("Estagiário").size());
    }

    @Test
    public void testAniversariantesNosMeses() {
        Set<Integer> meses = new HashSet<>(Arrays.asList(10, 12));
        List<Funcionario> aniversariantes = service.aniversariantesNosMeses(funcionarios, meses);
        List<String> nomes = aniversariantes.stream().map(Funcionario::getNome).toList();
        assertEquals(4, aniversariantes.size());
        assertTrue(nomes.contains("Maria"));
        assertTrue(nomes.contains("Miguel"));
        assertTrue(nomes.contains("Heloísa"));
        assertTrue(nomes.contains("Helena"));
    }

    @Test
    public void testFuncionarioMaisVelho() {
        Optional<Funcionario> maisVelho = service.funcionarioMaisVelho(funcionarios);
        assertTrue(maisVelho.isPresent());
        assertEquals("Caio", maisVelho.get().getNome());
    }

    @Test
    public void testOrdenarPorNome() {
        List<Funcionario> ordenados = service.ordenarPorNome(funcionarios);
        assertEquals(10, ordenados.size());
        // Verifica o primeiro e o último após ordenação
        assertEquals("Alice", ordenados.get(0).getNome());
        assertEquals("Miguel", ordenados.get(ordenados.size() - 1).getNome());
    }

    @Test
    public void testSomarSalarios() {
        BigDecimal soma = service.somarSalarios(funcionarios);
        // Valor total original antes do aumento
        BigDecimal esperado = new BigDecimal("48814.78");
        assertEquals(0, esperado.compareTo(soma));
    }

    @Test
    public void testSalariosMinimosQueRecebe() {
        Funcionario caio = funcionarios.stream()
                .filter(f -> f.getNome().equals("Caio"))
                .findFirst()
                .orElseThrow();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        BigDecimal qtde = service.salariosMinimosQueRecebe(caio, salarioMinimo);
        // Caio recebe 9836 / 1212 = 8.12 salários mínimos (arredondado)
        BigDecimal esperado = new BigDecimal("8.12");
        assertEquals(0, esperado.compareTo(qtde));
    }

    @Test
    public void testIdadeEmAnos() {
        LocalDate nascimento = LocalDate.of(1961, 5, 2);
        LocalDate referencia = LocalDate.of(2025, 8, 19);
        int idade = service.idadeEmAnos(nascimento, referencia);
        assertEquals(64, idade);
    }
}