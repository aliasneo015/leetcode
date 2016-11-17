package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring {
    // Solution-1
    // Brute-force O(n^2)
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int current = 0;
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < s.length(); ++i) {
            current = 0;
            set.clear();
            for (int j = i; j < s.length(); ++j) {
                if (set.contains(s.charAt(j))) {
                    break;
                }

                current++;
                longest = Math.max(longest, current);
                set.add(s.charAt(j));
            }
        }

        return longest;
    } 

    // Solution 2
    // Sliding Window-1
    // Complexity.
    //  Might traverse worst case twice if the duplicate char is last char, O(2n) = O(n).
    // Space. O(min(m, n)). Where n is length of string and m is number of char in the characer set
    public int lengthOfLongestSubstring2(String s) {
        int longest = 0;
        Set<Character> set = new HashSet<Character>();
        int i = 0;
        int j = 0;
        while(i<s.length() && j<s.length()) {
            if(!set.contains(s.charAt(j))) {
                longest = Math.max(longest, j-i+1);
                set.add(s.charAt(j));
                j++;
            }
            else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return longest;
    } 

    // Solution 3
    // Sliding Window-2
    // Complexity.
    // O(n) , Space O(min(m,n))
    public int lengthOfLongestSubstring3(String s) {
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0, j=0; j < s.length(); ++j) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            longest = Math.max(longest, j - i +1); 
            map.put(s.charAt(j), j+i);  // <==  putting j+1 is the key, this is setup so that 
                                        //      when we want to skip, i points to next char when it was registerd in map
        }
        return longest;
    }
    //same solution if the char set if fixed, example 128, we can use a array instead of the Map
    // Complexity.
    // O(n) , Space O(m)
    public int lengthOfLongestSubstring4(String s) {
        int longest = 0;
        Integer[] map = new Integer[128];
        for(int i=0, j=0; j < s.length(); ++j) {
            i = Math.max(map[s.charAt(j)], i);
            longest = Math.max(longest, j - i +1); 
            map[s.charAt(j)] = j+i;  // <==  putting j+1 is the key, this is setup so that 
                                        //      when we want to skip, i points to next char when it was registerd in map
        }
        return longest;
    }
}
