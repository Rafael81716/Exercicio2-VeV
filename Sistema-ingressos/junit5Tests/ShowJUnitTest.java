import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class ShowJUnitTest {

	@Nested
	class ShowJUnitTestAVL {
		
		private Map<Integer, Lote> lotesIngressos;
		
		@BeforeEach
		@DisplayName("Inicialização do lote de ingressos a ser testado.")
		void initLoteIngressos() {
			this.lotesIngressos = new HashMap<>();
			
			this.lotesIngressos.put(1, new Lote(1, 0.25));
		}
		
		@Test
		@DisplayName("Teste que realiza a distribuição de ingressos do tipo VIP.")
		void testDistribuiIngressosVip() {
			Show show = new Show(LocalDate.of(2025, 02, 17), "Caetano Veloso", 500000, 15000, lotesIngressos, true);
			int numeroIngressos = 500;
			int totalIngressosVip = 0;
			int totalIngressosNormal = 0;
			
			show.distribuiIngressos(numeroIngressos);
			
			for (Lote lote : this.lotesIngressos.values()) {
				for (Ingresso ingresso : lote.getIngressos()) {
					if (ingresso.getTipo() == TipoIngresso.NORMAL) {
						totalIngressosNormal += 1;
					} else if (ingresso.getTipo() == TipoIngresso.VIP) {
						totalIngressosVip += 1;
					}
				}
			}
			
			assertTrue(totalIngressosVip >= (0.2 * numeroIngressos) && totalIngressosVip <= (0.3 * numeroIngressos));
	        assertTrue(totalIngressosNormal == (numeroIngressos - totalIngressosVip - (0.1 * numeroIngressos)));
		}
		
		@Test
		@DisplayName("Teste que realiza a distribuição de ingressos do tipo MEIA_ENTRADA.")
		void testDistribuiIngressosMeiaEntrada() {
			Show show = new Show(LocalDate.of(2024, 03, 03), "Ariana Grande", 600000, 40000, lotesIngressos, true);
			int numeroIngressos = 1500;
			int totalIngressosMeiaEntrada = 0;
			
			show.distribuiIngressos(numeroIngressos);
			
			for (Lote lote : this.lotesIngressos.values()) {
				for (Ingresso ingresso : lote.getIngressos()) {
					if (ingresso.getTipo() == TipoIngresso.MEIA_ENTRADA) {
						totalIngressosMeiaEntrada += 1;
					}
				}
			}
			
			assertTrue(totalIngressosMeiaEntrada == (0.1 * numeroIngressos));
		}
	}
	
	
	@Nested
	class ShowJUnitTestParticaoEquivalencia {
		
		private Map<Integer, Lote> lotesIngressos;
		
		@BeforeEach
		@DisplayName("Inicialização do lote de ingressos a ser testado.")
		void initLoteIngressos() {
			this.lotesIngressos = new HashMap<>();
			
			this.lotesIngressos.put(1, new Lote(1, 0.25));
		}
		
		@Test
		@DisplayName("Teste que realiza a distribuição de todos os tipos de ingresso.")
		void testDistribuiIngressos() {
			Show show = new Show(LocalDate.of(2025, 01, 10), "Djavan", 700000, 20000, lotesIngressos, true);
			int numeroIngressos = 3000;
			int totalIngressosVip = 0;
			int totalIngressosNormal = 0;
			int totalIngressosMeiaEntrada = 0;
			
			show.distribuiIngressos(numeroIngressos);
			
			for (Lote lote : this.lotesIngressos.values()) {
				for (Ingresso ingresso : lote.getIngressos()) {
					if (ingresso.getTipo() == TipoIngresso.NORMAL) {
						totalIngressosNormal += 1;
					} else if (ingresso.getTipo() == TipoIngresso.VIP) {
						totalIngressosVip += 1;
					} else {
						totalIngressosMeiaEntrada += 1;
					}
				}
			}
			
			assertTrue(totalIngressosVip >= (0.2 * numeroIngressos) && totalIngressosVip <= (0.3 * numeroIngressos));
			assertTrue(totalIngressosMeiaEntrada == (0.1 * numeroIngressos));
	        assertTrue(totalIngressosNormal >= (0.6 * numeroIngressos) && totalIngressosVip <= (0.7 * numeroIngressos));
		}
	}
	
}