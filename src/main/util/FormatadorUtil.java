package util;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe utilitária responsável por padronizar a formatação de datas e
 * valores monetários.  A formatação segue o padrão brasileiro, com
 * datas no formato dd/MM/yyyy e números com separador de milhar como
 * ponto e separador decimal como vírgula.
 */
public final class FormatadorUtil {

    /** Formata as datas como dd/MM/yyyy. */
    private static final DateTimeFormatter DATA_FORMATADOR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Formata números com separador de milhar e decimal no padrão BR. */
    private static final NumberFormat VALOR_FORMATADOR;

    static {
        VALOR_FORMATADOR = NumberFormat.getInstance(new Locale("pt", "BR"));
        VALOR_FORMATADOR.setMinimumFractionDigits(2);
        VALOR_FORMATADOR.setMaximumFractionDigits(2);
    }

    private FormatadorUtil() {
        // classe utilitária; não deve ser instanciada
    }

    /**
     * Converte uma data para o formato dd/MM/yyyy.
     *
     * @param data a data a ser formatada
     * @return a data formatada como string
     */
    public static String formatarData(LocalDate data) {
        return data.format(DATA_FORMATADOR);
    }

    /**
     * Formata um valor monetário usando separador de milhar como ponto
     * e decimal como vírgula.  O número é arredondado para duas
     * casas decimais conforme configuração do {@link NumberFormat}.
     *
     * @param valor o valor a ser formatado
     * @return a representação formatada
     */
    public static String formatarValor(BigDecimal valor) {
        return VALOR_FORMATADOR.format(valor);
    }
}