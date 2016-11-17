package leetcode.medium;

import java.util.Iterator;

/**
    151. Reverse Words in a String

    Given an input string, reverse the string word by word.
    
    For example,
    Given s = "the sky is blue",
    return "blue is sky the".
    
    Update (2015-02-12):
        For C programmers: Try to solve it in-place in O(1) space.
    
    
    Clarification:
        What constitutes a word?
        > A sequence of non-space characters constitutes a word.
        Could the input string contain leading or trailing spaces?
        > Yes. However, your reversed string should not contain leading or trailing spaces.
        How about multiple spaces between two words?
        > Reduce them to a single space in the reversed string. 
 */
public class ReverseWords {
    public static class RevIterator implements Iterator<String> {
        private static char DELIM = ' ';
        private String str;
        private int pos;
        public RevIterator(String str) {
            this.str = str;
            this.pos = (str == null ? 0 : str.length()-1);
            
            //remove trailing spaces
            if (str != null) {
                while(pos >= 0 &&  str.charAt(pos) == DELIM) {
                    pos--;
                }
            }
        }
        
        @Override
        public boolean hasNext() {
            return str != null && pos >= 0;
        }
        
        @Override
        public String next() {
            if (pos < 0) {
                throw new RuntimeException("invalid state, pos < 0");
            }
            
            int i=pos;
            while (i>=0 && str.charAt(i) != DELIM) {
                --i;
            }
          
            if (i < pos) {
                String res = str.substring(i+1, pos+1);
                //adjust pos to next string (rev order, skip spaces)
                while (i>=0 && str.charAt(i) == DELIM) {
                    i--;
                }
                pos = i;
                return res;
            }
            return null;
        }
    } 
    
    public static String SPACE = " ";
    
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        RevIterator iter = new RevIterator(s);
        boolean first = true;
        while(iter.hasNext()) {
            if (!first) {
                sb.append(SPACE);
            } else {
                first = false;
            }
            
            String res = iter.next();
            if (res != null) {
                sb.append(res);
            }
        }
     
        return sb.toString();   
    }
}
