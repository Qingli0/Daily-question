package com.June.June13th;
// 回溯章
//n 皇后问题

import java.util.ArrayList;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之出在同一行 或者 同一列 或者 同一斜线的棋子
 * n 皇后问题 研究的是如何将 n 个 皇后放置在 n * n 的棋盘上，并且使皇后彼此之间不能相互攻击
 */
public class NQueenProblem {
    public static List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                chessboard[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(result,path,n,chessboard,0);
        return result;
    }
    public static void backtracking(List<List<String>> result,List<String> path,int n,char[][] chessboard,int row){
        if(row == n){
            result.add(Array2List(chessboard));
            return;
        }
        for(int i = 0; i < n; i ++){
            if(isValid(chessboard,row,i,n)){
                path.add(chessboard[i].toString());
                chessboard[row][i] = 'Q';
                backtracking(result,path,n,chessboard,row + 1);
                //回溯
                chessboard[row][i] = '.';
            }
        }

    }
    public static List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
    public static boolean isValid(char[][]chessboard,int row,int column, int n){
        //第 row 行 ，第 i 列 放了一个 皇后， 现在要在三个方向判断是否合法只要一个不合法返回false
        //遍历列，行固定
        for(int j = 0; j < n; j ++){
            if(j != column && chessboard[row][j] == 'Q')
                return false;
        }
        // 遍历行，列固定
        for(int i = 0; i < n; i ++){
            if(i != row && chessboard[i][column] == 'Q')
                return false;
        }
        // 检查45度对角线
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i --, j --) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i = row - 1, j = column + 1; i >= 0 && j <= n-1; i --, j ++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;

    }
    public static void main(String[] args) {
        System.out.println(solveNQueens(8));
    }
}
