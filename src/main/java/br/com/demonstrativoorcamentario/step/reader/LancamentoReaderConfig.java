package br.com.demonstrativoorcamentario.step.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
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
	public FlatFileItemReader<Lancamento> lancamentoReader(@Value("#{jobParameters['arquivoLancamento']}") Resource arquivoLancamento){
		return new FlatFileItemReaderBuilder<Lancamento>()
				.name("lancamentoReader")
				.resource(arquivoLancamento)
				.lineMapper((line, lineNumber) -> {
					String[] splitLine = line.split(",");
					return Lancamento.builder()
							.codigoDaNaturezaDaDespesa(Long.parseLong(splitLine[0]))
							.descricaoDaNaturezaDeDespesa(splitLine[1])
							.descricaoDoItem(splitLine[2])
							.data(LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
							.valor(Double.parseDouble(splitLine[4]))
							.build();
				})
				.build();
	}
	
	@Bean
	public JdbcCursorItemReader<Lancamento> jdbcLancamentoReader(DataSource appDataSource){
		return new JdbcCursorItemReaderBuilder<Lancamento>()
				.name("jdbcLancamentoReader")
				.dataSource(appDataSource)
				.sql("select * from lancamento order by codigoNaturezaDespesa")
				.rowMapper((rs, rowNum) -> Lancamento.builder()
						.codigoDaNaturezaDaDespesa(rs.getLong("codigoNaturezaDespesa"))
						.descricaoDaNaturezaDeDespesa(rs.getString("descricaoNaturezaDespesa"))
						.descricaoDoItem(rs.getString("descricaoLancamento"))
						.data(rs.getDate("dataLancamento").toLocalDate())
						.valor(rs.getDouble("ValorLancamento"))
						.build())
				.build();
	}
}
