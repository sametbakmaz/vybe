package com.example.auth.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "horoscopes")
public class HoroscopeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;

    public HoroscopeEntity() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoroscopeEntity horoscopeEntity = (HoroscopeEntity) o;
        return Objects.equals(id, horoscopeEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
} 