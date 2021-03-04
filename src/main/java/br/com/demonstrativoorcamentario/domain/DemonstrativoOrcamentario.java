package br.com.demonstrativoorcamentario.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
public class DemonstrativoOrcamentario {
	
	public static final String LABEL = "---- Demonstrativo orçamentário ----";
	
	@Getter
	private Long codigoDaNaturezaDaDespesa;
	
	@Getter
	private String descricaoDaNaturezaDaDespesa;
	
	@Getter
	private Double valorTotal;
	
	@Getter
	private List<Lancamento> lancamentos = new ArrayList<>();
	
	public DemonstrativoOrcamentario(Long codigoDaNaturezaDaDespesa, String descricaoDaNaturezaDaDespesa){
		this.codigoDaNaturezaDaDespesa = codigoDaNaturezaDaDespesa;
		this.descricaoDaNaturezaDaDespesa = descricaoDaNaturezaDaDespesa;
		this.valorTotal = 0d;
	}
	
	public void adicionarLancamento(Lancamento lancamento) {
		this.lancamentos.add(lancamento);
		this.valorTotal += lancamento.getValor();
	}
}
