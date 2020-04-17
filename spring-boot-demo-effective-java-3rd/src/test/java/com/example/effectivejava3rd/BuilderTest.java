package com.example.effectivejava3rd;

import com.example.effectivejava3rd.beans.NutritionFacts;
import com.example.effectivejava3rd.beans.NyPizza;
import com.example.effectivejava3rd.beans.Pizza;
import org.junit.Test;

/**
 * @ClassName BuilderTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/17 18:31
 * @Version 1.0
 */
public class BuilderTest extends EffectiveJava3rdApplicationTests {

    @Test
    public void testBuilder() {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
        System.out.println("NutritionFacts: " + cocaCola);
    }

    @Test
    public void testBuilder2(){
        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();

    }

}
