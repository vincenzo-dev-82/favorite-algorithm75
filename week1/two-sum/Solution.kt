/**
 * LeetCode 1. Two Sum
 * 
 * 문제: 정수 배열에서 합이 target이 되는 두 수의 인덱스를 반환
 * 
 * 해법:
 * 1. 브루트 포스: O(n²) 시간, O(1) 공간
 * 2. 해시 테이블: O(n) 시간, O(n) 공간 ⭐ 최적해
 */
class Solution {
    
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
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // 값을 키로, 인덱스를 값으로 저장하는 HashMap
        val numToIndex = mutableMapOf<Int, Int>()
        
        // 배열을 한 번만 순회
        for (i in nums.indices) {
            // 현재 숫자와 더해서 target이 되는 수 (complement)
            val complement = target - nums[i]
            
            // complement가 이미 HashMap에 있는지 확인
            numToIndex[complement]?.let { complementIndex ->
                // 찾았다! complement의 인덱스와 현재 인덱스 반환
                return intArrayOf(complementIndex, i)
            }
            
            // 아직 찾지 못했으므로 현재 숫자와 인덱스를 HashMap에 저장
            numToIndex[nums[i]] = i
        }
        
        // 문제에서 정확히 하나의 해답이 존재한다고 했으므로 여기에 도달하지 않음
        throw IllegalArgumentException("No two sum solution")
    }
    
    /**
     * 더 간결한 Kotlin 스타일 구현
     * 
     * withIndex()와 mapIndexedNotNull 활용
     */
    fun twoSumKotlinStyle(nums: IntArray, target: Int): IntArray {
        val numToIndex = mutableMapOf<Int, Int>()
        
        nums.forEachIndexed { index, num ->
            val complement = target - num
            numToIndex[complement]?.let { complementIndex ->
                return intArrayOf(complementIndex, index)
            }
            numToIndex[num] = index
        }
        
        throw IllegalArgumentException("No two sum solution")
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
    fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
        // 모든 가능한 두 수의 조합 확인
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                // 두 수의 합이 target과 같은지 확인
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        
        // 해답이 없는 경우 (문제 조건상 발생하지 않음)
        throw IllegalArgumentException("No two sum solution")
    }
    
    /**
     * 함수형 프로그래밍 스타일 구현 (참고용)
     * 
     * 실제로는 성능상 권장하지 않지만 Kotlin의 함수형 기능 활용 예시
     */
    fun twoSumFunctional(nums: IntArray, target: Int): IntArray {
        return nums.withIndex()
            .fold(mutableMapOf<Int, Int>()) { acc, (index, num) ->
                val complement = target - num
                acc[complement]?.let { complementIndex ->
                    return intArrayOf(complementIndex, index)
                }
                acc[num] = index
                acc
            }.let { throw IllegalArgumentException("No two sum solution") }
    }
}

/**
 * 테스트 및 사용 예제
 */
fun main() {
    val solution = Solution()
    
    // 테스트 케이스 1
    val nums1 = intArrayOf(2, 7, 11, 15)
    val target1 = 9
    val result1 = solution.twoSum(nums1, target1)
    println("Test 1: ${result1.contentToString()}") // [0, 1]
    
    // 테스트 케이스 2
    val nums2 = intArrayOf(3, 2, 4)
    val target2 = 6
    val result2 = solution.twoSum(nums2, target2)
    println("Test 2: ${result2.contentToString()}") // [1, 2]
    
    // 테스트 케이스 3
    val nums3 = intArrayOf(3, 3)
    val target3 = 6
    val result3 = solution.twoSum(nums3, target3)
    println("Test 3: ${result3.contentToString()}") // [0, 1]
    
    // 테스트 케이스 4 (음수 포함)
    val nums4 = intArrayOf(-1, -2, -3, -4, -5)
    val target4 = -8
    val result4 = solution.twoSum(nums4, target4)
    println("Test 4: ${result4.contentToString()}") // [2, 4]
    
    // Kotlin 스타일 테스트
    val resultKotlin = solution.twoSumKotlinStyle(nums1, target1)
    println("Kotlin Style Test: ${resultKotlin.contentToString()}") // [0, 1]
    
    // 브루트 포스 테스트
    val resultBF = solution.twoSumBruteForce(nums1, target1)
    println("Brute Force Test: ${resultBF.contentToString()}") // [0, 1]
}

/**
 * Kotlin 특징 활용 포인트:
 * 
 * 1. Safe Call 연산자 (?.)와 let 활용:
 *    - null 체크와 동시에 값 사용 가능
 *    - numToIndex[complement]?.let { ... }
 * 
 * 2. forEachIndexed 활용:
 *    - 인덱스와 값을 동시에 처리
 *    - 더 읽기 쉬운 코드
 * 
 * 3. IntArray 생성:
 *    - intArrayOf() 함수 사용
 *    - 더 간결한 문법
 * 
 * 4. 예외 처리:
 *    - throw 문도 expression으로 사용 가능
 * 
 * 5. 함수형 프로그래밍:
 *    - fold, withIndex 등 고차 함수 활용
 *    - 하지만 성능상 일반 반복문이 더 효율적
 * 
 * 면접에서는 첫 번째 twoSum 구현을 기본으로 하고,
 * Kotlin의 특징을 설명할 때 다른 스타일들을 언급하는 것이 좋음
 */