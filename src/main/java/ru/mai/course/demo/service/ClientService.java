package ru.mai.course.demo.service;

import ru.mai.course.demo.Client.Client;

import java.util.ArrayList;

public interface ClientService {

        /**
         * Создает нового клиента
         * @param client - клиент для создания
         */
        void create(Client client);

        /**
         * Возвращает список всех имеющихся клиентов
         * @return список клиентов
         */
        ArrayList<Client> readAll();

        /**
         * Возвращает клиента по его ID
         * @param id - ID клиента
         * @return - объект клиента с заданным ID
         */
        Client read(Integer id) throws Exception;

        /**
         * Обновляет клиента с заданным ID,
         * в соответствии с переданным клиентом
         * @param client - клиент в соответсвии с которым нужно обновить данные
         * @return - true если данные были обновлены, иначе false
         */
        boolean update(Client client,Integer id);

        /**
         * Удаляет клиента с заданным ID
         * @param id - id клиента, которого нужно удалить
         */
        void delete(Integer id);
}
