package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.*;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("123");

        HikariDataSource ds = new HikariDataSource(config);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

        for (int i = 0; i < 3; i++) {
            List<User> list = messagesRepository.findAll(i,2);
            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }

    }
}
