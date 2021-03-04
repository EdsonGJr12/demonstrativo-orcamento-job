package br.com.demonstrativoorcamentario.step.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.demonstrativoorcamentario.domain.Lancamento;

@Configuration
public class LancamentoReaderConfig {
	
	@Bean
	@StepScope
	public FlatFileItemReader<Lancamento> lancamentoReader(@Value("#{jobParameters['arquivoLancamento']}") Resource arquivoLancamento,
			                                               LineMapper<Lancamento> lineMapper){
		return new FlatFileItemReaderBuilder<Lancamento>()
				.name("lancamentoReader")
				.resource(arquivoLancamento)
				.lineMapper(lineMapper)
				.build();
	}
}
