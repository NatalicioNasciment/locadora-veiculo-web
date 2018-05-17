package com.algaworks.curso.jpa2.util;

import java.util.Calendar;
import java.util.Date;

public class LibraryUtil {
	
	public static Calendar formatadorData(Date data, Integer hora, Integer minuto, Integer segundo) {
		Calendar dataFormatada = Calendar.getInstance();
		dataFormatada.setTime(data);
		dataFormatada.set(Calendar.HOUR_OF_DAY, hora);
		dataFormatada.set(Calendar.MINUTE, minuto);
		dataFormatada.set(Calendar.SECOND, segundo);
		return dataFormatada;
	}

}
