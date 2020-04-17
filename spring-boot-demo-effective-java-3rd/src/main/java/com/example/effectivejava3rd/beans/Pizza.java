package com.example.effectivejava3rd.beans;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Pizza
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/17 18:42
 * @Version 1.0
 */
public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toplings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must overside this method to return "this"
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toplings = builder.toppings.clone();  // See Item 50
    }


}
