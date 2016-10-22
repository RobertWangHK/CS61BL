import java.util.HashMap;

public class Dictionary {

    /*
     * Since a trie node can have so many children, the children it has are
     * stored in a map.
     */
    private HashMap<Character, TrieNode> myStartingLetters;

    public Dictionary() {
        myStartingLetters = new HashMap<>();
    }

    /**
     * Associates the input word with the input definition in the dictionary.
     */
    public void addDefinition(String word, String definition) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        node.myNextLetters = this.myStartingLetters;
        addDefinitionHelper(word, definition, node);
    }

    public void addDefinitionHelper(String word, String definition, TrieNode node) {
        if (word.length() == 0) {
            node.myDefinition = definition;
        } else {
            if (node.myNextLetters.containsKey(word.charAt(0))) {
                addDefinitionHelper(word.substring(1), definition, node.getMyNextLetters().get(word.charAt(0)));
            } else {
                TrieNode newNode=new TrieNode();
                node.myNextLetters.put(word.charAt(0),newNode);
                addDefinitionHelper(word.substring(1),definition,node.getMyNextLetters().get(word.charAt(0)));
            }
        }
    }

    /**
     * Return the definition associated with this word in the Dictionary. Return
     * null if there is no definition for the word.
     */
    public String lookupDefinition(String word) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        node.myNextLetters=this.myStartingLetters;
        return lookupDefinitionHelper(word,node);
    }

    public String lookupDefinitionHelper(String word, TrieNode node) {
        if (word.length() == 0) {
            return node.getMyDefinition();
        } else {
            if (!node.myNextLetters.containsKey(word.charAt(0))) {
                return null;
            } else {
                return lookupDefinitionHelper(word.substring(1), node.myNextLetters.get(word.charAt(0)));
            }
        }
    }

    private class TrieNode {
        private HashMap<Character, TrieNode> myNextLetters;

        // Leave this null if this TrieNode is not the end of a complete word.
        private String myDefinition;

        private TrieNode() {
            myNextLetters = new HashMap<>();
        }

        // FEEL FREE TO ADD ADDITIONAL METHODS.
        public HashMap<Character, TrieNode> getMyNextLetters() {
            return myNextLetters;
        }

        public String getMyDefinition() {
            return myDefinition;
        }
    }
}