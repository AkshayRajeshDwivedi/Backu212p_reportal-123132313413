package org.reports.Generator.ReportsGenerator;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFconverter {

  public static void main(String[] args) {
    try {
      PdfReader pdfReader = new PdfReader("HelloWorld.pdf");

      PdfStamper pdfStamper = new PdfStamper(pdfReader,
            new FileOutputStream("HelloWorld-Stamped.pdf"));

      Image image = Image.getInstance("watermark.png");

      for(int i=1; i<= pdfReader.getNumberOfPages(); i++){

          PdfContentByte content = pdfStamper.getOverContent(i);

          image.setAbsolutePosition(100,2);

          content.beginText();
      }

      pdfStamper.close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }
}
