import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class IngressoJUnitTest {

	@ParameterizedTest
	@CsvSource({
	    "1, NORMAL, 300",
	    "2, VIP, 600",
	    "3, MEIA_ENTRADA, 150"
	})
	@DisplayName("Teste que calcula o custo de um ingresso com base no seu tipo.")
	void testCalculaCustoIngresso(int id, TipoIngresso tipoIngresso, int custo) {
		Ingresso ingresso = new Ingresso(id, tipoIngresso, false);
		double custoIngressoNormal = 300;
		
		assertEquals(ingresso.calculaCusto(custoIngressoNormal), custo);

	}
	
}