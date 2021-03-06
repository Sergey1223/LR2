package barBossHouse;

/**
 * Класс {@code Order} представляет реализацию свойств заказа, основных манипуляций с ним.
 */
public class Order {
    /** Массив блюд */
    private Dish[] dishes;

    /** Количество блюд */
    private int dishesAmount;

    /** Начальная емкость массива */
    private static final int DEFAULT_CAPACITY = 16;

    /** Степень увеличения емкости массива при ее недостатке */
    private static final int DEGREE_OF_INCREASE = 2;

    /**
     * Инициализирует новый {@code sOrder} объект
     */
    public Order() {
        dishes = new Dish[DEFAULT_CAPACITY];
    }

    /**
     * Инициализирует новый {@code Order} объект с заданной омкостью заказа
     * @param capacity емкость
     */
    public Order(int capacity) {
        dishes = new Dish[capacity];
    }

    /**
     * Инициализирует новый {@code Order} объект
     * @param dishes массив блюд
     */
    public Order(Dish[] dishes) {                   //Ты считаешь количество null? Ничего не перепутал? (FIXED)
        //А еще можешь нативный System.arraycopy юзать и в этом конструкторе тоже (FIXED)
        this.dishes = new Dish[dishes.length/DEFAULT_CAPACITY * DEFAULT_CAPACITY + DEFAULT_CAPACITY];
        System.arraycopy(dishes, 0, this.dishes, 0, dishes.length);
        dishesAmount = dishes.length;
    }

    /**
     * Добаляет блюдо в массив.
     * @param dish блюдо
     * @return {@code true}, если блюдо добавдено, иначе - {@code false}
     */
    public boolean addDish(Dish dish) {
        // Опять проходишься по массиву,тут не нужно этого делать. (FIXED)
        // Сверяешь, меньше ли фактический размер меньше длины массива и добавляешь. (FIXED)
        // Если что расширяешь, что ниже и делаешь, только невпопад (FIXED)
        if(dishesAmount != dishes.length){
            dishes[++dishesAmount - 1] = dish;
            return true;
        }
        Dish[] newDishes = new Dish[dishes.length * DEGREE_OF_INCREASE];
        System.arraycopy(dishes, 0, newDishes, 0, dishes.length); //можешь до фактического размера копировать (я не понял,
        // dishes полный же, больше чем dishes.length указать нельзя)
        newDishes[dishes.length] = dish;
        dishes = newDishes;
        dishesAmount++;
        return true;
    }

    /**
     * Удаляет первое найденное блюдо с заданным название.
     * @param name название
     * @return {@code true}, если блюдо удалено, иначе - {@code false}
     */
    private boolean removeDish(String name){
        for (int i = 0; i < dishes.length; i++) {
            if(dishes[i].getName().equals(name)){                //ссылочные тмпы сраниваются через equals (FIXED)
                System.arraycopy(dishes, i + 1, dishes, i, dishes.length - i - 2); //умница
                dishesAmount--;
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет все блюда с заданным название.
     * @param name название
     * @return число удаленных блюд
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
     * @return общее число блюд.
     */
    public int getDishesAmount(){
        return dishesAmount;
    }

    /**
     * @return массив блюд.
     */
    public Dish[] getDishes(){
        Dish[] newDishes = new Dish[dishesAmount];
        System.arraycopy(dishes, 0, newDishes, 0, dishesAmount);
//        for (int i = 0; i < newDishes.length; i++) {
//            System.out.println(newDishes[i]);  //а-та-та. Метод не должен делать ничего кроме удаления, в т.ч. вывода в консоль (использовал)
//        }                                      // (использовал для проверки, а затем не удалили, да, косяк в общем)
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
        for (int i = 0; i < dishesAmount; i++) {
            if(dishes[i].getName().equals(name)){ //такая же херня со сравнением (FIXED)
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return массив названий заказанных блюд (без повторов).
     */
    public String[] getDishesNames(){
        String names = "";
        int i;
        for (i = 0; i < dishesAmount; i++) {
            if(!names.contains(dishes[i].getName())){
                names += dishes[i].getName() + " ";
            }
        }
        return names.split("\\ ");
    }

    /**
     * @return отсортированный массив блюд.
     */
    public Dish[] getSortedDishes(){
        Dish[] newDishes = getDishes();
        Sorter.quickSort(newDishes, 0, newDishes.length - 1);
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
