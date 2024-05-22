package com.project;

import java.time.LocalDate;

public class Car {
    private String model;
    private String color;
    private LocalDate yearOfProduction;

    public Car(String model, String color, LocalDate yearOfProduction) {
        this.model = model;
        this.color = color;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public final boolean equals(Object o) {
        return true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
