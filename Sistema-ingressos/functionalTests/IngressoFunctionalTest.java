import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class IngressoFunctionalTest {

	@Test
	void testCalculaCustoIngressoNormal() {
		Ingresso ingresso = new Ingresso(1, TipoIngresso.NORMAL, false);
		double custoIngressoNormal = 300;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), custoIngressoNormal);
	}
	
	@Test
	void testCalculaCustoIngressoVip() {
		Ingresso ingresso = new Ingresso(2, TipoIngresso.VIP, false);
		double custoIngressoNormal = 300;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), (custoIngressoNormal * 2));
	}
	
	@Test
	void testCalculaCustoIngressoMeiaEntrada() {
		Ingresso ingresso = new Ingresso(3, TipoIngresso.MEIA_ENTRADA, false);
		double custoIngressoNormal = 300;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), (custoIngressoNormal / 2));
	}
	
}