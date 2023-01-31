package net.jdbc.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void write(String str, String path) {
        try {
            File writeName = new File(path); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(str + "\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> read(String path) {
        List<String> content = new ArrayList<>();
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                content.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static int readUserID(){
        return Integer.parseInt(read("account.txt").get(0));
    }

}
