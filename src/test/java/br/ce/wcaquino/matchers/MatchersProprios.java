package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {
	
	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher caiNumaSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
	
	public static DataDiferenteDiasMatcher ehHojeComDiferencaDias(Integer qtdDias) {
		return new DataDiferenteDiasMatcher(qtdDias);
	}
	
	public static DataDiferenteDiasMatcher ehHoje() {
		return new DataDiferenteDiasMatcher(0);
	}

}
