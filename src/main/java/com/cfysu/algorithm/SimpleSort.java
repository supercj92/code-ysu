package com.cfysu.algorithm;

public class SimpleSort {
	
	public static void bubbleSort(int[] sortArray){
		for(int out = sortArray.length - 1;out > 0;out--){
			for(int in = 0;in < out;in++){
				if(sortArray[in] > sortArray[in + 1]){
					swap(in, in + 1, sortArray);
				}
			}
			//每一次外层循环结束，产生的最大的数都绝对有序了
		}
	}

	public static void bubbleSort2(int[] sortArray){
		for(int i = sortArray.length -1;i > 0;i--){
			for(int j = 0;j < i;j++){
				if(sortArray[j] > sortArray[j + 1]){
					swap(j, j+1, sortArray);
				}
			}
		}
	}

	public static void selectSort(int[] sortArray){
		for(int out = 0;out < sortArray.length -1;out++){
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

	public static void selectSortAsc(int[] sortArray){
		for(int i = sortArray.length -1;i > 0;i--){
			int max = i;
			for(int j = 0;j <= i;j++){
				if(sortArray[j] > sortArray[max]){
					max = j;
				}
			}
			swap(max, i, sortArray);
		}
	}

	/**
	 * 错误
	 * @param sortArray
	 */
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

	public static void insertSort2(int[] sortArray){
		for(int i = 1;i <= sortArray.length - 1;i++){
			int temp = sortArray[i];
			int j = i - 1;
			for(;j >= 0;j--){
				if(sortArray[j] > temp){
					sortArray[j+1] = sortArray[j];
				}else {
					break;
				}
			}
			sortArray[j+1] = temp;
		}
	}

	public static void insertSort3(int[] sortArray){
		for(int outer = 1;outer <= sortArray.length - 1;outer++){
			int temp = sortArray[outer];
			int inner = outer;
			while (inner > 0 && sortArray[inner-1] > temp){
				sortArray[inner] = sortArray[inner - 1];
				inner--;
			}
			sortArray[inner] = temp;
		}
	}

	private static void swap(int a, int b, int[] sortArray){
		int temp = sortArray[a];
		sortArray[a] = sortArray[b];
		sortArray[b] = temp;
	}

}
