package com.longquanxiao.tsvideo.tool;

import junit.framework.TestCase;

/**
 * @author longquanxiao
 * @date 2019/10/8
 */
public class EncryFileUtilTest extends TestCase {

    public void testEncryptFile() {
        try{
            EncryFileUtil.encryptFile("origin.txt","encrypt.txt","tkr87ysao6qehn95");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testDecryptFile() throws Exception{
        // EncryFileUtil.decryptFile("encrypt.txt","origin2.txt","tkr87ysao6qehn95");
        EncryFileUtil.decryptFile("0000.ts","e0000.ts","tkr87ysao6qehn95");
    }
}
