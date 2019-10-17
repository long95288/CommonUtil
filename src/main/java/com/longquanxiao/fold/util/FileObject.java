package com.longquanxiao.fold.util;

import java.util.List;

/**
 * 文件描述对象
 * @author longquanxiao
 * @date 2019/10/17
 */
public class FileObject {
    /**
     * 文件/文件夹标记
     * 默认为文件
     */
    private boolean isDictionary;

    /**
     * 文件路径/绝对路径
     */
    private String path;

    /**
     * 父路径
     */
    private String parent;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件夹内的文件列表
     */
    private List<FileObject> context;

    public boolean isDictionary() {
        return isDictionary;
    }

    public void setDictionary(boolean dictionary) {
        isDictionary = dictionary;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileObject> getContext() {
        return context;
    }

    public void setContext(List<FileObject> context) {
        this.context = context;
    }
}
