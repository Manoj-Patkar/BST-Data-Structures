package com.structure.bst;

import java.util.HashMap;

public class Trie {

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("david");
    trie.insert("maria");
    trie.insert("mariana");
    System.out.println(trie.find("maria"));

    trie.delete("maria");
    System.out.println(trie.find("maria"));
    System.out.println(trie.find("mariana"));
  }

  public void insert(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
    }
    current.setEnd(true);
  }

  public boolean find(String s) {
    TrieNode current = root;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      TrieNode node = current.getChildren().get(ch);
      if (node == null) {
        return false;
      }
      current = node;
    }
    return current.isEnd();
  }

  public void delete(String word) {
    delete(root, word, 0);
  }

  private boolean delete(TrieNode current, String word, int i) {
    if (i == word.length()) {
      if (!current.isEnd()) {
        return false;
      }
      current.setEnd(false);
      return current.getChildren().isEmpty();
    }
    char ch = word.charAt(i);
    TrieNode node = current.getChildren().get(ch);
    if (node == null) {
      return false;
    }
    boolean shouldDeleteCurrentNode = delete(node, word, i + 1) && !node.isEnd();

    if (shouldDeleteCurrentNode) {
      current.getChildren().remove(ch);
      return current.getChildren().isEmpty();
    }

    return false;
  }

  class TrieNode {

    private HashMap<Character, TrieNode> children;
    private String content;
    private boolean isEnd;

    public TrieNode() {
      this.children = new HashMap<>();
    }

    public HashMap<Character, TrieNode> getChildren() {
      return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
      this.children = children;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public boolean isEnd() {
      return isEnd;
    }

    public void setEnd(boolean end) {
      isEnd = end;
    }
  }


}
