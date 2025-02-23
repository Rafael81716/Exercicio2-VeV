import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RelatorioShowFunctionalTest {

	private Map<Integer, Lote> lotesIngressos;
	
	@BeforeEach
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
	
	@Test
	void testCalculaReceitaShowComDataEspecial() {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 1500, 1000, this.lotesIngressos, true);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ 3350"));
	}
	
	@Test
	void testCalculaReceitaShowSemDataEspecial() {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 1500, 1000, this.lotesIngressos, false);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ 3500"));
	}
	
	@Test
	void testVerificaStatusShowComReceitaPositiva() {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 3000, 2750, this.lotesIngressos, false);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ 250"));
        assertTrue(relatorio.contains("Status Financeiro: LUCRO"));
	}
	
	@Test
	void testVerificaStatusShowComReceitaZero() {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 4000, 2000, this.lotesIngressos, false);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ 0"));
        assertTrue(relatorio.contains("Status Financeiro: ESTAVEL"));
	}
	
	@Test
	void testVerificaStatusShowComReceitaNegativa() {
		Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 4000, 2050, this.lotesIngressos, false);
        String relatorio = (new RelatorioShow()).geraRelatorio(show, 500);
        
        assertTrue(relatorio.contains("Receita Liquida: R$ -50"));
        assertTrue(relatorio.contains("Status Financeiro: PREJUIZO"));
	}
	
}