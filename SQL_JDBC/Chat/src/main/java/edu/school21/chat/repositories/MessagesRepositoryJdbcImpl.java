package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;
    private int capacity;
    private Connection conn;
    private List<User> users;
    private Vector<User> human;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<User> findAll(int page, int size) {
        human = null;
        users = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery("WITH owners AS (\n" +
                    "\tSELECT *\n" +
                    "\tFROM chat.user as us\n" +
                    "\tLEFT JOIN chat.chatroom as ch ON ch.room_owner_id = us.id),\n" +
                    "writers AS (\n" +
                    "\tSELECT *\n" +
                    "\tFROM chat.user as us\n" +
                    "\tJOIN chat.message as ms ON ms.author_id = us.id\n" +
                    "\tJOIN chat.chatroom as ch ON ch.id = ms.room_id),\n" +
                    "numbers AS (\n" +
                    "    SELECT COUNT(*)\n" +
                    "    FROM chat.user)\n" +
                    "SELECT * FROM numbers, writers as wr\n" +
                    "FULL JOIN owners as ow ON wr.author_id = ow.room_owner_id");
            Message msg;
            Chatroom chatroom;

            while (rst.next()) {
                if (human == null) {
                    human = new Vector<>();
                    human.setSize(rst.getInt(1));
                    capacity = rst.getInt(1);
                    for (int i = 1; i <= rst.getInt(1); i++)
                        human.add(i, new User(0, null, null, new ArrayList<>(), new ArrayList<>()));
                }
                if (human.get(rst.getInt(13)).getId() == 0) {
                    human.get(rst.getInt(13)).setId(rst.getLong(13));
                    human.get(rst.getInt(13)).setLogin(rst.getString(14));
                    human.get(rst.getInt(13)).setPassword(rst.getString(15));
                }

                if (rst.getLong(16) != 0) {
                    boolean info = true;
                    chatroom = new Chatroom(rst.getLong(16), rst.getString(17),
                            human.get(rst.getInt(13)), null);
                    for (Chatroom item:human.get(rst.getInt(13)).getCreatedChatroomList()) {
                        if (chatroom.getName().equals(item.getName()))
                            info = false;
                    }
                    if (info)
                        human.get(rst.getInt(13)).getCreatedChatroomList().add(chatroom);
                }

                if (rst.getLong(7) != 0) {
                    boolean info = true;
                    chatroom = new Chatroom(rst.getLong(7), rst.getString(11),
                            human.get(rst.getInt(12)), null);
                    for (Chatroom item:human.get(rst.getInt(13)).getSocializesChatroomList()) {
                        if (chatroom.getName().equals(item.getName()))
                            info = false;
                    }
                    if (info)
                        human.get(rst.getInt(13)).getSocializesChatroomList().add(chatroom);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

        if ((page) * size + 1 > capacity) {
            throw new RuntimeException("Error index");
        }

        users = new ArrayList<>();
        for (int i = page * size + 1; i <= (page + 1) * size && i <= capacity; i++) {
            users.add(human.get(i));
        }

        return users;
    }
}
