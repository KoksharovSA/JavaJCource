/*
* Задача 1:
Создайте абстрактный класс "Animal" с полями "name" и "age".
Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с
уникальными полями и методами.
Создайте массив объектов типа "Animal" и с использованием Reflection API
выполните следующие действия:
Выведите на экран информацию о каждом объекте.
Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.

Дополнительная задача:

Доработайте метод генерации запроса на удаление объекта из таблицы
БД (DELETE FROM <Table> WHERE ID = '<id>')
В классе QueryBuilder который мы разработали на семинаре.
* */
package org.example.hw2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Class<?>> animals = new ArrayList<>();
        animals.add(Class.forName("org.example.hw2.Cat"));
        animals.add(Class.forName("org.example.hw2.Dog"));
        for (Class<?> item: animals) {
            Constructor[] constructors = item.getConstructors();
            Object object = constructors[0].newInstance("Жора", 12, "Дворовый");
            Method method = item.getDeclaredMethod("makeSound");
            method.invoke(object);
        }
    }
}
