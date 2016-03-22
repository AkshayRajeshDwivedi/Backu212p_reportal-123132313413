package org.reports.Generator.ReportsGenerator;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConfig {
	
	public static String date_folderCreation = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	public static String date = new String("Reportgenerator_Downloads"+date_folderCreation);
	
	 public static final String downloaddirectory = createDownloadDirectory("src\\OutputFiles",date);
	

	public static String createDownloadDirectory(String path,String dirName)
	{
	File theDir = new File(path+"\\"+dirName);
	if (!theDir.exists()) 
	{
	System.out.println("Creating Directory as : " + dirName);
	boolean result = false;

	try{
	theDir.mkdir();
	result = true;
	} 
	catch(Exception se){se.printStackTrace();}    

	if(result) 
	{    
	System.out.println("Directory Successfully Created...");
	}
	}
	else
	{
	System.out.println("Directory already exists...");
	}
	return theDir.getAbsolutePath();
	}

}
