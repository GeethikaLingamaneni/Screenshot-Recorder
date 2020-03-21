import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sql.rowset.spi.XmlWriter;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.jface.text.IDocument;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ScreenDoc
{
	static XWPFRun run=null;
	static XWPFParagraph para=null;
	static XWPFDocument doc= new XWPFDocument();;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

    public void robo(String Username) throws Exception
    {
        Calendar now = Calendar.getInstance();
        String locString=null;
        File file=new File("D://Test/doc/"+Username+".docx");
        if(!file.exists()){
       file.getParentFile().mkdirs();
        file.createNewFile();	
        }
        Robot robot = new Robot();
      // FileInputStream fio=new FileInputStream("F://Basic//DevScreenShot//docs/Screendoc.docx");
       
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File(file+formatter.format(now.getTime())+".jpg"));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenShot, "jpg", baos);
        baos.flush();
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
        para= doc.createParagraph();    
        run = para.createRun();
        
        run.addBreak();
        run.addPicture(bis, XWPFDocument.PICTURE_TYPE_JPEG, "image file", Units.toEMU(500), Units.toEMU(500));
       // run.addPicture(bis, XWPFDocument.PICTURE_TYPE_JPEG, "image file12", Units.toEMU(500), Units.toEMU(500));
        // 200x200 pixels
        bis.close();
        // write word doc to file
       // FileOutputStream fos = new FileOutputStream("WordDocWithImage.docx");
        FileOutputStream fos = new FileOutputStream("D://Test/doc/"+Username+".docx");
        doc.write(fos);
        fos.close(); 
       // fio.close();
        
    }

    /*public static void main(String[] args) throws Exception
    {
    	//create word doc
               // create para and run
        ScreenDoc s2i = new ScreenDoc();
        while(1==1)
        {
            s2i.robo();
            Thread.sleep(10000);
        }*/
    }
'