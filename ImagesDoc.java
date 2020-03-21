import java.io.*;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

public class ImagesDoc 
{
    public static void main(String[] args) throws IOException, InvalidFormatException 
    {     //Blank Document
        XWPFDocument document= new XWPFDocument(); 
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("createparagraph.docx"));
          
        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run=paragraph.createRun();

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
     }
    }


