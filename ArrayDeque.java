public class ArrayDeque<Item> {
    private int size;
    private Item[] items;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(Item x) {
        items = (Item[]) new Object[8];
        items[0] = x;
        size = 1;
    }

    public ArrayDeque(ArrayDeque other) {
        int p = 0;
        items = (Item[]) new Object[other.size()];
        while (p <= other.size()) {
            items[p] = other.items[p];
        }
    }

    public void addFirst(Item x) {
        Item[] alist = (Item[]) new Object[(size + 1) * 2];
        alist[0] = x;
        System.arraycopy(items, 0, alist, 1, size);
        items = alist;
    }

    public void addLast(Item x) {
        if (size == items.length) {
            int[] alist = new int[size * 2];
            System.arraycopy(items, 0, alist, 0, size);
            items = alist;
        }
        items[size] = x;
        size += 1;
    }

    public Item removeFirst() {
        Item first = items[0];
        Item[] alist = (Item[]) new Object[size - 1];
        System.arraycopy(items, 1, alist, 0, size - 1);
        items = alist;
        return first;
    }

    public Item removeLast() {
        Item last = items[size - 1];
        Item[] alist = (Item[]) new Object[size - 1];
        System.arraycopy(items, 0, alist, 0, size - 1);
        items = alist;
        return last;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Item get(int index) {
        return items[index];
    }
}