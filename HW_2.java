public class HW_2 {
    public static void main(String[] args) {
        int n = 10;
        int[] array1 = new int[n];
        for (int i = 0; i < n; i++) {
            array1[i] = (int) (Math.random() * 100);
        }

        for (int i : array1)
            System.out.printf("%d, ", i);
        heapSort(array1);
        System.out.println();
        for (int i : array1)
            System.out.printf("%d, ", i);
    }

    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            heapify(array, array.length, i);

        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int size, int root) {
        int max = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size && array[left] > array[max])
            max = left;

        if (right < size && array[right] > array[max])
            max = right;

        if (max != root) {
            int temp = array[root];
            array[root] = array[max];
            array[max] = temp;

            heapify(array, size, max);
        }
    }
}
