/**
 * LeetCode Problem: Defanging an IP Address
 * Problem Link: https://leetcode.com/problems/defanging-an-ip-address/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-23
 * Submission Link: https://leetcode.com/problems/defanging-an-ip-address/submissions/1581362224/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Defang an IP address by replacing all "." with "[.]".
     * Approach: String replacement.
     * 
     * @param address IP address string
     * @return String Defanged IP address
     */
    public String defangIPaddr(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }
        
        return address.replace(".", "[.]");
    }
    
    /**
     * Alternative solution using StringBuilder
     */
    public String defangIPaddrStringBuilder(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                result.append("[.]");
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Alternative solution using character array
     */
    public String defangIPaddrCharArray(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }
        
        // Count dots to determine new array size
        int dotCount = 0;
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == '.') {
                dotCount++;
            }
        }
        
        // Each dot adds 2 extra characters (.[.] vs .)
        char[] result = new char[address.length() + 2 * dotCount];
        int index = 0;
        
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                result[index++] = '[';
                result[index++] = '.';
                result[index++] = ']';
            } else {
                result[index++] = c;
            }
        }
        
        return new String(result);
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: "1.1.1.1" -> "1[.]1[.]1[.]1"
        String address1 = "1.1.1.1";
        String result1 = solution.defangIPaddr(address1);
        System.out.println("Test 1: \"1.1.1.1\" -> \"" + result1 + "\""); // "1[.]1[.]1[.]1"
        
        // Test case 2: "255.100.50.0" -> "255[.]100[.]50[.]0"
        String address2 = "255.100.50.0";
        String result2 = solution.defangIPaddr(address2);
        System.out.println("Test 2: \"255.100.50.0\" -> \"" + result2 + "\""); // "255[.]100[.]50[.]0"
        
        // Test case 3: "0.0.0.0" -> "0[.]0[.]0[.]0"
        String address3 = "0.0.0.0";
        String result3 = solution.defangIPaddr(address3);
        System.out.println("Test 3: \"0.0.0.0\" -> \"" + result3 + "\""); // "0[.]0[.]0[.]0"
        
        // Test case 4: "127.0.0.1" -> "127[.]0[.]0[.]1"
        String address4 = "127.0.0.1";
        String result4 = solution.defangIPaddr(address4);
        System.out.println("Test 4: \"127.0.0.1\" -> \"" + result4 + "\""); // "127[.]0[.]0[.]1"
        
        // Test case 5: "192.168.1.1" -> "192[.]168[.]1[.]1"
        String address5 = "192.168.1.1";
        String result5 = solution.defangIPaddr(address5);
        System.out.println("Test 5: \"192.168.1.1\" -> \"" + result5 + "\""); // "192[.]168[.]1[.]1"
        
        // Test case 6: "" -> "" (empty string)
        String address6 = "";
        String result6 = solution.defangIPaddr(address6);
        System.out.println("Test 6: \"\" -> \"" + result6 + "\""); // ""
        
        // Test case 7: "1" -> "1" (no dots)
        String address7 = "1";
        String result7 = solution.defangIPaddr(address7);
        System.out.println("Test 7: \"1\" -> \"" + result7 + "\""); // "1"
        
        // Test case 8: "10.0.0.1" -> "10[.]0[.]0[.]1"
        String address8 = "10.0.0.1";
        String result8 = solution.defangIPaddr(address8);
        System.out.println("Test 8: \"10.0.0.1\" -> \"" + result8 + "\""); // "10[.]0[.]0[.]1"
        
        // Test StringBuilder method
        System.out.println("\nTesting StringBuilder method:");
        System.out.println("StringBuilder Test 1: \"1.1.1.1\" -> \"" + solution.defangIPaddrStringBuilder(address1) + "\""); // "1[.]1[.]1[.]1"
        
        // Test character array method
        System.out.println("\nTesting character array method:");
        System.out.println("CharArray Test 1: \"1.1.1.1\" -> \"" + solution.defangIPaddrCharArray(address1) + "\""); // "1[.]1[.]1[.]1"
        
        System.out.println("All test cases completed!");
    }
}
