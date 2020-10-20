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

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getTin() {
		return tin;
	}

	public void setTin(int tin) {
		this.tin = tin;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}