// In this problem, using hashmap to store the order string with it's index. Then going over the array and comparing each consecutive
// words, if there is any char that is not matching, checking its index value in map, if the first word char has lower index value
// than the second word char, than returning true, else false. Also if the chars match and first word has length greater than the second
// word, returning false.

// Time Complexity : O(nk) n length of words array and k avg length of each word
// Space Complexity : O(1) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    HashMap<Character, Integer> map;

    public boolean isAlienSorted(String[] words, String order) {
        // Base case
        if (words == null || words.length == 0) {
            return true;
        }
        map = new HashMap<>();
        // Loop and put the order string in the map
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }
        // Now loop over all words
        for (int i = 0; i < words.length - 1; i++) {
            // Take two consecutive words at a time
            String first = words[i];
            String second = words[i + 1];
            // Check if not ordered
            if (isNotOrdered(first, second)) {
                // Return false
                return false;
            }
        }
        // return true
        return true;
    }

    private boolean isNotOrdered(String first, String second) {
        // Take the length of two words
        int m = first.length();
        int n = second.length();
        // Loop over each char
        for (int i = 0; i < m && i < n; i++) {
            // Take two chars
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);
            // Check if not equal
            if (firstChar != secondChar) {
                // Check their index position in map
                if (map.get(firstChar) > map.get(secondChar)) {
                    // If first greater, means not sorted, so return true
                    return true;
                } else {
                    // Else false
                    return false;
                }
            }
        }
        // If no chars mismatch and the length of the first is greater than second, not
        // sorted, so return true else false
        return (m > n);
    }
}