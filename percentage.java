import java.util.* ;


public class percentage 
{
    public static float bestOf5(String arr[])
    {    
        int[] groups = new int[5] ;

        int english = (Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]) )/2 ; //ENGLISH LANGUAGE ; ENGLISH LITERATURE
        
        groups[0] = Integer.parseInt(arr[2]) ; // SECOND LANGUAGE
        groups[1] = Integer.parseInt(arr[3]) ;
        groups[2] = (Integer.parseInt(arr[4]) + Integer.parseInt(arr[5]) + Integer.parseInt(arr[6]))/3 ; //PHYSICS ,CHEMISTRY ,BIOLOGY
        groups[3] = (Integer.parseInt(arr[7]) + Integer.parseInt(arr[8])) / 2 ; //HISTORY CIVICS ; GEOGRAPHY
        groups[4] = Integer.parseInt(arr[9]) ;// COMPUTER OR ECONOMICS APPLICATIONS

        groups = bubbleSort(groups) ;

        float per = (english + groups[4] + groups[3] + groups[2] + groups[1]) / 5 ;
        return per ;
    }    

    public static int[] bubbleSort(int[] arr)
    {
        int temp = 0 ;
        for(int i=0;i<5;i++){
            for(int j=1;j<(5-i);j++){
                if(arr[j-1]>arr[j]){
                    temp = arr[j] ;
                    arr[j] = arr[j-1] ;
                    arr[j-1] = temp ;
                }
            }
        }

        return arr ;
    }

    public static int[] reverseArr(int[] arr)
    {
        int[] newArr = new int[5] ;
        for(int k=4,i=0;k>-1;k--,i++)
        newArr[k] = arr[i] ;

        return newArr ;
    }
    
}


