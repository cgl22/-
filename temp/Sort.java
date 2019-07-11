package test;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int[] arr = { 56, 21, 48, 6, 95, 23, 4, 98, 23, 56, 78 };
		// bubbleSort(arr);
		// insertionSort(arr);
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 插入排序<br>
	 * 将第一个元素标记为已排序,遍历arr,将未排序的元素插入到已排序的数组中<br>
	 * 从已排序数组的末尾开始比较
	 * 
	 * @param arr
	 */
	private static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			int key = arr[i];
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	/**
	 * 希尔排序<br>
	 * 是插入排序的改进版本<br>
	 * 基于插入排序的性质：1.对几乎已排序好的数据操作效率高  2.每次只能将数据移动一位,相当低效
	 * 
	 * @param arr
	 */
	private static void shellSort(int[] arr) {

	}

	/**
	 * 选择排序<br>
	 * 每一轮选出最小(大)的,放在已排序数组末尾(初始位置)
	 * 
	 * @param arr
	 */
	private static void selectionSort(int[] arr) {
		int min;
		for (int i = 0; i < arr.length; i++) {
			min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			if (min != i) {
				swap(arr, min, i);
			}
		}
	}

	/**
	 * 冒泡排序<br>
	 * 每一轮选出最大的放在最后
	 * 
	 * @param arr
	 */
	private static void bubbleSort(int[] arr) {
		int i, len = arr.length;
		boolean changed;
		do {
			changed = false;
			len--;
			for (i = 0; i < len; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					changed = true;
				}
			}
		} while (changed);
	}

}
