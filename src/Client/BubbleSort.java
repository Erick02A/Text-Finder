package Client;

/**
 * Clase para el algoritmo de ordenamiento BubbleSort
 */
class BubbleSort
{
    static String[] bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            System.out.println(arr[i]);
            String[] dats = arr[i].split(",");
            if (Integer.parseInt(dats[i].split(",")[7]) > Integer.parseInt(arr[i+1].split(",")[7])) {
                // swap temp and arr[i]
                String temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        return arr;
    }
}
