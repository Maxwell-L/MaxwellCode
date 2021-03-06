/**
 * 51.N-Queens
 *    Hard
 * Description:
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, 
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * 
 * Example:
 * Input: 4
 * Output: [
 * [".Q..",  // Solution 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *
 * ["..Q.",  // Solution 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

class Solution {
    private void helper(List<List<String>> result, int[] col, boolean[] check, boolean[] add, boolean[] sub, int last, int count, int n){
       if(count == n){
           char[] temp = new char[n];
           List<String> list = new ArrayList<String>();
           for(int i = 0; i < n; i++){
               int num = col[i]-1;
               for(int j = 0; j < n; j++){
                   temp[j] = j == num ? 'Q' : '.';
               }
               list.add(String.valueOf(temp));
           }
           result.add(list);
           return;
        }
        for(int j = 0; j < n; j++){
            if(!check[j] && !add[j+count] && !sub[j-count-1+n] && j != last && j != last-1 && j != last+1){
                add[j+count] = true;
                sub[j-count-1+n] = true;
                col[count] = j+1;
                check[j] = true;
                helper(result, col, check, add, sub, j, count+1, n);
                check[j] = false;
				col[count] = 0;
                sub[j-count-1+n] = false;
				add[j+count] = false;
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        helper(result, new int[n], new boolean[n], new boolean[2*n - 1], new boolean[2*n - 1], -2, 0, n);
        return result;
    }
}
