package br.com.ada.reactivejavasw.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.reactivejavasw.converter.ClientConverter;
import br.com.ada.reactivejavasw.dto.ClientDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.model.Client;
import br.com.ada.reactivejavasw.repository.ClientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ClientRepository clientRepository;


    public Mono<ResponseDTO> create(ClientDTO clientDTO) {

        
        Client client = this.clientConverter.toClient(clientDTO);
        
        Mono<Client> clientMono = this.clientRepository.save(client);
        
        return clientMono
                .map((clientDocument) -> new ResponseDTO("Produto cadastrado com sucesso!",
                        this.clientConverter.toClientDTO(clientDocument),
                        LocalDateTime.now()))
                .onErrorReturn(new ResponseDTO("Erro ao cadastrar produto",
                        new ClientDTO(),
                        LocalDateTime.now()));


    }

    public Flux<ResponseDTO<ClientDTO>> getAll() {
        Flux<Client> productFlux = this.clientRepository.findAll();
        return productFlux
                .map(product -> new ResponseDTO("Listagem de produtos retornada com sucesso!",
                                              this.clientConverter.toClientDTO(product),
                                              LocalDateTime.now()
        ));
    }

    public Mono<ResponseDTO<ClientDTO>> findByCode(String id) {
        Mono<Client> productMono = this.clientRepository.findById(id);
        return productMono
                .map(product -> new ResponseDTO("Busca por code retornada com sucesso!",
                                               this.clientConverter.toClientDTO(product),
                                               LocalDateTime.now()
                        ));

    }

    public Mono<ResponseDTO> update(ClientDTO clientDTO) {

        Mono<Client> productMono = this.clientRepository.findById(clientDTO.getId());

        return productMono.flatMap((existingProduct) -> {
            existingProduct.setId(clientDTO.getId());
            existingProduct.setName(clientDTO.getName());
            existingProduct.setAge(clientDTO.getAge());
            existingProduct.setEmail(clientDTO.getEmail());
            return this.clientRepository.save(existingProduct);
        }).map(client -> new ResponseDTO<>("Produto alterado com sucesso!",
                this.clientConverter.toClientDTO(client),
                LocalDateTime.now()));
    }

    public Mono<ResponseDTO> delete(String code) {
        return this.clientRepository
                        .deleteByCode(code).map((product) -> new ResponseDTO<>("Produto removido com sucesso!",
                                                                    null,
                                                                         LocalDateTime.now()));
    }

}
