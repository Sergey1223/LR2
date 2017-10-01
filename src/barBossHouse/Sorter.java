package barBossHouse;

public class Sorter {
    public static void bubbleSort(Dish[] dishes){
        Dish temp;
        for (int i = 0; i < dishes.length - 1; i--) {
            for (int j = 0; j < i; j++) {
                if (dishes[j].getPrice() < dishes[j + 1].getPrice()){
                    temp = dishes[j];
                    dishes[j] = dishes[j + 1];
                    dishes[j + 1] = temp;
                }
            }
        }
    }

    public static void SelectionSort(Dish[] dishes){
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

}
