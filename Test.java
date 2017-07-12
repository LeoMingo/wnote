import java.util.ArrayList;
import java.security.*;


public class Test{
    public static String[] splitAt(String str, String tok){
        char[] strArr = str.toCharArray();
        char[] tokArr = tok.toCharArray();
        String[] rstArr = new String[strArr.length];
        char[] tempArr = new char[tokArr.length];

        int splitStartIdx, dplitEndIdx;
        for(int i = 0; i < strArr.length-tokArr.length; i++){
            splitStartIdx = i;
            for (int j = 0; j < tokArr.length; j++){
                i += j;
                if (tokArr[j] != strArr[i]){
                    break;
                }
                if (j == tokArr.length-1){
                    splitEndIdx = i;
                     
                }
            }
        }
    }



}



