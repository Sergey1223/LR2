import barBossHouse.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Main {
    private static final int NUM_OF_TABLES = 15;
    private static final int MAX_NUM_OF_DISHES = 30;

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager(NUM_OF_TABLES);
        Random random = new Random(1);
        int numberOfOrder, numberOfDish;
        // Initialize and filling
        for (int i = 0; i < 10; i++) {
            //ну ебано-покоребано, ну в цикле-то объявлять переменные, ну как так-то
            numberOfOrder = random.nextInt(NUM_OF_TABLES) + 1;
            while (true){
                if(orderManager.getOrder(numberOfOrder) == null){
                    break;
                }
                numberOfOrder = random.nextInt(NUM_OF_TABLES) + 1;
            }
            // Add order
            orderManager.addOrder(numberOfOrder, new Order());
            for (int j = 0; j < random.nextInt(MAX_NUM_OF_DISHES); j++) {
                //такая ж херня
                numberOfDish = random.nextInt(OrderManager.dishesNames.length) ;
                // Add dish
//                System.out.println(numberOfOrder);
//                System.out.println(numberOfDish);
                orderManager.getOrder(numberOfOrder).addDish(new Dish(
                        OrderManager.dishesNames[numberOfDish],
                        OrderManager.dishesSpecifications[numberOfDish],
                        random.nextInt(900) + new BigDecimal(random.nextDouble()).setScale(2, RoundingMode.UP).doubleValue())
                );
            }
        }
        System.out.println(orderManager.toString());
        int temp = random.nextInt(NUM_OF_TABLES) + 1;
        orderManager.clearTable(orderManager.getFilledTablesNumbers()[0]);
        System.out.println("Стол " + temp + " освобожден.");
        System.out.println("Общая стоимость всех заказов: " + orderManager.getTotalPrice());
        Order order = orderManager.getOrder(orderManager.getFilledTablesNumbers()[0]);
        System.out.println("Заказ до сортировки:\n" + order.toString());
        Order order1 = new Order(order.getSortedDishes());
        System.out.println("Заказ после сортировки:\n" + order1.toString());
        System.out.println("Первый свободный стол: " + orderManager.getEmptyTableNumber());
        System.out.println("Свободные столы: ");
        int[] arr = orderManager.getEmptyTablesNumbers();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("Занятые столы: ");
        int[] arr1 = orderManager.getFilledTablesNumbers();
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
        System.out.println("Число порций ухи во всех имеющихся заказах: " + orderManager.getDishesAmount("Уха"));
        orderManager.addDish(15, new Dish("Уха", "Special", 1500));
        System.out.println("Добавлено блюдо к последнему заказу:\n" + orderManager.toString());
        String[] arr2 = orderManager.getOrder(15).getDishesNames();
        System.out.println("Заказанные блюда 15 стола:");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);

        }
    }
}
