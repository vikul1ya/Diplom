import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    private Burger burger;

    @Mock
    private Ingredient ingredientOne;

    @Mock
    private Ingredient ingredientTwo;

    @Mock
    private Bun bun;

    @Before
    public void setUp() {
        // Настраиваем поведение моков до того, как добавляем их в Burger
        Mockito.when(bun.getPrice()).thenReturn(1255f);
        Mockito.when(bun.getName()).thenReturn("Краторная булка");

        Mockito.when(ingredientOne.getPrice()).thenReturn(15f);
        Mockito.when(ingredientOne.getName()).thenReturn("Соус традиционный галактический");
        Mockito.when(ingredientOne.getType()).thenReturn(SAUCE);

        Mockito.when(ingredientTwo.getPrice()).thenReturn(988f);
        Mockito.when(ingredientTwo.getName()).thenReturn("Филе Люминесцентного тетраодонтимформа");
        Mockito.when(ingredientTwo.getType()).thenReturn(FILLING);

        // Теперь собираем бургер, используя настроенные моки
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
    }

    @Test
    public void setBunsTest() {
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        assertEquals(ingredientOne, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        burger.moveIngredient(0, 1);
        assertEquals(ingredientOne, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        // Ожидаемая цена: булка * 2 + цена ингредиентов
        float expectedPrice = 1255f * 2 + 15f + 988f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptTest() {
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "Краторная булка",
                ingredientOne.getType().toString().toLowerCase(), ingredientOne.getName(),
                ingredientTwo.getType().toString().toLowerCase(), ingredientTwo.getName(),
                "Краторная булка",
                burger.getPrice()
        );

        String actual = burger.getReceipt();
        assertEquals(expectedReceipt, actual);
    }


    // Дополнительные проверки граничных случаев
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexThrows() {
        burger.removeIngredient(10); // недопустимый индекс
    }

}
