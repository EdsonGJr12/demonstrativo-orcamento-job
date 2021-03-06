package br.com.demonstrativoorcamentario.step.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.demonstrativoorcamentario.domain.DemonstrativoOrcamentario;
import br.com.demonstrativoorcamentario.domain.Lancamento;

@Component
public class DemonstrativoOrcamentarioReader implements ItemStreamReader<DemonstrativoOrcamentario>, ResourceAwareItemReaderItemStream<DemonstrativoOrcamentario> {

	private JdbcCursorItemReader<Lancamento> jdbcLancamentoReader;
	
	private Lancamento lancamentoAtual;
	
	@Autowired
	public DemonstrativoOrcamentarioReader(JdbcCursorItemReader<Lancamento> jdbcLancamentoReader) {
		this.jdbcLancamentoReader = jdbcLancamentoReader;
	}

	@Override
	public DemonstrativoOrcamentario read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		DemonstrativoOrcamentario demonstrativoOrcamentario = null;
		if(lancamentoAtual == null) {
			lancamentoAtual = jdbcLancamentoReader.read();
		}
		
		if(lancamentoAtual != null) {
			demonstrativoOrcamentario = new DemonstrativoOrcamentario(lancamentoAtual.getCodigoDaNaturezaDaDespesa(), lancamentoAtual.getDescricaoDaNaturezaDeDespesa());
			while (lancamentoAtual != null && demonstrativoOrcamentario.getCodigoDaNaturezaDaDespesa().equals(lancamentoAtual.getCodigoDaNaturezaDaDespesa())) {
				demonstrativoOrcamentario.adicionarLancamento(lancamentoAtual);
				lancamentoAtual = jdbcLancamentoReader.read();
			}
		}
		
		return demonstrativoOrcamentario;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		jdbcLancamentoReader.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		jdbcLancamentoReader.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		jdbcLancamentoReader.close();
	}

	@Override
	public void setResource(Resource resource) {
		//jdbcLancamentoReader.setResource(resource);
	}
}
