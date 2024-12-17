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
	
	@Test
	void testCriaLoteSemDesconto() {
		int id = 3;
		double desconto = 0;
		Lote lote = new Lote(id, this.ingressos, desconto);
		
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoMáximo() {
		int id = 4;
		double desconto = 0.25;
		Lote lote = new Lote(id, this.ingressos, desconto);
		
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoNegativo() {
		int id = 5;
		double desconto = -0.1;
		
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, this.ingressos, desconto));
	}
	
	@Test
	void testCriaLoteComDescontoAcimaMaximo() {
		int id = 6;
		double desconto = 0.26;
		
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, this.ingressos, desconto));
	}

}