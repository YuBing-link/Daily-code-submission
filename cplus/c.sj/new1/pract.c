//#include<stdio.h>
//#include <stdlib.h>
//
#include <stdio.h>
#include <stdlib.h> 

  

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
//struct ListNode* merge(struct ListNode* first, struct ListNode* second) {
//    struct ListNode dummy; // ����ͷ�ڵ�
//    struct ListNode* tail = &dummy; // βָ��
//    dummy.next = NULL;
//
//    // ��������������˳��ϲ�
//    while (first && second) {
//        if (first->val < second->val) {
//            tail->next = first;
//            first = first->next;
//        }
//        else {
//            tail->next = second;
//            second = second->next;
//        }
//        tail = tail->next;
//    }
//
//    // ��ʣ����������ӵ���������
//    if (first) {
//        tail->next = first;
//    }
//    else {
//        tail->next = second;
//    }
//
//    return dummy.next; // ���غϲ��������ͷ�ڵ�
//}
//
//struct ListNode* mortsort(struct ListNode* head, struct ListNode* end) {
//    if (head == NULL || head->next == NULL) {
//        return head;
//    }
//    struct ListNode* mid = head, * temp = head;
//    while (temp != end && temp->next != end) {
//        mid = mid->next;
//        temp = temp->next->next;
//    }
//    struct ListNode* first = head;
//    struct ListNode* sceond = mid->next;
//    mid->next = NULL;
//    first = mortsort(first, mid);
//    sceond = mortsort(sceond, end);
//
//    return merge(first, sceond);
//}
//
//struct ListNode* sortInList(struct ListNode* head) {
//    // write code here
//    if (head == NULL || head->next == NULL) {
//        return head;
//    }
//    struct ListNode* end = head;
//    while (end->next != NULL) {
//        end = end->next;
//    }
//
//    return  mortsort(head, end);
//}
//struct ListNode {
//    int val;
//    struct ListNode* next;
//};
//struct ListNode* deleteDuplicates(struct ListNode* head) {
//    // write code here
//    struct ListNode* p = head;
//    while (p) {
//        if (p->next && p->val == p->next->val) {
//            p->next = p->next->next;
//        }
//        else
//            p = p->next;
//    }
//    return head;
//}
//struct ListNode* setpoint(int a) {
//    struct ListNode* p = (struct ListNode*)malloc(sizeof(struct ListNode));
//    if (p == NULL)
//        return NULL;
//    p->val = a;
//    p->next = NULL;
//}
//struct ListNode* main() {
//    
//    struct ListNode* head = setpoint(1);
//    struct ListNode* w1 =setpoint(1) ;
//    struct ListNode* w2 = setpoint(2);
//    struct ListNode* dummy = head;
//    head->next = w1;
//    w1->next = w2;
//    deleteDuplicates(head);
//     while (head!=NULL)
//     {
//         printf("%d ",head->val);
//         head = head->next;
//     }
//     while (dummy!=NULL)
//     {
//         free(dummy);
//         dummy = dummy->next;
//     }
//     return 0;
//}
//struct ListNode* deleteDuplicates(struct ListNode* head) {
//    // write code here
//    if (head->next == NULL || head == NULL) {
//        return head;
//    }
//    struct ListNode dummy;
//    struct ListNode* tail = &dummy, * end = head->next;
//    dummy.next = NULL;
//
//
//    while (end) {
//        int temp = head->val;
//        if (temp == end->val) {
//            head = head->next;
//            end = end->next;
//            if (end == NULL) { goto ab; }
//            if (head->val != end->val) {
//            ab:
//                head = head->next;
//                if (end == NULL) break;
//                end = end->next;
//            }
//        }
//        else {
//            tail->next = head;
//            head = head->next;
//            end = end->next;
//            tail = tail->next;
//        }
//    }
//    tail->next = head;
//
//    return dummy.next;
//}

