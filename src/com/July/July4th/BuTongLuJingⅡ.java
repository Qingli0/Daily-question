package com.July.July4th;
//动态规划
//不同路径Ⅱ

/**
 * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
 *
 * 返回机器人能够到达右下角的不同路径数量。
 */
public class BuTongLuJingⅡ {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        if(obstacleGrid[obstacleGrid.length -1][obstacleGrid[0].length -1] == 1) return 0;
        if(obstacleGrid[0][0] == 1) return 0;
        dp[0][0] = 1;
        for(int i = 1; i < obstacleGrid[0].length; i ++){
            dp[0][i] = 1;
            if(obstacleGrid[0][i] == 1){
                for(int j = i; j < obstacleGrid[0].length; j ++){
                    dp[0][j] = 0;
                }
                break;
            }
        }
        for(int i = 1; i < obstacleGrid.length; i ++){
            dp[i][0] = 1;
            if(obstacleGrid[i][0] == 1){
                for(int j = i; j < obstacleGrid.length; j ++){
                    dp[j][0] = 0;
                }
                break;
            }
        }

        for(int i = 1; i < obstacleGrid.length; i ++){
            for(int j = 1; j < obstacleGrid[0].length; j ++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
