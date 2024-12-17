import java.time.LocalDate;
import java.util.Map;


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
	
}