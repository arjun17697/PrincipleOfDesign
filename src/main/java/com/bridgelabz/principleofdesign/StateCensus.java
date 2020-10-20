package com.bridgelabz.principleofdesign;



import com.opencsv.bean.CsvBindByName;

public class StateCensus {

	@CsvBindByName (column = "State")
	private String stateName;

	@CsvBindByName (column = "Population")
	private long population;

	@CsvBindByName (column = "AreaInSqKm")
	private int area;

	@CsvBindByName (column = "DensityPerSqKm")
	private int density;

	@Override
	public String toString() {
		return "IndiaStateCensus [stateName=" + stateName + ", population=" + population + ", area=" + area
				+ ", density=" + density + "]";
	}

} 