import java.util.*;

/**
 * LeetCode 1. Two Sum
 * 
 * 문제: 정수 배열에서 합이 target이 되는 두 수의 인덱스를 반환
 * 
 * 해법:
 * 1. 브루트 포스: O(n²) 시간, O(1) 공간
 * 2. 해시 테이블: O(n) 시간, O(n) 공간 ⭐ 최적해
 */
public class Solution {
    
    /**
     * 최적 해법: 해시 테이블 사용 (One-pass)
     * 
     * 핵심 아이디어:
     * - 배열을 한 번만 순회하면서 각 원소에 대해
     * - complement(target - 현재값)가 이미 방문한 원소 중에 있는지 확인
     * - HashMap을 사용해 O(1) 시간에 조회
     * 
     * 시간 복잡도: O(n) - 배열을 한 번만 순회
     * 공간 복잡도: O(n) - 최악의 경우 모든 원소를 HashMap에 저장
     * 
     * @param nums 정수 배열
     * @param target 목표 합
     * @return 합이 target이 되는 두 수의 인덱스 배열
     */
    public int[] twoSum(int[] nums, int target) {
        // 값을 키로, 인덱스를 값으로 저장하는 HashMap
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        // 배열을 한 번만 순회
        for (int i = 0; i < nums.length; i++) {
            // 현재 숫자와 더해서 target이 되는 수 (complement)
            int complement = target - nums[i];
            
            // complement가 이미 HashMap에 있는지 확인
            if (numToIndex.containsKey(complement)) {
                // 찾았다! complement의 인덱스와 현재 인덱스 반환
                return new int[]{numToIndex.get(complement), i};
            }
            
            // 아직 찾지 못했으므로 현재 숫자와 인덱스를 HashMap에 저장
            numToIndex.put(nums[i], i);
        }
        
        // 문제에서 정확히 하나의 해답이 존재한다고 했으므로 여기에 도달하지 않음
        throw new IllegalArgumentException("No two sum solution");
    }
    
    /**
     * 브루트 포스 해법 (참고용)
     * 
     * 모든 가능한 두 수의 조합을 확인
     * 
     * 시간 복잡도: O(n²) - 중첩 반복문
     * 공간 복잡도: O(1) - 추가 공간 사용하지 않음
     * 
     * @param nums 정수 배열
     * @param target 목표 합
     * @return 합이 target이 되는 두 수의 인덱스 배열
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        // 모든 가능한 두 수의 조합 확인
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 두 수의 합이 target과 같은지 확인
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        // 해답이 없는 경우 (문제 조건상 발생하지 않음)
        throw new IllegalArgumentException("No two sum solution");
    }
    
    /**
     * 테스트 메서드
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 테스트 케이스 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test 1: " + Arrays.toString(result1)); // [0, 1]
        
        // 테스트 케이스 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test 2: " + Arrays.toString(result2)); // [1, 2]
        
        // 테스트 케이스 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test 3: " + Arrays.toString(result3)); // [0, 1]
        
        // 테스트 케이스 4 (음수 포함)
        int[] nums4 = {-1, -2, -3, -4, -5};
        int target4 = -8;
        int[] result4 = solution.twoSum(nums4, target4);
        System.out.println("Test 4: " + Arrays.toString(result4)); // [2, 4]
        
        // 브루트 포스 테스트
        int[] resultBF = solution.twoSumBruteForce(nums1, target1);
        System.out.println("Brute Force Test: " + Arrays.toString(resultBF)); // [0, 1]
    }
}

/**
 * 성능 비교 및 분석:
 * 
 * 1. 해시 테이블 방법 (권장):
 *    - 시간: O(n) - 한 번의 순회
 *    - 공간: O(n) - HashMap 저장
 *    - 장점: 빠른 실행 시간
 *    - 단점: 추가 메모리 사용
 * 
 * 2. 브루트 포스 방법:
 *    - 시간: O(n²) - 중첩 반복문
 *    - 공간: O(1) - 추가 메모리 없음
 *    - 장점: 메모리 효율적
 *    - 단점: 느린 실행 시간
 * 
 * 실제 면접에서는 해시 테이블 방법을 먼저 제시하고,
 * 브루트 포스 방법도 언급하여 trade-off를 설명하는 것이 좋음
 */