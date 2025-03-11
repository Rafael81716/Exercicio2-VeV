import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;


class LoteJUnitTest {

	@Nested
	class LoteJUnitTestAVL {
		
		@ParameterizedTest
		@CsvSource({
		    "1, -0.01",
		    "2, 0.26",
		})
		@DisplayName("Teste que cria lote com desconto inválido.")
		void testCriaLoteComDescontoInvalido(int id, double desconto) {
			assertThrows(IllegalArgumentException.class, () -> new Lote(id, desconto));
		}
		
		@ParameterizedTest
		@CsvSource({
		    "1, 0",
		    "2, 0.01",
		    "3, 0.13",
		    "4, 0.24",
		    "5, 0.25"
		})
		@DisplayName("Teste que cria lote com desconto válido.")
		void testCriaLoteComDescontoValido(int id, double desconto) {
			Lote lote = new Lote(id, desconto);
				
			assertEquals(lote.getDesconto(), desconto);
		}
	
	}

	
	@Nested
	class LoteJUnitTestTabelaDecisao {
		
		@ParameterizedTest
		@CsvSource({
		    "1, 0, NORMAL, 100, 100",
		    "2, 0.15, NORMAL, 100, 85",
		    "3, 0.15, VIP, 50, 85",
		    "4, 0.15, MEIA_ENTRADA, 200, 100"
		})
		@DisplayName("Teste que calcula o custo do ingresso com base no desconto aplicado.")
		void testCalculaCustoIngressoComDesconto(ArgumentsAccessor arguments) {
			Lote lote = new Lote(arguments.getInteger(0), arguments.getDouble(1));
			lote.adicionaIngresso(new Ingresso(1, arguments.get(2, TipoIngresso.class), false));
			Ingresso ingresso = (Ingresso) lote.getIngressos().toArray()[0];
				
			assertEquals(ingresso.calculaCusto(arguments.getDouble(3)), arguments.getDouble(4));
		}
		
	}
		
}