import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;


class ShowTest {
	
	private Set<Ingresso> ingressos;
	private Map<Integer, Lote> lotesIngressos;
	
	@BeforeEach
	void initLotesIngressos() {
		this.lotesIngressos = new HashMap<>();
		
		this.lotesIngressos.put(1, new Lote(1, 0.25));
		this.lotesIngressos.put(2, new Lote(2, 0));
		this.lotesIngressos.put(3, new Lote(3, 0.10));
	}

	@Test
	void testCriaShow() {
		LocalDate data = LocalDate.of(2024, 12, 16);
		String artista = "Djavan";
		double cache = 100000;
		double totalDespesasInfraestrutura = 50000;
		boolean dataEspecial = true;
		Show show = new Show(data, artista, cache, totalDespesasInfraestrutura, lotesIngressos, dataEspecial);
		
		assertEquals(show.getData(), data);
		assertEquals(show.getArtista(), artista);
		assertEquals(show.getCache(), cache);
		assertEquals(show.getTotalDespesasInfraestrutura(), totalDespesasInfraestrutura);
		assertEquals(show.getLotesIngressos(), this.lotesIngressos);
		assertTrue(show.getDataEspecial());
	}
	
	@Test
	void testDistribuiIngressos() {
		Show show = new Show(LocalDate.of(2024, 12, 14), "Alceu Valença", 900000, 40000, lotesIngressos, true);
		int numeroIngressos = 100;
		int totalIngressosVip = 0;
		int totalIngressosMeiaEntrada = 0;
		int totalIngressosNormal = 0;
		
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
		
		assertTrue(totalIngressosVip >= 20 && totalIngressosVip <= 30);
        assertTrue(totalIngressosMeiaEntrada == 10);
        assertTrue(totalIngressosNormal >= 60 && totalIngressosNormal <= 70);
	}

}