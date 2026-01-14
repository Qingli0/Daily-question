package com.August;



import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class test {
    /*
    有效的字母异位词
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] charArray = s.toCharArray();
        char[] charArray2 = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            //说明这个字符串没有放进来过
            if (!map.containsKey(c)) map.put(c, 1);
                //如果已经有了
            else
                map.put(c, map.get(c) + 1);
        }
        for (char c : charArray2) {
            //已经没有这个了，说明不匹配
            if (!map.containsKey(c)) return false;
            Integer count = map.get(c);
            if (count == 1) map.remove(c);
            else {
                map.put(c, map.get(c) - 1);
            }
        }
        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a']++;
        }
        for (int c : t.toCharArray()) {
            record[c - 'a']--;
        }
        for (int c : record) {
            if (c != 0) return false;
        }
        return true;

    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {// [left,right]
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                //说明 target 在左边
                right = mid - 1;
            } else if (nums[mid] < target) {
                //说明 target 在右边
                left = mid + 1;
            } else return mid;
        }
        //在循环里面都没有找到
        return -1;
    }

    public static int search2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {// [left,right)
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                //说明 target 在左边
                right = mid;
            } else if (nums[mid] < target) {
                //说明 target 在右边
                left = mid + 1;
            } else return mid;
        }
        //在循环里面都没有找到
        return -1;
    }

    /**
     * 原地移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        //这道题明显的双指针做法
        //当left 下标的元素等于 val 的时候，右指针就往后走，走到第一个非val的元素，num[left] = num[right]
        //最后返回left
        int left = 0, right = 0;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;

    }

    public static int[] sortedSquares(int[] nums) {
        //如果想用时间复杂度为o(n)的,使用排序肯定是不行的，因为时间Arrays.sort()的时间复杂度是O(nlogn)
        //使用双指针算法，从两端开始往里面移动，最大的肯定在两端
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1, k = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[k--] = nums[left] * nums[left];
                left++;
            } else {
                res[k--] = nums[right] * nums[right];
                right--;
            }
        }

        return res;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        //采用滑动窗口
        //如果窗口内的值小于 target 右指针右移，窗口值大于等于target 左指针右移
        int left = 0, right;
        int res = nums.length + 1, sum = 0;
        for (right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return res;
    }

    // 将数字插入一个已经排序好的位置
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else return mid;
        }
        //都没找到
        return left;
    }


    //写一个算法来判断是不是快乐数
    public static boolean isHappy(int n) {
        //这道题的宗旨就是在求 快乐数的过程中，看看是否有哪一个数组重复了，一旦有重复的，直接return false
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getN(n);
        }
        return !set.contains(n);
    }

    public static int getN(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }

    //两数之和
    public static int[] twoSum(int[] nums, int target) {
        //可以设计一个算法复杂度为o(n)
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> ma = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            ma.put(nums[i], i);
        }
        if (target % 2 == 0) {
            int temp = target / 2;
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
            List<Integer> list = new ArrayList<>();
            if (ma.get(temp) != null && map.get(temp) >= 2) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == temp) {
                        list.add(i);
                        if (list.size() == 2)
                            return list.stream().mapToInt(Integer::intValue).toArray();
                    }
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            ma.remove(nums[i]);
            if (ma.get(target - nums[i]) != null)
                return new int[]{ma.get(target - nums[i]), i};
            ma.put(nums[i], i);
        }

        return new int[]{1, 1};
    }

    //两数之和的解法2
    public static int[] twoSum2(int[] nums, int target) {
        //上面的解法1，为什么我在使用 map 找map.get(target - nums[i])之前用一个 target / 2
        //是因为当数组中有两个相同的元素，如果按nums[i] 为key  value 就不能存多个
        //但是解法2 不用考虑这个问题，它从前往后遍历，比如 2 , 2 ,5 ,8 target = 4
        //第一个 2 存入进去，遍历到第二个 2 的时候，就可以获取答案，他是遍历一个，放一个到map中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.get(temp) != null) {
                return new int[]{map.get(temp), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{1, 1};
    }

    //四个数组里面四个数相加 = 0
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                map.put(nums1[i] + nums2[j], map.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int temp = -(nums3[i] + nums4[j]);
                if (map.get(temp) != null) {
                    res += map.get(temp);
                }
            }
        }
        return res;
    }

    //赎金信
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] records = new int[26];
        for (char c : ransomNote.toCharArray()) {
            records[c - 'a']++;
        }
        for (char c : magazine.toCharArray()) {
            if (records[c - 'a'] > 0) {
                records[c - 'a']--;
            }
        }
        for (int c : records) {
            if (c != 0) return false;
        }
        return true;
    }

    //一个数组里面挑三个 和为 0 并且保证不重复
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            //对 i 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {//这个地方就是找到了
                    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    res.add(list);
                    //去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }

        return res;
    }

    //四数之和，并且给你一个target 不一定是0,多嵌套一个for循环
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > target && nums[k] >= 0) break;
            //对k去重
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            for (int i = k + 1; i < nums.length; i++) {
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) break;
                if (i > k + 1 && nums[i] == nums[i - 1]) continue;
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    long sum = (long) (nums[i] + nums[left] + nums[right] + nums[k]);
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        list.add(nums[k]);
                        res.add(list);
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public static void test() {
        Deque<Integer> stack = new ArrayDeque<>();
        Queue<Integer> queue = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int size = stack.size();
        System.out.println("栈顶元素：" + stack.peek() + " 栈中元素个数：" + stack.size());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        for (int i = 0; i < size; i++) {
            Integer pop = queue.poll();
            System.out.println(pop);
        }
        PriorityQueue<int[]> max = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        max.offer(new int[]{1, 2});
        max.offer(new int[]{3, 4});
        max.offer(new int[]{1, 6});
        int size1 = max.size();
        for (int i = 0; i < size1; i++) {
            int[] poll = max.poll();
            System.out.println(Arrays.toString(poll));
        }

    }

    public static boolean isValid(String s) {
        if(s.length() % 2 != 0) return false;
        Deque<Character> stack = new ArrayDeque<>();
        char[] array = s.toCharArray();
        for(char c : array) {
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if(stack.isEmpty()) return false;
            Character pop = stack.pop();
            switch (c){
                case ')':
                    if(pop != '(') return false;
                    break;
                case ']':
                    if(pop != '[') return false;
                    break;
                case '}':
                    if(pop != '{') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static int evalRPN(String[] tokens) {
        //简单的很，用栈就可以解决
        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : tokens) {
            if (!"+-*/".contains(s)) {
                int num = Integer.parseInt(s);
                stack.push(num);
                continue;
            }
            int result;
            switch (s) {
                case "+":
                    int op2 = stack.pop();
                    int op1 = stack.pop();
                    result = op1 + op2;
                    stack.push(result);
                    break;
                case "-":
                    int op4 = stack.pop();
                    int op3 = stack.pop();
                    result = op3 - op4;
                    stack.push(result);
                    break;
                case "*":
                    int op6 = stack.pop();
                    int op5 = stack.pop();
                    result = op5 * op6;
                    stack.push(result);
                    break;
                case "/":
                    int op8 = stack.pop();
                    int op7 = stack.pop();
                    result = op7 / op8;
                    stack.push(result);
                    break;

            }

        }
        return stack.pop();
    }
    //求得在每个窗口中的最大值

    /**
     * 求每个滑动窗口中的最大值，使用单调队列，在队列里面的元素是单调递减的
     * 入队规则push() 要入列 元素 a 先将所有小于a的入口元素弹出
     *
     * @param nums
     * @param k
     * @return
     */

    public static int[] maxSlidingWindow(int[] nums, int k) {
        //自己写一个数据结构来实现单调栈
        int res[] = new int[nums.length - k + 1];
        int j = 0;
        MyDeque deque = new MyDeque();
        for (int i = 0; i < k; i++) {
            deque.push(nums[i]);
        }
        res[j++] = deque.peek();
        int left = k;
        for (left = k; left < nums.length; left++) {
            deque.poll(nums[left - k]);
            deque.push(nums[left]);
            res[j++] = deque.peek();
        }
        return res;
    }

    //前k个高频元素
    //使用大根堆，先统计每个数字出现的频率，根据频率从大到小建立大根堆
    //取前面k个
    public static int[] toKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } else return b[1] - a[1];
        });
        int[] res = new int[k];
        //这个map用来存放nums[]里面每个数 出现的个数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[][] tmp = new int[map.size()][2];
        int i = 0;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            tmp[i][0] = entry.getKey();
            tmp[i][1] = entry.getValue();
            priorityQueue.offer(tmp[i]);
            i++;
        }
        for (int j = 0; j < k; j++) {
            int[] poll = priorityQueue.poll();
            res[j] = poll[0];
        }
        return res;
    }

    //用迭代法来先序递归遍历树
    public List<Integer> preorderTraversal(TreeNode root) {
        //用栈来实现
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        //弹出一个节点，然后将这个节点的右子树和左子树加入栈
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }

        return res;
    }

    //用迭代法来中序递归遍历树
    public List<Integer> postorderTraversal(TreeNode root) {
        //用栈来实现
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        //弹出一个节点，然后将这个节点的右子树和左子树加入栈
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            //当前的指针所指向的节点不是空 或者栈不为空往下走
            if (cur != null) {
                //如果当前指针指向的节点不为空，入栈，一直往左走
                stack.push(cur);
                cur = cur.left;
            } else {
                //说明走到头了
                cur = stack.pop();
                res.add(cur.val);//加入遍历结果集合
                cur = cur.right;//往这个被弹出来的节点的右边遍历
            }
        }
        return res;
    }

    //树的层序遍历，使用队列，每次出队一个节点，把这个节点的左右孩子加入队列中
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();//在这里记录每一层的节点个数
            while (len > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                len--;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                Node node = queue.poll();
                list.add(node.val);
                if (root.children != null) {
                    for (Node child : node.children) {
                        queue.add(child);
                    }
                }
                len--;
            }
            res.add(list);
        }
        return res;
    }

    //遍历所有的路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        binaryTreePaths_traversal(root, res, path);
        return res;
    }

    public void binaryTreePaths_traversal(TreeNode root, List<String> res, List<Integer> path) {
        //第一次递归从根节点开始肯定不为空
        path.add(root.val);
        if (root.left == null && root.right == null) {
            //终止条件，不遍历空节点
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i) + "->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
            return;
        }
        //回溯
        if (root.left != null) {
            binaryTreePaths_traversal(root.left, res, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            binaryTreePaths_traversal(root.right, res, path);
            path.remove(path.size() - 1);
        }
    }

    int sumOfLeftLeaves = 0;

    //左叶子之和
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum = root.left.val;
        }
        return sumOfLeftLeaves(root.right) + sumOfLeftLeaves(root.right) + sum;
    }
    //这是真正的求深度，从上往下，先序遍历
    int res = 0;
    public int getMaxDepth(TreeNode root) {
       if (root == null) return 0;
       getMax(root, 1);
       return res;
    }
    public void getMax(TreeNode root,int depth){
        res = depth > res ? depth : res;
        if(root.left == null && root.right == null) {
            //左右都为空
            return ;
        }
        if(root.left != null) {
            getMax(root.left, depth + 1);
        }
        if(root.right != null) {
            getMax(root.right, depth + 1);
        }
        return;
    }
    //是否是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return getHeight(root) != -1;
    }
    public int getHeight(TreeNode root){
        if (root == null) return 0;
        int left = getHeight(root.left);
        if (left == -1) return -1;
        int right = getHeight(root.right);
        if (right == -1) return -1;
        //根据左右子树的差值返回结果
//        if (Math.abs(left - right) > 1) {
//            return -1;
//        }
//        return Math.max(left, right) + 1;
        return Math.abs(left - right) > 1 ? -1 :Math.max(left, right) + 1;
    }
    //找左下角的值
    //这道题是否是可以用层序遍历来写
    public int findBottomLeftValue(TreeNode root) {
        int res = Integer.MIN_VALUE;
        if (root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int len2 = queue.size();
            while (len > 0) {
                TreeNode poll = queue.poll();
                if(len2 == len) res = poll.val;
                if(poll.left != null) queue.add(poll.left);
                if(poll.right != null) queue.add(poll.right);
                len --;
            }
        }
        return res;
    }
    //找左下角的值
    //采用回溯法
    //因为要一直往下找，并且找的一定是最左边的，往左下角找，同一层的话只会找一次，就是左下角
    int MaxDepth = 0;
    int result = Integer.MIN_VALUE;
    public void traversalFindBottomLeftValue(TreeNode root,int depth) {
        if(root.right == null && root.left == null) {
            if (depth > MaxDepth) {
                //这是最深的一层了
                MaxDepth = depth;
                result = root.val;
                return;
            }
        }
        //回溯
        if(root.left != null){
            depth ++;
            traversalFindBottomLeftValue(root.left, depth);
            depth --;
        }
        if(root.right != null){
            depth ++;
            traversalFindBottomLeftValue(root.right, depth);
            depth --;
        }
    }
    //路径总和，判断该树中是否存在
    //根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum
    //肯定是使用回溯
    boolean isHasPathSum = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        traversalHasPathSum(root, targetSum);
        return isHasPathSum;

    }
    public void traversalHasPathSum(TreeNode root, int targetSum){
        //终止节点要是叶子节点
        if(root.left == null && root.right == null) {
            if (targetSum - root.val == 0) {
                isHasPathSum = true;
                return;
            }
        }
        //回溯
        if(root.left != null){
            targetSum -= root.val;
            traversalHasPathSum(root.left, targetSum);
            targetSum += root.val;
        }
        if(root.right != null){
            targetSum -= root.val;
            traversalHasPathSum(root.right, targetSum);
            targetSum += root.val;
        }
        return;
    }
    //从中序与后序遍历序列构造二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTreetraversal(inorder,postorder);
    }
    public TreeNode buildTreetraversal(int[] inorder, int[] postorder) {
        //数组长度为0,没有节点了
        if (inorder.length == 0) return null;
        //获取后序遍历序列的最后一个值，就是当前子树的根节点
        int rootValue = postorder[postorder.length - 1];
        TreeNode treeNode = new TreeNode(rootValue);
        //如果分割到最后只有一个节点直接返回
        if(inorder.length == 1) return treeNode;
        // 找到中序数组中的 rootValue所在的下标
        int inorderIndex;
        for (inorderIndex = 0; inorderIndex < inorder.length; inorderIndex++) {
            if (inorder[inorderIndex] == rootValue) break;
        }
        //对 inorder 数组进行分割 [0,inorderIndex) 和[inorderIndex,end)
        int[] left_order = Arrays.copyOfRange(inorder, 0, inorderIndex);
        int[] right_order = Arrays.copyOfRange(inorder, inorderIndex + 1, inorder.length);
        //中序数组少了一个 order[inorderIndex] ,后序数组也要少最后一个
        int[] left_post = Arrays.copyOfRange(postorder, 0, left_order.length);
        int[] right_post = Arrays.copyOfRange(postorder, left_order.length , postorder.length - 1);

        treeNode.left = buildTreetraversal(left_order, left_post);
        treeNode.right = buildTreetraversal(right_order, right_post);
        return treeNode;
    }
    //最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return travseral(nums);
    }
    public TreeNode travseral(int[] nums) {
        if(nums.length == 0) return null;
        int max = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if(max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        TreeNode treeNode = new TreeNode(nums[index]);
        treeNode.left = travseral(Arrays.copyOfRange(nums,0,index));
        treeNode.right = travseral(Arrays.copyOfRange(nums,index + 1,nums.length));
        return treeNode;
    }
    //合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return null;
        if(root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }else {
            //两边的节点都不为空
            root1.val += root2.val;
        }
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
    //二叉搜索树中的搜索
    TreeNode resultNode = null;
    public TreeNode searchBST(TreeNode root, int val) {
        searchNode(root,val);
        return resultNode;
    }
    public void searchNode(TreeNode root, int val) {
        if (root == null) return;
        if (root.val == val) {
            resultNode = root;
        }
        if(root.val > val) { searchNode(root.left,val);}
        if(root.val < val) { searchNode(root.right,val);}
        return;
    }
    long Max = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        boolean leftValue = isValidBST(root.left);
        if(root.val > Max) Max = root.val;
        else return false;
        boolean rightValue = isValidBST(root.right);
        return leftValue && rightValue;
    }

    //二叉搜索树的最小绝对差
    public int getMinimumDifference(TreeNode root) {
        int result = Integer.MAX_VALUE;
        List<Integer> tree = new ArrayList<>();
        travseralTree(root,tree);
        int[] array = tree.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(array);
        for(int i = 0,j = 1;i < array.length -1 && j < array.length;i++,j ++){
            if(array[j] - array[i] < result) result = array[j] - array[i];
        }
        return result;

    }
    public void travseralTree(TreeNode root,List<Integer> tree) {
        if(root == null) return;
        tree.add(root.val);
        travseralTree(root.left,tree);
        travseralTree(root.right,tree);
    }
    //将有序树转化成平衡 二叉搜索树，nums里面已经排好序了
    public TreeNode sortedArrayToBST(int[] nums) {
//        if(nums.length == 0) return null;
//        if(nums.length == 1) return new TreeNode(nums[0]);
//        int index =  nums.length / 2;
//        TreeNode root = new TreeNode(nums[index]);
//        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, index));
//        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, index + 1, nums.length));
        return sortedToBST(nums,0,nums.length - 1);

    }
    public TreeNode sortedToBST(int[] nums, int start, int end) {
        if(start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = sortedToBST(nums,start,mid-1);
        TreeNode right = sortedToBST(nums,mid+1,end);
        root.left = left;
        root.right = right;
        return root;
    }
    //字母异位词分组
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            char[] string = str.toCharArray();
//            Arrays.sort(string);
//            String key = string.toString();
//            map.computeIfAbsent(key,k -> new ArrayList<>()).add(str);
//
//        }
        //解法二 不使用排序，而是将字母出现的次序的数组作为key
        for (String str : strs) {
            int[] records = new int[26];
            for(int i = 0; i < str.toCharArray().length; i++) {
                records[str.charAt(i) - 'a'] ++;
            }
            StringBuilder sb = new StringBuilder();
            //将#0#1#0#0#1....#作为key
            for(int i = 0; i < 26; i++) {
                sb.append("#").append(records[i]);
            }
            map.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
    public int longestConsecutive(int[] nums) {
        int maxLen = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        for(int num : nums) {
            if(!set.contains(num - 1)) {
                //说明这个数组一定可以当成起点
                int count = 1;
                int cur = num;
                while (set.contains(cur + 1)) {
                    count++;
                    cur ++;
                }
                maxLen = Math.max(maxLen, count);
            }
        }

        return maxLen;

    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;
        //last 是索引最大的位置
        int last = s.length() - p.length();
        //步长
        int step = p.length();
        int records[] = {};

        for (int i = 0; i <= last; i++) {
            records = new int[26];
            for (int j = 0; j < p.length(); j++) {
                records[p.charAt(j) - 'a']++;
            }
            String substring = s.substring(i, i + step);
            if (isAnangrams(substring, records)) {
                res.add(i);
            }

        }
        return res;
    }
    public static boolean isAnangrams(String s,int[] records) {
        for (int i = 0; i < s.length(); i++) {
            records[s.charAt(i) - 'a'] --;
        }
        for (int i = 0; i < records.length; i++) {
            if (records[i] != 0) return false;
        }
        return true;
    }
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 1) return 1;
        int max = 0;
        int left = 0;
        int right = 1;
        while(right < s.length()){
            String substring = s.substring(left, right);
            if(!substring.contains(String.valueOf(s.charAt(right)))){
                right ++;
                max = Math.max(max,right - left);
            }else {
                //说明前面已经有一个字符
                left ++;
            }
        }
        return max;
    }
    //感觉这一题需要用到栈来实现，双指针，单调栈。
    //保持这个栈从大到小排列，如果当前要入栈的高度 要大于栈顶的元素，弹出栈顶元素
    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0,right = height.length - 1;
        while(left < right){
            int width = right - left;
            int h = Math.min(height[right],height[left]);
            max = Math.max(max,width * h);

            //丢掉短板
            if(height[left] < height[right]) left ++;
            else right --;
        }
        return max;
    }
    //接雨水，这道题使用单调栈来求解，
    //保持这个栈从大到小排列，如果当前要入栈的高度 要大于栈顶的元素，弹出栈顶元素
    //将所积的雨水层层累加
    public static int trap(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        //压入栈的是数组的下标
        stack.push(0);
        for(int i = 1; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                //形成了一个凹字形，
                //pop是栈顶
                Integer pop = stack.pop();
                //top是弹出一个栈之后的栈顶
                Integer top = stack.peek();
                if(top != null) {
                    int h = Math.min(height[i], height[top]) - height[pop];
                    sum += h * (i - top - 1);
                }
            }
            stack.push(i);
        }
        return  sum;

    }

    //每日温度，这道题虽然是中等，但是对于写了接雨水的单调栈的题目来说就很简单了
    //这个栈里面保持递减的顺序
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for(int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        return res;
    }
    //和为K的子数组
    //感觉这道题要使用滑动窗口
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int num : nums){
            sum += num;
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum,map.getOrDefault(sum,0) + 1);
        }
        return count;
    }
    //最小覆盖字串
    public static String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        //need集合是 t 字符串 每个字符多少个
        Map<Character, Integer> need = new HashMap<>();
        //window集合是当前的滑动窗口已经有哪些字符并且每种字符多少个
        Map<Character, Integer> window = new HashMap<>();
        //对need进行初始化
        for (char c : t.toCharArray()) {
            need.put(c,need.getOrDefault(c,0) + 1);
        }
        // 定义一些变量
        // vaild 是当前滑动窗口中字符和need 字符相同的个数，如果vaild == need.size() 说明这个窗口满足要求，先更新结果，再缩进看看有没有更小的窗口
        //start 记录最后的滑动窗口开始的索引，len 记录左右指针的跨度
        int vaild = 0,left = 0,right = 0,start = 0,len = Integer.MAX_VALUE;
        while (right < s.length()){
            char c = s.charAt(right);
            right ++;
            if(need.containsKey(c)){
                //need中有这个字符，那就要加入
                window.put(c,window.getOrDefault(c,0) + 1);
                if(need.get(c).equals(window.get(c))){
                    //滑动窗口中的这个c字符的数量和need 窗口是相等的
                    vaild ++;
                }
            }

            //如果vaild == need.size()先更新结果，然后缩进窗口，看看有没有更优解
            while (vaild == need.size()){
                if(right - left < len){
                    len = right - left;
                    start = left;
                }

                //寻找更优解
                char d = s.charAt(left);
                left ++;
                //跟上面的对仗
                if(need.containsKey(d)){
                    //只有need.get(d).equals(window.get(d))相等的时候才需要vaild --
                    if(need.get(d).equals(window.get(d))){
                        vaild --;
                    }
                    //window中的某个字符少一个
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
    //最大子数组和
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums.length == 1) return nums[0];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] + dp[i- 1] < 0 || dp[i- 1] < 0){
                dp[i] = nums[i];
            }else if(nums[i] + dp[i- 1] >= 0){
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }
    //合并区间
    /*
    这道题要自定义排序，其实排好序之后就很简单了。
    根据区间的左端点排序，
     */
    public static int[][] merge(int[][] intervals) {
        //按照左端点从小到大排序，如果左端点相同，按照右端点从小到大排序
        Arrays.sort(intervals,(a,b) ->{
            if(a[0] == b[0]){
                return a[1] - b[1];
            }else return a[0] - b[0];
        });
        List<int[]> list = new ArrayList<>();
        int left = 0,right = 1;
        while (right < intervals.length){
            if(intervals[left][1] >= intervals[right][0]){
                //前面区间的 右端点 >= 后面区间的左端点，合并
                intervals[right][0] = intervals[left][0] ;
                intervals[right][1] = Math.max(intervals[right][1], intervals[left][1]);
            }else {//不能合并
                //直接加入到集合当中
                list.add(intervals[left]);
            }
            left ++;
            right++;
        }
        list.add(intervals[right - 1]);
        return list.toArray(new int[list.size()][]);
    }

    /*
    除自身以外数组的乘积
     */
    public static int[] productExceptSelf(int[] nums) {
        //优化之后只需要一个数组进行累加
        int[] result = new int[nums.length];
        //初始化result[0] = 1,result[nums.length - 1] = 1
        result[0] = 1;
        result[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i ++){
         result[i] = result[i - 1] * nums[i - 1];
        }
        //这样的是错误的，这样跟之前的back[]数组有什么区别呢？这样做不是直接覆盖了原来的result数组，变成了back[]数组吗
//      for(int i = nums.length - 2; i >= 0; i --){
//          result[i] = result[i + 1] * nums[i + 1];
//      }
        int right = 1;
        for(int i = nums.length - 1; i >= 0; i --){
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }

    //缺失的第一个正数
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i ++){
            //值为 x 要交换到 nums[x - 1]
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
                 int correctIndex = nums[i] - 1;
                 int temp = nums[i];
                 nums[i] = nums[correctIndex];
                 nums[correctIndex] = temp;
            }
        }
        for(int i = 0; i < n; i ++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return n + 1;
    }
    //矩阵置零
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //第一列是否有0
        boolean col0 = false;
        //第一行是否有0
        boolean row0 = false;
        //Step 1️⃣
        for(int i = 0; i < m; i ++){
            if(matrix[i][0] == 0){
                col0 = true;
                break;
            }
        }
        for(int j = 0; j < n; j ++){
            if(matrix[0][j] == 0){
                row0 = true;
                break;
            }
        }
        //Step 2️⃣ 从下标为1开始遍历
        for(int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                if(matrix[i][j] == 0){
                    //现在每一行的或某一列需要置零的地方都是0了
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //Step 3️⃣：根据标记，置零内部矩阵,现在每一行的或某一列需要置零的地方都是0了
        //行置零
        for(int i = 1; i < m; i ++){
            if(matrix[i][0] == 0){
                for(int j = 1; j < n; j ++){
                    matrix[i][j] = 0;
                }
            }
        }
        //列置零
        for(int j = 1; j < n; j ++){
            if(matrix[0][j] == 0){
                for(int k = 1; k < m; k ++){
                    matrix[k][j] = 0;
                }
            }
        }
        //Step 4️⃣：最后处理第一行和第一列
        if(col0){
            for(int i = 0; i < m; i ++){
                matrix[i][0] = 0;
            }
        }
        if(row0){
            for(int j = 0; j < n; j ++){
                matrix[0][j] = 0;
            }
        }


    }
    //旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //矩阵转置，按照对角线对称
        for(int i = 0; i < n; i ++){
            for(int j = i + 1; j < n; j ++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //每一行反转
        for(int i = 0; i < n; i ++){
            int left = 0,right = n - 1;
            while (left <= right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
    //组合
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int startIndex = 1;
        zuhe_backtracking(n,k,res,path,startIndex);
        return res;
    }
    static void zuhe_backtracking(int n, int k, List<List<Integer>> res, List<Integer> path,int startIndex){
        //终止条件
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        //这一层每一个孩子都要走一次回溯，startIndex 是这一层从哪里开始取
        for(int i = startIndex; i <= n; i ++){
            path.add(i);
            zuhe_backtracking(n,k,res,path,i + 1);
            path.remove(path.size()-1);
        }

    }
    static int sum = 0;
    //组合总和
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,path,candidates,target,0);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int[] candidates,int target,int startIndex){
        if(target == sum){
            result.add(new ArrayList<>(path));
            return;
        }
        if(sum > target) return;
        for(int i = startIndex; i < candidates.length; i ++){
            sum += candidates[i];
            path.add(candidates[i]);
            backtracking(result,path,candidates,target,i);
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
    //组合总和Ⅲ
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,path,k,n,1);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int k,int n,int startIndex) {
        if (n == sum && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > n) return;
        for (int i = startIndex; i <= 9 + path.size() - k + 1; i++) {
            sum += i;
            path.add(i);
            backtracking(result, path, k, n, i + 1);
            path.remove(path.size() - 1);
            sum -= i;
        }
    }
    //组合总和Ⅱ
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combinationSum2_backtracking(result,path,candidates,target,0);
        return result;
    }
    public static void combinationSum2_backtracking(List<List<Integer>> result,List<Integer> path,int[] candidates,int target,int startIndex){
        if(sum > target) return;
        if(sum == target){
            result.add(new ArrayList<>(path));
        }
        for(int i = startIndex; i < candidates.length; i ++){
            //去重
            if(i > startIndex && candidates[i] == candidates[i - 1])continue;
            sum += candidates[i];
            path.add(candidates[i]);
            combinationSum2_backtracking(result,path,candidates,target,i + 1);
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
    //电话号码的字母组合
    static String nums[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        List<Character> path = new ArrayList<>();
        char[] charArray = digits.toCharArray();
        backtracking(res,path,charArray,0);
        return res;
    }
    public static void backtracking(List<String> res,List<Character> path,char[] charArray,int level){
        if(path.size() == charArray.length){
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            res.add(new String(sb));
            return;
        }
        //当前遍历的层数 比如 "23" charArray[0] = '2';charArray[0] - '0' = 2
        int x = charArray[level] - '0';
        String s = nums[x];//s == "abc"
        for(int i = 0; i < s.length(); i ++){
            path.add(s.charAt(i));
            backtracking(res,path,charArray,level + 1);
            path.remove(path.size()-1);
        }

    }
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(result,path,s,0);
        return result;
    }
    public static void backtracking(List<List<String>> result,List<String> path,String s,int startIndex){
        if(startIndex >= s.length()){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex; i < s.length();i ++){
            if(isPalindrome(s,startIndex,i)){
                //如果这一段切割是回文，获取这一段
                String str = s.substring(startIndex,i + 1);
                path.add(str);
            }else{
                continue;//相当于是剪枝
            }
            //回溯
            backtracking(result,path,s,i + 1);
            path.remove(path.size() - 1);
        }

    }
    //是否是回文
    public static boolean isPalindrome(String s,int start,int end){
        int i = start,j = end;
        char[] ch = s.toCharArray();
        while(i < j){
            if(ch[i] != ch[j])
                return false;
            else {
                i ++;
                j --;
            }
        }
        return true;
    }
    //子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,nums,path,0);
        return result;
    }
    //不重复，元素互不相同，所有的节点都是其结果，不仅仅是叶子节点，但是一定要保证，从前往后，并且每一个元素只能用一次
    public static void backtracking(List<List<Integer>> result,int[] nums,List<Integer> path,int startIndex){
        //path第一次进来是null
        result.add(new ArrayList<>(path));
        for(int i = startIndex; i < nums.length; i ++){
            path.add(nums[i]);
            backtracking(result,nums,path,i + 1);
            path.remove(path.size() - 1);
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permute_backtracking(result,nums,path,visited);
        return result;
    }
    public static void permute_backtracking(List<List<Integer>> result,int[] nums,List<Integer> path,boolean[] visited){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i ++){
            if(visited[i]) continue;
            path.add(nums[i]);
            visited[i] = true;
            permute_backtracking(result,nums,path,visited);
            visited[i] = false;
            path.remove(path.size() - 1);
        }

    }
    //括号生成
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        List<Character> path = new ArrayList<>();
        g_backtracking(result,path,0,0,n);
        return result;
    }
    public static void g_backtracking(List<String> result,List<Character> path,int left,int right,int n){
        if(path.size() == n * 2){
            StringBuilder sb = new StringBuilder();
            for (char c : path){
                sb.append(c);
            }
            result.add(new String(sb));
            return;
        }
        //当left < n的时候，可以加入左括号
        if(left < n){
            path.add('(');
            g_backtracking(result,path,left + 1,right,n);
            path.remove(path.size() - 1);
        }
        //当right < left 的时候可以加入右括号
        if(right < left){
            path.add(')');
            g_backtracking(result,path,left,right + 1,n);
            path.remove(path.size() - 1);
        }
    }
    //单词搜索
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        //对于board中的每一个元素都要去遍历寻找word[0],看看从board[i][j]开始找word[0]，能不能覆盖整个word
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(backtracking(board,word,i,j,0)) return true;
            }
        }
        return false;
    }
    public static boolean backtracking(char[][] board, String word,int i,int j,int k){
        //i 和 j 是board[][]的坐标

        //当前坐标越界了，或者当前的word[k] 中的元素和board[][]中的元素不相等，不用往后面找了，当前分支无答案
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(k)) return false;
        if(k == word.length() - 1){
            //k 是word字符串的匹配的下标的指针，k == word.length - 1说明匹配到了
            return true;
        }
        //备份
        char temp = board[i][j];
        //'#'和任何字符都不匹配，说明已经使用过了
        board[i][j] = '#';
        //递归，四个方向都要寻找到
        boolean flag =  backtracking(board,word,i + 1,j,k+1) ||
                        backtracking(board,word,i - 1,j,k+1) ||
                        backtracking(board,word,i,j + 1,k+1) ||
                        backtracking(board,word,i,j - 1,k+1);
        //回溯
        board[i][j] = temp;

        return flag;
    }
    //n皇后问题
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] checkboard = new char[n][n];
        //给棋盘初始化
        for (char[] chars : checkboard) {
            Arrays.fill(chars, '.');
        }
        backtracking(result,checkboard,n,0);
        return result;
    }
    public static void backtracking(List<List<String>> result,char[][] checkboard, int n, int row) {
        //终止条件，遍历到棋盘的最后一行，只要遍历到了棋盘的最后一行一定是结果，因为不满足的被剪枝了
        if(row == n){
            //将棋盘加入到result中
            List<String> path = new ArrayList<>();
            for (char[] chars : checkboard) {
                path.add(new String(chars));
            }
            result.add(path);
            return;
        }
        //递归,到了某一行，开始从左往右放皇后
        for(int col = 0; col < n; col ++){
            //合法才往后走，不然直接不走，剪枝
            if(isQueensValid(checkboard,row,col,n)){
                checkboard[row][col] = 'Q';
                backtracking(result,checkboard,n,row + 1);
                //还原
                checkboard[row][col] = '.';
            }
        }
    }

    private static boolean isQueensValid(char[][] checkboard, int row, int col, int n) {
        //同一列是否有皇后
        for(int i = 0; i < n; i ++){
            if(checkboard[i][col] == 'Q') return false;
        }
        //左上角45°
        for(int i = row - 1,j = col - 1; i >= 0 && j >= 0; i --,j --){
            if(checkboard[i][j] == 'Q') return false;
        }
        //右上角135°*
        for(int i = row - 1,j = col + 1; i >= 0 && j < n; i --,j ++){
            if(checkboard[i][j] == 'Q') return false;
        }

        return true;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0,right= m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //不是使用nums[mid]  而是 matrix[mid / n][mid % n]
            if(matrix[mid / n][mid % n] == target) return true;
            else if (matrix[mid / n][mid % n] < target) {
                left = mid + 1;
            }else right = mid - 1;
        }
        return false;
    }
    //在排序数组中查找第一个和最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.fill(result, -1);
        if(nums.length == 0) return result;
        int mid = -1;
        boolean found = false;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] == target) {
                found = true;
                break;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else right = mid - 1;
        }
        if(found){
            left = mid;
            right = mid;
            while (left >= 0 && nums[left] == target) {left --;}
            while (right < nums.length && nums[right] == target) {right ++;}
            return new int[]{++left, --right};
        }else return result;
    }
    public static int search3(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) return mid;
            //看是在那边的有序序列

            //说明在左边
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else left = mid + 1;
            }else {//在右边
                //只要保证 nums[right] >= target 一定保证这个target在右边
                //nums[right] 是右边最大的，比右边最大的还小，一定在[mid,right]
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else right = mid - 1;
            }
        }
        return -1;
    }

    //字符串解码
    public static String decodeString(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<StringBuilder> strs = new ArrayDeque<>();
        int num = 0;
        StringBuilder cur = new StringBuilder();
        for(char c : s.toCharArray()){

            //如果当前字符是数字，就累加进去
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }else if(c == '['){
                nums.push(num);
                strs.push(cur);
                cur = new StringBuilder();
                num = 0;
            }else if(c == ']'){
                Integer times = nums.pop();
                StringBuilder prev = strs.pop();
                StringBuilder temp = new StringBuilder();
                for(int i = 0; i < times; i++){
                    temp.append(cur);
                }
                cur = prev.append(temp);
            }else cur.append(c);
        }
        return cur.toString();
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) ->{
            return b[1] - a[1];
        });
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        int[][] tmp = new int[map.size()][2];
        int n = 0;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            tmp[n][0] = entry.getKey();
            tmp[n][1] = entry.getValue();
            priorityQueue.offer(tmp[n]);
            n ++;
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll()[0];
        }
        return result;
    }
    //数组中的第K个最大的元素
    //使用快排，传入一个参数n - k,快排从小到大排序，但是题目是求第 K 个最大的元素，那就是n - k小的元素
    public static int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickSelect(_nums, 0, n - 1, n - k);
    }
    public static int quickSelect(int[] nums, int l,int r, int k) {
        //找到了结果
        if(l == r) return nums[l];
        int i = l - 1,j = r + 1;
        int x = nums[l];//枢轴
        while (i < j){
            do i ++; while (nums[i] < x);
            do j --; while (nums[j] > x);
            if(i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if(k <= j) return quickSelect(nums,l,j,k);
        else return quickSelect(nums,j + 1,r,k);
    }
    //买股票的最佳时机
    public static int maxProfit(int[] prices){
        //prices[1] - prices[0] = profit[0]
        //prices[2] - prices[1] = profit[1];
        //prices[3] - prices[2] = profit[2];
        //prices[3] - prices[0] = profit[0] + profit[1] + profit[2]
        int n = prices.length;
        int[] profits = new int[n - 1];
        for(int i = 0; i < n - 1; i++){
            profits[i] = prices[i + 1] - prices[i];
        }
        //然后对profits数组求最大子数组和
        int max = profits[0];
        int[] dp = new int[n - 1];
        dp[0] = profits[0];
        for(int i = 1; i < n - 1; i++){
         if(dp[i - 1] < 0){
             dp[i] = profits[i];
         }else dp[i] = dp[i - 1] + profits[i];
         max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(profits));
        System.out.println(Arrays.toString(dp));

        return max < 0 ? 0 : max;
    }
    //跳跃游戏Ⅱ
    public static int jump(int[] nums) {
        //这道题是求解跳跃到最后一个下标的最小跳跃的次数。
        if(nums.length == 1) return 0;
        int count = 0;
        //下一个可以跳跃的最远的距离
        int nextCoverRange = 0;
        //当前跳跃的最远距离，在0 - n-1的遍历中，未达到curConverRange,一直遍历，
        //达到了curCoverRange,curCoverRange = nextConverRange,
        int curCoverRange = 0;
        for(int i = 0; i < nums.length; i++){
            if(i + nums[i] > nextCoverRange){
                nextCoverRange = i + nums[i];
            }
            if(i == curCoverRange){
                curCoverRange = nextCoverRange;
                count ++;
                //已经走完了前面的，往后走了一步，这一步的最远距离已经超过了数组下标
                if(curCoverRange >= nums.length - 1) break;
            }
        }
        return count;
    }
    //跳跃游戏
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;
        int coverRange = 0;
        for(int i = 0; i <= coverRange; i++){
            coverRange = Math.max(coverRange,i + nums[i]);
            if(coverRange >= n - 1) return true;
        }
        return false;
    }
    //划分字母区间
    //这道题的意思就是，要把所有相同的字母放到一个区间。
    //比如 ababcbacadeb...... 这里a的最远下标是8，hash[a - '0'] = 8,然后遍历0-8，看到b的最远下标11，那么这个区间的最远距离就是0 - 11下标
    //在每个字母的区间遍历，如果有更远的，就更新，直到遍历走到这个最远的地方比如11，切割 0-11
    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[26];
        //初始化hash表
        for(int i = 0; i < s.length(); i++){
            hash[s.charAt(i) - 'a'] = i;
        }
        int max_index = -1;
        //记录上一次分割的index
        int lastIndex = -1;
        for(int i = 0; i < s.length(); i++){
            int index = hash[s.charAt(i) - 'a'];
            if(max_index < index){
                //更新最远距离
                max_index = index;
            }
            if(max_index == i){
                //到达了最远距离，分割
                result.add(max_index - lastIndex );
                lastIndex = i;
            }
        }
        return result;
    }
    //爬楼梯
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if(n > 1) dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
    //杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] nums = new int[numRows][];
        for(int i = 0; i < numRows; i++){
            nums[i] = new int[i + 1];
        }
        //初始化
        for(int i = 0; i < numRows; i++){
            nums[i][0] = 1;
        }
        for (int i = 1; i < numRows; i++){
            nums[i][i] = 1;
        }
        for(int i = 2; i < numRows; i++){
            for(int j = 1; j < nums[i].length - 1; j++){
                nums[i][j] = nums[i - 1][j] + nums[i - 1][j - 1];
            }
        }
        for(int i = 0; i < numRows; i++){
            result.add(Arrays.stream(nums[i]).boxed().collect(Collectors.toList()));
        }
        return result;
    }
    //打家劫舍
    public static int rob(int[] nums) {
        //dp[i]表示从 0 - i 偷盗的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if(nums.length > 1) dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
    //完全平方数
    public static int numSquares(int n) {
        //先给这个数开一个根号，所组成的数一定在 1 到 n开根号(向下取整) 之间
        int sqrt = (int )Math.sqrt(n);
        int[] coins = new int[sqrt];
        for(int i = 0; i < sqrt; i++){
            coins[i] = (i + 1) *(i + 1);
        }
        //coins[1,4,9]
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            dp[coin] = 1;
        }
        for(int i = 1; i <= n; i++){
            //dp[i] == 0 说明不在上面的硬币初始化里面，要找最小值了
            if(dp[i] == Integer.MAX_VALUE){
                for(int j = 0; j < sqrt; j++){
                    if(i > coins[j])
                        dp[i] = Math.min(dp[i],dp[i - coins[j]] + dp[coins[j]]);
                }
            }
        }
        return dp[n];
    }
    //零钱兑换
    public static int coinChange(int[] coins, int amount) {
        //coins[1,4,9]
        if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 10000);
        dp[0] = 0;
        for (int coin : coins) {
            dp[coin] = 1;
        }
        for(int i = 1; i <= amount; i++){
            //dp[i] == 0 说明不在上面的硬币初始化里面，要找最小值了
            if(dp[i] == 10000){
                for(int j = 0; j < coins.length; j++){
                    if(i > coins[j])
                        dp[i] = Math.min(dp[i],dp[i - coins[j]] + dp[coins[j]]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] == 10000 ? -1 : dp[amount];
    }
    //不同路径
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        for(int i = 0; i < n; i++){dp[0][i] = 1;}
        for(int j = 0; j < m; j++){dp[j][0] = 1;}
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    //最长公共子序列
    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        //dp[i][j] 表示text1的下标 0 - i 和 text2 0 - j最长的公共子序列
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
    //最小路径
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < n; i++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m - 1][n - 1];
    }
    //最长递增子序列
    public static int lengthOfLIS(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j ++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
    //乘积最大子数组
    public static int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        int res = nums[0];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for(int i = 1; i < nums.length; i ++){
            dpMax[i] = Math.max(nums[i],Math.max(dpMax[i - 1] * nums[i],dpMin[i - 1] * nums[i]));
            dpMin[i] = Math.min(nums[i],Math.min(dpMax[i - 1] * nums[i],dpMin[i - 1] * nums[i]));
            res = Math.max(res,dpMax[i]);
        }
        return res;

    }
    //分割等子集和
    public static boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 != 0) return false;
        //0 1 背包问题，装满价值为 sum / 2
        int n = nums.length;
        int[][] dp = new int[n][sum / 2 + 1];
        for(int i = 1; i < sum / 2 + 1; i ++){
            if(i >= nums[0]) dp[0][i] = 1;
        }
        for(int i = 1; i < n; i ++){
            for(int j = 1; j <= sum / 2; j ++){
                if(j < nums[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
            }
        }
        return dp[n - 1][sum / 2] == sum / 2;
    }
    //只出现一次的数字
    public static int singleNumber(int[] nums) {
        if(nums.length == 1) return nums[0];
        int single = 0;
        for(int i = 0; i < nums.length; i++){
            single ^= nums[i];
        }
        return single;
    }
    // 寻找重复的数字
    public int findDuplicate(int[] nums) {
        //初始化
        // 1 3 4 2 2
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(fast != slow){
            //直到两者相遇
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;

    }
    //颜色分类，荷兰国旗
    public static void sortColors(int[] nums) {
        //用三个指针分成四个部分，0[0,left - 1],1[left,i - 1],[i,right]未处理的,[right + 1,nums.length - 1]2
        int left = 0,i = 0,right = nums.length - 1;
        while(i <= right){
            if(nums[i] == 0){
                swap(nums, i, left);
                left ++;
                i ++;
            }else if(nums[i] == 1){
                i ++;
            }else if(nums[i] == 2){
                swap(nums, i, right);
                right --;
            }
        }
    }
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //下一个排序
    public static void nextPermutation(int[] nums) {
        //这道题我先试试从后往前遍历，如果nums[j - 1] < nums[j]调一个个儿，也即是找到第一个前一个小于后面的数，调换一下这两个数
        //使得改变的数组的字典次序最小比如找到的这个下标为 i
        //再往从右往左边找到第一个比这个nums[i] 大的nums[j] swap(nums,i,j),然后将下标为 i - end 逆序倒转一下
        int first_index = -1;
        //找到第一个
        for(int j = nums.length - 1; j >= 1;j --){
            if(nums[j - 1] < nums[j]){
                first_index = j - 1;
                break;
            }
        }
        //有第一个下降点的前提下
        if(first_index >= 0){
          int j = nums.length - 1;
          while(nums[j] <= nums[first_index]) {
              j--;
          }
          //找到了第一个比nums[i] 大的
          swap(nums, first_index, j);
        }
            //数组逆序
        for(int i = first_index + 1,j = nums.length - 1; i <= j; i ++,j --){
                swap(nums, i, j);
        }
        System.out.println(Arrays.toString(nums));
    }
    //单词拆分
    public static boolean wordBreak(String s,List<String> wordDict){
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i ++){
            for(int j = 0; j < i; j ++){
                String substring = s.substring(j, i);
                if(wordDict.contains(substring) && dp[j]){
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }

    //编辑距离
    public static int minDistance(String word1, String word2) {
        //删除和增加是一起的
        //替换
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //dp[i][0] 是从下标 i - 1个删除到空字符串的操作个数
        for(int i = 0; i <= m; i ++){dp[i][0] = i;}
        //dp[0][j] 是从下标 0 个字符增加到 j - 1个字符的操作数
        for(int j = 0; j <= n; j ++){dp[0][j] = j;}
        for(int i = 1; i <= m; i ++){
            for(int j = 1; j <= n; j ++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j],Math.min(dp[i][j - 1],dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
    //最长有效括号
    public static int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        //碰到右括号才做判断
        for(int i = 1; i < s.length(); i ++){
            if(s.charAt(i) == ')'){
                //s.charAt(i) == ')' && s.charAt(i - 1) == '('
                if(s.charAt(i - 1) == '('){
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else{//s.charAt(i) == ')' && s.charAt(i - 1) == ')'
                    int pre = i - dp[i - 1] - 1;
                    if(pre >= 0 && s.charAt(pre) == '('){
                        dp[i] = dp[i - 1] + 2 + (pre >= 1 ? dp[pre - 1]: 0);
                    }
                }
                max = Math.max(dp[i],max);
            }
        }
        return max;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;
        int lengthA = 0, lengthB = 0;
        while(curA != null){
            lengthA++;
            curA = curA.next;
        }
        while(curB != null){
            lengthB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if(lengthA < lengthB){
            int cha = lengthB - lengthA;
            while (cha > 0){
                curB = curB.next;
                cha --;
            }
            while (curA != null && curB != null){
                if(curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }else {
            int cha = lengthA - lengthB;
            while (cha > 0){
                curA = curA.next;
                cha --;
            }
            while (curA != null && curB != null){
                if(curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }
        return null;
    }
    public static ListNode reverseList(ListNode head) {
        //原地转置
        if(head == null) return head;
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode p = pre.next;
        while(p.next != null){
            ListNode q = p.next;
            ListNode x = pre.next;
            pre.next = p.next;
            p.next  = q.next;
            q.next = x;
        }
        return pre.next;
    }
    public static ListNode swapPairs(ListNode head) {
        if(head.next == null) return head;
        ListNode pre = new ListNode();
        ListNode nohead = pre;
        nohead.next = head;
        pre.next = head;
        ListNode cur = head;
        ListNode post;
        while (cur != null){
            post = cur.next;
            if(post == null) break;
            //交换
            cur.next = post.next;
            post.next = pre.next;
            pre.next = post;
            pre = cur;
            cur = cur.next;
        }
        return nohead.next;
    }
    //倒数第k个，就是正数n- k个
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode cur = pre,post = pre;
        while(n > 0){
            post = post.next;
            n --;
        }
        while(post.next != null){
            post = post.next;
            cur = cur.next;
        }
        //就是删除cur后面那一个
        ListNode temp = cur.next;
        cur.next = temp.next;
        return pre.next;

    }
    //从前序和中序遍历序列构造二叉树
    private static Map<Integer,Integer> map;
    public static TreeNode buildTree(int[] preorder,int[] inorder){
        map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        //数组的范围是左闭右闭
        return build(preorder,0,preorder.length - 1,inorder,0,inorder.length - 1);

    }
    public static TreeNode build(int[] preorder,int preorder_start,int preorder_end,int[] inorder,int inorder_start,int inorder_end){
        //如果当前数组没有元素直接返回null
        if(preorder_start > preorder_end) return null;
        TreeNode root = new TreeNode(preorder[preorder_start]);
        if(preorder_start == preorder_end) return root;
        //找到根节点在inorder数组中的下标
        int inorderIndex = map.get(preorder[preorder_start]);
        //左边元素的个数 inorderIndex - inorder_start
        int count = inorderIndex - inorder_start;
        TreeNode left = build(
                preorder,
                preorder_start + 1,preorder_start + count,
                inorder,
                inorder_start,inorderIndex - 1);
        TreeNode right = build(
                preorder,
                preorder_start + count + 1, preorder_end,
                inorder,
                inorderIndex + 1,inorder_end);
        root.left = left;
        root.right = right;
        return root;

    }
    //二叉树展开为链表
    public static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if(cur.left != null){
                TreeNode pre = cur.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                //找到左子树的最右边节点 pre
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
    //
    static int maxSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root){
        dfs(root);
        return maxSum;
    }
    public static int dfs(TreeNode root){
        if(root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);

    }
    public static boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;
        ListNode slow = head,fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //以slow 为起点反转后面的
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        ListNode left = head;
        ListNode right = prev;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }
    private static Map<ListNode,Boolean> map1;
    public static boolean hasCycle(ListNode head) {
        map1 = new HashMap<>();
        ListNode cur = head;
        while(cur != null){
            boolean result = map1.getOrDefault(cur,false);
            if(result) return true;
            map1.put(cur,true);
            cur = cur.next;
        }
        return false;
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode newnode = new ListNode();
        ListNode list3 = newnode;
        ListNode p = list1,q = list2;
        while(p != null && q != null){
            if(p.val <= q.val){
                newnode.next = p;
                newnode = newnode.next;
                p = p.next;
                newnode.next = null;
            }else{
                newnode.next = q;
                newnode = newnode.next;
                q = q.next;
                newnode.next = null;
            }
        }
        while(p != null){
            newnode.next = p;
            break;
        }
        while(q != null){
            newnode.next = q;
            break;
        }
        return list3.next;
    }
    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head,fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null) return null;
        //说明有环了
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位
        int flag = 0;
        ListNode prev = null;
        ListNode head1 = l1, head2 = l2;
        while(head1 != null && head2 != null){
            int val1 = head1.val;
            int val2 = head2.val;
            if(val2 + val1 + flag > 9){
                //进位
                head1.val = (val2 + val1 + flag) % 10;
                flag = 1;
            }else{
                //没有进位
                head1.val = val2 + val1 + flag;
                flag = 0;
            }
            if(head1.next == null){prev = head1;}
            head1 = head1.next;
            head2 = head2.next;
        }
        if(head1 == null && head2 == null) {
            //每次结束之前都要看是否有进位
            if (flag == 1) prev.next = new ListNode(flag);
            return l1;
        }
        else if(head1 == null) {
            //head1 == null ,head2 != null
            prev.next = head2;
            while (head2 != null) {
                int val2 = head2.val;
                if(val2 + flag > 9){
                    head2.val = (val2 + flag) % 10;
                    flag = 1;
                }else {
                    head2.val = val2 + flag;
                    flag = 0;
                }
                if(head2.next == null){prev = head2;}
                head2 = head2.next;
            }
            if(flag == 1) prev.next = new ListNode(flag);
        }else {
            while (head1 != null) {
                int val1 = head1.val;
                if(val1 + flag > 9){
                    head1.val = (val1 + flag) % 10;
                    flag = 1;
                }else {
                    head1.val = val1 + flag;
                    flag = 0;
                }
                if(head1.next == null){prev = head1;}
                head1 = head1.next;
            }
            //结束之前看看有没有进位，就在最后一个进位的地方需要new一个新的节点
            if(flag == 1) prev.next = new ListNode(flag);
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode11;
        System.out.println(addTwoNumbers(listNode1,listNode4));
        System.out.println();
    }

}
@SuppressWarnings("all")
class MyLinkedList {
    public static int size = 0;
    //头指针
    private ListNode head;
    //尾指针
    private ListNode rear;

    public MyLinkedList() {
        //带头结点的链表
        head = new ListNode();
        rear = head.next;
    }

    public int get(int index) {
        if(index > size - 1) return -1;
        ListNode p = head;
        System.out.println(index);
        while(index >= 0){
            p = p.next;
            index --;
        }
        return p.val;
    }

    public void addAtHead(int val) {
        ListNode p = new ListNode(val);
        if(head.next == null){
            head.next = p;
            rear = p;
        }
        else {
            ListNode q = head.next;
            head.next = p;
            p.next = q;
        }
        size ++;
    }

    public void addAtTail(int val) {
        ListNode p = new ListNode(val);
        if(head.next == null){
            head.next = p;
            rear = p;
        }else{
            rear.next = p;
            rear = p;
        }
        size ++;
    }

    public void addAtIndex(int index, int val) {
        ListNode p = new ListNode(val);
        //也是插入的第一个
        if(index == size && size == 0){
            head.next = p;
            rear = p;
            size ++;
        }else if(index == size){
            rear.next = p;
            rear = p;
            size ++;
        }else if(index < size){
            ListNode q = head;
            while(index > 0){
                q = q.next;
                index --;
            }
            p.next = q.next;
            q.next = p;
            size ++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index < size){
            ListNode p = head;
            while(index > 0){
                p = p.next;
                index --;
            }
            ListNode q = p.next.next;
            if(q == null){
                rear = p;
            }
            else p.next = q;
            size --;
        }
    }

}

@SuppressWarnings("all")
class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
    ListNode(){
    }
}
@SuppressWarnings("all")
class MedianFinder {
    //大根堆，存放数据小的那一半
    private PriorityQueue<Integer> maxHeap;
    //小根堆，存放数据大的那一半
    private PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //插入的时候默认插入大根堆小的那一堆
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.add(num);
        }else minHeap.add(num);
        //调整两个堆的数量，大根堆的size == 小根堆的数量 || 大根堆的数量 = 小根堆的数量 + 1
        if(maxHeap.size() > minHeap.size() + 1){
            //移一个到小根堆里面
            minHeap.add(maxHeap.poll());
        }
        if(minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }
    public double findMedian() {
        if(maxHeap.size() > minHeap.size()){
            //说明是奇数
            return maxHeap.peek();
        }else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}
@SuppressWarnings("all")
class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    public MinStack() {
        stack = new ArrayDeque<>();
        //小根堆
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if(pop.equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
@SuppressWarnings("all")
class MyQueue {
    private final Deque<Integer> stack_in;
    private final Deque<Integer> stack_out;
    public MyQueue() {
        stack_in = new ArrayDeque<>();
        stack_out = new ArrayDeque<>();
    }

    public void push(int x) {
        stack_in.push(x);
    }

    public int pop() {
        if (stack_out.isEmpty()) {
            //出队列为空，就要把入队列中所有的元素给出队栈
            while (!stack_in.isEmpty()) {
                stack_out.push(stack_in.pop());
            }
        }
        return stack_out.pop();
    }


    public int peek() {
        if (stack_out.isEmpty()) {
            //出队列为空，就要把入队列中所有的元素给出队栈
            while (!stack_in.isEmpty()) {
                stack_out.push(stack_in.pop());
            }
        }
        return stack_out.peek();
    }

    public boolean empty() {
        return stack_out.isEmpty() && stack_in.isEmpty();
    }
}
@SuppressWarnings("all")
class MyStack {
    private final Queue<Integer> queue_1;
    private final Queue<Integer> queue_2;
    public MyStack() {
        queue_1 = new ArrayDeque<>();
        queue_2 = new ArrayDeque<>();
    }

    public void push(int x) {
        queue_1.offer(x);
    }

    public int pop() {
        int count = queue_1.size() - 1;
        while (count > 0){
            queue_2.offer(queue_1.poll());
            count --;
        }
        int pop = queue_1.poll();
        count = queue_2.size();
        while (count > 0){
            queue_1.offer(queue_2.poll());
            count --;
        }
        return pop;
    }

    public int top() {
        int count = queue_1.size() - 1;
        while (count > 0){
            queue_2.offer(queue_1.poll());
            count --;
        }
        int pop = queue_1.poll();
        count = queue_2.size();
        while (count > 0){
            queue_1.offer(queue_2.poll());
            count --;
        }
        queue_1.offer(pop);
        return pop;
    }

    public boolean empty() {
        return queue_1.isEmpty();
    }
}
@SuppressWarnings("all")
class MyDeque{
    public Deque<Integer> deque = new LinkedList<>();

    public void poll(Integer x){
        if(!deque.isEmpty() && Objects.equals(deque.peek(), x)){
            deque.poll();
        }
    }

    public void push(Integer x){
        while (!deque.isEmpty() && deque.getLast() < x){
            deque.removeLast();
        }
        deque.add(x);
    }
    public int peek(){
        return deque.peek();
    }
}
@SuppressWarnings("all")
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
    TreeNode(){}
    TreeNode(TreeNode left,TreeNode right,int val){
        this.left = left;
        this.right = right;
        this.val = val;
    }
}
@SuppressWarnings("all")
class Node{
    public int val;
    public List<Node> children;

    public Node(){}

    public Node(int val){
        this.val = val;
    }

    public Node(int val,List<Node> children){
        this.val = val;
        this.children = children;
    }
}
@SuppressWarnings("all")
class Employee{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Employee( int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age;
    }

    @Override
    public int hashCode() {
        return 100;
    }

    @Override
    public String toString() {
        return "\n Employee{" +
                "age=" + age +
                '}';
    }
}