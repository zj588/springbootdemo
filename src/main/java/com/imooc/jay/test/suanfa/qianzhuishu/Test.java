package com.imooc.jay.test.suanfa.qianzhuishu;

/**
 * 前缀树
 */
public class Test {
    public static void main(String[] args) {

    }

    public static class TrieNode {
        // 走过当前节点的次数
        public int pass;
        // 以当前节点结束的次数
        public int end;
        // 当前节点的后续节点
        public TrieNode[] nexts;
        
        public TrieNode() {
            pass = 0;
            end = 0;
            
            // nexts[0] == null  没有a路径
            // nexts[0] != null  有a路径
            // ...
            // nexts[25] != null 有z路径
            nexts = new TrieNode[26];
        }
    }
    
    public static class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            root.pass++;
            
            for (int i = 0; i < chars.length; i++) {
                // 当前字符的索引
                int index = chars[i] - 'a';
                
                if (node.nexts[index] == null) {
                    // 没有当前字符的路径，则新建一个字符
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 当前字符出现的次数
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                
                if (node.nexts[index] == null) {
                    return 0;
                }
                
                node = node.nexts[index];
            }
            
            return node.end;
        }

        /**
         * 以当前字符串为前缀的数量
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';

                if (node.nexts[index] == null) {
                    return 0;
                }

                node = node.nexts[index];
            }
            
            return node.pass;
        }

        /**
         * 删除一个当前字符串
         * @param word
         */
        public void delete(String word) {
            if (search(word) > 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                for (int i = 0; i < chars.length; i++){
                    int index = chars[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        // 当前节点已经没有当前字符路径，删除
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * 删除所有的当前字符串
         * @param word
         */
        public void deleteAll(String word) {
            int num = search(word);
            if (num > 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass -= num;
                for (int i = 0; i < chars.length; i++){
                    int index = chars[i] - 'a';
                    if ((node.nexts[index].pass -= num) == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end -= num;
            }
        }
    }
}
