package com.example.TastyKing.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComboFoodId implements Serializable {
    private Long comboID;
    private Long foodID;

    // Default constructor
    public ComboFoodId() {}

    public ComboFoodId(Long comboID, Long foodID) {
        this.comboID = comboID;
        this.foodID = foodID;
    }

    // Getters, setters, equals(), and hashCode() methods

    public Long getComboID() {
        return comboID;
    }

    public void setComboID(Long comboID) {
        this.comboID = comboID;
    }

    public Long getFoodID() {
        return foodID;
    }

    public void setFoodID(Long foodID) {
        this.foodID = foodID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboFoodId that = (ComboFoodId) o;
        return Objects.equals(comboID, that.comboID) && Objects.equals(foodID, that.foodID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comboID, foodID);
    }
}
