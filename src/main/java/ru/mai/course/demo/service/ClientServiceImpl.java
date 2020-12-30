package ru.mai.course.demo.service;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.course.demo.Client.Client;
import ru.mai.course.demo.Config.HibernateSessionFactoryUtil;
import ru.mai.course.demo.repo.ClientDAO;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO ;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    @Transactional
    public void create(Client client) {
        clientDAO.save(client);
    }

    @Override
    public ArrayList<Client> readAll() {

        return (ArrayList<Client>) clientDAO.findAll();
    }

    @Override
    public Client read(Integer id) throws Exception {
        return clientDAO.findById(id).orElseThrow(()->new Exception("Client not found"));
    }

    @Transactional
    @Override
    public boolean update(Client client,Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        System.out.println("current id, seat is "+id+", " + client.getSeat());
        String hql = "from Client  where seat = "  + client.getSeat() + " and not id = "+client.getId()+"";
        List<Client> clients = (ArrayList<Client>)session.createQuery(hql).list();
        hql = "from Client  where id = "+client.getId()+"";
        List<Client> clientsId = (ArrayList<Client>)session.createQuery(hql).list();
        boolean valid = clients.isEmpty() && !clientsId.isEmpty();
        System.out.println("id  different, validation = "+valid);

        if(valid){
            Client ClientToUpdate = clientDAO.getOne(id);
            ClientToUpdate.setName(client.getName());
            ClientToUpdate.setSurname(client.getSurname());
            ClientToUpdate.setFlight(client.getFlight());
            ClientToUpdate.setNum(client.getNum());
            ClientToUpdate.setPhoneNumber(client.getPhoneNumber());
            ClientToUpdate.setSeat(client.getSeat());
            ClientToUpdate.setSeries(client.getSeries());

            clientDAO.save(ClientToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
         clientDAO.deleteById(id);
    }

    public ArrayList<Client> readAllOrderByField(String field, String order) {
        return clientDAO.readAllOrderByField(field, order);
    }
}