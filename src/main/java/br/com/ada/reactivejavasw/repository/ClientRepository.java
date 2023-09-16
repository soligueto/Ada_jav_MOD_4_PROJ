package br.com.ada.reactivejavasw.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ada.reactivejavasw.model.Client;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

    Mono<Client> findById(String id);

    Mono<Void> deleteByCode(String code);
}
