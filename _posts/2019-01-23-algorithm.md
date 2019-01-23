---
layout: post
title:  "【面试准备】算法"
date:   2019-01-23 20:35:12 +0800
categories: algorithm
---

<!-- vscode-markdown-toc -->
* 1. [ 链表](#)
	* 1.1. [ 翻转列表](#-1)
	* 1.2. [ 返回链表中倒数第 n 个节点](#n)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# 算法
##  1. <a name=''></a> 链表
###  1.1. <a name='-1'></a> 翻转列表
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
###  1.2. <a name='n'></a> 返回链表中倒数第 n 个节点
> 这么巧妙的解决办法我怎么就没想到呢？
直接用两个快慢指针……倒数第 n 个就让一个指针先走 n 步，然后再让两个一起走……先走的那个到末尾了那么慢走的那个就到了倒数第 n 位。