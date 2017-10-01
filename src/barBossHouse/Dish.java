package barBossHouse;

/**
 * Класс {@code src.barBossHouse.Dish} представляет реализацию свойст блюда.
 */

public class Dish {
    /** Название блюда. */
    private String name;
    /** Краткое описание блюда. */
    private String specification;
    /** Цена за порцию блюда. */
    private double price;

    private static final double DEFAULT_PRICE = 0;


    /**
     * Инициализирует новый {@code src.barBossHouse.Dish} объект.
     * @param name
     *          Название
     * @param specification
     *          Краткое описание
     * @see Dish#Dish(String, String, double)
     */
    public Dish(String name, String specification) {
        this.name = name;
        this.specification = specification;
        price = DEFAULT_PRICE;
    }

    /**
     * Инициализирует новый {@code src.barBossHouse.Dish} объект.
     * @param name
     *          Название
     * @param specification
     *          Краткое писание
     * @param price
     *          Цена
     * @see Dish#Dish(String, String)
     */
    public Dish(String name, String specification, double price) {
        this(name, specification);
        this.price = price;
    }

    /**
     * Возвращает название блюда.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает новое название блюда.
     * @param name
     *          Название
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Возвращает краткое описание блюда */
    public String getSpecification() {
        return specification;
    }

    /**
     * Устанавливает новое краткое описание блюда
     * @param specification
     *          Краткое описание
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /** Возвращает цену порции блюда */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает новую цену одной порции блюда
     * @param price
     *          Цена
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
