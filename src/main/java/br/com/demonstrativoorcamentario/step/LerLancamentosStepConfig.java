package br.com.demonstrativoorcamentario.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.demonstrativoorcamentario.domain.DemonstrativoOrcamentario;

@Configuration
public class LerLancamentosStepConfig {
	
	@Bean
	public Step lerLancamentosStep(StepBuilderFactory stepBuilderFactory,
								   ItemStreamReader<DemonstrativoOrcamentario> demonstrativoOrcamentarioReader,
			                       ItemWriter<DemonstrativoOrcamentario> demonstrativoOrcamentarioWriter) {
		return stepBuilderFactory
				.get("lerLancamentosStep")
				.<DemonstrativoOrcamentario, DemonstrativoOrcamentario>chunk(1)
				.reader(demonstrativoOrcamentarioReader)
				.writer(demonstrativoOrcamentarioWriter)
				.build();
	}
}
