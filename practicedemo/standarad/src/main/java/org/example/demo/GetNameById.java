package org.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetNameById {
    static ClassLoader classLoader;

    public static void main(String[] args) {
        String file1Path = "file\\11.txt"; // 第一个文件路径，存放id
        String file2Path = "file\\22.txt"; // 第二个文件路径，存放id和values

        classLoader = GetNameById.class.getClassLoader();
        // 从第一个文件读取id
        List<String> idsFromFile1 = readIdsFromFile(file1Path);
        List<String> valuesList = findValuesByIds(idsFromFile1, file2Path);

        for (int i = 0; i < valuesList.size(); i++) {
            //System.out.println(valuesList.get(i) );
            System.out.println(valuesList.get(i)/*+"\t"+idsFromFile1.get(i)*/);
        }

    }

    // 从第一个文件读取id的方法
    public static List<String> readIdsFromFile(String filePath) {
        List<String> ids = new ArrayList<>();
        //FileReader  FileReader主要是基于文件系统的绝对路径或相对路径去操作文件，而resource目录下的文件在运行时是在类路径里，不一定能简单地通过常规文件路径去访问
        try  {
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine())!= null) {
                ids.add(line.trim()); // 去除每行前后空白字符后添加到列表
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }

    // 根据第一个文件获取的id列表，在第二个文件中查找对应values的方法
    public static List<String> findValuesByIds(List<String> ids, String file2Path) {
        List<String> values = new ArrayList<>();
        try  {
            String line;

            for (String id : ids) {
                boolean flag = false;
                id="name=\"" +id+"\"";
                InputStream inputStream = classLoader.getResourceAsStream(file2Path);

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                while ((line = reader.readLine())!= null) {
                    boolean b = StringUtils.containsIgnoreCase(line, id);
                    if (b) {
                        int i = line.indexOf("label=\"");
                        if (i != -1) {
                            i+=7;
                            int i1 = line.indexOf("\"", i);
                            if (i1 != -1) {
                                String substring = line.substring(i, i1);
                                values.add(substring); // 如果找到对应id，添加对应values到列表
                                flag = true;
                            }
                        }
                    }
                }
                if(flag==false){
                    values.add("");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}
