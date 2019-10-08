package com.longquanxiao.tsvideo.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 解密程序
 * @author longquanxiao
 * @date 2019/10/8
 */
public class TsMain {
    public static void main(String[] args) {
        String sourcePath ="G:\\win下载\\m3u8\\";
        String destination = "G:\\win下载\\m3u8\\";
        String m3u8 = "G:\\win下载\\m3u8\\m3u8.m3u8";
        String key = "tkr87ysao6qehn95";
        FileReader fileReader = null;
        BufferedReader reader = null;
        ArrayList<String> files = new ArrayList<>();
        try{
            fileReader = new FileReader(m3u8);
            reader = new BufferedReader(fileReader);
            String filename = null;
            while (( filename = reader.readLine())!=null){
                if(filename.charAt(0)!='#'){
                    files.add(filename);
                }
            }
            if(fileReader!=null){
                fileReader.close();
            }
            if(reader!=null){
                reader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        int partSize = files.size();
        String sourceFile;
        String destFile;
        try{
            for(int i=0;i < partSize ;i++){
                sourceFile = sourcePath + files.get(i);
                destFile = destination + "e"+files.get(i);
                EncryFileUtil.decryptFile(sourceFile,destFile,key);
            }
            System.out.println("========全部解密成功============");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            // 合并数据
            String mergeFilePath = sourcePath+"merge.ts";
            FileOutputStream mergeFile = new FileOutputStream(mergeFilePath);
            for (int i=0;i<partSize;i++){
                String encodeFile = sourcePath +"e"+files.get(i);
                FileInputStream inputStream = new FileInputStream(encodeFile);
                byte[] buffer = new byte[1024];
                int n=0;
                while ((n = inputStream.read(buffer))!= -1){
                    mergeFile.write(buffer,0,n);
                }
                inputStream.close();
            }
            System.out.println("合并数据成功");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
