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
	void initIngressos() {
		this.ingressos = new HashSet<>();
		
		this.ingressos.add(new Ingresso(1, TipoIngresso.NORMAL, false));
		this.ingressos.add(new Ingresso(2, TipoIngresso.MEIA_ENTRADA, true));
		this.ingressos.add(new Ingresso(3, TipoIngresso.VIP, false));
	}
	
	@BeforeEach
	void initLotesIngressos() {
		this.lotesIngressos = new HashMap<>();
		
		this.lotesIngressos.put(1, new Lote(1, this.ingressos, 0.25));
		this.lotesIngressos.put(2, new Lote(2, this.ingressos, 0));
		this.lotesIngressos.put(3, new Lote(3, this.ingressos, 0.10));
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

}
