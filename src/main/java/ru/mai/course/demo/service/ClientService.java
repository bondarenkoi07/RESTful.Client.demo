package ru.mai.course.demo.service;

import ru.mai.course.demo.Client.Client;

import java.util.ArrayList;

public interface ClientService {


        void create(Client client);

        ArrayList<Client> readAll();

        Client read(Integer id) throws Exception;

        boolean update(Client client,Integer id);

        void delete(Integer id);
}
