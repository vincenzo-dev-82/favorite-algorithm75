import java.util.*

/**
 * 이진 트리 노드를 위한 기본 클래스
 * LeetCode 문제에서 사용되는 표준 TreeNode 정의
 */
class TreeNode(
    /** 노드의 값 */
    var `val`: Int = 0,
    /** 왼쪽 자식 노드 */
    var left: TreeNode? = null,
    /** 오른쪽 자식 노드 */
    var right: TreeNode? = null
) {
    
    companion object {
        /**
         * 배열을 이용해 완전 이진 트리 생성하는 유틸리티 메서드
         * null 값은 null로 표현
         * @param values 트리로 만들 값들 (레벨 순서)
         * @return 생성된 트리의 root 노드
         */
        fun createTree(values: Array<Int?>?): TreeNode? {
            if (values == null || values.isEmpty() || values[0] == null) {
                return null
            }
            
            val root = TreeNode(values[0]!!)
            val queue: Queue<TreeNode> = LinkedList()
            queue.offer(root)
            
            var i = 1
            while (queue.isNotEmpty() && i < values.size) {
                val current = queue.poll()
                
                // 왼쪽 자식 추가
                if (i < values.size && values[i] != null) {
                    current.left = TreeNode(values[i]!!)
                    queue.offer(current.left)
                }
                i++
                
                // 오른쪽 자식 추가
                if (i < values.size && values[i] != null) {
                    current.right = TreeNode(values[i]!!)
                    queue.offer(current.right)
                }
                i++
            }
            
            return root
        }
        
        /**
         * 트리를 배열로 변환하는 유틸리티 메서드 (레벨 순서)
         * @param root 트리의 root 노드
         * @return 변환된 정수 배열 (null 포함)
         */
        fun toArray(root: TreeNode?): List<Int?> {
            val result = mutableListOf<Int?>()
            if (root == null) {
                return result
            }
            
            val queue: Queue<TreeNode?> = LinkedList()
            queue.offer(root)
            
            while (queue.isNotEmpty()) {
                val current = queue.poll()
                
                if (current != null) {
                    result.add(current.`val`)
                    queue.offer(current.left)
                    queue.offer(current.right)
                } else {
                    result.add(null)
                }
            }
            
            // 끝의 null 제거
            while (result.isNotEmpty() && result.last() == null) {
                result.removeAt(result.size - 1)
            }
            
            return result
        }
    }
    
    /**
     * 중위 순회 (Inorder Traversal)
     * @return 중위 순회 결과 리스트
     */
    fun inorderTraversal(): List<Int> {
        val result = mutableListOf<Int>()
        inorderHelper(this, result)
        return result
    }
    
    private fun inorderHelper(node: TreeNode?, result: MutableList<Int>) {
        if (node != null) {
            inorderHelper(node.left, result)
            result.add(node.`val`)
            inorderHelper(node.right, result)
        }
    }
    
    /**
     * 전위 순회 (Preorder Traversal)
     * @return 전위 순회 결과 리스트
     */
    fun preorderTraversal(): List<Int> {
        val result = mutableListOf<Int>()
        preorderHelper(this, result)
        return result
    }
    
    private fun preorderHelper(node: TreeNode?, result: MutableList<Int>) {
        if (node != null) {
            result.add(node.`val`)
            preorderHelper(node.left, result)
            preorderHelper(node.right, result)
        }
    }
    
    /**
     * 후위 순회 (Postorder Traversal)
     * @return 후위 순회 결과 리스트
     */
    fun postorderTraversal(): List<Int> {
        val result = mutableListOf<Int>()
        postorderHelper(this, result)
        return result
    }
    
    private fun postorderHelper(node: TreeNode?, result: MutableList<Int>) {
        if (node != null) {
            postorderHelper(node.left, result)
            postorderHelper(node.right, result)
            result.add(node.`val`)
        }
    }
    
    /**
     * 레벨 순회 (Level Order Traversal)
     * @return 레벨 순회 결과 리스트
     */
    fun levelOrder(): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (this == null) {
            return result
        }
        
        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(this)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val current = queue.poll()
                level.add(current.`val`)
                
                current.left?.let { queue.offer(it) }
                current.right?.let { queue.offer(it) }
            }
            
            result.add(level)
        }
        
        return result
    }
    
    /**
     * 트리의 최대 깊이 반환
     * @return 트리의 최대 깊이
     */
    fun maxDepth(): Int {
        val leftDepth = left?.maxDepth() ?: 0
        val rightDepth = right?.maxDepth() ?: 0
        
        return maxOf(leftDepth, rightDepth) + 1
    }
    
    /**
     * 트리가 균형잡혀 있는지 확인
     * @return 균형잡혀 있으면 true, 아니면 false
     */
    fun isBalanced(): Boolean {
        return checkBalance(this) != -1
    }
    
    private fun checkBalance(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        
        val leftHeight = checkBalance(node.left)
        if (leftHeight == -1) return -1
        
        val rightHeight = checkBalance(node.right)
        if (rightHeight == -1) return -1
        
        if (kotlin.math.abs(leftHeight - rightHeight) > 1) {
            return -1
        }
        
        return maxOf(leftHeight, rightHeight) + 1
    }
    
    /**
     * 트리를 문자열로 표현 (간단한 형태)
     * @return 트리의 문자열 표현
     */
    override fun toString(): String {
        return toArray(this).toString()
    }
}

/**
 * 확장 함수: 트리 출력
 */
fun TreeNode?.print() {
    if (this == null) {
        println("null")
        return
    }
    println(this.toString())
}

/**
 * 확장 함수: 트리 높이
 */
fun TreeNode?.height(): Int {
    return this?.maxDepth() ?: 0
}