// In this problem, simply traversing through the 2 by 2 matrix and maintainin a indegrees array. As we know that for a celeb since 
// everyone knows celeb, the indegree value will be n-1, and since celeb doesnt know anyone the outdegree value will be 0. So in this
// indegree array if we find that i knows j, we decrement the indegree[i] by 1 indicating an outgoing edge, and increment the indegree[j]
// by 1, indicating an incoming edge. At end check if any index having n-1, that is our ans else -1.
// Time Complexity : O(n^2)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
public class FindACelebrity extends Relation {
    public int findCeleb(int n) {
        // Base case
        if (n == 0) {
            return -1;
        }
        // Indegree array
        int[] indegree = new int[n];
        // Loop through n*n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check for all the combinations except i==j
                if (i != j) {
                    // Check if the i knows j
                    if (knows(i, j)) {
                        // Decrement
                        indegrees[i]--;
                        // Increment
                        indegrees[j]++;
                    }
                }
            }
        }
        // Check what contains the n-1 value, return that i
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == n - 1) {
                return i;
            }
        }
        // Else -1
        return -1;
    }
}

// In this approach, we are making assumption that 0 is our celeb, than running
// a loop and checking if there is anyone to whom our
// current celeb (0) knows, if yes than making that person as the celeb, becoz 0
// is knowing someone so 0 cant be celeb. Now with this
// second celeb, we check if everyone knows him and he doenst know anyone, if
// any breach found, return -1, else return this celeb.

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
public class FindACelebrity extends Relation {
    public int findCeleb(int n) {
        // Base case
        if (n == 0) {
            return -1;
        }
        int celeb = 0;
        // Loop over and check if the first assumed celeb is knowing anyone
        for (int i = 0; i < n; i++) {
            if (i != celeb) {
                if (knows(celeb, i)) {
                    // Then 0 cannot be celeb, so change the celeb to the person whom 0 knows that
                    // is i
                    celeb = i;
                }
            }
        }
        // Now again loop over and check if this celeb knows anyone or anyone doesnt
        // know this celeb, then return -1, else return this celeb
        for (int i = 0; i < n; i++) {
            if (i != celeb) {
                if (knows(i, celeb) == 0 || knows(celeb, i) == 1) {
                    return -1;
                }
            }
        }
        return celeb;
    }
}