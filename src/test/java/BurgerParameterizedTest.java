import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final Bun bun;
    private final Ingredient ingredientOne;
    private final Ingredient ingredientTwo;
    private final float expectedPrice;

    public BurgerParameterizedTest(Bun bun, Ingredient ingredientOne, Ingredient ingredientTwo, float expectedPrice) {
        this.bun = bun;
        this.ingredientOne = ingredientOne;
        this.ingredientTwo = ingredientTwo;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Булка={0}, Инг1={1}, Инг2={2} -> цена={3}")
    public static Object[][] getPrice() {
        return new Object[][] {
                { new Bun("Краторная булка", 1255f),
                        new Ingredient(SAUCE, "Соус традиционный галактический", 15f),
                        new Ingredient(FILLING, "Филе Люминесцентного тетраодонтимформа", 988f),
                        3513f },

                { new Bun("Краторная булка", 1255f),
                        new Ingredient(SAUCE, "Соус Spicy-X", 90f),
                        new Ingredient(FILLING, "Говяжий метеорит (отбивная)", 3000f),
                        5600f },

                { new Bun("Флюоресцентная булка", 988f),
                        new Ingredient(SAUCE, "Соус традиционный галактический", 15f),
                        new Ingredient(FILLING, "Мясо бессмертных моллюсков Protostomia", 1337f),
                        3328f }
        };
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        // используем тот же порядок: сначала собираем бургер, затем проверяем цену
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }
}
