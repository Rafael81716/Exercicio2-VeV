import java.util.Set;


public class Lote {

	private int id;
	private Set<Ingresso> ingressos;
	private double desconto;
	
	public Lote(int id, Set<Ingresso> ingressos, double desconto) {
		this.id = id;
		this.ingressos = ingressos;
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
	
	private void adicionaDesconto(double desconto) {
		if (desconto >= 0 && desconto <= 0.25) {
			this.desconto = desconto;
		} else {
			throw new IllegalArgumentException("Valor de desconto inválido.");
		}
	}
	
}