import java.time.LocalDate;
import java.util.Map;
import java.util.Random;


public class Show {

	private LocalDate data;
	private String artista;
	private double cache;
	private double totalDespesasInfraestrutura;
	private Map<Integer, Lote> lotesIngressos;
	private boolean dataEspecial;
	
	public Show(LocalDate data, String artista, double cache, double totalDespesasInfraestrutura, Map<Integer, Lote> lotesIngressos, boolean dataEspecial) {
		this.data = data;
		this.artista = artista;
		this.cache = cache;
		this.totalDespesasInfraestrutura = totalDespesasInfraestrutura;
		this.lotesIngressos = lotesIngressos;
		this.dataEspecial = dataEspecial;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	public String getArtista() {
		return this.artista;
	}
	
	public double getCache() {
		return this.cache;
	}
	
	public double getTotalDespesasInfraestrutura() {
		return this.totalDespesasInfraestrutura;
	}
	
	public Map<Integer, Lote> getLotesIngressos() {
		return this.lotesIngressos;
	}
	
	public boolean getDataEspecial() {
		return this.dataEspecial;
	}
	
	 public void distribuiIngressos(int totalIngressos) {
        Random random = new Random();
        int totalIngressosVIP = random.nextInt((int)(totalIngressos * 0.30) - (int)(totalIngressos * 0.20) + 1) + (int)(totalIngressos * 0.20);
        int totalIngressosMeiaEntrada = (int) (totalIngressos * 0.10);
        int totalIngressosNormais = totalIngressos - totalIngressosVIP - totalIngressosMeiaEntrada;

        int totalLotes = this.lotesIngressos.size();
        int ingressosPorLoteVIP = totalIngressosVIP / totalLotes;
        int ingressosPorLoteMeiaEntrada = totalIngressosMeiaEntrada / totalLotes;
        int ingressosPorLoteNormal = totalIngressosNormais / totalLotes;

        for (Lote lote : this.lotesIngressos.values()) {
            for (int i = 0; i < ingressosPorLoteVIP; i++) {
                lote.adicionaIngresso(new Ingresso(i, TipoIngresso.VIP, true));
            }
            for (int i = 0; i < ingressosPorLoteMeiaEntrada; i++) {
                lote.adicionaIngresso(new Ingresso(i, TipoIngresso.MEIA_ENTRADA, true));
            }
            for (int i = 0; i < ingressosPorLoteNormal; i++) {
                lote.adicionaIngresso(new Ingresso(i, TipoIngresso.NORMAL, true));
            }
        }
    }
	
}