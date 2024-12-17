import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


class RelatorioShowTest {

    @Test
    public void testGeraRelatorio() {
        Map<Integer, Lote> lotes = new HashMap<>();
        Lote lote1 = new Lote(1, 0.1);
        Lote lote2 = new Lote(2, 0.2);

        lote1.adicionaIngresso(new Ingresso(1, TipoIngresso.NORMAL, true));
        lote1.adicionaIngresso(new Ingresso(2, TipoIngresso.VIP, true));
        lote2.adicionaIngresso(new Ingresso(3, TipoIngresso.MEIA_ENTRADA, true));
        lote2.adicionaIngresso(new Ingresso(4, TipoIngresso.NORMAL, false));

        lotes.put(1, lote1);
        lotes.put(2, lote2);

        Show show = new Show(LocalDate.now(), "Artista Teste", 5000, 3000, lotes, true);
        String relatorio = RelatorioShow.geraRelatorio(show, 100.0);

        assertTrue(relatorio.contains("Artista: Artista Teste"));
        assertTrue(relatorio.contains("Normais: 1"));
        assertTrue(relatorio.contains("Meia-Entrada: 1"));
        assertTrue(relatorio.contains("VIP: 1"));
        assertTrue(relatorio.contains("Status Financeiro: PREJUÍZO"));
    }

}