package org.example.hw2.task3.models;


import org.example.hw2.task3.Column;

import java.util.UUID;

@org.example.hw2.task3.Entity
public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}
