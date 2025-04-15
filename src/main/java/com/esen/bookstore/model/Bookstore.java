package com.esen.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private Double priceModifier;
    private Double moneyInCashRegister;

    public Bookstore() {
    }

    public Bookstore(Long id, String location, Double priceModifier, Double moneyInCashRegister, Map<Book, Integer> inventory) {
        this.id = id;
        this.location = location;
        this.priceModifier = priceModifier;
        this.moneyInCashRegister = moneyInCashRegister;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(Double priceModifier) {
        this.priceModifier = priceModifier;
    }

    public Double getMoneyInCashRegister() {
        return moneyInCashRegister;
    }

    public void setMoneyInCashRegister(Double moneyInCashRegister) {
        this.moneyInCashRegister = moneyInCashRegister;
    }

    public Map<Book, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Book, Integer> inventory) {
        this.inventory = inventory;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Book, Integer> inventory;
}
