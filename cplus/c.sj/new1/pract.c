//#include<stdio.h>
//#include <stdlib.h>
//
#include <cstddef>
struct ListNode {
    int val;
    struct ListNode* next;
};
//
//struct ListNode* addInList(struct ListNode* head1, struct ListNode* head2) {
//    int i = 0, j = 0;
//    struct ListNode* H1 = head1, * H2 = head2;
//
//    // ������������ĳ���
//    while (head1) {
//        head1 = head1->next;
//        i++;
//    }
//    while (head2) {
//        head2 = head2->next;
//        j++;
//    }
//
//    head1 = H1;
//    head2 = H2;
//    // �����������飬���ڴ洢���������ֵ
//    int h1[i], h2[j];
//    // ��ʼ������Ϊ0
//    memset(h1, 0, 4 * i);
//    memset(h2, 0, 4 * j);
//
//    // �����������ֵ�洢��������
//    i = 0, j = 0;
//    while (head1) {
//        h1[i++] = head1->val;
//        head1 = head1->next;
//    }
//    while (head2) {
//        h2[j++] = head2->val;
//        head2 = head2->next;
//    }
//
//    // ȷ���������ĳ��ȣ�ȡ���������ȵ����ֵ��1�����ڿ��ܵĽ�λ��
//    int c = i > j ? i : j;
//    // ����������飬����ʼ��Ϊ0
//    int add[c + 1];
//    memset(add, 0, 4 * (c + 1));
//    // ��ʼ���������������
//    i--, j--;
//    int v = 0;
//
//    // �Ӻ���ǰ��ӣ������λ
//    for (int m = c; m >= 0; m--) {
//        // ��ȡ��ǰλ��ֵ�����������Ч��ȡֵ������ȡ0
//        int sum = (i >= 0 ? h1[i--] : 0) + (j >= 0 ? h2[j--] : 0) +add[m];
//        // ��ǰλ��ֵΪsum�ĸ�λ
//        add[m] = sum % 10;
//        // ��λΪsum��ʮλ���ӵ�ǰһλ
//        if (m > 0) add[m - 1] = sum / 10;
//    }
//
//    // �����������
//    struct ListNode* result = NULL;
//    struct ListNode* current = NULL;
//    for (int m = 0; m <= c; m++) {
//        // ����ǰ����
//        if (m == 0 && add[m] == 0) continue;
//        // �����½ڵ�
//        struct ListNode* newNode = (struct ListNode*)malloc(sizeof(struct ListNode));
//        newNode->val = add[m];
//        newNode->next = NULL;
//        // ����������Ϊ�գ���ʼ���������
//        if (result == NULL) {
//            result = newNode;
//            current = newNode;
//        }
//        else {
//            // �����½ڵ���ӵ�����ĩβ
//            current->next = newNode;
//            current = newNode;
//        }
//    }
//
//    return result;
//}

//�鲢����
struct ListNode* merge(struct ListNode* first, struct ListNode* second) {
    struct ListNode dummy; // ����ͷ�ڵ�
    struct ListNode* tail = &dummy; // βָ��
    dummy.next = NULL;

    // ��������������˳��ϲ�
    while (first && second) {
        if (first->val < second->val) {
            tail->next = first;
            first = first->next;
        }
        else {
            tail->next = second;
            second = second->next;
        }
        tail = tail->next;
    }

    // ��ʣ����������ӵ���������
    if (first) {
        tail->next = first;
    }
    else {
        tail->next = second;
    }

    return dummy.next; // ���غϲ��������ͷ�ڵ�
}

struct ListNode* mortsort(struct ListNode* head, struct ListNode* end) {
    if (head == NULL || head->next == NULL) {
        return head;
    }
    struct ListNode* mid = head, * temp = head;
    while (temp != end && temp->next != end) {
        mid = mid->next;
        temp = temp->next->next;
    }
    struct ListNode* first = head;
    struct ListNode* sceond = mid->next;
    mid->next = NULL;
    first = mortsort(first, mid);
    sceond = mortsort(sceond, end);

    return merge(first, sceond);
}

struct ListNode* sortInList(struct ListNode* head) {
    // write code here
    if (head == NULL || head->next == NULL) {
        return head;
    }
    struct ListNode* end = head;
    while (end->next != NULL) {
        end = end->next;
    }

    return  mortsort(head, end);
}
