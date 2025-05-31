struct ListNode* merge(struct ListNode* first, struct ListNode* second) {
    struct ListNode dummy; // 虚拟头节点
    struct ListNode* tail = &dummy; // 尾指针
    dummy.next = NULL;

    // 遍历两个链表，按顺序合并
    while (first && second) {
        if (first->val < second->val) {
            tail->next = first;
            first = first->next;
        } else {
            tail->next = second;
            second = second->next;
        }
        tail = tail->next;
    }

    // 将剩余的链表链接到新链表中
    if (first) {
        tail->next = first;
    } else {
        tail->next = second;
    }

    return dummy.next; // 返回合并后的链表头节点
}

struct ListNode* mortsort(struct ListNode* head,  struct ListNode* end) {
    if (head==NULL||head->next==NULL) {
        return head;
    }
    struct ListNode*mid=head,*temp=head;
    while (temp!=end&&temp->next!=end) {
        mid=mid->next;
        temp=temp->next->next;
    }
    struct ListNode* first=head;
    struct ListNode*sceond=mid->next;
    mid->next=NULL;
    first=mortsort(first , mid);
    sceond=mortsort(sceond , end);

    return merge(first,sceond);
}

struct ListNode* sortInList(struct ListNode* head ) {
    // write code here
    if (head==NULL||head->next==NULL) {
        return head;
    }
    struct ListNode* end=head;
    while (end->next!=NULL) {
        end=end->next;
    }

   return  mortsort(head,end);
}