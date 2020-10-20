package com.bridgelabz.principleofdesign;
import com.opencsv.bean.CsvBindByName;
public class StateCode {
	@CsvBindByName(column = "SrNo")
	private int serialNo;

	@CsvBindByName(column = "State Name")
	private String stateName;

	@CsvBindByName(column = "TIN")
	private int tin;

	@CsvBindByName(column = "StateCode")
	private String stateCode;

	@Override
	public String toString() {
		return "StateCode [serialNo=" + serialNo + ", stateName=" + stateName + ", tin=" + tin + ", stateCode="
				+ stateCode + "]";
	}
} 