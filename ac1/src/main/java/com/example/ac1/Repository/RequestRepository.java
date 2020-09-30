package com.example.ac1.Repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.ac1.model.Request;

public class RequestRepository {
    
    private List<Request> listOfRequests;
    private int nextCode = 0;


    @PostConstruct
    public void saveRequest (Request request) {

        request.setCod(++nextCode);
        request.setDate(LocalDateTime.now());
        listOfRequests.add(request);
    }

    public List<Request> getAllRequests() {
        return listOfRequests;
    }

    public Request  getRequestByCode(int cod) {
        for(Request aux: listOfRequests) {
            if(aux.getCod() == cod){
                return aux;
            }
        }
        return null;
    } 

    public void removeRequest(Request aux) {
        listOfRequests.remove(aux);
    }
}
