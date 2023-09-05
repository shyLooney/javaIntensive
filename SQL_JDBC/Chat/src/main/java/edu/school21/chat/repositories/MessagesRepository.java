package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository {
    List<User> findAll(int page, int size);
}
