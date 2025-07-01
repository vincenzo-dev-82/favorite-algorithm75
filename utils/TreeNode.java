import java.util.*;

/**
 * 이진 트리 노드를 위한 기본 클래스
 * LeetCode 문제에서 사용되는 표준 TreeNode 정의
 */
public class TreeNode {
    /** 노드의 값 */
    public int val;
    /** 왼쪽 자식 노드 */
    public TreeNode left;
    /** 오른쪽 자식 노드 */
    public TreeNode right;
    
    /**
     * 기본 생성자
     */
    public TreeNode() {}
    
    /**
     * 값을 지정하는 생성자
     * @param val 노드의 값
     */
    public TreeNode(int val) { 
        this.val = val; 
    }
    
    /**
     * 값과 자식 노드들을 지정하는 생성자
     * @param val 노드의 값
     * @param left 왼쪽 자식 노드
     * @param right 오른쪽 자식 노드
     */
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    /**
     * 배열을 이용해 완전 이진 트리 생성하는 유틸리티 메서드
     * null 값은 Integer.MIN_VALUE로 표현
     * @param values 트리로 만들 값들 (레벨 순서)
     * @return 생성된 트리의 root 노드
     */
    public static TreeNode createTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            
            // 왼쪽 자식 추가
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;
            
            // 오른쪽 자식 추가
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * 트리를 배열로 변환하는 유틸리티 메서드 (레벨 순서)
     * @param root 트리의 root 노드
     * @return 변환된 정수 배열 (null은 Integer.MIN_VALUE로 표현)
     */
    public static List<Integer> toArray(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            if (current != null) {
                result.add(current.val);
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                result.add(null);
            }
        }
        
        // 끝의 null 제거
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        
        return result;
    }
    
    /**
     * 중위 순회 (Inorder Traversal)
     * @return 중위 순회 결과 리스트
     */
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderHelper(this, result);
        return result;
    }
    
    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);
            result.add(node.val);
            inorderHelper(node.right, result);
        }
    }
    
    /**
     * 전위 순회 (Preorder Traversal)
     * @return 전위 순회 결과 리스트
     */
    public List<Integer> preorderTraversal() {
        List<Integer> result = new ArrayList<>();
        preorderHelper(this, result);
        return result;
    }
    
    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.val);
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }
    }
    
    /**
     * 후위 순회 (Postorder Traversal)
     * @return 후위 순회 결과 리스트
     */
    public List<Integer> postorderTraversal() {
        List<Integer> result = new ArrayList<>();
        postorderHelper(this, result);
        return result;
    }
    
    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            postorderHelper(node.left, result);
            postorderHelper(node.right, result);
            result.add(node.val);
        }
    }
    
    /**
     * 레벨 순회 (Level Order Traversal)
     * @return 레벨 순회 결과 리스트
     */
    public List<List<Integer>> levelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if (this == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(level);
        }
        
        return result;
    }
    
    /**
     * 트리의 최대 깊이 반환
     * @return 트리의 최대 깊이
     */
    public int maxDepth() {
        if (this == null) {
            return 0;
        }
        
        int leftDepth = left != null ? left.maxDepth() : 0;
        int rightDepth = right != null ? right.maxDepth() : 0;
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * 트리가 균형잡혀 있는지 확인
     * @return 균형잡혀 있으면 true, 아니면 false
     */
    public boolean isBalanced() {
        return checkBalance(this) != -1;
    }
    
    private int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * 트리를 문자열로 표현 (간단한 형태)
     * @return 트리의 문자열 표현
     */
    @Override
    public String toString() {
        return toArray(this).toString();
    }
}