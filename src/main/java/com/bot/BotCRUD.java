package com.bot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BotCRUD extends CrudRepository<Chat, String> {


}
