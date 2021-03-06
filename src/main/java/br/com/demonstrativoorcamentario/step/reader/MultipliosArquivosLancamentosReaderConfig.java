package br.com.demonstrativoorcamentario.step.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.demonstrativoorcamentario.domain.DemonstrativoOrcamentario;

@Configuration
public class MultipliosArquivosLancamentosReaderConfig {

	@Bean
	@StepScope
	public MultiResourceItemReader<DemonstrativoOrcamentario> multiplosArquivosLancamentosReader(
			@Value("#{jobParameters['arquivosLancamentos']}") Resource[] arquivosDeLancamentos,
			ResourceAwareItemReaderItemStream<DemonstrativoOrcamentario> demonstrativoOrcamentarioReader) {

		return new MultiResourceItemReaderBuilder<DemonstrativoOrcamentario>()
				.name("multipliosArquivosLancamentosReader")
				.resources(arquivosDeLancamentos)
				.delegate(demonstrativoOrcamentarioReader)
				.build();
	}
}
