import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;


class RelatorioShowJUnitTest {

	private Map<Integer, Lote> lotesIngressos;
	
	@BeforeEach
	@DisplayName("Inicialização do lote de ingressos a ser testado.")
	void initLoteIngressos() {
		this.lotesIngressos = new HashMap<>();
		Lote lote = new Lote(1, 0);
		
		for (int i = 1; i < 15; i++) {
			if (i >= 1 && i <= 4) {
		        lote.adicionaIngresso(new Ingresso(i, TipoIngresso.NORMAL, true));
			} else if (i == 5 || i == 6) {
		        lote.adicionaIngresso(new Ingresso(i, TipoIngresso.VIP, true));
			} else {
		        lote.adicionaIngresso(new Ingresso(i, TipoIngresso.MEIA_ENTRADA, true));
			}
		}

		this.lotesIngressos.put(1, lote);
	}
	
	@ParameterizedTest
	@CsvSource({
	    "true, 3350",
	    "false, 3500"
	})
	@DisplayName("Teste que calcula a receita do show com base no tipo de data.")
	void testCalculaReceitaShow(boolean dataEspecial, double receita) {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 1500, 1000, this.lotesIngressos, dataEspecial);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ " + receita));
	}
	
	@ParameterizedTest
	@CsvSource({
	    "3000, 2750, 250, LUCRO",
	    "4000, 2000, 0, ESTAVEL",
	    "4000, 2050, -50, PREJUIZO",
	})
	@DisplayName("Teste que calcula o status do show com base no valor da receita liquida.")
	void testVerificaStatusShow(ArgumentsAccessor arguments) {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", arguments.getDouble(0), arguments.getDouble(1), this.lotesIngressos, false);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ " + arguments.getDouble(2)));
        assertTrue(relatorio.contains("Status Financeiro: " + arguments.getString(3)));
	}
	
}