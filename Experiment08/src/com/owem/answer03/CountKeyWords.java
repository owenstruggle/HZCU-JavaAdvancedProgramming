package com.owem.answer03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Owem
 * @date 2022/11/13 16:19
 * @description TODO
 **/
public class CountKeyWords {
    private static final HashMap<String, Integer> records = new HashMap<>();

    static {
        getKeywords();
    }

    /**
     * 获取关键字
     */
    private static void getKeywords() {
        String[] keywordString = {"abstract", "finally", "public", "boolean", "float", "return", "break", "for",
                "short", "byte", "goto", "static", "case", "if", "super", "catch", "implements", "switch", "char",
                "import", "synchronized", "class", "instanceof", "this", "const", "int", "throw", "continue",
                "interface", "throws", "default", "long", "transient", "do", "native", "try", "double", "new", "void",
                "else", "package", "volatile", "extends", "private", "while", "final", "protected", "true", "null"};

        for (String string : keywordString) {
            records.put(string, 0);
        }
    }

    public static void main(String[] args) {
        String path = new Scanner(System.in).nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("File or directory does not exist!");
            System.exit(0);
        } else {
            checkAndCountFromFile(path);
        }
        Set<String> keywordsSet = records.keySet();
        for (String keyword : keywordsSet) {
            System.out.println(keyword + " : " + records.get(keyword));
        }
    }

    /**
     * 统计.java文件中各个关键字的个数
     */
    private static void checkAndCountFromFile(String path) {
        File[] files;
        File file = new File(path);
        //若是目录则获取目录下的所有子文件或子目录的绝对路径递归调用该方法，对.java文件和子目录中.java进行统计
        if (file.isDirectory()) {
            files = file.listFiles();

            assert files != null;
            for (File value : files) {
                checkAndCountFromFile(value.getAbsolutePath());
            }
        } else if (file.isFile()) {  //若是.java文件则直接统计
            if (file.getName().endsWith(".java")) {
                try (Scanner scanner = new Scanner(file)) {
                    boolean isAdd = true;
                    while (scanner.hasNextLine()) {
                        isAdd = checkAndCountFromStr(scanner.nextLine(), isAdd);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查从Java源文件中逐个读取的字符串并且对关键字逐个统计数目
     */
    private static boolean checkAndCountFromStr(String string, boolean isAdd) {
        String subString[] = string.split(" ");
        for (String s : subString) {
            if (s.equals("/**")) return false;
            if (s.equals("**/")) return true;
            if (s.startsWith("\"")) return false;
            if (s.endsWith("\"")) return true;
            if (s.equals("//")) return isAdd;
            if (isAdd) {
                if (records.containsKey(s)) {
                    records.put(s, records.get(s) + 1);
                }

                switch (s) {
                    case "(true)" -> records.put("true", records.get("true") + 1);
                    case "break;" -> records.put("break", records.get("break") + 1);
                    case "continue;" -> records.put("continue", records.get("continue") + 1);
                }

                if (string.length() > 6) {
                    if (string.startsWith("super.")) records.put("super", records.get("super") + 1);
                    if (string.startsWith("this.")) records.put("this", records.get("super") + 1);
                }
            }
        }

        return isAdd;
    }
}
