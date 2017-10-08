package barBossHouse;

/**
 * Класс {@code Dish} представляет реализацию свойст блюда.
 */

public class Dish {
    /** Название блюда. */
    private String name;
    /** Краткое описание блюда. */
    private String specification;
    /** Цена за порцию блюда. */
    private double price;
    /** Цена при инициализации */
    private static final double DEFAULT_PRICE = 0;

//наоборот, более узкий конструктор вызывает более широкий this(name, specification, DEFAULT_PRICE); (FIXED)
    /**
     * Иициализирует новый объект.
     * @param name название
     * @param specification краткое описание
     * @see Dish#Dish(String, String, double)
     */
    public Dish(String name, String specification) {
        this(name, specification, DEFAULT_PRICE);
    }

    /**
     * Инициализирует новый {@code Dish} объект.
     * @param name название
     * @param specification краткое писание
     * @param price цена
     * @see Dish#Dish(String, String)
     */
    public Dish(String name, String specification, double price) {
        this.name = name;
        this.specification = specification;
        this.price = price;
    }

    /**
     * @return название блюда
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает новое название блюда.
     * @param name
     *          название
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
