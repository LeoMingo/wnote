import java.io.*;
import java.security.*;
import java.util.*;


public class WebTextTest{
    
    public static void main(String[] args){
    try{
        final String url = 
            "https://raw.githubusercontent.com/LeoMingo/wnote/master/FileManip.java";
        WebText wt = new WebText(url);
        String MIME = wt.getMIMEType();
        Object content = wt.getContent();
        System.out.println(MIME);
        if (content instanceof String){ 
            System.out.println((String)content);
        }
        
    }catch(Exception e){
        e.printStackTrace();
    }
}

}



