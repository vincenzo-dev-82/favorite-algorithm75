/**
 * 연결 리스트 노드를 위한 기본 클래스
 * LeetCode 문제에서 사용되는 표준 ListNode 정의
 */
public class ListNode {
    /** 노드의 값 */
    public int val;
    /** 다음 노드를 가리키는 참조 */
    public ListNode next;
    
    /**
     * 기본 생성자
     */
    public ListNode() {}
    
    /**
     * 값을 지정하는 생성자
     * @param val 노드의 값
     */
    public ListNode(int val) { 
        this.val = val; 
    }
    
    /**
     * 값과 다음 노드를 지정하는 생성자
     * @param val 노드의 값
     * @param next 다음 노드 참조
     */
    public ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
    
    /**
     * 배열을 이용해 연결 리스트 생성하는 유틸리티 메서드
     * @param values 연결 리스트로 만들 값들
     * @return 생성된 연결 리스트의 head 노드
     */
    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * 연결 리스트를 배열로 변환하는 유틸리티 메서드
     * @param head 연결 리스트의 head 노드
     * @return 변환된 정수 배열
     */
    public static int[] toArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        
        java.util.List<Integer> list = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /**
     * 연결 리스트를 문자열로 표현
     * @return 연결 리스트의 문자열 표현 (예: "1 -> 2 -> 3 -> null")
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append(" -> null");
        
        return sb.toString();
    }
    
    /**
     * 두 연결 리스트가 같은지 비교
     * @param other 비교할 다른 연결 리스트
     * @return 같으면 true, 다르면 false
     */
    public boolean equals(ListNode other) {
        ListNode current1 = this;
        ListNode current2 = other;
        
        while (current1 != null && current2 != null) {
            if (current1.val != current2.val) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        
        return current1 == null && current2 == null;
    }
    
    /**
     * 연결 리스트의 길이 반환
     * @return 연결 리스트의 노드 개수
     */
    public int length() {
        int count = 0;
        ListNode current = this;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        
        return count;
    }
}