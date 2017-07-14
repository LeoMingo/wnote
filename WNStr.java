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
public class WNStr{
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

    public WNStr(String wnStr){
        this.wnStr = wnStr;
        wnCharArr = this.wnStr.toCharArray();
    }

    public void getUnitToksIdx(){
        for (int i = 0; i < wnCharArr.length; i++){
            //^--------^And new lines???
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
            wnKeys[i] = wnStr.substring((keysStartIdx.get(i)+1), keysEndIdx.get(i));
            wnVals[i] = wnStr.substring((valsStartIdx.get(i)+1), valsEndIdx.get(i));
        }
    }
    
    
    public int formatCheck(){
        return 1;
       // return 0;
    }

    public String getKeyByIdx(int idx){
        return wnKeys[idx];
    }

    public String getValByIdx(int idx){
        return wnVals[idx];
    }

    public Vector seachUnits(String keywordsStr){
        String tempWholeUnit = "";
        Vector unitIdxList = new Vector(0);
        
        int unitMatchedNum = 0;
        for (int i = 0; i < unitNum; i++){
            tempWholeUnit = wnKeys[i] + " " + wnVals[i];
            unitMatchedNum = unitHasKeywords(tempWholeUnit, keywordsStr);
            if (unitMatchedNum >= keywordsStr.split(' ').length){
                unitIdxList.addElement(i);
            }
        }

        return unitIdxList;
    }


    public int unitHasKeywords(String unitStr, String keywordsStr){
        String[] unitStrArr = unitStr.split(' ');
        String[] kwArr = keywordsStr.split(' ');
        int matchedNum = 0;
        for (int i = 0; i < unitStrArr.length; i++){
            for (int j = 0; j < kwArr.length; j++){
                if(unitStrArr[i] == kwArr[j]){
                    matchedNum += 1;
                }
            }
        }

        return matchedNum;
    }


    public boolean strMatch(String strBase, String strTarget, int errRange){
        char[] strBArr = strBase.toCharArray();
        int sblen = strBArr.length;
        int stlen = strTarget.length();
        if (Math.abs(sblen-stlen) > errRange){
            return false;
        }

        int errCharNum = 0;
        int lastIdx = 0;
        int newIdx = 0;
        int hasChar = 0;
        int[] containsCharVals = new int[2];
        
        for (int i = 0; i < sblen; i++){
           containsCharVals = containsChar(strTarget, strBArr[i], lastIdx, errRange);
           hasChar = containsCharVals[0];
           newIdx = containsCharVals[1];
           if(hasChar == 0 || newIdx<lastIdx){
               errCharNum += 1;
           }
           lastIdx = newIdx;
        }

        if(errCharNum > errRange){
            return false;
        }else{
            return true;
        }

    }

    public int[] containsChar(String str, char ch, int lastIdx, int errRange){
        //{hasChar, idx}
        //For hasChar , 0 for false, 1 for true;
        int hasChar = 0;
        int charIdx = -1;
        int[] returnVals = {hasChar,charIdx};

        char[] strArr = str.toCharArray();
        int strLen = strArr.length;
        
        int idxMin, idxMax;
        if (lastIdx-errRange < 0) {
            idxMin = 0;
        }else {
            idxMin = lastIdx-errRange;
        }

        if (lastIdx+errRange >= strLen){
            idxMax = strLen-1;
        }else {
            idxMax = lastIdx+errRange;
        }

        for (int i = idxMin; i <= idxMax; i++){
            if (strArr[i] == ch){
                hasChar = 1;
                charIdx = i;
                returnVals[0] = hasChar;
                returnVals[1] = charIdx;
            }
        }

        return returnVals;

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






