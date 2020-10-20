package com.bridgelabz.principleofdesign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.principleofdesign.service.AnalyserException;
import com.google.gson.Gson;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class StateCensusTest {
	/**
	 * Rigorous Test :-)
	 */
	private static final String CENSUS_DATA = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusData.csv";
	private static final String CENSUS_DATA_INCOR = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataRANDOM.csv";;
	private static final String CENSUS_DATA_DELIMITER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataDelimiter.csv";;
	private static final String CENSUS_DATA_HEADER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataHeader.csv";;
	private static final String CODE_DATA = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCode.csv";
	private static final String CODE_DATA_INCOR = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCodeiNCOR.csv";
	private static final String CODE_DATA_DELIMITER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCodeDelimiter.csv";
	private static final String CODE_DATA_HEADER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCodeHeader.csv";;

	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFileCheckNoOfEntries() throws AnalyserException {
		int noOfEntries = stateCensusAnalyser.loadCensusData(CENSUS_DATA);
		assertEquals(29, noOfEntries);
	}

	@Test(expected = AnalyserException.class)
	public void givenIncorrectCSVFilePathThrowsException() throws AnalyserException {
		stateCensusAnalyser.loadCensusData(CENSUS_DATA_INCOR);
	}

	@Test
	public void givenIncorrectCSVClassTypeThrowsInvalidClassTypeException() {
		try {
			stateCensusAnalyser.loadCensusData(CENSUS_DATA);
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_CLASS_TYPE, e.type);
		}
	}

	@Test
	public void givenIncorrectDelimiterThrowsCustomException() {
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_DELIMITER));
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}

	@Test
	public void givenIncorrectHeaderThrowsInvalidHeaderException() {
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_HEADER));
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}

	@Test
	public void givenCodeCSVFile_ReturnsCorrectNoOfEntries() throws AnalyserException {
		int noOfEntries = stateCensusAnalyser.loadCodeData(CODE_DATA);
		assertEquals(37, noOfEntries);
	}

	@Test
	public void givenIncorrectCSVCodeFilePath_ThrowsCustomException() {
		try {
			stateCensusAnalyser.loadCodeData(CODE_DATA_INCOR);
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	@Test
	public void givenIncorrectCodeCSVClassTypeThrowsInvalidClassTypeException() {
		try {
			stateCensusAnalyser.loadCodeData(CODE_DATA);
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_CLASS_TYPE, e.type);
		}
	}

	@Test
	public void givenIncorrectStateCodeCSVDelimiter_ThrowsCodeAnalyserExceptionOfTypeInvalidDelimiter() {
		try {
			stateCensusAnalyser.loadCodeData(CODE_DATA_DELIMITER);
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}

	@Test
	public void givenIncorrectCodeHeaderThrowsInvalidHeaderException() {
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CODE_DATA_HEADER));
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}

	@Test
	public void givenIndianCensusDataWhenSortedOnStateShouldReturnSortedResult() throws AnalyserException, IOException {
		String sortedCensusData = stateCensusAnalyser.sortCensusDataByState(CENSUS_DATA);
		StateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, StateCensus[].class);
		Assert.assertEquals("Andhra Pradesh", censusCSV[0].getStateName());
	}

	@Test
	public void givenStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedResult() throws AnalyserException {
		String sortedCensusDataByStateCode = stateCensusAnalyser.sortStateCodeDataByStateCode(CODE_DATA);
		StateCode[] csvStateCode = new Gson().fromJson(sortedCensusDataByStateCode, StateCode[].class);
		assertEquals("AD", csvStateCode[0].getStateCode());
		assertEquals("WB", csvStateCode[csvStateCode.length - 1].getStateCode());
	}

	@Test
	public void givenStateCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedResult() throws AnalyserException {
		String sortedCensusDataByPopulation = stateCensusAnalyser.sortCensusDataByPopulation(CENSUS_DATA);
		StateCensus[] csvStateCensus = new Gson().fromJson(sortedCensusDataByPopulation, StateCensus[].class);
		assertEquals("Uttar Pradesh", csvStateCensus[0].getStateName());
		assertEquals("Sikkim", csvStateCensus[csvStateCensus.length - 1].getStateName());
	}

	@Test
	public void givenStateCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
			throws AnalyserException {
		String sortedCensusDataByPopulationDensity = stateCensusAnalyser.sortCensusDataByPopulationDensity(CENSUS_DATA);
		StateCensus[] csvStateCensus = new Gson().fromJson(sortedCensusDataByPopulationDensity, StateCensus[].class);
		assertEquals("Bihar", csvStateCensus[0].getStateName());
		assertEquals("Arunachal Pradesh", csvStateCensus[csvStateCensus.length - 1].getStateName());
	}

	@Test
	public void givenStateCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedResult() throws AnalyserException {
		String sortedCensusDataByArea = stateCensusAnalyser.sortCensusDataByArea(CENSUS_DATA);
		StateCensus[] csvStateCensus = new Gson().fromJson(sortedCensusDataByArea, StateCensus[].class);
		assertEquals("Rajasthan", csvStateCensus[0].getStateName());
		assertEquals("Goa", csvStateCensus[csvStateCensus.length - 1].getStateName());
	}
}
