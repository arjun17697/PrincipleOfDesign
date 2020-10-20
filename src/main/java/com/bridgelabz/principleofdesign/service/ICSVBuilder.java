package com.bridgelabz.principleofdesign.service;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public interface ICSVBuilder<E> {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CsvException;

	public List<E> getCSVFileList(Reader reader, Class csvClass) throws CsvException;

}