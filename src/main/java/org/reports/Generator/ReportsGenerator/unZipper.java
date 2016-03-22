package org.reports.Generator.ReportsGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;

public class unZipper {
	
	
	 private static final int BUFFER_SIZE = 4096;
	    
	public void unzip(String zipFilePath, String destDirectory) throws IOException {
		
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
      
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
              
                extractFile(zipIn, filePath);
            } 
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
	 public void get() throws IOException
	 { 
	     String date_folderCreation = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		 String Finaldate = new String("FinalPDF"+date_folderCreation);
		 String Unzipdate = new String("FinalPDF"+date_folderCreation);
		 String path="src\\OutputFiles";
		 String absolutePath="C:\\Users\\Akshay Rajesh\\Desktop\\jk";
		 
		 String absolutePath1=TestConfig.createDownloadDirectory(path,Finaldate);
			String unzippedpath=TestConfig.createDownloadDirectory(path,Unzipdate);
		
     File dir = new File(absolutePath);
     File[] filesInDir = dir.listFiles();
     int i = 0;
     for(File file:filesInDir) {
         i++;
         String name = file.getName();
         unZipper unzipper = new unZipper();
	        try {
	        String	zipname=absolutePath+"\\"+name;
	        System.out.println(zipname);
	        unzipper.unzip(zipname,unzippedpath);
	        } catch (Exception ex) {
	            
	            ex.printStackTrace();
	        }
     
    
     File dir1 = new File(unzippedpath);
     File[] filesInDir1 = dir1.listFiles();
    
     for(File file1:filesInDir1) {
         
         String name1 = file1.getName();
       System.out.println(name1);
	       int n=0,inc=0;
	       char c;
	        	while(n!=2)
	        	{
	        		 c=name1.charAt(inc);
	             if(c=='_')
	        		{
	        			n=n+1;
	        			
	        		}
	        inc=inc+1;
	        	}
	        	
	        	
	        	String newName=name1.substring(inc);
	        	String newPath =absolutePath1+ "\\" + newName;
	        	file1.renameTo(new File(newPath));
	           
       }
        
     }
  }
	 public static void main(String[] args) throws IOException
	 {
		 unZipper un=new unZipper();
		 un.get();
	 }
	 
}