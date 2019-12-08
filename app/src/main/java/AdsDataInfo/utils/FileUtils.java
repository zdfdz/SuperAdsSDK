package AdsDataInfo.utils;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author ZhengDeFeng
 * @description: 文件存储工具类
 * @date :2019/12/8 22:02
 */
public class FileUtils {

    //保存文件到sd卡
    public static boolean saveToFile(String content) {
        BufferedWriter out = null;

        //获取SD卡状态
        String state = Environment.getExternalStorageState();
        //判断SD卡是否就绪
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        //取得SD卡根目录
        File file = Environment.getExternalStorageDirectory();
        try {
            if (file.exists()) {
                // 文件存在属于正常预期,直接覆盖就好
            }
      /*
      输出流的构造参数1：可以是File对象 也可以是文件路径
      输出流的构造参数2：默认为False=>覆盖内容； true=>追加内容
       */
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getCanonicalPath() + "/readMsg.txt", false)));
            out.newLine();
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    //从SD卡读取文件
    public static String readFromFile() {
        //读的时候要用字符流  万一里面有中文
        BufferedReader reader = null;
        FileInputStream fis;
        StringBuilder sbd = new StringBuilder();
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return "";
        }
        File root = Environment.getExternalStorageDirectory();
        try {
            fis = new FileInputStream(root + "/readMsg.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            String row;
            while ((row = reader.readLine()) != null) {
                sbd.append(row);
            }
        } catch (FileNotFoundException e) {
            return "";
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sbd.toString();
    }
}
