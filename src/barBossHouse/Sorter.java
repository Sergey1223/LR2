package barBossHouse;

public class Sorter {
    public static void bubbleSort(Dish[] dishes){
        Dish temp;
        for (int i = dishes.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (dishes[j].getPrice() < dishes[j + 1].getPrice()){
                    temp = dishes[j];
                    dishes[j] = dishes[j + 1];
                    dishes[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(Dish[] dishes){
        double min;
        Dish temp;
        int minIndex;
        for(int i = 0; i < dishes.length; i++){
            min = dishes[i].getPrice();
            minIndex = i;
            for (int j = i + 1; j < dishes.length; j++) {
                if(dishes[j].getPrice() < min){
                    min = dishes[j].getPrice();
                    minIndex = j;
                }
            }
            if(i != minIndex){
                temp = dishes[i];
                dishes[i] = dishes[minIndex];
                dishes[minIndex] = temp;
            }
        }
    }

    public static void quickSort(Dish[] dishes, int left, int right){
        int i = left, j = right;
        Dish tmp;
        double pivot = dishes[(left + right) / 2].getPrice();

        while (i <= j) {
            while (dishes[i].getPrice() < pivot)
                i++;
            while (dishes[j].getPrice() > pivot)
                j--;
            if (i <= j) {
                tmp = dishes[i];
                dishes[i] = dishes[j];
                dishes[j] = tmp;
                i++;
                j--;
            }
        }
        if (left < i - 1)
            quickSort(dishes, left, i - 1);
        if (i < right)
            quickSort(dishes, i, right);
    }

}
