/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ldv9_lab3;

/**
 *
 * @author dmitriy
 */
/**
 * Класс, соответсвующий записи в таблице Phones
 *
 */
public class Phones {

    int id;            // Код записи
    String Brand;  // Бренд
    String Model;   // Модель
    int capacity;   // Объем памяти
    int price;  // Цена
    
    public Phones() {
        this.id = 0;
        this.Brand = "";
        this.Model = "";
        this.capacity = 0;
        this.price = 0;
    }
    
    public Phones(String Brand, String Model, int capacity, int price) {
        this.id = 0;
        this.Brand= Brand;
        this.Model = Model;
        this.capacity = capacity;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
        @Override
    public String toString() {
        return String.format("Бренд=%s, Модель=%s, Память=%d, Цена=%d", Brand, Model, capacity, price);
    }

    
}



