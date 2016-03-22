package org.reports.Generator.ReportsGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class jkz {
	public static void main(String[] args) throws IOException
	{
		
	
	//String absolutePath1="src\\main\\java\\org\\reports\\Generator\\ReportsGenerator\\Final.xls";
	
	String absolutePath="C:\\Users\\Akshay Rajesh\\Desktop\\allPDF";
	File dir1 = new File(absolutePath);
	File[] filesInDir1 = dir1.listFiles();
	// File dir2 = new File(absolutePath1);
	//File[] filesInDir2 = dir2.listFiles();
	
/*    for(File file1:filesInDir1) {
        int cw=0;
        String name1 = file1.getName();
      	
	        	int iend = name1.indexOf(".");
	        	String userName=name1.substring(0,iend);
	        	String newName= userName.replace('"','_' );
	        //	System.out.println(newName);
	        	for(File file2:filesInDir2) {
	        		 String name2 = file2.getName();
	        		 int iend1 = name2.indexOf(".");
	 	        	String newName2=name2.substring(0,iend1);
	 	        //	System.out.println(newName2);
	        		 if(newName.equals(newName2))
	        		 {
	        			 cw=1;
	        	}
	        		 
	        	}
	        	if(cw!=1)
       			 System.out.println(newName);
	        	
}*/
	List<String> getNames=ExtractExcel.readExcel();
    for(String g:getNames)
    {
    	int cw=0,check=0;
    	  String g2=g.replace("-","_");
    
	for(File file1:filesInDir1) {
        
        String name1 = file1.getName();
          if(name1.contains(g2))
	        	{
	        		check=1;
	        	  	
	        		break;
	        	}
	}
	        	if(check!=1)
	        	{
	       		System.out.println(g2);
	        	}
	        		
	        
	      
	
}
}
}