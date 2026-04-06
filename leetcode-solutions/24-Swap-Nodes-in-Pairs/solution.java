/**
 * LeetCode Problem: Swap Nodes in Pairs
 * Problem Link: https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-06
 * Submission Link: https://leetcode.com/problems/swap-nodes-in-pairs/submissions/1559533753/
 */

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Swap nodes in pairs in a linked list.
     * Approach: Use dummy node and iterative swapping.
     * 
     * @param head Head of the linked list
     * @return ListNode Head of the modified linked list
     */
    public ListNode swapPairs(ListNode head) {
        // Create a dummy node to serve as the new head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            // Identify the two nodes to swap
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // Perform the swap
            first.next = second.next;
            second.next = first;
            prev.next = second;
            
            // Move prev to the next pair
            prev = first;
        }
        
        return dummy.next;
    }
    
    /**
     * Helper method to create a linked list from array
     */
    public ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }
    
    /**
     * Helper method to convert linked list to string
     */
    public String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [1,2,3,4] -> [2,1,4,3]
        int[] arr1 = {1,2,3,4};
        ListNode head1 = solution.createList(arr1);
        ListNode result1 = solution.swapPairs(head1);
        System.out.println("Test 1: [1,2,3,4] -> " + solution.listToString(result1)); // [2,1,4,3]
        
        // Test case 2: [] -> []
        int[] arr2 = {};
        ListNode head2 = solution.createList(arr2);
        ListNode result2 = solution.swapPairs(head2);
        System.out.println("Test 2: [] -> " + solution.listToString(result2)); // []
        
        // Test case 3: [1] -> [1]
        int[] arr3 = {1};
        ListNode head3 = solution.createList(arr3);
        ListNode result3 = solution.swapPairs(head3);
        System.out.println("Test 3: [1] -> " + solution.listToString(result3)); // [1]
        
        // Test case 4: [1,2,3] -> [2,1,3]
        int[] arr4 = {1,2,3};
        ListNode head4 = solution.createList(arr4);
        ListNode result4 = solution.swapPairs(head4);
        System.out.println("Test 4: [1,2,3] -> " + solution.listToString(result4)); // [2,1,3]
        
        // Test case 5: [1,2,3,4,5] -> [2,1,4,3,5]
        int[] arr5 = {1,2,3,4,5};
        ListNode head5 = solution.createList(arr5);
        ListNode result5 = solution.swapPairs(head5);
        System.out.println("Test 5: [1,2,3,4,5] -> " + solution.listToString(result5)); // [2,1,4,3,5]
        
        System.out.println("All test cases completed!");
    }
}
