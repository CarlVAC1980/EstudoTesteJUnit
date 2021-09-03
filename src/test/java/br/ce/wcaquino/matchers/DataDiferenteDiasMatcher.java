package br.ce.wcaquino.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DataDiferenteDiasMatcher extends TypeSafeMatcher<Date> {

	private Integer qtdDias;
	
	public DataDiferenteDiasMatcher(Integer qtdDias) {
		this.qtdDias = qtdDias;
	}
	
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean matchesSafely(Date data) {
		return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qtdDias));
	}

}
