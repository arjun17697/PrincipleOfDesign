package com.bridgelabz.principleofdesign;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadCensusData(String censusDataPath) {
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
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(new StateCensusAnalyser().loadCensusData(
				"C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusData.csv"));
	}
}
