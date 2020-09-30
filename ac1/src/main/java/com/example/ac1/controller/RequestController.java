package com.example.ac1.controller;

import java.net.URI;
import java.util.List;

import com.example.ac1.Repository.RequestRepository;
import com.example.ac1.model.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/request")

public class RequestController {
    
    @Autowired
    private RequestRepository requestRepository;

    @PostMapping()
    public ResponseEntity<Void> save (@RequestBody Request request) {
        request = requestRepository.saveRequest(request);
        URI uri = URI.create("http://localhost:8080/request/" + request.getCod());
        return ResponseEntity.created(uri).build();
    }
    
    @GetMapping()
    public List<Request> getAllRequests() {
        return requestRepository.getAllRequests();
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Request> getRequestsByCode(@PathVariable int codigo) {

        Request request = requestRepository.getRequestByCode(codigo);

        if( request != null){
            return ResponseEntity.ok(request);   
        }
        else {
            return ResponseEntity.notFound().build();
        }            
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){   
        Request request = requestRepository.getRequestByCode(codigo);
        
        if(request != null){
            requestRepository.removeRequest(request);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}