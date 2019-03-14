/*
Author: Jameson Price
This is a java implementation of a Trie used to store words, in this case a provided dictionary.
Each TrieNode has a TreeMap of children associated with it and a boolean
variable dictating that the path to that child is a word.
 */
import java.util.Hashtable;

public class DictPrefixTree {
    private class TrieNode {
        Hashtable<Character, TrieNode> children = new Hashtable<>();
        boolean isWord = false;
    }

    private TrieNode root;
    public DictPrefixTree() {
        this.root = new TrieNode();
    }

    public void insert(String s) { insert(root, s); }

    /**
     * This method first traverses the Trie until a child might be added that isn't already
     * contained in the tree. At this point in time, we begin making new TrieNodes into the Trie
     * associated with the provided word s.
     * When the string has been completed inserted into the Trie, we set the boolean variable of isWord
     * to true to signify the path above that node is a word.
     * @param root The root of the passed in Trie branch, initially the root of the entire Trie.
     * @param s The word to be inserted
     */
    private void insert(TrieNode root, String s) {
        TrieNode cur = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = cur.children.get(ch);
            if (next == null)
                cur.children.put(ch, next = new TrieNode());
            cur = next;
        }
        cur.isWord = true;
    }

    public boolean isWord(String s) { return isWord(root, s); }

    /**
     * This method traverses down the tree until the final node that represents
     * the final character in String s is reached, s.length() == 1
     * At this point, it checks if the child node is not null and has its marker
     * set for isWord as true. If so it returns true and otherwise returns false.
     * @param node The root of the passed in DictPrefixTree branch, initially the root of the entire Trie.
     * @param s The current word provided by the user, which will be stripped of the first character until s.length == 1
     * @return True if the word exists in the dictionary, false otherwise.
     */
    private boolean isWord(TrieNode node, String s)
    {
        if(s != null)
        {
            String rest = s.substring(1);
            char ch = s.charAt(0);
            TrieNode child = node.children.get(ch);
            if(s.length() == 1 && child != null && child.isWord)
                return true;
            if(child == null || s.length() == 1)
                return false;
            else
                return isWord(child, rest);
        }
        return false;
    }
}
