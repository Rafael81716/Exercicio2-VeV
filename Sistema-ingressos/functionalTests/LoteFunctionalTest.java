import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


class LoteFunctionalTest {

	@Test
	void testCriaLoteComDescontoNegativo() {
		int id = 1;
		double desconto = -0.01;
		
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, desconto));
	}
	
	@Test
	void testCriaLoteSemDesconto() {
		int id = 2;
		double desconto = 0;
		Lote lote = new Lote(id, desconto);
			
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoMinimo() {
		int id = 3;
		double desconto = 0.01;
		Lote lote = new Lote(id, desconto);
			
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoQualquerPermitido() {
		int id = 4;
		double desconto = 0.13;
		Lote lote = new Lote(id, desconto);
			
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoAbaixoMaximo() {
		int id = 5;
		double desconto = 0.24;
		Lote lote = new Lote(id, desconto);
			
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoMaximo() {
		int id = 6;
		double desconto = 0.25;
		Lote lote = new Lote(id, desconto);
			
		assertEquals(lote.getDesconto(), desconto);
	}
	
	@Test
	void testCriaLoteComDescontoAcimaMaximo() {
		int id = 7;
		double desconto = 0.26;
			
		assertThrows(IllegalArgumentException.class, () -> new Lote(id, desconto));
	}
	
	@Test
	void testCalculaCustoIngressoSemDesconto() {
		Lote lote = new Lote(8, 0);
		lote.adicionaIngresso(new Ingresso(1, TipoIngresso.NORMAL, false));
		Ingresso ingresso = (Ingresso) lote.getIngressos().toArray()[0];
		double custoIngressoNormal = 100;
			
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), custoIngressoNormal);
	}
	
	@Test
	void testCalculaCustoIngressoNormalComDesconto() {
		Lote lote = new Lote(9, 0.15);
		lote.adicionaIngresso(new Ingresso(2, TipoIngresso.NORMAL, false));
		Ingresso ingresso = (Ingresso) lote.getIngressos().toArray()[0];
		double custoIngressoNormal = 100;
			
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), 85);
	}
	
	@Test
	void testCalculaCustoIngressoVipComDesconto() {
		Lote lote = new Lote(10, 0.15);
		lote.adicionaIngresso(new Ingresso(3, TipoIngresso.VIP, false));
		Ingresso ingresso = (Ingresso) lote.getIngressos().toArray()[0];
		double custoIngressoNormal = 50;
			
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), 85);
	}
	
	@Test
	void testCalculaCustoIngressoMeiaEntradaComDesconto() {
		Lote lote = new Lote(11, 0.15);
		lote.adicionaIngresso(new Ingresso(4, TipoIngresso.MEIA_ENTRADA, false));
		Ingresso ingresso = (Ingresso) lote.getIngressos().toArray()[0];
		double custoIngressoNormal = 200;
			
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), 100);
	}
	
}