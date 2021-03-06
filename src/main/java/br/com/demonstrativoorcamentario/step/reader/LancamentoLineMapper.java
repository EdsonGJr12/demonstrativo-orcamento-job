package br.com.demonstrativoorcamentario.step.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

import br.com.demonstrativoorcamentario.domain.Lancamento;

@Component
public class LancamentoLineMapper implements LineMapper<Lancamento>{

	private static final String CARACTERE_DELIMITADOR = ",";

	@Override
	public Lancamento mapLine(String line, int lineNumber) throws Exception {
		String[] splitLine = line.split(CARACTERE_DELIMITADOR);
		Lancamento lancamento = new Lancamento();
		lancamento.setCodigoDaNaturezaDaDespesa(Long.parseLong(splitLine[0]));
		lancamento.setDescricaoDaNaturezaDeDespesa(splitLine[1]);
		lancamento.setDescricaoDoItem(splitLine[2]);
		lancamento.setData(LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		lancamento.setValor(Double.parseDouble(splitLine[4]));
		return lancamento;
	}
	
}
