package org.example.hw3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Fedor", 25, 4.8);

        System.out.println(student.toString());
        try (FileOutputStream fileOutputStream = new FileOutputStream("studObj")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(student);
        }

        ObjectInputStream objectInputStream;
        try (FileInputStream fileInputStream = new FileInputStream("studObj")) {
            objectInputStream = new ObjectInputStream(fileInputStream);
            Student studentObj = (Student)objectInputStream.readObject();
            System.out.println(studentObj.toString());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStudent = objectMapper.writeValueAsString(student);
        System.out.println(jsonStudent);

        Student studentJson = objectMapper.readValue(jsonStudent, Student.class);
        System.out.println(studentJson.toString());

        XmlMapper xmlMapper = new XmlMapper();
        String xmlStudent = xmlMapper.writeValueAsString(student);
        System.out.println(xmlStudent);

        Student studentXml = xmlMapper.readValue(xmlStudent, Student.class);
        System.out.println(studentXml.toString());

    }
}
