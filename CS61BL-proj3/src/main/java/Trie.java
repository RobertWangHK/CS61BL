import java.util.*;

/**
 * Created by cs61bl-jr on 7/16/16.
 */
public class Trie {
    private HashMap<Character, TrieNode> myStartingLetters = new HashMap<>();
    ArrayList<TrieNode> collect = new ArrayList<>();

    public void addDefinition(String word, long id) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        node.myNextLetters = this.myStartingLetters;
        addDefinitionHelper(word, id, node);
    }

    private void addDefinitionHelper(String word, long id, TrieNode node) {
        if (word.length() == 0) {
            node.id = id;
            node.isEnd = true;
        } else {
            if (node.myNextLetters.containsKey(word.charAt(0))) {
                addDefinitionHelper(word.substring(1), id,
                        node.getMyNextLetters().get(word.charAt(0)));
            } else {
                TrieNode newNode = new TrieNode();
                node.myNextLetters.put(word.charAt(0), newNode);
                addDefinitionHelper(word.substring(1), id,
                        node.getMyNextLetters().get(word.charAt(0)));
            }
        }
    }

    public ArrayList<Long> lookupDefinition(String word) {
        // YOUR CODE HERE
        TrieNode node = new TrieNode();
        ArrayList<Long> result = new ArrayList<>();
        node.myNextLetters = this.myStartingLetters;
        return lookupDefinitionHelper(word, node, result);
    }

    private ArrayList<Long> lookupDefinitionHelper(String word,
                                                   TrieNode node, ArrayList<Long> list) {
        if (word.length() == 0) {
            if (node.isEnd) {
                list.add(node.getId());
                return list;
            } else {
                return fetch(node, list);
            }
        } else {
            if (!node.myNextLetters.containsKey(word.charAt(0))) {
                return null;
            } else {
                return lookupDefinitionHelper(word.substring(1),
                        node.myNextLetters.get(word.charAt(0)), list);
            }
        }
    }

    private ArrayList<Long> fetch(TrieNode node, ArrayList<Long> list) {
        collect.clear();
        fetchHelper(node);
        for (TrieNode a: collect) {
            list.add(a.getId());
        }
        return list;
    }

    private void fetchHelper(TrieNode node) {
        if (node.isEnd) {
            if (node.getMyNextLetters() != null) {
                Set set = node.getMyNextLetters().keySet();
                Iterator<Character> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Character c = iterator.next();
                    TrieNode child = node.getMyNextLetters().get(c);
                    fetchHelper(child);
                }
            }
            collect.add(node);
        } else {
            Set set = node.getMyNextLetters().keySet();
            Iterator<Character> iterator = set.iterator();
            while (iterator.hasNext()) {
                Character c = iterator.next();
                TrieNode child = node.getMyNextLetters().get(c);
                fetchHelper(child);
            }
        }
    }


    private class TrieNode {
        private HashMap<Character, TrieNode> myNextLetters = new HashMap<>();
        private long id;
        private boolean isEnd = false;

        TrieNode() {

        }

        public HashMap<Character, TrieNode> getMyNextLetters() {
            return myNextLetters;
        }

        public long getId() {
            return id;
        }
    }

}
