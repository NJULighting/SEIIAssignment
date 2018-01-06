package nju.lighting.data.utils;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created on 2018/1/6.
 * Description:
 *
 * @author iznauy
 */
public class Zipper {

    private String zipFileName;

    private String sourceFileName;

    public Zipper(String zipFileName, String sourceFileName) {
        this.zipFileName = zipFileName;
        this.sourceFileName = sourceFileName;
    }

    public void zip() throws Exception {
        System.out.println("正在压缩" + sourceFileName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
        File source = new File(sourceFileName);
        compress(out, source);
        out.close();
        System.out.println("压缩ojbk");
    }

    private void compress(ZipOutputStream out, File source) throws Exception {
        File[] fileList = source.listFiles();
        if (fileList == null)
            throw new Exception();
        for (File file: fileList) {
            out.putNextEntry(new ZipEntry(source.getName() + "/" + file.getName()));
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
            int tag = -1;
            while ((tag = buffer.read())!= -1) {
                out.write(tag);
            }
            buffer.close();
        }
    }


}