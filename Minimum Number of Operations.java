
Problem:-
  
You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:

Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.

The gcd of two integers is the greatest common divisor of the two integers.

 

Example 1:

Input: nums = [2,6,3,4]
Output: 4
Explanation: We can do the following operations:
- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
Example 2:

Input: nums = [2,10,6,14]
Output: -1
Explanation: It can be shown that it is impossible to make all the elements equal to 1.
 

Constraints:

2 <= nums.length <= 50
1 <= nums[i] <= 106

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
SOLUTION:--

Intuition
We need to make all elements in the array equal to 1 using the minimum number of operations.
If there’s already at least one 1 in the array, we can easily convert all other elements to 1 because taking the GCD with 1 always gives 1.
Hence, the minimum operations in that case is simply the count of non-one elements.

If there are no 1s, we must find the smallest subarray whose GCD becomes 1.
Once we obtain a single 1, we can propagate it to the rest of the array in (n - 1) additional operations.

Approach
Count the number of elements equal to 1.
If at least one exists, return n - ones as the answer.
Otherwise, for each index i, compute the GCD of subarrays starting from i until it becomes 1.
Keep track of the minimum length of such a subarray.
If no subarray GCD becomes 1, return -1.
Otherwise, the result is the minimum subarray length (to create one 1) plus (n - 1) (to make all elements 1).
Complexity
Time complexity: O(N ( 2 ) Logm)
Space complexity:O(1)


CODE:---------------------------------------------------------------------------------------------------------------------------------------------------------------


import java.util.*;

public class MinOperationsToOne {
    public static int minOperations(int[] nums) {
        int n = nums.length;
        int overallGCD = nums[0];

        // Step 1: Check if overall GCD is 1
        for (int i = 1; i < n; i++) {
            overallGCD = gcd(overallGCD, nums[i]);
        }
        if (overallGCD != 1) return -1;

        // Step 2: Find shortest subarray with GCD 1
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int currentGCD = nums[i];
            for (int j = i + 1; j < n; j++) {
                currentGCD = gcd(currentGCD, nums[j]);
                if (currentGCD == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }

        // Step 3: Total operations = (minLen - 1) + (n - 1)
        return (minLen - 1) + (n - 1);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums1 = {2, 6, 3, 4};
        int[] nums2 = {2, 10, 6, 14};

        System.out.println(minOperations(nums1)); // Output: 4
        System.out.println(minOperations(nums2)); // Output: -1
    }
}
