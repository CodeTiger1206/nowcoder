package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/8
 * @Description:
 */

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init(){
        try(
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine())!=null){
                // 添加到前缀树
                this.addKeyWord(keyword);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败："+e.getMessage());
        }
    }

    private void addKeyWord(String keyword){
        TrieNode tempNode = rootNode;
        for (int i=0;i<keyword.length();i++){
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);

            if (subNode == null){
                subNode = new TrieNode();
                tempNode.addSubNode(c,subNode);
            }
            tempNode = subNode;
            if (i==keyword.length()-1){
                tempNode.setKeywordEnd(true);
            }
        }
    }

    //过滤敏感词
    public String filter(String text){
        if (StringUtils.isBlank(text)){
            return null;
        }

        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;
        StringBuilder sb = new StringBuilder();

        while (begin < text.length()) {
            char c = text.charAt(position);

            // 跳过符号
            if (isSymbol(c)) {
                // 若指针1处于根节点,将此符号计入结果,让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // 无论符号在开头或中间,指针3都向下走一步
                position++;
                continue;
            }

            // 检查下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                // 以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                // 进入下一个位置
                position = ++begin;
                // 重新指向根节点
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // 发现敏感词,将begin~position字符串替换掉
                sb.append(REPLACEMENT);
                // 进入下一个位置
                begin = ++position;
                // 重新指向根节点
                tempNode = rootNode;
            } else {
                // 检查下一个字符
                if (position < text.length() - 1) {
                    position++;
                } else {
                    position = begin;
                }
            }
        }

        return sb.toString();
    }

    // 判断是否为符号
    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    private class TrieNode{
        private boolean isKeywordEnd = false;

        private Map<Character,TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        public void addSubNode(Character c, TrieNode node){
            subNodes.put(c,node);
        }

        public TrieNode getSubNode(Character c){
            return subNodes.get(c);
        }
    }

}
