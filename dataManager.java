import java.io.FileInputStream;
import java.io.InputStream;

import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class dataManager 
{
    private static String dir = "H:\\FILES\\Java Projects\\ResultGenerator\\data\\data.xlsx" ;
    private static XSSFSheet sheet ;
    
    public static String[] doesStudentExist(String UID ,String indexNo){
        connectData() ;
        Iterator rows = sheet.rowIterator() ;

        String dbUID = "" ;
        String dbCentreCode = "" ;
        String dbRoll = "" ;
        String dbIndexNo = "" ;
        XSSFRow row ;

        String dataArr[] = new String[33] ;
        
        while(rows.hasNext()){
            row = (XSSFRow) rows.next() ;
            dbUID = row.getCell(0).getStringCellValue() ;
            dbCentreCode = row.getCell(1).getStringCellValue() ;
            dbRoll = row.getCell(2).getStringCellValue() ;
            dbIndexNo = dbCentreCode+"/"+dbRoll ;
            
            if(dbUID.equals(UID)&&dbIndexNo.equals(indexNo)){
                dataArr[0] = dbUID ;
                dataArr[1] = dbIndexNo ;
                for(int i=3;i<34;i++)
                dataArr[i-1] = row.getCell(i).getStringCellValue() ;
            }
        }
        return dataArr ;
    }

    public static void connectData()
    {         
        try{
            InputStream file = new FileInputStream(dir) ;
            XSSFWorkbook wb = new XSSFWorkbook(file) ;
            sheet = wb.getSheetAt(0) ;
        }
            catch(Exception e){
                e.printStackTrace();
            } 
        }
        
}