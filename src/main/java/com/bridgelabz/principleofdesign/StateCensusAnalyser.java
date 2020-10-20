package com.bridgelabz.principleofdesign;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadCensusData(String censusDataPath) throws AnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(censusDataPath));
			CsvToBeanBuilder<StateCensus> csvToBeanBuilder = new CsvToBeanBuilder<StateCensus>(reader);
			csvToBeanBuilder.withType(StateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCensus> censusIterator = csvToBean.iterator();
			int noOfEntries = 0;
			while (censusIterator.hasNext()) {
				noOfEntries++;
				StateCensus censusData = censusIterator.next();
			}
			return noOfEntries;
		} catch (IOException e) {
			throw new AnalyserException("Invalid file location", AnalyserException.ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}
