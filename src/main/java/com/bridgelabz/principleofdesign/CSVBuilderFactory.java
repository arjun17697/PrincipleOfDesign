package com.bridgelabz.principleofdesign;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}

}