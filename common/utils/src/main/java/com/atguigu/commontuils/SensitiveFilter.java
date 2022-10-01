package com.atguigu.commontuils;

import org.apache.commons.lang3.CharUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;

@Component
public class SensitiveFilter {

    private static  String REPLACEWORD="***";
    TrieNode treeRoot=new TrieNode();

    public void init(File file) throws IOException {

        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream("sensitive.txt");
        InputStreamReader reader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String s ="";

        while((s=bufferedReader.readLine())!=null){
            this.addKeyWord(s);
        }
    }

    private void addKeyWord(String s) {

    }


    public String filter(String text){

        TrieNode temp=treeRoot;
        int begin=0;
        int end=0;
        StringBuffer buffer=new StringBuffer();
        while(end<text.length()){
            Character c=text.charAt(end);
            if(isSymbol(c)){
                if(temp==treeRoot){
                    begin++;
                    buffer.append(c);
                }
                end++;
            }
            temp=temp.getSon(c);
            if(temp==null){
                buffer.append(text.charAt(begin));
                begin++;
                end=begin;
            }else if(temp.isEnd){
                buffer.append(REPLACEWORD);
                end++;
                begin=end;
                temp=treeRoot;
            }else{
                end++;
            }

        }
        buffer.append(text.charAt(begin));
        return buffer.toString();

    }

    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }



}

class TrieNode{

    boolean isEnd;

    public TrieNode(){

    }



    HashMap<Character, TrieNode> map=new HashMap<>();

    public void addTrieNode(Character character,TrieNode trieNode){
        map.put(character,trieNode);
    }

    public boolean isEnd(TrieNode trieNode){
        return trieNode.isEnd;
    }

    public void setEnd(boolean f){
        isEnd=f;
    }


    public TrieNode getSon(Character character){
        return map.get(character);
    }





}