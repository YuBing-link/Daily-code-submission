#include<stdio.h>
#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode* next;
};

struct ListNode* addInList(struct ListNode* head1, struct ListNode* head2) {
    int i = 0, j = 0;
    struct ListNode* H1 = head1, * H2 = head2;

    // 计算两个链表的长度
    while (head1) {
        head1 = head1->next;
        i++;
    }
    while (head2) {
        head2 = head2->next;
        j++;
    }

    head1 = H1;
    head2 = H2;
    // 创建两个数组，用于存储两个链表的值
    int h1[i], h2[j];
    // 初始化数组为0
    memset(h1, 0, 4 * i);
    memset(h2, 0, 4 * j);

    // 将两个链表的值存储到数组中
    i = 0, j = 0;
    while (head1) {
        h1[i++] = head1->val;
        head1 = head1->next;
    }
    while (head2) {
        h2[j++] = head2->val;
        head2 = head2->next;
    }

    // 确定结果数组的长度，取两个链表长度的最大值加1（用于可能的进位）
    int c = i > j ? i : j;
    // 创建结果数组，并初始化为0
    int add[c + 1];
    memset(add, 0, 4 * (c + 1));
    // 初始化两个数组的索引
    i--, j--;
    int v = 0;

    // 从后往前相加，处理进位
    for (int m = c; m >= 0; m--) {
        // 获取当前位的值，如果索引有效则取值，否则取0
        int sum = (i >= 0 ? h1[i--] : 0) + (j >= 0 ? h2[j--] : 0) +add[m];
        // 当前位的值为sum的个位
        add[m] = sum % 10;
        // 进位为sum的十位，加到前一位
        if (m > 0) add[m - 1] = sum / 10;
    }

    // 创建结果链表
    struct ListNode* result = NULL;
    struct ListNode* current = NULL;
    for (int m = 0; m <= c; m++) {
        // 跳过前导零
        if (m == 0 && add[m] == 0) continue;
        // 创建新节点
        struct ListNode* newNode = (struct ListNode*)malloc(sizeof(struct ListNode));
        newNode->val = add[m];
        newNode->next = NULL;
        // 如果结果链表为空，初始化结果链表
        if (result == NULL) {
            result = newNode;
            current = newNode;
        }
        else {
            // 否则将新节点添加到链表末尾
            current->next = newNode;
            current = newNode;
        }
    }

    return result;
}