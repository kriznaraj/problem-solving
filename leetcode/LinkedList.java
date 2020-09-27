package leetcode;

public class LinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(final int x) {
            val = x;
        }
    }

    public int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        final String[] parts = input.split(",");
        final int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            final String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public ListNode stringToListNode(final String input) {
        // Generate array from the input
        final int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        final ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (final int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Empty LinkedList");
        }
    }

    public String toString(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null && node.next != null) {
            sb.append(node.val + "->");
            node = node.next;
        }
        if (node != null) {
            sb.append(node.val);
        } else {
            sb.append("[]");
        }
        return sb.toString();
    }

    ListNode swapPairs(ListNode node) {
        return swap(node);
    }

    ListNode swap(ListNode a) {
        if (a != null && a.next != null) {
            ListNode n = swap(a.next.next);
            ListNode t = a.next;
            a.next = n;
            t.next = a;
            return t;
        }
        return a;
    }

    ListNode reverse(ListNode h) {
        return rutil(h, null);
    }

    ListNode rutil(ListNode c, ListNode h) {
        if (c == null) {
            return h;
        }
        ListNode n = c.next;
        c.next = h;
        return rutil(n, c);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode head = l1.val < l2.val ? l1 : l2;
        ListNode p = l1.val < l2.val ? l1.next : l1;
        ListNode q = l1.val < l2.val ? l2 : l2.next;
        ListNode t = head;
        while (p != null && q != null) {
            if (p.val < q.val) {
                t.next = p;
                p = p.next;
            } else {
                t.next = q;
                q = q.next;
            }
            t = t.next;
        }
        t.next = p != null ? p : q;
        return head;
    }

    

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        var list1 = list.stringToListNode("[1,2,4]");
        var list2 = list.stringToListNode("[1,3,4]");
        var r = list.mergeTwoLists(list1, list2);
        list.prettyPrintLinkedList(r);
    }

}
