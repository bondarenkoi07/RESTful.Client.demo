package ru.mai.course.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.mai.course.demo.Client.Client;
import ru.mai.course.demo.service.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/clients")
    public String create(Model model, @ModelAttribute("clientForm") Client client) {
        if ((client.getName() == null) || (client.getFlight() == null)
                || (client.getSeat()==null) || (client.getNum()==null)
                || (client.getSeries()==null) || (client.getPhoneNumber()==null)
                || (client.getSurname()==null)
                || !(client.getFlight()>0)
                || !(client.getSeat()>0)) {
            model.addAttribute("status","failure");
            model.addAttribute("log","column name caused error!");
        }else{
            model.addAttribute("status","succeed");
            clientService.create(client);
        }
        return "status";
    }

    @GetMapping(value = "/")
    public String read(ModelMap model) {
        final List<Client> clients = clientService.readAll();
        model.addAttribute("clients",clients);
        model.addAttribute("formClient", new Client());
        return "clients";
    }

    @GetMapping(value = "/client/{id}")
    public String read(@PathVariable(name = "id") int id, Model model) {
        try {
            final Optional<Client> client = Optional.ofNullable(clientService.read(id));
            model.addAttribute("client",client.orElse(new Client()));
        }catch(Exception e){
            model.addAttribute("client",new Client());
        }
        return "client";
    }

    @PostMapping(value = "/update/{id}")
    public String update(Model model, @ModelAttribute("client") Client client,@PathVariable int id) {
        if (!( (client.getName() == null)
                || (client.getFlight() == null) || (client.getSeat()==null)
                || (client.getNum()==null)  || (client.getSeries()==null)
                || (client.getPhoneNumber()==null)
                || (client.getSurname()==null)
                || !(client.getFlight()>0)
                || !(client.getSeat()>0)
                || !(client.getId()>0
                || (client.getId() != id))
        )){
            final boolean updated = clientService.update(client,id);
            if (updated) {
                model.addAttribute("status","succeed");
            }else{
                model.addAttribute("status","failure");
                model.addAttribute("log","constraint error");
            }


        }else{
            model.addAttribute("status","failure");
            model.addAttribute("log","wrong data!");
        }
        return "status";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") int id, Model model) {
        boolean deleted = true;
        try {
            clientService.delete(id);
        }catch (Exception e){
             deleted = false;
        }
        if (deleted){
            model.addAttribute("status","succeed");
        }else{
            model.addAttribute("status","failure");
            model.addAttribute("log","column name caused error!");
        }
        return "status";
    }

    @GetMapping(value = "/sort")
    public String readOrder(Model model,@RequestParam(name = "field") String field, @RequestParam(name = "order")  String order) {
        ArrayList<Client> clients = clientService.readAllOrderByField(field,order);
        model.addAttribute("clients",clients);
        model.addAttribute("formClient", new Client());
        return "clients";
    }
}