package br.com.demonstrativoorcamentario.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Lancamento {
	private Long codigoDaNaturezaDaDespesa;
	private String descricaoDaNaturezaDeDespesa;
	private String descricaoDoItem;
	private LocalDate data;
	private Double valor;
}
