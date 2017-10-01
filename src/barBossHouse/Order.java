package barBossHouse;

/**
 * Класс {@code src.barBossHouse.Order} представляет реализацию свойств заказа, основных манипуляций с ним.
 */
public class Order {
    /** Массив блюд */
    private Dish[] dishes;

    /** Количество блюд */
    private int dishesAmount;

    /** Начальная емкость массива */
    private static final int defaultCapacity = 16;

    /** Степень увеличения емкости массива при ее недостатке */
    private static final int degreeOfIncrease = 2;

    /**
     * Инициализирует новый {@code src.barBossHouse.Order} объект
     */
    public Order() {
        dishes = new Dish[defaultCapacity];
    }

    /**
     * Инициализирует новый {@code src.barBossHouse.Order} объект
     * @param capacity
     *          Емкость
     */
    public Order(int capacity) {
        dishes = new Dish[capacity];
    }

    /**
     * Инициализирует новый {@code src.barBossHouse.Order} объект
     * @param dishes
     *          Массив блюд
     */
    public Order(Dish[] dishes) {
        this.dishes = dishes;
        int i;
        for (i = 0; i < dishes.length; i++) {
            if(dishes[i] == null) {
                dishesAmount = i + 1;
                break;
            }
        }
        if (i == dishes.length){
            dishesAmount = dishes.length;
        }
    }

    /**
     * Добаляет блюдо в массив.
     * @param dish
     *          Блюдо
     * @return {@code true}, если блюдо добавдено, иначе - {@code false}
     */
    public boolean addDish(Dish dish) {
        for (int i = 0; i < dishes.length; i++) {
            if(dishes[i] == null) {
                dishes[i] = dish;
                dishesAmount++;
                return true;
            }
        }
        Dish[] newDishes = new Dish[dishes.length * degreeOfIncrease];
        System.arraycopy(dishes, 0, newDishes, 0, dishes.length);
        newDishes[dishes.length] = dish;
        dishes = newDishes;
        dishesAmount++;
        return true;
    }

    /**
     * Удаляет первое найденное блюдо с данным именем.
     * @param name
     * @return {@code true}, если блюдо удалено, иначе - {@code false}
     */
    private boolean removeDish(String name){
        for (int i = 0; i < dishes.length; i++) {
            if(dishes[i].getName() == name){
                System.arraycopy(dishes, i + 1, dishes, i, dishes.length - i);
                dishesAmount--;
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет все блюда с данным именем.
     * @param name
     * @return Число удаленных блюд
     */
    private int removeDishes(String name){
        int counter = 0;
        while (removeDish(name)){
            counter++;
        }
        dishesAmount -= counter;
        return counter;
    }

    /**
     * @return общее чисо блюд.
     */
    private int getDishesAmount(){
        return dishesAmount;
    }

    /**
     * @return массив блюд.
     */
    public Dish[] getDishes(){
        Dish[] newDishes = new Dish[getDishesAmount()];
        System.arraycopy(dishes, 0, newDishes, 0, dishesAmount - 1);
        return newDishes;
    }

    /**
     * @return общую стоимость заказа.
     */
    public double getTotalPrice(){
        double result = 0;
        for (int i = 0; i < dishesAmount; i++) {
            result += dishes[i].getPrice();
        }
        return result;
    }

    /**
     * @param name название
     * @return число заказанных порций данного блюда.
     */
    public int getDishesAmount(String name){
        int counter = 0;
        for (int i = 0; i < getDishesAmount(); i++) {
            if(dishes[i].getName() == name){
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return массив названий заказанных блюд.
     */
    private String[] getNamesOfDishes(){
        String[] names = new String[dishesAmount];
        for (int i = 0; i < getDishesAmount(); i++) {
            for (int j = 0; j < i; j++) {
                if(names[j] == dishes[i].getName()){
                    break;
                }
                if (j == i - 1){
                    names[i] = dishes[i].getName();
                }
            }
        }
        return names;
    }

    /**
     * @return отсортированный массив блюд.
     */
    private Dish[] getSortedDishes(){
        Dish[] newDishes = getDishes();
        Sorter.bubbleSort(newDishes);
        return newDishes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The amount of dishes: " + dishesAmount + "\r\n");
        for (int i = 0; i < dishesAmount; i++) {
            stringBuilder.append("Dish " + dishes[i].getName() + ":\r\n");
            stringBuilder.append("\t-" + dishes[i].getSpecification() + "\r\n");
            stringBuilder.append("\t-" + dishes[i].getPrice() + "\r\n");
        }
        return stringBuilder.toString();
    }
}