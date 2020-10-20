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
	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() {
		int noOfEntries = stateCensusAnalyser.loadCensusData(CENSUS_DATA);
		assertEquals(29, noOfEntries);
	}
} 