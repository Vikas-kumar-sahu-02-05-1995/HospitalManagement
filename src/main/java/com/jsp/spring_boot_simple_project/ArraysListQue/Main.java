package com.jsp.spring_boot_simple_project.ArraysListQue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        ArrayList<Integer> inputs = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 8, 2, 5));
        int target = 15;

        List<List<Integer>> result = findAllContinuousSubarrays(inputs, target);
        System.out.println("All continuous subarrays that sum to target:");

        for (List<Integer> subarray : result) {
            int start = inputs.indexOf(subarray.get(0));  // Get start index
            int end = start + subarray.size() - 1;  // Get end index
            System.out.println("Subarray: " + subarray + " (Start Index: " + start + ", End Index: " + end + ")");
        }
    }
    public static List<List<Integer>> findAllContinuousSubarrays(ArrayList<Integer> inputs, int target) {
        List<List<Integer>> result = new ArrayList<>();

        for (int start = 0; start < inputs.size(); start++) {
            int sum = 0;

            for (int end = start; end < inputs.size(); end++) {
                sum += inputs.get(end);

                if (sum == target) {
                    result.add(new ArrayList<>(inputs.subList(start, end + 1)));
                } else if (sum > target) {
                    break; // No need to continue if sum exceeds target
                }
            }
        }
        return result;
    }
}

