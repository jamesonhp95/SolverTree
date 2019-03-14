/*
    Author: Jameson Price
    This class has an inner private class of treeNode that holds char data as well as up to four pointers to other treeNodes
    Additionally, the SolverTree class has a treeNode root, and a DictPrefixTree and DictHashTable that are two classes
    to implement the two different dictionary implementations of a prefix tree and a hash table.
 */

import java.io.*;
import java.util.ArrayList;

public class SolverTree {

    private class TreeNode
    {
        private char cData;
        private TreeNode c1;
        private TreeNode c2;
        private TreeNode c3;
        private TreeNode c4;
        private TreeNode(char ch)
        {
            this.cData = ch;
            this.c1 = null;
            this.c2 = null;
            this.c3 = null;
            this.c4 = null;
        }
    }
    private TreeNode root;
    private DictPrefixTree DP;
    private DictHashTable DH;

    public SolverTree()
    {
        this.root = new TreeNode('\0');
        this.DP = new DictPrefixTree();
        this.DH = new DictHashTable();
        parseDictionary();
    }

    /**
        This is a small method that parses the dictionary file into a usable dictionary for both the PrefixTree dictionary and HashTable dictionary.
     */
    public void parseDictionary()
    {
        try
        {
            InputStream inputStream = new FileInputStream(new File("dictionary.txt"));
            BufferedReader rdr = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            if(inputStream != null)
            {
                while((temp = rdr.readLine()) != null)
                {
                    temp = temp.split(",")[0];
                    DP.insert(temp);
                    DH.insert(temp);
                }
            }
        } catch (Exception err)
        {
            System.err.println(err.toString());
        }
    }

    /**
        This will print the possible words given the Solver tree that has been constructed from a sequence of integers.
        It makes a call to both the "printPossibleWordsHelperDictPrefix" and "printPossibleWordsHelperDictHashTable" methods
        so as to show both implementations of the dictionary are working.
     */
    public void printPossibleWords()
    {
        ArrayList<Character> possibleWord = new ArrayList<>();
        System.out.println("Words checked with DictPrefixTree:");
        printPossibleWordsHelperDictPrefix(root, possibleWord, 0);
        System.out.println();
        System.out.println("Words checked with DictHashTable:");
        printPossibleWordsHelperDictHashTable(root, possibleWord, 0);
    }
    /**
        This is the code for the printing of possible words via hash table,
        first I check if the node is null, and return if so. Then i add the char data at the current node
        to the word I am constructing as I go down the tree, then if the nodes pointers are null (aka its a leaf node)
        I print the word for the tree. I took heavily from my code regarding "PrintAllPaths" and took inspiration from it
        to print the words at the end of each leaf node, since that would be a possible word if it is in the dictionary.
     */
    private void printPossibleWordsHelperDictHashTable(TreeNode node, ArrayList<Character> pWord, int i)
    {
        if(node == null) { return; }
        pWord.add(i, node.cData);
        if(node.c1 == null && node.c2 == null && node.c3 == null && node.c4 == null)
        {
            String word = "";
            for(int x = 0; x <= i; x++)
                word += pWord.get(x);
            word = word.trim();
            if(DH.isWord(word))
                System.out.println(word);
        }
        else
        {
            i++;
            printPossibleWordsHelperDictHashTable(node.c1, pWord, i);
            printPossibleWordsHelperDictHashTable(node.c2, pWord, i);
            printPossibleWordsHelperDictHashTable(node.c3, pWord, i);
            if(node.c4 != null)
                printPossibleWordsHelperDictHashTable(node.c4, pWord, i);
        }

    }

    /**
        This code is practically identical to the printPossibleWordsHelperDictHashTable, except it checks the prefix tree if the
        leaf node word is a word or not.
     */
    private void printPossibleWordsHelperDictPrefix(TreeNode node, ArrayList<Character> pWord, int i)
    {
        if(node == null) { return; }
        pWord.add(i, node.cData);
        if(node.c1 == null && node.c2 == null && node.c3 == null && node.c4 == null)
        {
            String word = "";
            for(int x = 0; x <= i; x++)
                word += pWord.get(x);
            word = word.trim();
            if(DP.isWord(word))
                System.out.println(word);
        }
        else
        {
            i++;
            printPossibleWordsHelperDictPrefix(node.c1, pWord, i);
            printPossibleWordsHelperDictPrefix(node.c2, pWord, i);
            printPossibleWordsHelperDictPrefix(node.c3, pWord, i);
            if(node.c4 != null)
                printPossibleWordsHelperDictPrefix(node.c4, pWord, i);
        }

    }

    /**
        This is my insertion method that takes a string read in from the user and parses the integers from it
        I first check if the character is a digit and that it is greater than 1, since 1 does not represent any characters,
        if it is a digit and greater than 1 I insert the digit into the Solver Tree, otherwise I throw a numberFormatException.
     */
    public void insertSequence(String sequence)
    {
        int num = 0;
        for(int x = 0; x < sequence.length(); x++)
        {
            num = Character.getNumericValue(sequence.charAt(x));
            if(Character.isDigit(sequence.charAt(x)) && num > 1)
                insertRecursive(root, num);
            else
                throw new NumberFormatException();
        }
    }

    /**
        This does all the work for insertion, essentially I check if the node pointers in the current node
        are null, and if so I call the insert method that inserts the proper characters that the number represents
        otherwise, i traverse to each child node of the current node recursively.
     */
    private void insertRecursive(TreeNode node, int num)
    {
        if(node.c1 == null && node.c2 == null && node.c3 == null && node.c4 == null)
        {
            insert(node, num);
        }
        else
        {
            insertRecursive(node.c1, num);
            insertRecursive(node.c2, num);
            insertRecursive(node.c3, num);
            if(node.c4 != null)
                insertRecursive(node.c4, num);
        }
    }

    /**
        This method merely sets the node points in the passed in node to point to the necessary characters that are represented
        by a number, I did this with a switch statement.
     */
    private void insert(TreeNode node, int num)
    {
        switch(num)
        {
            case 2:
                node.c1 = new TreeNode('a');
                node.c2 = new TreeNode('b');
                node.c3 = new TreeNode('c');
                break;
            case 3:
                node.c1 = new TreeNode('d');
                node.c2 = new TreeNode('e');
                node.c3 = new TreeNode('f');
                break;
            case 4:
                node.c1 = new TreeNode('g');
                node.c2 = new TreeNode('h');
                node.c3 = new TreeNode('i');
                break;
            case 5:
                node.c1 = new TreeNode('j');
                node.c2 = new TreeNode('k');
                node.c3 = new TreeNode('l');
                break;
            case 6:
                node.c1 = new TreeNode('m');
                node.c2 = new TreeNode('n');
                node.c3 = new TreeNode('o');
                break;
            case 7:
                node.c1 = new TreeNode('p');
                node.c2 = new TreeNode('q');
                node.c3 = new TreeNode('r');
                node.c4 = new TreeNode('s');
                break;
            case 8:
                node.c1 = new TreeNode('t');
                node.c2 = new TreeNode('u');
                node.c3 = new TreeNode('v');
                break;
            case 9:
                node.c1 = new TreeNode('w');
                node.c2 = new TreeNode('x');
                node.c3 = new TreeNode('y');
                node.c4 = new TreeNode('z');
                break;
        }
    }
}
