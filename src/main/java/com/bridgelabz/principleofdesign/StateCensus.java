package com.bridgelabz.principleofdesign;



import com.opencsv.bean.CsvBindByName;

public class StateCensus {

	@CsvBindByName (column = "State")
	private String stateName;

	@CsvBindByName (column = "Population")
	private String population;

	@CsvBindByName (column = "AreaInSqKm")
	private String area;

	@CsvBindByName (column = "DensityPerSqKm")
	private String density;

	@Override
	public String toString() {
		return "IndiaStateCensus [stateName=" + stateName + ", population=" + population + ", area=" + area
				+ ", density=" + density + "]";
	}

} 