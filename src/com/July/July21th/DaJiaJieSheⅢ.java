package com.July.July21th;
//动态规划篇
//打家劫舍Ⅲ

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 */
public class DaJiaJieSheⅢ {
    public int rob(TreeNode root) {
        int[] dp = robTree(root);
        return Math.max(dp[0],dp[1]);
    }
    public int[] robTree(TreeNode root){
        int[] dp = new int[2];
        if(root == null)
            return dp;
        int[] leftdp = robTree(root.left);
        int[] rightdp = robTree(root.right);
        //偷该父节点dp[0] 表示偷
        dp[0] = root.val + leftdp[1] + rightdp[1];
        //不偷该父节点
        dp[1] = Math.max(leftdp[0],leftdp[1]) + Math.max(rightdp[0],rightdp[1]);
        return dp;
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }