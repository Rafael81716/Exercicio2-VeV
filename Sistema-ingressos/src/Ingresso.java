public class Ingresso {

	private int id;
	private TipoIngresso tipo;
	private boolean statusVendido;
	
	public Ingresso(int id, TipoIngresso tipo, boolean statusVendido) {
		this.id = id;
		this.tipo = tipo;
		this.statusVendido = statusVendido;
	}
	
	public int getId() {
		return this.id;
	}
	
	public TipoIngresso getTipo() {
		return this.tipo;
	}
	
	public boolean getStatus() {
		return this.statusVendido;
	}
	
	public void setStatus(boolean novoStatus) {
		if (novoStatus != this.statusVendido) {
			this.statusVendido = novoStatus;
		}
	}
	
	public double calculaCusto(double custoIngressoNormal) {
		if (tipo == TipoIngresso.NORMAL) {
			return custoIngressoNormal;
		} else if (tipo == TipoIngresso.MEIA_ENTRADA) {
			return custoIngressoNormal / 2;
		} else {
			return custoIngressoNormal * 2;
		}
	}
	
}