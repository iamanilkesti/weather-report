package com.kesti.weatherreport;

import java.util.Arrays;

public class MergeArrays {
	public static void main(String[] args) 
    {
        int[] firstArr = new int[] {1,3,5,7,8};
         
        int[] secondArr = new int[] {2,4,6,9,10};
         
        int[] resArray = merge(firstArr, secondArr);
        System.out.println("First Array  : "+Arrays.toString(firstArr));
        System.out.println("Second Array  : "+Arrays.toString(secondArr));
        System.out.println("Final Merged Array : "+Arrays.toString(resArray));
    }
	private static int[] merge(int[] arrayA, int[] arrayB)
    {
        int[] resultArr = new int[arrayA.length + arrayB.length];
        int i=0, j=0, k=0;
         
        while (i < arrayA.length && j < arrayB.length)
        {
            if (arrayA[i] < arrayB[j]) 
            {
                resultArr[k] = arrayA[i];
                i++; k++;
            } 
            else
            {
                resultArr[k] = arrayB[j];
                j++; k++;
            }
        } 
                 
        while (i < arrayA.length) 
        {
            resultArr[k] = arrayA[i];
            i++;k++;
        } 
                 
        while (j < arrayB.length) 
        {
            resultArr[k] = arrayB[j];
            j++; k++;
        } 
        return resultArr;
    }
}
