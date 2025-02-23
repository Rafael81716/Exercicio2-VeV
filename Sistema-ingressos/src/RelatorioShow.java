public class RelatorioShow {

    public String geraRelatorio(Show show, double custoIngressoNormal) {
        int ingressosNormais = 0;
        int ingressosMeiaEntrada = 0;
        int ingressosVIP = 0;
        double receitaBruta = 0;

        for (Lote lote : show.getLotesIngressos().values()) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus()) {
                    double custo = ingresso.calculaCusto(custoIngressoNormal);
                    custo -= custo * lote.getDesconto();
                    receitaBruta += custo;

                    if (ingresso.getTipo() == TipoIngresso.NORMAL) {
                        ingressosNormais++;
                    } else if (ingresso.getTipo() == TipoIngresso.MEIA_ENTRADA) {
                        ingressosMeiaEntrada++;
                    } else if (ingresso.getTipo() == TipoIngresso.VIP) {
                        ingressosVIP++;
                    }
                }
            }
        }

        double despesasInfraestrutura = show.getTotalDespesasInfraestrutura();
        
        if (show.getDataEspecial()) {
            despesasInfraestrutura *= 1.15;
        }

        double receitaLiquida = receitaBruta - despesasInfraestrutura - show.getCache();
        String statusFinanceiro;

        if (receitaLiquida > 0) {
            statusFinanceiro = "LUCRO";
        } else if (receitaLiquida == 0) {
            statusFinanceiro = "ESTAVEL";
        } else {
            statusFinanceiro = "PREJUIZO";
        }

        return "Relatorio do Show:\n" +
                "Artista: " + show.getArtista() + "\n" +
                "Ingressos Vendidos: Normais: " + ingressosNormais + ", Meia-Entrada: " + ingressosMeiaEntrada + ", VIP: " + ingressosVIP + "\n" +
                "Receita Liquida: R$ " + receitaLiquida + "\n" +
                "Status Financeiro: " + statusFinanceiro;
    }
    
}