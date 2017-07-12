import java.io.*;
import java.security.*;


public class fileManip{
    String filename = "";
    public fileManip(String filename){
        this.filename = filename;
    }

    public String fileRead(){
        String fstr = "";
        try{
            FileInputStream fi = new FileInputStream(filename);
            int fSize = fi.available();
            for (int i = 0; i < fSize; i++){
                fstr += (char)fi.read();
            }
            fi.close();
        }catch(Exception e){
            System.err.println("An error occured at fileRead() function for \""+filename+"\"");
            e.printStackTrace();
        }
        return fstr;
    }
    
    public void fileWrite(String strCont){
        try{
            FileOutputStream fo = new FileOutputStream(filename);
            char[] strContArr = strCont.toCharArray();
            for (int i = 0; i < strContArr.length; i++){
                fo.write(strContArr[i]);
            }
            fo.close();
        }catch(Exception e){ 
            System.err.println("An error occured at fileWrite() function for \""+filename+"\"");
            e.printStackTrace();
        }
    }


}





















