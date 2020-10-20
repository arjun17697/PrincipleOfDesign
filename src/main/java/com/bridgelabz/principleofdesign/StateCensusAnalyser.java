package com.bridgelabz.principleofdesign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.bridgelabz.principleofdesign.service.CSVBuilderFactory;
import com.bridgelabz.principleofdesign.service.ICSVBuilder;
import com.bridgelabz.principleofdesign.service.AnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class StateCensusAnalyser {

	public int loadCensusData(String censusDataPath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(censusDataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> censusList = null;
			try {
				censusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			} catch (CsvException e) {
				throw new AnalyserException("Invalid Class Type", ExceptionType.INVALID_CLASS_TYPE);
			}
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
				return censusList.size();
			}
		} catch (IOException e) {
			throw new AnalyserException("Invalid file location", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", ExceptionType.INVALID_CLASS_TYPE);
		}
		return 0;
	}

	public int loadCodeData(String codeDataPath) throws AnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(codeDataPath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCode> censusList = null;
			try {
				censusList = csvBuilder.getCSVFileList(reader, StateCode.class);
			} catch (CsvException e) {
				throw new AnalyserException("Invalid Class Type", ExceptionType.INVALID_CLASS_TYPE);
			}
			BufferedReader br = new BufferedReader(new FileReader(codeDataPath));
			String line = "";
			int flag = 0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new AnalyserException("Invalid delimiter For Code Data", ExceptionType.INVALID_DELIMITER);
				if (flag == 0) {
					String[] headers = line.split(",");
					if (!(headers[0].equals("SrNo") && headers[1].equals("State Name") && headers[2].equals("TIN")
							&& headers[3].equals("StateCode")))
						throw new AnalyserException("Invalid header(s) For Code Data", ExceptionType.INVALID_HEADER);
					flag++;
				}
				br.close();
				return censusList.size();
			}
		} catch (IOException e) {
			throw new AnalyserException("Invalid file location", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", ExceptionType.INVALID_CLASS_TYPE);
		}
		return 0;
	}

	private <E> int getCount(Iterator<E> iterator) {
		int noOfEntries = 0;
		while (iterator.hasNext()) {
			noOfEntries++;
			E censusData = iterator.next();
		}
		return noOfEntries;
	}
}
