import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LoteTest {

private Ingresso ingresso1;
private Ingresso ingresso2;
private Ingresso ingresso3;

	
	@BeforeEach
	void initIngressos() {		
		this.ingresso1 = new Ingresso(1, TipoIngresso.NORMAL, false);
		this.ingresso2 = new Ingresso(2, TipoIngresso.MEIA_ENTRADA, true);
		this.ingresso3 = new Ingresso(3, TipoIngresso.VIP, false);
	}

	@Test
	void testCriaLoteSemIngressos() {
		int id = 1;
		double desconto = 0.05;
		Lote lote = new Lote(id, desconto);
		
		assertEquals(lote.getId(), id);
		assertEquals(lote.getIngressos().size(), 0);
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComIngressos() {
		int id = 2;
		double desconto = 0.05;
		Lote lote = new Lote(id, desconto);
		
		lote.adicionaIngresso(ingresso1);
		lote.adicionaIngresso(ingresso2);
		lote.adicionaIngresso(ingresso3);
		
		assertEquals(lote.getId(), id);
		assertEquals(lote.getIngressos().size(), 3);
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteSemDesconto() {
		int id = 3;
		double desconto = 0;
		Lote lote = new Lote(id, desconto);
		
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoMáximo() {
		int id = 4;
		double desconto = 0.25;
		Lote lote = new Lote(id, desconto);
		
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoNegativo() {
		int id = 5;
		double desconto = -0.1;
		
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, desconto));
	}
	
	@Test
	void testCriaLoteComDescontoAcimaMaximo() {
		int id = 6;
		double desconto = 0.26;
		
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, desconto));
	}

}