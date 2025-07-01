package com.July.July1st;
//贪心篇
//监控二叉树


/**
 * 给定一个二叉树，我们在树的节点上安装摄像头
 * 节点上的每个摄像头都可以监视其 父对象，自身以及直接子对象
 * 计算监控树上所有节点 所需的最小摄像头数量。
 */

/**
 * 定义三种状态，0-无覆盖  1-有摄像头 2-已经覆盖了
 */
public class JianKongErChaShu {
    int result = 0;
    public int minCameraCover(TreeNode root) {
        if(getCamera(root) == 0){
            //还遗留了一个根节点没有覆盖，摄像头的数量得 +1
            result ++;
        }
        return result;
    }
    public int getCamera(TreeNode root){
        if(root == null) return 2;
        //采用后序遍历，看看该父节点的左右子树是一个什么情况
        int left = getCamera(root.left);
        int right = getCamera(root.right);
        //case 1: 左右子树都覆盖了,父节点为 0
        if(left == 2 && right == 2) return 0;
        //case 2: 左右孩子任意一个为无覆盖,父节点为 1
        if(left == 0 || right == 0){
            result ++;
            return 1;
        }
        //case 3: 左右孩子任意孩子有摄像头，父节点为已经覆盖了
        if(left == 1 || right == 1){
            return 2;
        }
        return -1;
    }

    public static void main(String[] args) {

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