import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel ;
import javax.swing.JOptionPane;
import javax.swing.JTextField ;

import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JButton ;

public class GUI implements ActionListener
{
    private static JFrame nFrame ;
    private static JPanel nPanel ;
    private static JTextField uidField ;
    private static JTextField indexNoField ;
    private static JButton submitBtn ;

    private static JFrame dFrame ;
    private static JButton printBtn ;
    private static JButton closeBtn ;
    private static JTable table1 ;
    private static JTable table2 ;

    private static String UID ,indexNo ,name ,bO5per ;

    public static void main(String[] args){
        launchFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource() ;

        if(obj==submitBtn){
            performCheck() ;
        }

        if(obj==closeBtn){
            dFrame.dispose();
            nFrame.dispose() ;
            launchFrame();
        }

        if(obj==printBtn){
            pdfGenerator.printContentsToPDF(table1,table2,UID,indexNo,name,bO5per) ;
        }
    }


    public static void launchFrame(){
        nFrame = new JFrame("Student Result Viewer") ;
        nFrame.setSize(300,180) ;
        nFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nPanel = new JPanel() ;
        nPanel.setLayout(null) ;
        nFrame.add(nPanel) ;

        JLabel lbl1 = new JLabel("UID : ") ;
        lbl1.setBounds(20,15,100,30) ;
        lbl1.setFont(new Font("Times New Roman",Font.BOLD,15)) ;
        nPanel.add(lbl1) ;

        uidField = new JTextField() ;
        uidField.setBounds(130,15,120,30) ;
        uidField.setFont(new Font("Times New Roman",Font.PLAIN,15)) ;
        nPanel.add(uidField) ;

        JLabel lbl2 = new JLabel("Index Number : ") ;
        lbl2.setBounds(20,50,110,30) ;
        lbl2.setFont(new Font("Times New Roman",Font.BOLD,15)) ;
        nPanel.add(lbl2) ;

        indexNoField = new JTextField() ;
        indexNoField.setBounds(130,50,120,30) ;
        indexNoField.setFont(new Font("Times New Roman",Font.PLAIN,15)) ;
        nPanel.add(indexNoField) ;

        submitBtn = new JButton("View Result") ;
        submitBtn.setBounds(70,95,140,30);
        submitBtn.setFont(new Font("Arial",Font.CENTER_BASELINE,15)) ;
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(new GUI());
        nPanel.add(submitBtn) ;

        nFrame.setVisible(true);
    }    

    public static void performCheck(){
        String UID = uidField.getText().toString() ;
        String indexNo = indexNoField.getText().toString() ;

        String[] dataArr = dataManager.doesStudentExist(UID, indexNo) ;
        if(dataArr[0]==null){
            JOptionPane.showMessageDialog(null,"Invalid Student Details", "Message",JOptionPane.ERROR_MESSAGE);
        }

        else{
            displayResult(dataArr) ;
        }
    }

    public static void displayResult(String[] arr)
    {
        UID = arr[0] ;
        indexNo = arr[1] ;
        name = arr[2] ;

        String[] firstSem = new String[10] ;
        for(int i=3,j=0;i<13;i++,j++)
        firstSem[j] = arr[i] ;

        String data[][] = {
                            {"English Language",    arr[3],     arr[13],    arr[23],    Integer.toString((Integer.parseInt(arr[3])  +       Integer.parseInt(arr[13])   +   Integer.parseInt(arr[23])))},
                            {"English Literature",  arr[4],     arr[14],    arr[24],    Integer.toString((Integer.parseInt(arr[4])  +       Integer.parseInt(arr[14])   +   Integer.parseInt(arr[24])))},
                            {"Second Langauge",     arr[5],     arr[15],    arr[25],    Integer.toString((Integer.parseInt(arr[5])  +       Integer.parseInt(arr[15])   +   Integer.parseInt(arr[25])))},
                            {"Mathematcis",         arr[6],     arr[16],    arr[26],    Integer.toString((Integer.parseInt(arr[6])  +       Integer.parseInt(arr[16])   +   Integer.parseInt(arr[26])))},
                            {"Physics",             arr[7],     arr[17],    arr[27],    Integer.toString((Integer.parseInt(arr[7])  +       Integer.parseInt(arr[17])   +   Integer.parseInt(arr[27])))},
                            {"Chemistry",           arr[8],     arr[18],    arr[28],    Integer.toString((Integer.parseInt(arr[8])  +       Integer.parseInt(arr[18])   +   Integer.parseInt(arr[28])))},
                            {"Biology",             arr[9],     arr[19],    arr[29],    Integer.toString((Integer.parseInt(arr[9])  +       Integer.parseInt(arr[19])   +   Integer.parseInt(arr[29])))},
                            {"History & Civics",    arr[10],    arr[20],    arr[30],    Integer.toString((Integer.parseInt(arr[10])  +      Integer.parseInt(arr[20])   +   Integer.parseInt(arr[30])))},
                            {"Geography",           arr[11],    arr[21],    arr[31],    Integer.toString((Integer.parseInt(arr[11])  +      Integer.parseInt(arr[21])   +   Integer.parseInt(arr[31])))},
                            {"Computer/Economics",  arr[12],    arr[22],    arr[32],    Integer.toString((((Integer.parseInt(arr[12])  +    Integer.parseInt(arr[22])   +   Integer.parseInt(arr[32]))))/2)}
                               } ;

        String column[] = {"Subject","1st Sem","2nd Sem","Internal","Total"} ;

        String total[] = new String[10] ;
        for(int m=0;m<10;m++){
            total[m] = data[m][4] ;
        }

        float percent = percentage.bestOf5(total) ;

        dFrame = new JFrame("Result ") ;
        dFrame.setSize(520,350) ;
    
        String studentData[][] = {{UID,indexNo,name,Float.toString(percent)} } ;       
        bO5per = Float.toString(percent) ;             
    
        String col2[] = {"UID","INDEX NUMBER ","NAME","PERCENTAGE"} ;
        
        table1 = new JTable(data,column) ;
        table1.getColumnModel().getColumn(0).setPreferredWidth(150);
        table1.setDefaultEditor(Object.class,null);

        table2 = new JTable(studentData,col2) ;
        table2.getColumnModel().getColumn(0).setPreferredWidth(120);
        table2.getColumnModel().getColumn(1).setPreferredWidth(150);
        table2.getColumnModel().getColumn(2).setPreferredWidth(220);
        table2.getColumnModel().getColumn(3).setPreferredWidth(100);
        table2.setDefaultEditor(Object.class,null) ;

        JScrollPane sPane = new JScrollPane(table1) ;
        JScrollPane sp2 = new JScrollPane(table2) ;
        sp2.setPreferredSize(new Dimension(520,60));

        JPanel lastPanel = new JPanel() ;
        lastPanel.setPreferredSize(new Dimension(520,40));

        printBtn = new JButton("Print") ;
        printBtn.addActionListener(new GUI()) ;
        printBtn.setFont(new Font("Open Sans",Font.CENTER_BASELINE,15));
        printBtn.setBackground(Color.BLUE);
        printBtn.setForeground(Color.WHITE);
        printBtn.setBounds(175,15,60,30);
        lastPanel.add(printBtn) ;

        closeBtn = new JButton("Close") ;
        closeBtn.setFont(new Font("Open Sans",Font.CENTER_BASELINE,15));
        closeBtn.setBackground(Color.RED);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBounds(235,15,60,30);
        closeBtn.addActionListener(new GUI());
        lastPanel.add(closeBtn) ;

        dFrame.getContentPane().add(sp2,BorderLayout.NORTH) ;
        dFrame.getContentPane().add(sPane,BorderLayout.CENTER) ;
        dFrame.getContentPane().add(lastPanel,BorderLayout.PAGE_END) ;

        dFrame.setVisible(true);

        
    }

    
}