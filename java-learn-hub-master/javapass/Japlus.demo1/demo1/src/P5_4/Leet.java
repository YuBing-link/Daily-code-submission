package P5_4;

public class Leet {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }







    public static ListNode swapPairs(ListNode head) {

        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy=new ListNode(0,head);
        ListNode cur=dummy;
        while(cur.next!=null&&cur.next.next!=null){
            ListNode first=cur.next;
            ListNode sceond=cur.next.next;
            first.next=sceond.next;
            sceond.next=first;
            cur.next=sceond;
            cur=first;
        }
        return dummy.next;
    }
public static ListNode swapPairs1(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode newHead = head.next;
    head.next = swapPairs(newHead.next);
    newHead.next = head;
    return newHead;
}

        }



