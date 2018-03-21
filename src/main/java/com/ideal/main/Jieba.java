package com.ideal.main;


import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.LinkedHashMap;
import java.util.Map;

public class Jieba {
    public static Map<String, Integer> segStr(String content) {

        JiebaSegmenter segmenter = new JiebaSegmenter();
        Map<String, Integer> words = new LinkedHashMap<String, Integer>();
        for (SegToken list : segmenter.process(content, JiebaSegmenter.SegMode.INDEX)) {
            // System.out.println(list.word.getToken());
            if (!isBelongList(list.word.getToken())) {
                if (words.containsKey(list.word.getToken())) {
                    words.put(list.word.getToken(), words.get(list.word.getToken()) + 1);
                } else {
                    words.put(list.word.getToken(), 1);
                }
            }
        }
        return words;
    }

    private static boolean isBelongList(String str) {

        String[] strArray = {"公司", "有限", "有限公司", "本部", "分行", "有限责任", "责任", "上海市", "股份", "集团", "浦东支行", "黄埔支行"};
        boolean bResult = false;
        for (String temp : strArray) {
            if (temp.equals(str)) {
                bResult = true;
            }
        }
        return bResult;
    }

}
