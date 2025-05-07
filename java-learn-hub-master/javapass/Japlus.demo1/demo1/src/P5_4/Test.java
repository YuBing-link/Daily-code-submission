package P5_4;

public class Test extends Leet {
    public static void main(String[] args) {
        Leet.ListNode p1 = new Leet.ListNode(1);
        Leet.ListNode p2 = new Leet.ListNode(2);
        Leet.ListNode p3 = new Leet.ListNode(3);
        Leet.ListNode p4 = new Leet.ListNode(4);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        Leet.ListNode head =
                swapPairs(p1);
        while (head.next !=null){
            System.out.print(head.val);head=head.next;

        }
    }
}
