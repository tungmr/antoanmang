package com.example.tools;

public class CheckFilename {
	
	public static boolean checkFaultFileName(String fileName) {
		String subFilename = "";
		if (fileName.lastIndexOf(".")>0) {
			 subFilename = fileName.substring(0,fileName.lastIndexOf("."));
		}else {
			subFilename = fileName;
		}
		return subFilename.contains(".") || subFilename.contains("!") || 
				subFilename.contains("@") ||  subFilename.contains("#") ||  
				subFilename.contains("$")  || subFilename.contains("%") || subFilename.contains("&") ;
		
	}
	
	public static void main(String[] args) {
		System.out.println(checkFaultFileName("tungmr.txt.png"));
	}

}
