
// In this problem, we have to identify that if the claim that words are sorted in alien order is correct or not. If correct than return
// order string else return "" empty string. So here, we are first building the graph, when comparing two consecutive words at a time
// if two chars are different we get to know that first char comes before second char, so there is a relation, there is a dependency,
// therefore it is a graph problem. Keeping one map to store the dependancy(char and the list of char depending on the key char), and
// indegrees list, so we will decrement the indegree of the node by 1, if it has some dependancy. And all the independent nodes we put
// in the queue, we can start from any char that is independent(indegree as 0) so we put all in queue. Then we start the bfs, poll the
// current char, append to stringbuilder sb, and check for it's dependant nodes, reduce their indegree by 1, and if it becomes zero 
// put them in the queue.

// Time Complexity : O(nk) n length of words array and k avg length of each word
// Space Complexity : O(nk)  dependancy Hashmap 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
import java.util.*;

public class AlienDictionary {
    int[] indegrees;
    HashMap<Character, List<Character>> m;

    public String alienOrder(String[] words) {
        // Base case
        if (words == null || words.length == 0) {
            return "";
        }
        // indegrees array of length 26, each index represent a char (a-z)
        indegrees = new int[26];
        // Map for storing the char and list<char>
        m = new HashMap<>();
        // constructGraph method will fill this two data structures
        constructGraph(words);
        // Queue for bfs
        Queue<Character> q = new java.util.LinkedList<>();
        // Ans
        StringBuilder sb = new StringBuilder();
        // Iterate over the map and check if any key from map having indegree zero, put
        // them in queue
        for (Character key : m.keySet()) {
            if (indegrees[key - 'a'] == 0) {
                q.add(key);
            }
        }
        // Start bfs
        while (!q.isEmpty()) {
            // poll the current
            char curr = q.poll();
            // append to sb
            sb.append(curr);
            // Get the list of all dependant nodes(chars)
            List<Character> allChar = m.get(curr);
            // if it is null, continue
            if (allChar.size() == 0) {
                continue;
            }
            // Else for each char, reduce their indegree by 1 and if it becomes 0, put the
            // char in queue
            for (char singleChar : allChar) {
                indegrees[singleChar - 'a']--;
                if (indegrees[singleChar - 'a'] == 0) {
                    q.add(singleChar);
                }
            }
        }
        // At last if the string size is not equal to the map size, means we have not
        // got the correct order
        if (sb.length() != m.size()) {
            return "";
        }
        // Else return string
        return sb.toString();
    }

    private void constructGraph(String[] words) {
        // First go over each word, each char, put all the unique chars in map with
        // empty list as value
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!m.containsKey(words[i].charAt(j))) {
                    m.put(words[i].charAt(j), new ArrayList<>());
                }
            }
        }
        // Go over each words
        for (int i = 0; i < words.length - 1; i++) {
            // Take two consecutive words at a time
            String first = words[i];
            String second = words[i + 1];
            // Take their length
            int m1 = first.length();
            int n = second.length();
            // If the first word startswith second, that means not sorted, so clear map and
            // return
            if (first.startsWith(second) && m1 > n) {
                m.clear();
                return;
            }
            // Else compare each char
            for (int j = 0; j < n && j < m1; j++) {
                char firstChar = first.charAt(j);
                char secondChar = second.charAt(j);
                // If different
                if (firstChar != secondChar) {
                    // Increase the indegree of second char by 1
                    indegrees[secondChar - 'a']++;
                    // And add to the list in map
                    m.get(firstChar).add(secondChar);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "wrt", "wrf", "er", "ett", "rfttz" };
        AlienDictionary a = new AlienDictionary();
        System.out.println(a.alienOrder(words));
    }
}
