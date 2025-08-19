import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    // Поля для параметров
    @Parameterized.Parameter
    public String bunName;

    @Parameterized.Parameter(1)
    public float bunPrice;

    @Parameterized.Parameter(2)
    public String ingredientOneName;

    @Parameterized.Parameter(3)
    public float ingredientOnePrice;

    @Parameterized.Parameter(4)
    public String ingredientTwoName;

    @Parameterized.Parameter(5)
    public float ingredientTwoPrice;

    @Parameterized.Parameter(6)
    public float expectedPrice;

    // Моки, которые будут использоваться в тесте
    private Bun bunMock;
    private Ingredient ingredientOneMock;
    private Ingredient ingredientTwoMock;

    @Parameterized.Parameters(name = "Булка={0}({1}), Инг1={2}({3}), Инг2={4}({5}) -> цена={6}")
    public static Object[][] getData() {
        return new Object[][]{
                { "Краторная булка", 1255f,
                        "Соус традиционный галактический", 15f,
                        "Филе Люминесцентного тетраодонтимформа", 988f,
                        3513f },

                { "Краторная булка", 1255f,
                        "Соус Spicy-X", 90f,
                        "Говяжий метеорит (отбивная)", 3000f,
                        5600f },

                { "Флюоресцентная булка", 988f,
                        "Соус традиционный галактический", 15f,
                        "Мясо бессмертных моллюсков Protostomia", 1337f,
                        3328f }
        };
    }

    // Перед каждым тестом создаём моки
    @Test
    public void getPriceTest() {
        // Создаём моки
        bunMock = Mockito.mock(Bun.class);
        ingredientOneMock = Mockito.mock(Ingredient.class);
        ingredientTwoMock = Mockito.mock(Ingredient.class);

        // Настраиваем моки
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientOneMock.getPrice()).thenReturn(ingredientOnePrice);
        Mockito.when(ingredientTwoMock.getPrice()).thenReturn(ingredientTwoPrice);

        // Можно также замокать getName(), если нужно для отладки
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(ingredientOneMock.getName()).thenReturn(ingredientOneName);
        Mockito.when(ingredientTwoMock.getName()).thenReturn(ingredientTwoName);

        // Собираем бургер
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientOneMock);
        burger.addIngredient(ingredientTwoMock);

        // Проверяем цену
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }
}
