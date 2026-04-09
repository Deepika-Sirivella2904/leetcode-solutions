/**
 * LeetCode Problem: Reverse Linked List
 * Problem Link: https://leetcode.com/problems/reverse-linked-list/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-09
 * Submission Link: https://leetcode.com/problems/reverse-linked-list/submissions/1566214148/
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
     * Reverse a singly linked list.
     * Approach: Iterative three-pointer technique.
     * 
     * @param head Head of the linked list
     * @return ListNode New head of the reversed linked list
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        
        while (current != null) {
            next = current.next;  // Store next node
            current.next = prev; // Reverse the link
            prev = current;       // Move prev forward
            current = next;       // Move current forward
        }
        
        return prev; // prev is the new head
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
        
        // Test case 1: [1,2,3,4,5] -> [5,4,3,2,1]
        int[] arr1 = {1,2,3,4,5};
        ListNode head1 = solution.createList(arr1);
        ListNode result1 = solution.reverseList(head1);
        System.out.println("Test 1: [1,2,3,4,5] -> " + solution.listToString(result1)); // [5,4,3,2,1]
        
        // Test case 2: [1,2] -> [2,1]
        int[] arr2 = {1,2};
        ListNode head2 = solution.createList(arr2);
        ListNode result2 = solution.reverseList(head2);
        System.out.println("Test 2: [1,2] -> " + solution.listToString(result2)); // [2,1]
        
        // Test case 3: [] -> []
        int[] arr3 = {};
        ListNode head3 = solution.createList(arr3);
        ListNode result3 = solution.reverseList(head3);
        System.out.println("Test 3: [] -> " + solution.listToString(result3)); // []
        
        // Test case 4: [1] -> [1]
        int[] arr4 = {1};
        ListNode head4 = solution.createList(arr4);
        ListNode result4 = solution.reverseList(head4);
        System.out.println("Test 4: [1] -> " + solution.listToString(result4)); // [1]
        
        // Test case 5: [1,2,3] -> [3,2,1]
        int[] arr5 = {1,2,3};
        ListNode head5 = solution.createList(arr5);
        ListNode result5 = solution.reverseList(head5);
        System.out.println("Test 5: [1,2,3] -> " + solution.listToString(result5)); // [3,2,1]
        
        // Test case 6: [0] -> [0]
        int[] arr6 = {0};
        ListNode head6 = solution.createList(arr6);
        ListNode result6 = solution.reverseList(head6);
        System.out.println("Test 6: [0] -> " + solution.listToString(result6)); // [0]
        
        // Test case 7: [10,20,30,40] -> [40,30,20,10]
        int[] arr7 = {10,20,30,40};
        ListNode head7 = solution.createList(arr7);
        ListNode result7 = solution.reverseList(head7);
        System.out.println("Test 7: [10,20,30,40] -> " + solution.listToString(result7)); // [40,30,20,10]
        
        System.out.println("All test cases completed!");
    }
}
