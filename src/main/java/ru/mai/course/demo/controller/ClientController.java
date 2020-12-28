package ru.mai.course.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.mai.course.demo.Client.Client;
import ru.mai.course.demo.service.BoardService;
import ru.mai.course.demo.service.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private final ClientServiceImpl clientService;
    private final BoardService boardService;

    @Autowired
    public ClientController(ClientServiceImpl clientService, BoardService boardService) {
        this.clientService = clientService;
        this.boardService = boardService;
    }

    @PostMapping(value = "/clients")
    public String create(Model model, @ModelAttribute("clientForm") Client client) {
        if (client.getName() == null) {
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
    public String read(@PathVariable(name = "id") int id, Model model) throws Exception {
        final Optional<Client> client = Optional.ofNullable(clientService.read(id));
        model.addAttribute("client",client.orElse(new Client()));
        return "client";
    }

    @PostMapping(value = "/update/{id}")
    public String update(Model model, @ModelAttribute("client") Client client,@PathVariable int id) {
        System.out.println("hello, how low?"+client.toString());
        final boolean updated = clientService.update(client,id);
        if (updated) {
            model.addAttribute("status","succeed");

        }else{
            model.addAttribute("status","failure");
            model.addAttribute("log","column name caused error!");
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