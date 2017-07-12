//The format of wnote :
//idx1 
//[key1]
//{value1}
//
//idx2
//[key2]
//{value2}
//
//...



import java.io.*;
import java.security.*;
import java.util.*;


//Remeber to escape special chars like '[' ']' in the wnStrWrite
public class WNStrManip{
    String wnStr = "";
    char[] wnCharArr;
    //The followings are used in strToWNUnits()
    //and wnFormatCheck()
    Vector keysStartIdx = new Vector(0);
    Vector keysEndIdx = new Vector(0);
    Vector valsStartIdx = new Vector(0);
    Vector valsEndIdx = new Vector(0);
    //Manipulations
    String[] wnKeys; 
    String[] wnVals;
    int unitNum;

    public WNStrManip(String wnStr){
        this.wnStr = wnStr;
        wnCharArr = this.wnStr.toCharArray();
    }

    public void getUnitToksIdx(){
        for (int i = 0; i < wnCharArr.length; i++){
            if (wnCharArr[i] == '[' && wnCharArr[i-1] != '\\'){
                keysStartIdx.addElement(i);
            }else if (wnCharArr[i] == ']' && wnCharArr[i-1] != '\\'){
                keysEndIdx.addElement(i);
            }else if (wnCharArr[i] == '{' && wnCharArr[i-1] != '\\'){
                valsStartIdx.addElement(i);
            }else if (wnCharArr[i] == '}' && wnCharArr[i-1] != '\\'){
            
            }else{
                continue;
            }
        }
    }
    

    public void strToWNUnits(){
        //?----------? size() vs capacity()?
        unitNum = keysStartIdx.size();    
        wnKeys = new String[unitNum];
        wnVals = new String[unitNum];
        for (int i = 0; i < unitNum; i++){
            wnKeys[i] = wnStr.substring((keysStartIdx.get(i) + 1), keysEndIdx.get(i));
            wnVals[i] = wnStr.substring((valsStartIdx.get(i)+1), valsEndIdx.get(i));
        }
    }
    
    
    public int formatCheck(){
        return 1;
       // return 0;
    }

}





//wn = WNStrManip(wnStr);
//wn.getUnitToksIdx();
//if(wn.formatCheck() == 1){
//  ...
//}else{
//  System.err.println("Incorrect formatted wnote file")
//  ...
//}






