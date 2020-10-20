package com.bridgelabz.principleofdesign;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {

	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) 
			throws AnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new AnalyserException(e.getMessage(),
					AnalyserException.ExceptionType.INVALID_FILE_PATH);
		}
	}

}