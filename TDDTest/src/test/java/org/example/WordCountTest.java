package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author karister
 * @create 2021-08-27 15:37
 */
public class WordCountTest {


    private String[] wordList;

    @Before
    /**
     * 读取“D://word.txt”下的单词到StringBuilder
     * 分割读取出来的word文本，按空格分割
     */
    public void wordTextHandle() throws IOException {
        File file = new File("D://word.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder strBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            strBuffer.append(line);
            strBuffer.append(' ');
        }
        reader.close();
        String word = ""+strBuffer;
        wordList = word.split(" ");
        /*for (int i = 0; i < wordList.length; i++) {
            if(wordList[i].contains("/")) {
                String newStr = wordList[i].replace("/", "");
                wordList[i] = newStr;
            }
        }*/
    }

    @Test
    public void test1(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String s : wordList) {
            if (hashMap.containsKey(s)) {
                Integer num = hashMap.get(s);
                num ++;
                hashMap.put(s,num);
            }
            else {
                hashMap.put(s,1);
            }
        }
        System.out.println(hashMap.toString());
    }

}
