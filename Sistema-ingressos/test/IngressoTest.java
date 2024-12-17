import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class IngressoTest {

	@Test
	void testCriaIngressoNormal() {
		int id = 1;
		TipoIngresso tipo = TipoIngresso.NORMAL;
		boolean statusVendido = false;
		Ingresso ingresso = new Ingresso(id, tipo, statusVendido);
		
		assertEquals(ingresso.getId(), id);
		assertEquals(ingresso.getTipo(), tipo);
		assertFalse(ingresso.getStatus());
	}
	
	@Test
	void testCriaIngressoVip() {
		int id = 2;
		TipoIngresso tipo = TipoIngresso.VIP;
		boolean statusVendido = true;
		Ingresso ingresso = new Ingresso(id, tipo, statusVendido);
		
		assertEquals(ingresso.getId(), id);
		assertEquals(ingresso.getTipo(), tipo);
		assertTrue(ingresso.getStatus());
	}
	
	@Test
	void testCriaIngressoMeiaEntrada() {
		int id = 3;
		TipoIngresso tipo = TipoIngresso.MEIA_ENTRADA;
		boolean statusVendido = true;
		Ingresso ingresso = new Ingresso(id, tipo, statusVendido);
		
		assertEquals(ingresso.getId(), id);
		assertEquals(ingresso.getTipo(), tipo);
		assertTrue(ingresso.getStatus());
	}
	
	@Test
	void testMarcaIngressoComoVendido() {
		Ingresso ingresso = new Ingresso(4, TipoIngresso.NORMAL, false);
		
		ingresso.setStatus(true);
		
		assertTrue(ingresso.getStatus());
	}
	
	@Test
	void testMarcaIngressoComoNaoVendido() {
		Ingresso ingresso = new Ingresso(5, TipoIngresso.VIP, true);
		
		ingresso.setStatus(false);
		
		assertFalse(ingresso.getStatus());
	}
	
	@Test
	void testCalculaCustoIngressoNormal() {
		Ingresso ingresso = new Ingresso(6, TipoIngresso.NORMAL, false);
		double custoIngressoNormal = 10.50;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), custoIngressoNormal);
	}
	
	@Test
	void testCalculaCustoIngressoMeiaEntrada() {
		Ingresso ingresso = new Ingresso(7, TipoIngresso.MEIA_ENTRADA, false);
		double custoIngressoNormal = 45.50;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), 22.75);
	}
	
	@Test
	void testCalculaCustoIngressoVip() {
		Ingresso ingresso = new Ingresso(8, TipoIngresso.VIP, true);
		double custoIngressoNormal = 30.90;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), 61.80);
	}

}