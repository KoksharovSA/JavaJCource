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
        addCourse(connector, new Course("Python", 55));
        addCourse(connector, new Course("Java", 155));
        readTableCourses(connector);
        updateCourse(connector, 2, new Course("C#", 165));
        removeAllCourses(connector);
    }

    private static void readTableCourses(Connector connector) {
        //Чтение
        try (Session session = connector.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            courses.forEach(System.out::println);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void updateCourse(Connector connector, int id, Course course) {
        //Изменение
        try (Session session = connector.getSession()) {
            String hql = "FROM Course WHERE id = :id";
            Query<Course> query = session.createQuery(hql,Course.class);
            query.setParameter("id", id);
            Course courseFromQuery = query.getSingleResult();
            courseFromQuery.setTitle(course.getTitle());
            courseFromQuery.setDuration(course.getDuration());
            session.beginTransaction();
            session.update(courseFromQuery);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void removeAllCourses(Connector connector) {
        //Удаление
        try (Session session = connector.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            courses.forEach(m->{
                session.beginTransaction();
                session.update(m);
                session.getTransaction().commit();
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void addCourse(Connector connector, Course course) {
        //Добавление
        try (Session session = connector.getSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
