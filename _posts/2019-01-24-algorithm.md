---
layout: post
title:  "【面试准备】算法"
date:   2019-01-23 20:35:12 +0800
categories: algorithm
---

<!-- vscode-markdown-toc -->
* 1. [选择排序 select sort](#selectsort)
* 2. [冒泡排序 bubble sort](#bubblesort)
* 3. [插入排序 Insertion sort](#Insertionsort)
* 4. [希尔排序 shell sort](#shellsort)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# 排序 sort
不另行说明均为将待排序变为增序。

##  1. <a name='selectsort'></a>选择排序 select sort
选取每一轮查询剩余最大，然后交换至当前剩余的最末尾。很好理解
- 时间复杂度 O(n²)
- 空间复杂度 O(1)
##  2. <a name='bubblesort'></a>冒泡排序 bubble sort
这个也很好理解。
每次对相邻两个比较，如果不按顺序则二者交换。
（交换次数多啊！）
- 时间复杂度 O(n²)
- 空间复杂度 O(1)
##  3. <a name='Insertionsort'></a>插入排序 Insertion sort
每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
插入排序的复杂度取决于数组的初始顺序，如果数组已经部分有序了，逆序较少，那么插入排序会很快。

- 平均情况下插入排序需要 ~N²/4 比较以及 ~N²/4 次交换；
- 最坏的情况下需要 ~N²/2 比较以及 ~N²/2 次交换，最坏的情况是数组是倒序的；
- 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。

##  4. <a name='shellsort'></a>希尔排序 shell sort
> 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。希尔排序是非稳定排序算法。
希尔排序是基于插入排序的以下两点性质而提出改进方法的：
插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
——维基百科

希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。
这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。

```java
public static void shellSort(int[] array) {
    int number = array.length / 2;
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