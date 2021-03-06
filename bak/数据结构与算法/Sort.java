package test;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int[] arr = { 56, 21, 48, 6, 95, 23, 4, 98, 23, 56, 78 };

		// insertionSort(arr);
		// shellSort(arr);
		// selectionSort(arr);
		// heapSort(arr);
		// bubbleSort(arr);
		// quickSort(arr);
		mergeSort(arr);

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
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	/**
	 * 希尔排序<br>
	 * 是插入排序的改进版本<br>
	 * 基于插入排序的性质：1.对几乎已排序好的数据操作效率高 2.每次只能将数据移动一位,相当低效
	 * 
	 * @param arr
	 */
	private static void shellSort(int[] arr) {
		int num = arr.length / 2;
		while (num >= 1) {
			for (int i = num; i < arr.length; i++) {
				int key = arr[i];
				int j = i - num;
				while (j >= 0 && arr[j] > key) {
					arr[j + num] = arr[j];
					j = j - num;
				}
				arr[j + num] = key;
			}
			num = num / 2;
		}
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
	 * 堆排序
	 * 
	 * @param arr
	 */
	private static void heapSort(int[] arr) {
		// 数组下标从0开始
		int len = arr.length - 1;
		// 最后一个非叶子节点的位置
		int beginIndex = arr.length / 2 - 1;
		// 构造大顶堆
		for (int i = beginIndex; i >= 0; i--) {
			maxHeapify(i, len, arr);
		}
		// 将根节点（最大）与未排序序列末位元素交换
		// 交换后验证根节点是否满足堆的定义， 并调整
		for (int i = len; i > 0; i--) {
			swap(arr, i, 0);
			maxHeapify(0, i - 1, arr);
		}
	}

	/**
	 * 调整位置为index处的节点，使其数据满足堆的定义
	 * 
	 * @param index
	 * @param len
	 *            未堆化处理的序列长度
	 * @param arr
	 */
	private static void maxHeapify(int index, int len, int[] arr) {
		int leftChild = index * 2 + 1;
		int rightChild = leftChild + 1;
		// 若左子节点越界则直接返回
		if (leftChild > len) {
			return;
		}
		// 比较左右子节点大小
		int maxChild = leftChild;
		if (rightChild <= len && arr[rightChild] > arr[leftChild]) {
			maxChild = rightChild;
		}
		// 若较大的子节点大于父节点，则交换，并继续验证交换后的父节点是否满足堆的定义
		if (arr[maxChild] > arr[index]) {
			swap(arr, maxChild, index);
			maxHeapify(maxChild, len, arr);
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

	/**
	 * 快速排序<br>
	 * 挑选基准值，将比基准值小的放在前面，大的放在后面<br>
	 * 递归执行
	 * 
	 * @param arr
	 */
	private static void quickSort(int[] arr) {
		qSort(arr, 0, arr.length - 1);
	}

	private static void qSort(int[] arr, int begin, int end) {
		if (begin >= end || arr == null || arr.length <= 1) {
			return;
		}
		int i = begin, j = end;
		// 选取基准值
		int pivot = arr[(begin + end) / 2];
		while (i <= j) {
			// 从i开始往后找到大于等于pivot的值
			while (arr[i] < pivot) {
				i++;
			}
			// 从j开始往前找到小于等于pivot的值
			while (arr[j] > pivot) {
				j--;
			}
			// 交换
			// 若i >= j则说明此轮排序过程完成
			if (i < j) {
				swap(arr, i, j);
				i++;
				j--;
			} else if (i == j) {
				i++;
			}
		}
		// i > j
		qSort(arr, begin, j);
		qSort(arr, i, end);
	}

	/**
	 * 归并排序
	 * 
	 * @param arr
	 */
	private static void mergeSort(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		mergeSortRecursion(arr, result, 0, len - 1);
		// mergeSortIteration(arr);
	}

	/**
	 * 递归法实现归并排序
	 * 
	 * @param arr
	 * @param result
	 * @param start
	 * @param end
	 */
	private static void mergeSortRecursion(int[] arr, int[] result, int start, int end) {
		if (start >= end) {
			return;
		}
		// 拆分
		int mid = (end - start) / 2 + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		mergeSortRecursion(arr, result, start1, end1);
		mergeSortRecursion(arr, result, start2, end2);
		// 排序合并
		int i = start;
		// 比较两段序列，小的元素放入result
		while (start1 <= end1 && start2 <= end2) {
			if (arr[start1] < arr[start2]) {
				result[i++] = arr[start1++];
			} else {
				result[i++] = arr[start2++];
			}
		}
		// 剩余没有比较完成的序列元素，放入result
		while (start1 <= end1) {
			result[i++] = arr[start1++];
		}
		while (start2 <= end2) {
			result[i++] = arr[start2++];
		}
		// 将合并后的结果复制到arr中
		for (i = start; i <= end; i++) {
			arr[i] = result[i];
		}
	}

	/**
	 * 迭代法实现归并排序
	 * 
	 * @param arr
	 */
	private static void mergeSortIteration(int[] arr) {
		int[] result = new int[arr.length];
		// 以2^n循环，2/4/8....进行分组排序
		for (int i = 2; i < arr.length * 2; i *= 2) {
			for (int j = 0; j < (arr.length + i - 1) / i; j++) {
				// 得到相邻的两个分组的序号
				int left = i * j;
				int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
				int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
				int start = left, l = left, m = mid;
				// 合并
				while (l < mid && m <= right) {
					if (arr[l] < arr[m]) {
						result[start++] = arr[l++];
					} else {
						result[start++] = arr[m++];
					}
				}
				while (l < mid)
					result[start++] = arr[l++];
				while (m <= right)
					result[start++] = arr[m++];
				// 将合并后的结果复制到arr中
				System.arraycopy(result, left, arr, left, right - left + 1);
			}
		}
	}
}