//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//int main(int argc, char* argv[])
//{
//    // ���ڴ��������Ĵ���
//    int n;
//    scanf_s("%d", &n);
//    int add[100000];
//    for (int i = 1; i <= n; i++) {
//        scanf_s("%d", &add[i]);
//    }
//    int p[100000];
//    memset(p, 0, sizeof(int) * (n + 1));
//    for (int i = 1; i <= n; i++) {
//        p[add[i]]++;
//    }
//    int b = 0;
//    for (int i = 1; i <= n; i++) {
//        if (p[i] != 0) {
//            if (p[i] % 2 != 0) { b++; }
//        }
//    }
//    b = b / 2;
//    printf("%d", b);
//
//    return 0;
//}
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//int main() {
//    int n;
//    scanf("%d", &n);
//    int add[n + 1];
//    for (int i = 1; i <= n; i++) {
//        scanf("%d", &add[i]);
//    }
//
//    int p[n + 1];
//    memset(p, 0, sizeof(p));
//
//
//    for (int i = 1; i <= n; i++) {
//        p[add[i]]++;
//    }
//    int a = 0, b = 0, c = 0;
//    for (int i = 1; i <= n; i++) {
//        if (p[i] == 1)a++;
//        else if (p[i] > 2)
//            b += p[i] - 2;
//    }
//    int sum = 0;
//    if (a >= b)sum = (a + b) / 2;
//    else sum = b;
//    printf("%d", sum);
//
//}

//#include <stdio.h>
//#include <stdlib.h>
//
//int main(int argc, char* argv[])
//{
//    // ���ڴ��������Ĵ���
//    int i = 2023;
//    int a = 0, b = 0, c = 0;
//    for (int j = 1; j <= i; j++) {
//        while (j) {
//            int v = j % 10;
//            if (v == 2)a++;
//            if (v == 0)b++;
//            if (v == 3)c++;
//            j = j / 10;
//        }
//    }
//    a -= 1; c -= 1;
//    long long x = a * a * b * c;
//    printf("%lld", x);
//
//    return 0;
//}








//#include <stdio.h>
//#include <stdlib.h>
//
//int main(int argc, char *argv[])
//{
//  // ���ڴ��������Ĵ���
//  int n=0;
//  
//  scanf_s("%d",&n);
//  int add[3][2];
//  for(int i=0;i<n;i++){
//    scanf_s("%d %d",&add[i][0],&add[i][1]);
//  }
//  int max=0,min=0;
//  max=add[0][0]/add[0][1];
//  for(int i=1;i<n;i++){
//      if(max>add[i][0]/add[i][0]){
//         max=add[i][0]/add[i][0];
//      }
//  }
//  min=max;
//  int temp,p=1;
//  while(1){
//    temp=min;
//    min--;
//    for(int i=0;i<n;i++){
//      if(add[i][0]/min!=add[i][1]){
//          p = 0;
//          break;
//      }
//    }
//    if(p==0){
//      break;
//    }
//  }
//  printf("%d %d",temp,max);
//  return 0;
//}

#include <stdio.h>
#include <stdlib.h>
int compare(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}
int main(int argc, char* argv[])
{
    // ���ڴ��������Ĵ���
    int t = 0;
    scanf_s("%d", &t);
    for (int i = 0; i < t; i++) {
        int n = 0;
        scanf_s("%d", &n);
        int add[3][2];
        int sum1 = 0, sum2 = 0;
        for (int j = 0; j < n; j++) {
            int a, b, c;
            scanf_s("%d %d %d", &a, &b, &c);
            sum1 = a + b; sum2 = a + c;
            add[j][0] = sum1; add[j][1] = sum2;
        }
        qsort(add, n, sizeof(int*), compare);
        int temp = add[0][1];
        int p = 0;
        for (int j = 0; j + 1 < n; j++) {
            if (add[j + 1][0] - temp < 0) {
                p = -1;
                break;
            }
            temp += add[j + 1][1];
        }

        if (p == -1) {
            printf("NO\n");
        }
        else {
            printf("YES\n");
        }
    }
    return 0;
}
