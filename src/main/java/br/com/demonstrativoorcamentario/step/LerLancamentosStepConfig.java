package br.com.demonstrativoorcamentario.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.demonstrativoorcamentario.domain.DemonstrativoOrcamentario;
import br.com.demonstrativoorcamentario.domain.Lancamento;
import br.com.demonstrativoorcamentario.step.reader.DemonstrativoOrcamentarioReader;

@Configuration
public class LerLancamentosStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step lerLancamentosStep(FlatFileItemReader<Lancamento> lancamentoReader,
			                       ItemWriter<DemonstrativoOrcamentario> demonstrativoOrcamentarioWriter) {
		return stepBuilderFactory
				.get("lerLancamentosStep")
				.<DemonstrativoOrcamentario, DemonstrativoOrcamentario>chunk(1)
				.reader(new DemonstrativoOrcamentarioReader(lancamentoReader))
				.writer(demonstrativoOrcamentarioWriter)
				.build();
	}
}
