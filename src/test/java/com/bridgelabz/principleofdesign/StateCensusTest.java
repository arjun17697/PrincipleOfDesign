package com.bridgelabz.principleofdesign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class StateCensusTest 
{
    /**
     * Rigorous Test :-)
     */
	private static final String CENSUS_DATA = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusData.csv";
	private static final String CENSUS_DATA_INCOR = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataRANDOM.csv";;
	private static final String CENSUS_DATA_DELIMITER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataDelimiter.csv";;
	private static final String CENSUS_DATA_HEADER = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCensusDataHeader.csv";;
	private static final String CODE_DATA = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCode.csv";
	private static final String CODE_DATA_INCOR = "C:\\Users\\HP LAP\\Desktop\\BridgeLabz\\SoftwareDesignPrinciples\\designprinciples\\src\\Resources\\IndiaStateCodeiNCOR.csv";;
	
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
	public void givenIncorrectDelimiterThrowsCustomException(){
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_DELIMITER));
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}
	@Test
	public void givenIncorrectHeaderThrowsInvalidHeaderException(){
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
	public void givenIncorrectCSVCodeFilePath_ThrowsCustomException(){
		try {
			stateCensusAnalyser.loadCensusData(CODE_DATA_INCOR);
		} catch (AnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(AnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
} 