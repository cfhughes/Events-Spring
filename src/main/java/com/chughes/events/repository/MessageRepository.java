package com.chughes.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.chughes.events.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
