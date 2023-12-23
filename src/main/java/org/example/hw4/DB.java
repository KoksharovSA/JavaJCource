package org.example.hw4;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "serg";
    private static final String PASSWORD = "admin";

    public DB() {
    }

    public static void con(){
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Course course = new Course("Python", 55);
            session.beginTransaction();
            session.save(course);
            course = new Course("Java", 105);
            session.save(course);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        try (Session session = connector.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            courses.forEach(System.out::println);
            String hql = "FROM Course WHERE id = :id";
            Query<Course> query = session.createQuery(hql,Course.class);
            query.setParameter("id", 5);
            Course course = query.getSingleResult();
            System.out.println(course);
            course.setTitle("C#");
            course.setDuration(130);
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
