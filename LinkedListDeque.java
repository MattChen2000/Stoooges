public class LinkedListDeque<Type> {
    private StuffNode sentinel;
    private int size;

    public class StuffNode {
        public StuffNode prev;
        public Type item;
        public StuffNode next;

        public StuffNode(StuffNode p, Type i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new StuffNode(sentinel, null, sentinel);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(Type x) {
        sentinel = new StuffNode(sentinel, null, sentinel);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public int size() {
        return size;
    }

    public void addFirst(Type item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        size += 1;
    }

    public Type getFirst() {
        return sentinel.next.item;
    }

    public void addLast(Type item) {
        sentinel.prev.next = new StuffNode(sentinel.prev, item, sentinel);
        size += 1;
    }

    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        StuffNode n = sentinel.next;
        while (n.item != null) {
            System.out.print(n.item);
            System.out.print(" ");
            n = n.next;
        }
        System.out.println("");
    }

    public Type removeFirst() {
        try {
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return sentinel.next.item;
        }
        catch(Exception e) {
            return null;
        }
    }

    public Type removeLast() {
        try {
            sentinel.prev.prev.next = sentinel;
            size = size - 1;
            return sentinel.prev.item;
        }
        catch(Exception e) {
            return null;
        }
    }

    public Type get(int index) {
        StuffNode n = sentinel;

        if (index > size - 1) {
            System.out.println("Index out of range!");
            return null;
        }

        while (index >= 0) {
            n = n.next;
            index = index - 1;
        }
        return n.item;
    }
    /*
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(sentinel, null, sentinel);
        int i = 0;
        while (i < other.size()) {
            addLast(other.get(i));
            i += 1;
        }
    }

    public Type getRecursive(int index) {
        StuffNode m = sentinel;
        return tailrecur(sentinel, index);
    }

    public Type tailrecur(StuffNode n, int index) {
        if (index > size - 1) {
            return null;
        } else if (index == 0) {
            return n.item;
        } else {
            return getRecursive(n.next, index - 1);
        }
    }
    **/
    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque<>();
        //System.out.println(L.sentinel.prev);
        L.addFirst("abc");
        L.addLast("cde");
        //System.out.println(L.size());
        System.out.println(L.getFirst());
        //System.out.println(L.removeFirst());
        //System.out.println(L.removeLast());
        //L.printDeque();
        //System.out.println(L.get(1));
        //System.out.println(L.getRecursive(1));
        //LinkedListDeque<String> L1 = new LinkedListDeque(L);
        //System.out.println(L1.size());
    }
}
