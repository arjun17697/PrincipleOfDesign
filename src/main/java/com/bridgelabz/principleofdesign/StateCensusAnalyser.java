package com.bridgelabz.principleofdesign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.bridgelabz.principleofdesign.AnalyserException.ExceptionType;
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
			BufferedReader br = new BufferedReader(new FileReader(censusDataPath));
			String line = "";
			int flag = 0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new AnalyserException("Invalid delimiter", ExceptionType.INVALID_DELIMITER);
				if (flag == 0) {
					String[] headers = line.split(",");
					if (!(headers[0].equals("State") && headers[1].equals("Population")
							&& headers[2].equals("AreaInSqKm") && headers[3].equals("DensityPerSqKm")))
						throw new AnalyserException("Invalid headers", ExceptionType.INVALID_HEADER);
					flag++;
				}
				br.close();
				Iterator<StateCensus> censusIterator = csvToBean.iterator();
				int noOfEntries = 0;
				while (censusIterator.hasNext()) {
					noOfEntries++;
					StateCensus censusData = censusIterator.next();
				}
				return noOfEntries;
			}
		} catch (IOException e) {
			throw new AnalyserException("Invalid file location", AnalyserException.ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
		return 0;
	}
}
