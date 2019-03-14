/*
Author: Jameson Price
This is a small class to create a Dictionary Hash Table.
This class really just holds the private variable for the Hashtable
and handles insertion/isWord with predefined methods in the Hashtable API
 */
import java.util.Hashtable;

public class DictHashTable {

    private Hashtable<Integer, String> hashtable;

    public DictHashTable()
    {
        hashtable = new Hashtable<>();
    }

    public void insert(String s)
    {
        hashtable.put(s.hashCode(), s);
    }

    public boolean isWord(String s)
    {
        return hashtable.containsValue(s);
    }
}
