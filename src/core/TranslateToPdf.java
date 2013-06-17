package core;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JPanel;


import com.itextpdf.awt.PdfGraphics2D;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
public class TranslateToPdf {
    @SuppressWarnings("deprecation")
	public static void Pdf(ArrayList<PaintArea> intoImage,String mOutputPdfFileName,float w,float h) {//傳入ArrayList和File
 
        Document doc = new Document(new Rectangle(w-159, h),10, 10, 10, 10);	//文件規格 a4大小 	*rotate為反轉
       // float width = PageSize.A4.getWidth();
        //float height = PageSize.A4.getHeight() / 2;

        try 
	        {
	        	//Object stepObjet[] = intoImage.toArray();							//轉換ArrayList
	        	FileOutputStream Fop = new FileOutputStream(mOutputPdfFileName);	//檔案的輸出
	        	PdfWriter writer = PdfWriter.getInstance(doc, Fop);		//PDF 輸出writer
	        	doc.open();			
	        	PdfContentByte contentByte = writer.getDirectContent();
	        	//System.out.println(intoImage.size());
	        	for(int i = 0; i <intoImage.size();i++)
	        		{
	        	doc.newPage();     										//新增一頁PDF
	        	PaintArea stepPanel = intoImage.get(i);				//Object轉換JPanel
	        	float panelWidth = stepPanel.getWidth();				//抓取Panel大小
	        	float panelHeight = stepPanel.getHeight();				//抓取Panel大小
	        	PdfTemplate template = contentByte.createTemplate(panelWidth,panelHeight);//新增  PDF template容器
	            Graphics2D g2 = new PdfGraphics2D(template,panelWidth,panelHeight);//容器上進行畫圖
	        	g2.scale(0.8, 1.0);
	            stepPanel.print(g2);//將Panel圖像輸出
	        	
	        	g2.dispose();											//Graphics2D部署
	            contentByte.addTemplate(template,50,50);				//在content上加上	template
	        
	            
	        		}
	        }
        catch(Exception e) 
	        {
		            e.printStackTrace();
		    }
	    finally
	    	{
	         if(doc.isOpen())
		         {
		            	doc.close();
		         }
	        }
									
    }

}