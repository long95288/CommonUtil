package com.longquanxiao.fold.util;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longquanxiao
 * @date 2019/10/17
 */
public class ReadFoldTest extends TestCase {

    public static List<FileObject> getFileObjectList(String path){
        List<FileObject> objectList = null;
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            if(null != files){
                objectList = new ArrayList<>(files.length);
                for (File item :
                        files) {
                    FileObject fileObject = new FileObject();
                    fileObject.setDictionary(item.isDirectory());
                    fileObject.setPath(item.getPath());
                    fileObject.setName(item.getName());
                    fileObject.setParent(item.getParent());

                    objectList.add(fileObject);
                }
            }
        }
        return objectList;
    }

    public void testGetFile() {
        String path = "G:\\element-uii";
        FileObject fileObject = ReadFold.getFileObject(path);
        String json = JSON.toJSONString(fileObject);
        System.out.println(json);
        if(null != json && !"null".equalsIgnoreCase(json)){
            FileObject fileObject1 = (FileObject) JSON.parseObject(json,FileObject.class);
            System.out.println(fileObject1.getName());
        }
//        File file = new File(path);
//        boolean isFolder = file.isDirectory();
//        File[] fileList = file.listFiles();
//
//        // to json
//        String json = JSON.toJSONString(file.listFiles());
//        // System.out.println(json);
//
//        FileObject fileObject = new FileObject();
//        fileObject.setDictionary(file.isDirectory());
//        fileObject.setName(file.getName());
//        fileObject.setPath(file.getPath());
//        fileObject.setParent(file.getParent());
//        if(isFolder){
//            fileObject.setContext(getFileObjectList(path));
//        }
//
//        json = JSON.toJSONString(fileObject);
//        System.out.println(json);
        /**
        for (File item :
                fileList) {
            System.out.println("AbsolutePath:\t" + item.getAbsolutePath());
            System.out.println("getPath:\t"+item.getPath());
            System.out.println("toURI:\t"+item.toURI());
            System.out.println("getName:\t" + item.getName());
            System.out.println("getParent:\t" + item.getParent());
        }
         */

    }
}
