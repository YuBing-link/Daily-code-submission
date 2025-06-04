class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class IntersectionLinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        ListNode pA = headA, pB = headB;
        // 双指针遍历，直到相遇或同时为null
        while (pA != pB) {
            // 遍历完A则跳转到B，反之同理
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA; // 相交则返回交点，不相交则返回null
    }

    // 测试用例
    public static void main(String[] args) {
        IntersectionLinkedList solution = new IntersectionLinkedList();
        
        // 构造相交链表：A = 1 -> 2 -> 3 \    B = 6 -> 3（共享3及后续）
        ListNode common = new ListNode(3);
        common.next = new ListNode(4);
        
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = common;
        
        ListNode headB = new ListNode(6);
        headB.next = common;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        if (result != null) {
            System.out.println("相交节点值：" + result.val); // 输出 3
        } else {
            System.out.println("不相交");
        }
    }
}