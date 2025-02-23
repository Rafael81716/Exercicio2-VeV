import java.util.Set;
import java.util.HashSet;


public class Lote {

	private int id;
	private Set<Ingresso> ingressos;
	private double desconto;
	
	public Lote(int id, double desconto) {
		this.id = id;
		this.ingressos = new HashSet<>();
		this.adicionaDesconto(desconto);
	}
	
	public int getId() {
		return this.id;
	}
	
	public Set<Ingresso> getIngressos() {
		return this.ingressos;
	}
	
	public double getDesconto() {
		return this.desconto;
	}
	
	public void adicionaIngresso(Ingresso ingresso) {
		this.ingressos.add(ingresso);
	}
	
	private void adicionaDesconto(double desconto) {
		if (desconto >= 0 && desconto <= 0.25) {
			this.desconto = desconto;
		} else {
			throw new IllegalArgumentException("Valor de desconto invalido.");
		}
	}
	
}