import java.io.FileOutputStream ;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;


public class pdfGenerator{

    public static void printContentsToPDF(JTable table1,JTable table2 ,String UID,String indexNo,String name ,String bO5per)
    {
        try{
            Document doc = new Document(PageSize.A4) ;

            PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream("result.pdf")) ;
            doc.open(); 

            Font fontHeading = new Font(Font.FontFamily.TIMES_ROMAN,25,Font.ITALIC | Font.UNDERLINE | Font.BOLD) ;

            Paragraph para1 = new Paragraph() ;
            para1.add("Results YEAR 2021-22") ;
            para1.setAlignment(Element.ALIGN_CENTER);
            para1.setFont(new com.itextpdf.text.Font(fontHeading));
            doc.add(para1) ;

            Paragraph sPara1 = new Paragraph() ;
            sPara1.add("        ") ;
            sPara1.add("        ") ;
            sPara1.add("        ") ;
            sPara1.add("        ") ;
            sPara1.add("        ") ;
            doc.add(sPara1) ;

            Paragraph para2 = new Paragraph() ;
            para2.add("UID : "+UID) ;
            doc.add(para2) ;

            Paragraph para3 = new Paragraph() ;
            para3.add("Index Number : "+indexNo) ;
            doc.add(para3) ;

            Paragraph para4 = new Paragraph() ;
            para4.add("Name : "+name) ;
            doc.add(para4) ;

            Paragraph para5 = new Paragraph() ;
            para5.add("Best Of 5 % ----> "+bO5per) ;
            doc.add(para5) ;

            Paragraph sPara2 = new Paragraph() ;
            sPara2.add("     ") ;
            sPara2.add("     ") ;
            sPara2.add("     ") ;
            sPara2.add("     ") ;
            sPara2.add("     ") ;
            doc.add(sPara2) ;
            
            PdfPTable pdfTable1 = new PdfPTable(table1.getColumnCount()) ;
            //headers
            for(int a=0;a<table1.getColumnCount();a++)
            pdfTable1.addCell(table1.getColumnName(a)) ;

            //table data
            for(int rows=0;rows<table1.getRowCount()-1;rows++){
                for(int cols=0;cols<table1.getColumnCount();cols++){
                    pdfTable1.addCell(table1.getModel().getValueAt(rows,cols).toString()) ;
                }
            }

            doc.add(pdfTable1) ;

            Paragraph sPara3 = new Paragraph() ;
            sPara3.add("\n") ;
            doc.add(sPara3) ;

            Paragraph para6 = new Paragraph() ;
            para6.add("***This is a system generated result and hence requires no signature ") ;
            doc.add(para6) ;

            doc.close();           

            JOptionPane.showMessageDialog(null,"Result Printed Successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}