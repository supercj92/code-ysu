package com.cfysu.algorithm;

public class SimpleSort {
	
	public static void bubbleSort(int[] sortArray){
		for(int out = sortArray.length - 1;out > 0;out--){
			for(int in = 0;in < out;in++){
				//System.out.println(sortArray[in] + ":" + sortArray[in + 1]);
				if(sortArray[in] > sortArray[in + 1]){
					swap(in, in + 1, sortArray);
				}
			}
		}
	}
	
	public static void selectSort(int[] sortArray){
		for(int out = 0;out < sortArray.length -1;out ++){
			int max = out;
			for(int in = out + 1;in < sortArray.length;in++){
				if(sortArray[in] > sortArray[max]){
					//保存最大元素的下标
					max = in;
				}
			}
			swap(out, max, sortArray);
		}
	}
	
	public static void insertSort(int[] sortArray){
		int in;
		for(in = 1;in < sortArray.length;in++){
			int temp = sortArray[in];
			while (in > 0 && temp <= sortArray[in-1]){
				//向后移动
				sortArray[in] = sortArray[in-1];
				in--;
			}
			//插入
			sortArray[in] = temp;
		}
	}
	
	private static void swap(int a, int b, int[] sortArray){
		int temp = sortArray[a];
		sortArray[a] = sortArray[b];
		sortArray[b] = temp;
	}

}
