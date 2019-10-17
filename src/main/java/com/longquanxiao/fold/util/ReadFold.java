package com.longquanxiao.fold.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件树
 * @author longquanxiao
 * @date 2019/10/17
 */
public class ReadFold {
    /**
     * 根据文件路径获得路径树
     * @param path
     * @return
     */
    public static FileObject getFile(String path){
        return new FileObject();
    }

    /**
     * 获得文件夹的文件列表
     * @param path 文件夹路径
     * @return 文件对象列表
     */
    private static List<FileObject> getFileObjectList(String path){
        List<FileObject> fileObjectList = null;
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            if(null != files){
                fileObjectList = new ArrayList<>(files.length);
                for (File item :
                        files) {
                    FileObject fileObject = new FileObject();
                    fileObject.setDictionary(item.isDirectory());
                    fileObject.setPath(item.getPath());
                    fileObject.setName(item.getName());
                    fileObject.setParent(item.getParent());

                    fileObjectList.add(fileObject);
                }
            }
        }
        return fileObjectList;
    }

    /**
     * 获得文件的对象
     * @param path 文件的路径
     * @return 返回文件路径/如果不存在返回null
     */
    public static FileObject getFileObject(String path){
        File file = new File(path);
        FileObject fileObject = null;
        if(file.exists()){
            fileObject = new FileObject();
            fileObject.setDictionary(file.isDirectory());
            fileObject.setName(file.getName());
            fileObject.setPath(file.getPath());
            fileObject.setParent(file.getParent());
            if(file.isDirectory()){
                fileObject.setContext(getFileObjectList(path));
            }
        }
        return fileObject;
    }


}
