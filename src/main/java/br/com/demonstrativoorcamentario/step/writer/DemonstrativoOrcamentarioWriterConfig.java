package br.com.demonstrativoorcamentario.step.writer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.demonstrativoorcamentario.domain.DemonstrativoOrcamentario;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {
	
	@Bean
	public ItemWriter<DemonstrativoOrcamentario> demonstrativoOrcamentarioWriter(){
		DecimalFormat moneyFormat = getMoneyFormat();
		
		return demonstrativos -> {
			demonstrativos.forEach(demonstrativo -> {
				System.out.println(DemonstrativoOrcamentario.LABEL);
				System.out.println("[" + demonstrativo.getCodigoDaNaturezaDaDespesa() + "]" + " " + demonstrativo.getDescricaoDaNaturezaDaDespesa() + " - " + moneyFormat.format(demonstrativo.getValorTotal()));
				demonstrativo.getLancamentos().forEach(lancamento -> 
					System.out.println("        [" + lancamento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "] " + lancamento.getDescricaoDoItem() + " - " + moneyFormat.format(lancamento.getValor()))
				);
			});
		};
	}

	private DecimalFormat getMoneyFormat() {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "Brazil"));
		return new DecimalFormat("R$ ###,###,###,##0.00", dfs);
	}
}
