---
layout: post
title:  "【面试准备】算法"
date:   2019-01-23 20:35:12 +0800
use_math: true
categories: algorithm
---

<!-- vscode-markdown-toc -->
* 1. [排序 sort](#sort)
	* 1.1. [选择排序 select sort](#selectsort)
	* 1.2. [冒泡排序 bubble sort](#bubblesort)
	* 1.3. [插入排序 Insertion sort](#Insertionsort)
	* 1.4. [希尔排序 shell sort](#shellsort)
	* 1.5. [归并排序 merge sort](#mergesort)
	* 1.6. [快排 quick sort](#quicksort)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

##  1. <a name='sort'></a>排序 sort
不另行说明均为将待排序变为增序。

###  1.1. <a name='selectsort'></a>选择排序 select sort
选取每一轮查询剩余最大，然后交换至当前剩余的最末尾。很好理解
- 时间复杂度 O(n²)
- 空间复杂度 O(1)
###  1.2. <a name='bubblesort'></a>冒泡排序 bubble sort
这个也很好理解。
每次对相邻两个比较，如果不按顺序则二者交换。
（交换次数多啊！）
- 时间复杂度 O(n²)
- 空间复杂度 O(1)
###  1.3. <a name='Insertionsort'></a>插入排序 Insertion sort
每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
插入排序的复杂度取决于数组的初始顺序，如果数组已经部分有序了，逆序较少，那么插入排序会很快。（因此希尔排序也是借助了这一特性）

- 平均情况下插入排序需要 ~N²/4 比较以及 ~N²/4 次交换；
- 最坏的情况下需要 ~N²/2 比较以及 ~N²/2 次交换，最坏的情况是数组是倒序的；
- 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。

###  1.4. <a name='shellsort'></a>希尔排序 shell sort
> 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。希尔排序是非稳定排序算法。
希尔排序是基于插入排序的以下两点性质而提出改进方法的：
插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
——维基百科

希尔排序通过 将比较的全部元素分为几个区域来提升插入排序的性能。
这样可以让一个元素可以一次性地朝最终位置前进一大步。
然后算法再取越来越小的步长进行排序，
算法的**最后一步**就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。

```java
public static void shellSort(int[] array) {
    int number = array.length / 2;// 间隔
    int i;
    int j;
    int temp;
    while (number >= 1) {
        for (i = number; i < array.length; i++) {
            temp = array[i];
            j = i - number;
            while (j >= 0 && array[j] < temp) {
                array[j + number] = array[j];
                j = j - number;
            }
            array[j + number] = temp;
        }
        number = number / 2;
    }
}
```
然而最坏情况下实际上就是插入排序……（至于步长序列，选择2弊端较多
已知的最好步长序列是由Sedgewick提出的(1, 5, 19, 41, 109,...)，该序列的项来自 $${\displaystyle 9\times 4^{i}-9\times 2^{i}+1} 9\times 4^{i}-9\times 2^{i}+1 $$ 和 $${\displaystyle 2^{i+2}\times (2^{i+2}-3)+1} 2^{{i+2}}\times (2^{{i+2}}-3)+1$$这两个算式[1]。这项研究也表明“比较在希尔排序中是最主要的操作，而不是交换。”用这样步长序列的希尔排序比插入排序要快，甚至在小数组中比快速排序和堆排序还快，但是在涉及大量数据时希尔排序还是比快速排序慢。


###  1.5. <a name='mergesort'></a>归并排序 merge sort
分成多个比较小的块，排序后将有序的数组合并。
```java
void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
	if (start >= end)
		return;
	int len = end - start, mid = start + (len >> 1);
	int start1 = start, end1 = mid;
	int start2 = mid + 1, end2 = end;
	merge_sort_recursive(arr, result, start1, end1);
	merge_sort_recursive(arr, result, start2, end2);
	int k = start;
    // merge 合并有序
	while (start1 <= end1 && start2 <= end2)
		result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
	while (start1 <= end1)
		result[k++] = arr[start1++];
	while (start2 <= end2)
		result[k++] = arr[start2++];
	for (k = start; k <= end; k++)
		arr[k] = result[k];
}
```

###  1.6. <a name='quicksort'></a>快排 quick sort
步骤：
- 从数列中挑出一个元素，称为“基准”（pivot）。此处用位于数据中间的作为基准。
- 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任何一边）。在这个分割结束之后，该基准就处于数列的中间位置。这个称为分割（partition）操作。
- 递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。
```java
void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }
```