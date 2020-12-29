package ru.mai.course.demo.repo;

import org.hibernate.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mai.course.demo.Client.Client;
import ru.mai.course.demo.Config.HibernateSessionFactoryUtil;

import java.util.ArrayList;

@Repository
public interface ClientDAO extends JpaRepository<Client, Integer> {
    default ArrayList<Client> readAllOrderByField(String field, String order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            String hql = "from Client  order by " + field + " " +order;
            ArrayList<Client> list = (ArrayList<Client>) session.createQuery(hql).list();
            session.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}