package barBossHouse;

/**
 * Класс {@code Order} представляет реализацию поведения и свойств менеджера заказов.
 */
public class OrderManager {
    /** Массив заказов */
    private Order[] orders;

    /*
    Маленький коммент:
    Поскольку нельзя свдигать заказы из-за привязки индекса к номеру столика, каждый раз приходится
    проходить по массиву, сравнивая на null.
    Отсыл к задаче про подсчет нечетных чисел от 1 до 10: можно реализовать дополнительный массив
    для хранения индексов занятых столиков. Это позволит избежать наихудшией линейной сложности в очень многих методах.
    */

    /*
    А так в целом мне понравилось, с учетом мелких недочетов вполне добротно, только ты делаешь ошибки,
    которые странно делать для человека, который год назад уже прошел семестр джавы :)
    */

    /** Число занятых столов */
    //имя так себе, count здесь подойдет больше, и я уже говорил,что лучше избегать Of в именах, сложнее читается (FIXED)
    private int filledTablesCount;

    /** Список названий доступных блюд */
    public static String[] dishesNames = { "Уха", "Борщ", "Мороженное", "Кофе", "Стейк", "Салат", "Соленья"};
    //стейк топ))
    /** Список доступных описаний блюд */
    public static String[] dishesSpecifications = { "Горячее", "Горячее", "Холодное", "Горячее", "Жаренное", "Теплое", "Слабосоленое"};

    /**
     * Инициализирует новый {@code Order} объект c заданным числом столов.
     * @param numOfTables
     *          число столов
     */
    public OrderManager(int numOfTables){
        orders = new Order[numOfTables];
        filledTablesCount = 0;
    }
// Здесь и далее, то, что юзаешь исключения - молодец, но потом придется их перепискать, потому что потом будут задания на исключения
    /**
     * Добавляет новый заказ в массив под указанным номером
     * @param number номер стола
     * @param order аказ
     */
    public void addOrder(int number, Order order){
        try{
            orders[number - 1] = order;
            filledTablesCount++;
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Добавляет блюдо к заказу.
     * @param number номер стола
     * @param dish блюдо
     */
    public void addDish(int number, Dish dish){
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
        filledTablesCount--;
    }

    /**
     * Возвращает номер первого свободного стола.
     * @return номер
     */
    public int getEmptyTableNumber(){
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null){
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * @param type тип столов ({@code true} - занятые, {@code false} - свободные)
     * @return массив номеров
     */
    private int[] getTablesNumbers(boolean type){
        int[] arr = (type) ? new int[filledTablesCount] : new int[orders.length - filledTablesCount];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(type ^ (orders[i] == null)){
                arr[j] = (i + 1);
                j++;
            }
        }
        return arr;
    }
    /*позже попрошу передлать следующие два метода под функциональный интерфейс, чтобы избежать дублирования кода*/ //(IMPLEMENTED)
    /**
     * Возвращает все номера свободных столов.
     * @return массив номеров
     */
    public int[] getEmptyTablesNumbers(){
        /*int[] arr = new int[orders.length - filledTablesCount];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(orders[i] == null){
                arr[j] = (i + 1);
                j++;
            }
        }*/
        return getTablesNumbers(false);
    }

    /**
     * Возвращает все номера занятых столов.
     * @return
     */
    public int[] getFilledTablesNumbers(){
        /*int[] arr = new int[filledTablesCount];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if(orders[i] != null){
                arr[j] = (i + 1);
                j++;
            }
        }*/
        return getTablesNumbers(true);
    }

    /**
     * @return массив заказов
     */
    public Order[] getOrders(){
        Order[] orders = new Order[filledTablesCount];
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
    public int getDishesAmount(String name){
        int count = 0;
        for (int i = 0; i < orders.length; i++){
            if(orders[i] != null){
                count += orders[i].getDishesAmount(name);
            }
        }
        return count;
    }
    // плюс за наличие toString
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The amount of tables: " + orders.length + "\r\n");
        for (int i = 0; i < orders.length; i++) {
            stringBuilder.append("Table №" + (i + 1) + ":\r\n");
            if(orders[i] == null){
                stringBuilder.append("EMPTY\r\n");
            }
            else {
                stringBuilder.append(orders[i].toString());
            }
        }
        return stringBuilder.toString();
    }
}
