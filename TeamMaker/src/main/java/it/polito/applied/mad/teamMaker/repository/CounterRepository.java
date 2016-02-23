package it.polito.applied.mad.teamMaker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.Counter;

public interface CounterRepository extends MongoRepository<Counter, String>, CustomCounterRepository{

}
