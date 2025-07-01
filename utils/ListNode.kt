/**
 * 연결 리스트 노드를 위한 기본 클래스
 * LeetCode 문제에서 사용되는 표준 ListNode 정의
 */
class ListNode(
    /** 노드의 값 */
    var `val`: Int = 0,
    /** 다음 노드를 가리키는 참조 */
    var next: ListNode? = null
) {
    
    companion object {
        /**
         * 배열을 이용해 연결 리스트 생성하는 유틸리티 메서드
         * @param values 연결 리스트로 만들 값들
         * @return 생성된 연결 리스트의 head 노드
         */
        fun createList(values: IntArray?): ListNode? {
            if (values == null || values.isEmpty()) {
                return null
            }
            
            val head = ListNode(values[0])
            var current = head
            
            for (i in 1 until values.size) {
                current.next = ListNode(values[i])
                current = current.next!!
            }
            
            return head
        }
        
        /**
         * 연결 리스트를 배열로 변환하는 유틸리티 메서드
         * @param head 연결 리스트의 head 노드
         * @return 변환된 정수 배열
         */
        fun toArray(head: ListNode?): IntArray {
            if (head == null) {
                return intArrayOf()
            }
            
            val list = mutableListOf<Int>()
            var current = head
            
            while (current != null) {
                list.add(current.`val`)
                current = current.next
            }
            
            return list.toIntArray()
        }
    }
    
    /**
     * 연결 리스트를 문자열로 표현
     * @return 연결 리스트의 문자열 표현 (예: "1 -> 2 -> 3 -> null")
     */
    override fun toString(): String {
        val sb = StringBuilder()
        var current: ListNode? = this
        
        while (current != null) {
            sb.append(current.`val`)
            if (current.next != null) {
                sb.append(" -> ")
            }
            current = current.next
        }
        sb.append(" -> null")
        
        return sb.toString()
    }
    
    /**
     * 두 연결 리스트가 같은지 비교
     * @param other 비교할 다른 연결 리스트
     * @return 같으면 true, 다르면 false
     */
    fun equals(other: ListNode?): Boolean {
        var current1: ListNode? = this
        var current2 = other
        
        while (current1 != null && current2 != null) {
            if (current1.`val` != current2.`val`) {
                return false
            }
            current1 = current1.next
            current2 = current2.next
        }
        
        return current1 == null && current2 == null
    }
    
    /**
     * 연결 리스트의 길이 반환
     * @return 연결 리스트의 노드 개수
     */
    fun length(): Int {
        var count = 0
        var current: ListNode? = this
        
        while (current != null) {
            count++
            current = current.next
        }
        
        return count
    }
}

/**
 * 확장 함수: 연결 리스트 출력
 */
fun ListNode?.print() {
    if (this == null) {
        println("null")
        return
    }
    println(this.toString())
}

/**
 * 확장 함수: 연결 리스트 길이
 */
fun ListNode?.size(): Int {
    return this?.length() ?: 0
}