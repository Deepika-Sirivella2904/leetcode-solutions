/**
 * LeetCode Problem: Convert The Temperature
 * Problem Link: https://leetcode.com/problems/convert-the-temperature/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-06
 * Submission Link: https://leetcode.com/problems/convert-the-temperature/submissions/1597205025/
 */

class Solution {
    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * Convert Celsius to Kelvin and Fahrenheit.
     * Kelvin = Celsius + 273.15
     * Fahrenheit = Celsius * 1.80 + 32.00
     * Approach: Direct formula application.
     * 
     * @param celsius Temperature in Celsius
     * @return double[] Array containing [Kelvin, Fahrenheit]
     */
    public double[] convertTemperature(double celsius) {
        double[] result = new double[2];
        
        // Convert to Kelvin
        result[0] = celsius + 273.15;
        
        // Convert to Fahrenheit
        result[1] = celsius * 1.80 + 32.00;
        
        return result;
    }
    
    /**
     * Alternative solution using separate methods
     */
    public double[] convertTemperatureAlternative(double celsius) {
        double kelvin = celsiusToKelvin(celsius);
        double fahrenheit = celsiusToFahrenheit(celsius);
        
        return new double[]{kelvin, fahrenheit};
    }
    
    /**
     * Helper method to convert Celsius to Kelvin
     */
    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    /**
     * Helper method to convert Celsius to Fahrenheit
     */
    private double celsiusToFahrenheit(double celsius) {
        return celsius * 1.80 + 32.00;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: 36.50 -> [309.65,97.70]
        double[] result1 = solution.convertTemperature(36.50);
        System.out.println("Test 1: 36.50 -> [" + result1[0] + ", " + result1[1] + "]"); // [309.65,97.70]
        
        // Test case 2: 0.00 -> [273.15,32.00]
        double[] result2 = solution.convertTemperature(0.00);
        System.out.println("Test 2: 0.00 -> [" + result2[0] + ", " + result2[1] + "]"); // [273.15,32.00]
        
        // Test case 3: -100.00 -> [173.15,-148.00]
        double[] result3 = solution.convertTemperature(-100.00);
        System.out.println("Test 3: -100.00 -> [" + result3[0] + ", " + result3[1] + "]"); // [173.15,-148.00]
        
        // Test case 4: 100.00 -> [373.15,212.00]
        double[] result4 = solution.convertTemperature(100.00);
        System.out.println("Test 4: 100.00 -> [" + result4[0] + ", " + result4[1] + "]"); // [373.15,212.00]
        
        // Test case 5: -273.15 -> [0.00,-459.67]
        double[] result5 = solution.convertTemperature(-273.15);
        System.out.println("Test 5: -273.15 -> [" + result5[0] + ", " + result5[1] + "]"); // [0.00,-459.67]
        
        // Test case 6: 25.00 -> [298.15,77.00]
        double[] result6 = solution.convertTemperature(25.00);
        System.out.println("Test 6: 25.00 -> [" + result6[0] + ", " + result6[1] + "]"); // [298.15,77.00]
        
        // Test case 7: -40.00 -> [233.15,-40.00] (interesting case where Celsius = Fahrenheit)
        double[] result7 = solution.convertTemperature(-40.00);
        System.out.println("Test 7: -40.00 -> [" + result7[0] + ", " + result7[1] + "]"); // [233.15,-40.00]
        
        // Test case 8: 37.00 -> [310.15,98.60] (human body temperature)
        double[] result8 = solution.convertTemperature(37.00);
        System.out.println("Test 8: 37.00 -> [" + result8[0] + ", " + result8[1] + "]"); // [310.15,98.60]
        
        // Test case 9: 98.60 -> [371.75,209.48]
        double[] result9 = solution.convertTemperature(98.60);
        System.out.println("Test 9: 98.60 -> [" + result9[0] + ", " + result9[1] + "]"); // [371.75,209.48]
        
        // Test case 10: -17.78 -> [255.37,0.00] (freezing point in Fahrenheit)
        double[] result10 = solution.convertTemperature(-17.78);
        System.out.println("Test 10: -17.78 -> [" + result10[0] + ", " + result10[1] + "]"); // [255.37,0.00]
        
        // Test alternative method
        System.out.println("\nTesting alternative method:");
        double[] altResult = solution.convertTemperatureAlternative(36.50);
        System.out.println("Alternative Test 1: 36.50 -> [" + altResult[0] + ", " + altResult[1] + "]"); // [309.65,97.70]
        
        System.out.println("All test cases completed!");
    }
}
