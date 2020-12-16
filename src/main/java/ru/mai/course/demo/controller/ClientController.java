package ru.mai.course.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mai.course.demo.Client.Client;
import ru.mai.course.demo.service.ClientService;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mai.course.demo.service.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        if (client.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            clientService.create(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() {
        final List<Client> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) throws Exception {
        final Optional<Client> client = Optional.ofNullable(clientService.read(id));

        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Client client) {
        final boolean updated = clientService.update(client,id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        boolean deleted = true;
        try {
            clientService.delete(id);
        }catch (Exception e){
             deleted = false;
        }
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/clients/order_by/{field}/{order}")
    public ResponseEntity<?> readOrder(@PathVariable(name = "field") String field, @PathVariable(name = "order")  String order) {
        ArrayList<Client> clients = clientService.readAllOrderByField(field,order);

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}