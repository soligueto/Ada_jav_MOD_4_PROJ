package br.com.ada.reactivejavasw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.reactivejavasw.dto.ClientDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/products")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create a product",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
    public Mono<ResponseDTO> create(@RequestBody ClientDTO clientDTO) {
        return this.clientService.create(clientDTO);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find all products")
    public Flux<ResponseDTO<ClientDTO>> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("{code}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Find by code of product")
    public Mono<ResponseDTO<ClientDTO>> findByCode(@PathVariable("code") String id) {
        return this.clientService.findByCode(id);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Update a product")
    public Mono<ResponseDTO> update(@RequestBody ClientDTO clientDTO){
        return this.clientService.update(clientDTO);
    }

    @DeleteMapping("{code}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<ResponseDTO> delete(@PathVariable("code") String code) {
        return this.clientService.delete(code);
    }

}
