package com.ideal.main;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CosineSimilar {
    public static Double cosSimilarityByString(String first, String second) {
        try {
            Map<String, Integer> firstTfMap = Jieba.segStr(first);
            // System.out.println(firstTfMap);
            Map<String, Integer> secondTfMap = Jieba.segStr(second);




            // System.out.println(secondTfMap);
            if (firstTfMap.size() < secondTfMap.size()) {
                Map<String, Integer> temp = firstTfMap;
                firstTfMap = secondTfMap;
                secondTfMap = temp;
            }
            return calculateCos((LinkedHashMap<String, Integer>) firstTfMap, (LinkedHashMap<String, Integer>) secondTfMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }
    /**
     * @param @param  first
     * @param @param  second
     * @param @return
     * @return Double
     * @throws
     * @Title: calculateCos
     * @Description: 计算余弦相似性
     */
    private static Double calculateCos(LinkedHashMap<String, Integer> first, LinkedHashMap<String, Integer> second) {
        //System.out.println(first+"<++++>"+second);
        List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
        List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
        //计算相似度
        double vectorFirstModulo = 0.00;//向量1的模
        double vectorSecondModulo = 0.00;//向量2的模
        double vectorProduct = 0.00; //向量积
        int secondSize = second.size();
        for (int i = 0; i < firstList.size(); i++) {
            if (i < secondSize) {
                vectorSecondModulo += secondList.get(i).getValue().doubleValue() * secondList.get(i).getValue().doubleValue();
                vectorProduct += firstList.get(i).getValue().doubleValue() * secondList.get(i).getValue().doubleValue();
            }
            vectorFirstModulo += firstList.get(i).getValue().doubleValue() * firstList.get(i).getValue().doubleValue();
        }
        return vectorProduct / (Math.sqrt(vectorFirstModulo) * Math.sqrt(vectorSecondModulo));
    }

    public static void main() {
        long begin = System.currentTimeMillis();
        File file1 = new File("C:\\Users\\chenyang\\Desktop\\vector1");
        BufferedReader reader1 = null;
        try {
            reader1 = new BufferedReader(new FileReader(file1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file2 = new File("C:\\Users\\chenyang\\Desktop\\vector2");
        BufferedReader reader2 = null;
        String tempString = null;
        String tempString2 = null;
        int i = 0;
        try {
            while ((tempString = reader1.readLine()) != null) {
                reader2 = new BufferedReader(new FileReader(file2));
                while ((tempString2 = reader2.readLine()) != null) {
                    i++;
                    Double result = cosSimilarityByString(tempString,tempString2);
                    System.out.println(tempString+"\t"+tempString2+"\t"+result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
    public static void main(String[] args) {
        Double result = cosSimilarityByString("上海凯腾数码有限公司","上海凯瑞数码有限公司");
        System.out.println(result);
//        main();

    }
}
