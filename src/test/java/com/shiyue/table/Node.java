package com.shiyue.table;

/**
 * 结点类
 * （因为后续在main方法中运行，为了方便定义为static内部类）
 */
class Node {
    int val; // 数据域
    Node next; // 指针域，指向下一个结点

    Node(int x, Node nextNode) {
        val = x;
        next = nextNode;
    }

    public static Node removeNode(Node node,int target){
        Node fast = node ,slow = node;
        if (node == null || node.next == null){
            return null;
        }
        for (int l = 0; l<target;l++){
            fast = fast.next;
        }

        while (fast.next!= null){
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        
        return node;
    }
    
    public static Node cycleNode(Node head) {

        Node prev = null; // 保存前一个结点的信息

        // 循环遍历链表中的结点
        while (head.next != null) {
            // 1. 先保存当前结点的下一个结点的信息到tempNext
            Node tempNext = head.next;
            // 2. 修改当前结点指针域，使其指向上一个结点（如果是第一次进入循环的头结点，则其上一个结点为null）
            head.next = prev;
            // 3. 将当前结点信息保存到prev中（以作为下一次循环中第二步使用到的"上一个结点"）
            prev = head;
            // 4. 当前结点在之前的123步中指针域已经修改完毕，此时让head重新指向待处理的下一个结点
            head = tempNext;
        }

        // 上面的循环完成后，实际只修改了原先链表中的头结点到倒数第二个结点间的结点指向，倒数第一个结点（尾结点）并未处理
        // 此时prev指向原先链表中的倒数第二个结点，head指向尾结点
        // 处理尾结点的指针域，使其指向前一个结点
        head.next = prev;

        // 返回尾结点，此时的尾结点既是原先链表中的尾结点，又是反转后的新链表中的头结点
        return head;
    }

    

    public static void main(String[] args) {
        // 构造测试用例，链表指向为 N1 -> N2 -> N3 -> N4
        Node n5 = new Node(5, null);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        Node head = n1;
        // 输出测试用例
        System.out.println("原始链表指向为：");
        printNode(head);

        System.out.println("原始链表指向为：");
        head = removeNode(head,4 );
        printNode(head);
        
        // 普通方式反转链表
        System.out.println("循环方式反转链表指向为：");
        head = cycleNode(head);
        
        
        
        printNode(head);
    }

//    public static void main(String[] args) {
//        // 构造测试用例，链表指向为 N1 -> N2 -> N3 -> N4
//        Node n4 = new Node(4, null);
//        Node n3 = new Node(3, n4);
//        Node n2 = new Node(2, n3);
//        Node n1 = new Node(1, n2);
//        Node head = n1;
//        // 输出测试用例
//        System.out.println("原始链表指向为：");
//        printNode(head);
//
//
//        // 递归方式反转链表
//        System.out.println("递归方式反转链表指向为：");
//        System.out.println("递归调用前 head 引用指向对象: " + head.toString());
//        recursionNode(head, null);
//        System.out.println("递归调用后 head 引用指向对象: " + head.toString());
//        printNode(head);
//    }

    /**
     * 循环打印链表数据域
     * @param head
     */
    public static void printNode(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 递归实现链表反转
     * 递归方法执行完成后，head指向就从原链表顺序：头结点->尾结点 中的第一个结点（头结点） 变成了反转后的链表顺序：尾结点->头结点 中的第一个结点（尾结点）
     *
     * @param head 头结点
     * @param prev 存储上一个结点
     */
    public static void recursionNode(Node head, Node prev) {
        System.out.println("递归调用中 head引用指向对象: " + head.toString());
        if (null == head.next) {
            // 设定递归终止条件
            // 当head.next为空时，表名已经递归到了原链表中的尾结点，此时单独处理尾结点指针域，然后结束递归
            head.next = prev;
            System.out.println("递归调用返回前 head引用指向对象: " + head.toString());
            return;
        }

        // 1. 先保存当前结点的下一个结点的信息到tempNext
        Node tempNext = head.next;
        // 2. 修改当前结点指针域，使其指向上一个结点（如果是第一次进入循环的头结点，则其上一个结点为null）
        head.next = prev;
        // 3. 将当前结点信息保存到prev中（以作为下一次递归中第二步使用到的"上一个结点"）
        prev = head;
        // 4. 当前结点在之前的123步中指针域修改已经修改完毕，此时让head重新指向待处理的下一个结点
        head = tempNext;

        // 递归处理下一个结点
        recursionNode(head, prev);
    }

 
    
    
    
}

    
