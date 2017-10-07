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
        // Initialize and filling
        for (int i = 0; i < 10; i++) {
            int numberOfOrder = random.nextInt(NUM_OF_TABLES) + 1;
            while (true){
                if(orderManager.getOrder(numberOfOrder) == null){
                    break;
                }
                numberOfOrder = random.nextInt(NUM_OF_TABLES) + 1;
            }
            // Add order
            orderManager.addOrder(numberOfOrder, new Order());
            for (int j = 0; j < random.nextInt(MAX_NUM_OF_DISHES); j++) {
                int numberOfDish = random.nextInt(OrderManager.namesOfDishes.length) ;
                // Add dish
//                System.out.println(numberOfOrder);
//                System.out.println(numberOfDish);
                orderManager.getOrder(numberOfOrder).addDish(new Dish(
                        OrderManager.namesOfDishes[numberOfDish],
                        OrderManager.specificationsOfDishes[numberOfDish],
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
        System.out.println("Заказ до сортировки:");
        order.toString();
        Order order1 = new Order(order.getSortedDishes());
        System.out.println("Заказ после сортировки:\n" + order1.toString());
    }
}
