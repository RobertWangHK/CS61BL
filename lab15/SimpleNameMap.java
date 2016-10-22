import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author You
 */
public class SimpleNameMap {
    ArrayList<Entry> list;
    static int module;

    public SimpleNameMap(){
        module =10;
        list = new ArrayList<Entry>(10);
    }

    /** Returns true if the map contains the KEY. */
    public boolean containsKey(String key){
        if(isValidName(key)) {
            Entry start = list.get(getModule(key));
            if(start._key.equals(key)){
                return true;
            }
            while(start.getNext()!=null){
                start = start.next;
                if(start._key.equals(key)){
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /** Returns the value for the specified KEY. */
    public String get(String key){
        if(isValidName(key)) {
            Entry start = list.get(getModule(key));
            if(start._key.equals(key)){
                return start._value;
            }
            while(start.getNext()!=null){
                start = start.next;
                if(start._key.equals(key)){
                    return start._value;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /** Put a (KEY, VALUE) pair into this map. */
    public void put(String key, String value){
        if(list.size()/list.toArray().length >= 0.75){
            list=resize(list);
        }//size is filled out elements?
        if(isValidName(key)) {
            //list.get(key.charAt(0) - 'A')._value;
            Entry temp = new Entry(key, value);
            Entry start = list.get(getModule(key));
            if(start==null){
                start = temp;
                return;
            }
            while(start.getNext()!=null){
                start = start.next;
            }
            start.next = temp;
            //list.s(key.charAt(0) - 'A', temp);
        }
    }

    /** Remove a single entry, KEY, from this table and returns true if successful. */
    public boolean remove(String key){
        if(isValidName(key)) {
            Entry start = list.get(getModule(key));
            if(start._key.equals(key)){
                list.set(getModule(key), start.next);//[key.charAt(0)-'A'];
                return true;
            }
            while(start.getNext()!=null){
                if(start.next._key.equals(key)){
                    start.setNext(start.next.next);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /** A wrapper class for holding each (KEY, VALUE) pair. */
    private static class Entry {

        /** The key used for lookup. */
        private String _key;
        /** The associated value. */
        private String _value;
        private Entry next;

        /** Create a new (KEY, VALUE) pair. */
        public Entry(String key, String value) {
            _key = key;
            _value = value;
        }

        /** Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return _key.equals(other._key);
        }

        /** Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

        public Entry getNext(){
            return next;
        }

        public void setNext(Entry r){
            next = r;
        }

    }

    /** Returns true if the given KEY is a valid name that starts with A-Z. */
    //private static boolean isValidName(String key) {
    //    return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    //}
    //private static int getModule(String key) { return (key.charAt(0)-'A')% 10;}
    private static boolean isValidName(String key) {
        return 'A'<= key.charAt(0) && key.charAt(0) <= 'Z';
    }
    private static int getModule(String key) { return (key.hashCode()& 0x7FFFFFFF )% module;}

    private static ArrayList<Entry> resize(ArrayList<Entry> list){
        ArrayList<Entry> temp = new ArrayList<Entry>(2*list.toArray().length);
        module *= 2;
        //Collections.copy(temp,list);
        for (Entry a: temp) {
            if (a==null){
                continue;
            }
            else{
                Entry curr = new Entry(a._key,a._value);
                temp.add(getModule(curr._key),curr);
                while(a.next!=null){
                    a = a.next;
                    curr = new Entry(a._key,a._value);
                    temp.add(getModule(curr._key),curr);
                }
            }
        }
        return temp;
    }

}