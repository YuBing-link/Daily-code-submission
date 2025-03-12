/**
 * struct ListNode {
 *  int val;
 *  struct ListNode *next;
 * };
 */
/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 *
 * @param pHead ListNode类
 * @return ListNode类
 */
//int wp(struct ListNode** slow, struct ListNode** fast) {
//    while ((*fast)->next != NULL && (*fast)->next->next != NULL) {
//        *fast = (*fast)->next->next;
//        *slow = (*slow)->next;
//        if (*fast == *slow) {
//            return 0;;
//        }
//
//    }
//    return 1;
//}
//struct ListNode* EntryNodeOfLoop(struct ListNode* pHead ) {
//    // write code here
//    if (pHead == NULL || pHead->next == NULL) {
//        return NULL;
//    }
//    struct ListNode* slow = pHead, *fast = pHead;
//
//    int s = wp(&slow,&fast);
//    if (s==1) {
//        return NULL;
//    }
//
//    fast = pHead;
//    while (fast != slow) {
//        fast = fast->next;
//        slow = slow->next;
//    }
//    return fast;
//}

struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    // write code here
    if (head == NULL) {
        return NULL;
    }
    struct ListNode dume;
    dume.next = head;
    int i = 0;
    struct ListNode* los = head;
    while (los) {
        los = los->next;
        i++;
    }
    int c = i - n;
    if (c < 0)return NULL;
    struct ListNode* w = &dume;
    for (int y = 0; y < c; y++) {
        w = w->next;
    }
    struct ListNode* s = w->next;
    w->next = s->next;
    s->next = NULL;

    return dume.next;



}





