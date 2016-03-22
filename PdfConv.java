

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfConv {

	public static boolean main(File openfile,File savefile) {
		try {
			 File dir = openfile;
		     File[] filesInDir = dir.listFiles();
		     
		     for(File file:filesInDir) {
		    	 String name = file.getName();
		    	 String oldname = openfile+"\\"+name;
		      PdfReader reader = new PdfReader(oldname);
		      int n = reader.getNumberOfPages();
               String newName=savefile+"\\"+name;
		      PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(newName));

		      int i = 0;

		      PdfContentByte over;
		      BaseFont bf = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
		      while (i < n) {
		        i++;
		        over = stamp.getOverContent(i);
		        over.beginText();
		        over.setFontAndSize(bf, 12);
		        over.setTextMatrix(30, 30);
		       over.moveText(300, 0.5f);
		      //  over.showText(""+ i);
		        over.setFontAndSize(bf,12);
		       over.showTextAligned(Element.ALIGN_BOTTOM,""+i,300,2,0);
		       
		        over.endText();
		      }
		        stamp.close();
		     
		    }
		}catch (Exception de) {
		      de.printStackTrace();
		      
	}
		return true;
		}	

	}

