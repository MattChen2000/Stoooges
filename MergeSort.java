public class MergeSort {
    public static int[] merge(int[] L1, int[] L2) {
        int[] newList = new int[L1.length + L2.length];   // Note the length of the new list
        int pt1 = 0;
        int pt2 = 0;                                      // Double pointers

        for (int i = 0; i < newList.length; i++) {        // This for loop with i is not designed to be naturally finished
            if (pt1 >= L1.length || pt2 >= L2.length) {   // when we reaches the end of either of input lists
                break;                                    // then jump out of this for loop
            }

            if (L1[pt1] > L2[pt2]) {                      // Put the smaller item in the list and move the
                newList[i] = L2[pt2];                     // corresponding pointer
                pt2 += 1;
            } else {
                newList[i] = L1[pt1];
                pt1 += 1;
            }
        }
        if (pt1 >= L1.length) {
            System.arraycopy(L2, pt2, newList, pt1+pt2, L2.length-pt2);
        } else if (pt2 >= L2.length) {
            System.arraycopy(L1, pt1, newList, pt1+pt2, L1.length-pt1);
        }
        return newList;
    }

    public static void main(String[] args) {
        int[] L1 = new int[] {1, 3, 7, 9, 11, 13, 15};
        int[] L2 = new int[] {2, 4, 6, 8};
        int[] L3 = merge(L1, L2);
        System.out.println("Here is the merged list");
        for (int i = 0; i < L3.length; i++) {
            System.out.print(L3[i] + " ");
        }
    }
}
