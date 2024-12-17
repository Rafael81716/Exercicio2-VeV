import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;


class LoteTest {

private Set<Ingresso> ingressos;
	
	@BeforeEach
	void initIngressos() {
		this.ingressos = new HashSet<>();
		
		this.ingressos.add(new Ingresso(1, TipoIngresso.NORMAL, false));
		this.ingressos.add(new Ingresso(2, TipoIngresso.MEIA_ENTRADA, true));
		this.ingressos.add(new Ingresso(3, TipoIngresso.VIP, false));
	}

	@Test
	void testCriaLoteSemIngressos() {
		int id = 1;
		Set<Ingresso> ingressosVazios = new HashSet<>();
		double desconto = 0.05;
		Lote lote = new Lote(id, ingressosVazios, desconto);
		
		assertEquals(lote.getId(), id);
		assertEquals(lote.getIngressos(), ingressosVazios);
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComIngressos() {
		int id = 2;
		double desconto = 0.05;
		Lote lote = new Lote(id, this.ingressos, desconto);
		
		assertEquals(lote.getId(), id);
		assertEquals(lote.getIngressos(), this.ingressos);
		assertEquals(lote.getDesconto(), desconto);
	}

}