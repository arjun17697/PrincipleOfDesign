package com.bridgelabz.principleofdesign.service;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {
	@Override
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CsvException {
		return this.getCSVBean(reader, csvClass).iterator();
	}

	@Override
	public List getCSVFileList(Reader reader, Class csvClass) throws CsvException {
		return this.getCSVBean(reader, csvClass).parse();

	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CsvException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			throw new CsvException();
		}
	}
}
