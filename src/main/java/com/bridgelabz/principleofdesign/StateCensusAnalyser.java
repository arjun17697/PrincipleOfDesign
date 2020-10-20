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
			Iterator<StateCensus> censusIterator = this.getCSVFileIterator(reader, StateCensus.class);
			BufferedReader br = new BufferedReader(new FileReader(censusDataPath));
			String line = "";
			int noOfEntries = 0;
			while (censusIterator.hasNext()) {
				noOfEntries++;
				StateCensus censusData = censusIterator.next();
			}
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
				return noOfEntries;
			}
		} catch (IOException e) {
			throw new AnalyserException("Invalid file location", AnalyserException.ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
		return 0;
	}

	public int loadCodeData(String codeDataPath) throws AnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(codeDataPath));
			Iterator<StateCode> codeIterator = this.getCSVFileIterator(reader, StateCode.class);
			int noOfEntries = 0;
			while (codeIterator.hasNext()) {
				noOfEntries++;
				StateCode codeData = codeIterator.next();
				System.out.println(codeData);
			}
			BufferedReader br = new BufferedReader(new FileReader(codeDataPath));
			String line = "";
			int flag = 0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new AnalyserException("Invalid delimiter For Code Data",
							AnalyserException.ExceptionType.INVALID_DELIMITER);
				if (flag == 0) {
					String[] headers = line.split(",");
					if (!(headers[0].equals("SrNo") && headers[1].equals("State Name") && headers[2].equals("TIN")
							&& headers[3].equals("StateCode")))
						throw new AnalyserException("Invalid header(s) For Code Data",
								AnalyserException.ExceptionType.INVALID_HEADER);
					flag++;
				}
				br.close();
				return noOfEntries;
		} }catch (IOException e) {
			throw new AnalyserException("Invalid file location", AnalyserException.ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new AnalyserException("Incorrect class type", AnalyserException.ExceptionType.INVALID_CLASS_TYPE);
		}
		return 0;
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws AnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.INVALID_FILE_PATH);
		}
	}
}
