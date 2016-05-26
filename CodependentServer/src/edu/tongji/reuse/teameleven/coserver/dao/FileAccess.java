package edu.tongji.reuse.teameleven.coserver.dao;

import java.io.*;

public class FileAccess {

    public static String readFile(String path, String encoding){
        BufferedReader reader = null;
        String lastStr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
            String tmpString = null;
            while((tmpString = reader.readLine())!= null){
                lastStr += tmpString;
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }
        return lastStr;
    }

    public static String readFile(String path){
        return readFile(path, "UTF-8");
    }

    public static boolean fileWrite(String path, String content, boolean append){
        boolean succeed = false;
        FileWriter fw = null;
        try{
            fw = new FileWriter(path, append);
            fw.write(content);
            fw.flush();
            succeed = true;
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fw != null){
                try{
                    fw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return succeed;
    }

    public static boolean fileOverWrite(String path, String content){
        return fileWrite(path, content, false);
    }

    public static boolean fileAppend(String path, String content){
        return fileWrite(path, content, true);
    }

}
