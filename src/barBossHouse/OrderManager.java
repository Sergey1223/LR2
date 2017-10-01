package barBossHouse;

/**
 * Класс {@code Order} представляет реализацию поведения менеджера заказов.
 */
public class OrderManager {
    /** Массив заказов */
    private Order[] orders;

    /** Число занятых столов */
    private int numOdFilledTables;

    /** Список названий доступных блюд */
    public static String[] namesOfDishes = { "Уха", "Борщ", "Мороженное", "Кофе", "Стейк", "Салат", "Соленья"};
    public static String[] specificationsOfDishes = { "Горячее", "Горячее", "Холодное", "Горячее", "Жаренное", "Теплое", "Слабосоленое"};

    /**
     * Инициализирует новый {@code Order} объект.
     * @param numOfTables
     *          число столов
     */
    public OrderManager(int numOfTables){
        orders = new Order[numOfTables];
        numOdFilledTables = 0;
    }

    /**
     * Добавляет новый заказ в массив под указанным номером
     * @param number
     *          номер стола
     * @param order
     *          заказ
     */
    public void addOrder(int number, Order order){
        try{
            orders[number - 1] = order;
            numOdFilledTables++;
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    /**
     * Возвращает заказ по номеру.
     * @param number номер стола
     * @return заказ
     */
    public Order getOrder(int number){
        try{
            return orders[number - 1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Добавляет блюдо к заказу.
     * @param number номер стола
     * @param dish блюдо
     */
    private void addDish(int number, Dish dish){
        try{
            orders[number - 1].addDish(dish);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    /**
     * Освобождает стол
     * @param number номер стола
     */
    public void clearTable(int number){
        orders[number - 1] = null;
        numOdFilledTables--;
    }

    /**
     * Возвращает номер первого свободного стола.
     * @return номер
     */
    private int getNumberOfEmptyTable(){
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null){
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Возвращает все номера свободных столов.
     * @return массив номеров
     */
    private int[] getNumbersOfEmptyTables(){
        int[] arr = new int[orders.length - numOdFilledTables];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(orders[i] == null){
                arr[j] = (i + 1);
                j++;
            }
        }
        return arr;
    }

    /**
     * Возвращает все номера занятых столов.
     * @return
     */
    public int[] getNumbersOfFilledTables(){
        int[] arr = new int[numOdFilledTables];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(orders[i] != null){
                arr[j] = (i + 1);
                j++;
            }
        }
        return arr;
    }

    /**
     * @return массив заказов
     */
    private Order[] getOrders(){
        Order[] orders = new Order[numOdFilledTables];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(orders[i] != null){
                orders[j] = this.orders[i];
                j++;
            }
        }
        return orders;
    }

    /**
     * @return общую стоимость всех заказов
     */
    public double getTotalPrice(){
        float result = 0;
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] != null){
                result += orders[i].getTotalPrice();
            }
        }
        return result;
    }

    /**
     * Возвращает число порций блюда во всех имеющихся заказах
     * @param name название
     * @return число порций
     */
    public int getAmountOfDishes(String name){
        int count = 0;
        for (int i = 0; i < orders.length; i++){
            if(orders[i] != null){
                count += orders[i].getDishesAmount(name);
            }
        }
        return count;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The amount of tables: " + orders.length + "\r\n");
        for (int i = 0; i < orders.length; i++) {
            stringBuilder.append("Table №" + (i + 1) + ":\r\n");
            if(orders[i] == null){
                stringBuilder.append("EMTY\r\n");
            }
            else {
                stringBuilder.append(orders[i].toString());
            }
        }
        return stringBuilder.toString();
    }
}
