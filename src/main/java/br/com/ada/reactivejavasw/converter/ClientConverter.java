package br.com.ada.reactivejavasw.converter;

import org.springframework.stereotype.Component;

import br.com.ada.reactivejavasw.dto.ClientDTO;
import br.com.ada.reactivejavasw.model.Client;

@Component
public class ClientConverter {

    public ClientDTO toClientDTO(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getAge(), client.getEmail());
    }


    public Client toClient(ClientDTO clienttDTO) {
        return new Client(clienttDTO.getName(), clienttDTO.getAge(), clienttDTO.getEmail());
    }
    
}
