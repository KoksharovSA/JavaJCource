package org.example.hw2;

import org.example.hw2.Animal;

public class Dog extends Animal {
    private String breedOfDog;

    public Dog(String name, int age, String breedOfDog) {
        super(name, age);;
        this.breedOfDog = breedOfDog;
    }
    void BarkingAtTheDoor(){
        System.out.println(this.name + " barking at the door.");
    }
    void makeSound(){
        System.out.println("Woof-woof");
    }
}
