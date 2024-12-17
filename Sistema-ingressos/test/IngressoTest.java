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

}
