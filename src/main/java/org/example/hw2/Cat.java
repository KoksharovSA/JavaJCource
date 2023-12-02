package org.example.hw2;

public class Cat extends Animal{
    private String breedOfCat;

    public Cat(String name, int age, String breedOfCat) {
        super(name, age);
        this.breedOfCat = breedOfCat;
    }

    void pukeFur(){
        System.out.println(this.name + " pure fur!!!");
    }
    void makeSound(){
        System.out.println("Meao-meao");
    }
}
