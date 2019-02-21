---
layout: post
title:  "【面试准备】数据结构"
date:   2019-01-23 20:35:12 +0800
categories: algorithm
---

<!-- vscode-markdown-toc -->
* 1. [ 链表 linkedlist](#linkedlist)
	* 1.1. [ 翻转列表 reverse the  linkedlist](#reversethelinkedlist)
	* 1.2. [ 返回链表中倒数第 n 个节点 Returns the nth reciprocal node in a linked list](#nReturnsthenthreciprocalnodeinalinkedlist)
	* 1.3. [ 判断链表是否存在环 Ring Exists](#RingExists)
		* 1.3.1. [若单链表有环，如何找出环的入口节点。 If the single-chain table has a ring, how to find the entry node of the ring.](#Ifthesingle-chaintablehasaringhowtofindtheentrynodeofthering.)
	* 1.4. [链表去重 remove duplicate elements](#removeduplicateelements)
* 2. [栈和队列　Stack and queue](#Stackandqueue)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# 算法
##  1. <a name='linkedlist'></a> 链表 linkedlist
###  1.1. <a name='reversethelinkedlist'></a> 翻转列表 reverse the  linkedlist
```cpp
ListNode* ReverseList(ListNode* pHead) {
        ListNode *root = pHead; 
        ListNode *pre = NULL;  
        ListNode *next = NULL;
        if(pHead==NULL) return NULL; 
        while(root->next){
            // 先保存下一个节点
            next = root->next;
            // 让 “头” 的下一个节点指向前一个
            root->next = pre;
            // 因为要往后走，所以“之后的前一个”就是现在的 “头”
            pre = root;
            // “头” 去下一个节点
            root = next;
        }    
        root->next=pre; 
        return root; 
}
```
###  1.2. <a name='nReturnsthenthreciprocalnodeinalinkedlist'></a> 返回链表中倒数第 n 个节点 Returns the nth reciprocal node in a linked list
> 这么巧妙的解决办法我怎么就没想到呢？

- 直接用两个快慢指针……
- 倒数第 n 个就让一个指针先走 n 步
- 然后再让两个一起走
- 先走的那个到末尾了。那么，慢走的那个就到了倒数第 n 位。
###  1.3. <a name='RingExists'></a> 判断链表是否存在环 Ring Exists
> 居然有人说直接用快慢指针是错误答案？

- 快慢指针法
快指针 pf (pointer fast) 每次移动2个节点，慢指针 ps (pointer slowly) 每次移动1个节点，如果快指针能够追上慢指针，那就说明其中有一个环，否则不存在环。

这没啥问题吧？！！

某网站某博客的神逻辑：

> 想像一种情况，当快指针走到一个环的时候，慢指针还离快指针很远，甚至当快指针走出环的时候慢指针还没到达环，这时候快指针永远不会追上慢指针。所以快慢指针无法解决链表存在循环的问题，快慢指针能解决的只是链表存在环的问题，也就是这个循环在链表尾部。可以说链表存在环是链表存在循环的一种特殊情况。

您都成环了啊喂！当这是过山车吗？

- map 存储映射
使用 `map` 进行映射。定义 `map<Node*, int> m` 将每个 `Node*` 映射为数组下标，并赋值为一个 `int` 。然后从链表的头指针开始往后遍历，每次遇到一个指针p，就判断 `m[p]` 是否为0。如果为0，则将 `m[p]` 赋值为1，表示该节点第一次访问；而如果 `m[p]` 的值为1，则说明这个节点已经被访问过一次了，于是就形成了环。

####  1.3.1. <a name='Ifthesingle-chaintablehasaringhowtofindtheentrynodeofthering.'></a>若单链表有环，如何找出环的入口节点。 If the single-chain table has a ring, how to find the entry node of the ring.
同上……如果用 map 来存储那么一切都很简单了……如果再次访问到 1 就说明到了。
但是如果是用快慢指针？
先得计算出环的长度 n ……（如何计算？再绕一圈然后计数）
> 然后，有点神奇

- 再从头开始用两个指针
- 一个先走 长度 n
- 然后两个再以相同的步幅同时走。
- 再能相遇就是对应的要求的入口节点位置。
(为什么？因为两个指针的间隔是 N，环的长度也是 N，两个能相遇的时候就是两个间隔 N 的时候)
###  1.4. <a name='removeduplicateelements'></a>链表去重 remove duplicate elements
- 方法一，最简单粗暴的
（我都不想讲了……太丢人了）
知道数组怎么暴力的吧……道理一样……

- 用一个map（好了，勉强看起来不那么暴力）
1. 建立一个hash表，key为链表中已经遍历的节点内容，初始时为空。 
2. 从头开始遍历单链表中的节点。 
3. 如果节点内容已经在hash表中存在，则删除此节点，继续向后遍历。 
4. 如果节点内容不在hash表中，则保留此节点，将节点内容添加到hash表中，继续向后遍历。 

（注意删除时需要知道前一节点。）
##  2. <a name='Stackandqueue'></a>栈和队列　Stack and queue

###　两个栈实现队列　Two stack implementation queue
- 入队：直接把元素压入 stack1 中；
- 出队：
    - 若 stack2 中不为空，则直接弹出 stack2 中的元素；
    - 若 stack2 中为空，则将 stack1 中的所有元素倒入 stack2 中，然后弹出 stack2 的栈顶元素。
    - 若两个栈都为空，则队列为空队，无法出队。

```java
T pop() {
    if (!stack2.isEmpty()) {
        return stack2.pop();
    } else { // if stack2 isEmpty
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }`
}
```

好像有点不能理解？
入队时非常简单。在出队时，多数情况下可以直接通过弹出 stack2 完成。如果把stack1中的元素倒入stack2中，则一般不用每次都进行这样的操作。最坏的情况就是出队一个元素、入队一个元素这样的循环，导致每次出队都需要转移元素。

思考的内容：
- 一定得是 stack2 空了才能让 1 的东西进来。（为什么呢？因为这是已有的顺序，1 只是暂存倒序，栈全部逆置了再依次出就是队列的出队顺序了）
- 同样道理，2 里面也就是已有的顺序。已有的：出队顺序。

哇，这个好鸡贼啊。