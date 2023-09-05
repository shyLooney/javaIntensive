package edu.school21.chat.models;

import java.util.*;
import java.time.LocalDateTime;

public class Message
{
    private Long id;
    private User user;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime localDateTime;

    public Message(Long id, User user, Chatroom chatroom, String text, LocalDateTime localDateTime)
    {
        this.id = id;
        this.user = user;
        this.chatroom = chatroom;
        this.text = text;
        this.localDateTime = localDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString()
    {
        return "{ Id = \"" + id + "\"\nUser = \"" + user + "\"\nChatroom = \"" + chatroom +
                "\"\ntext = \"" + text + "\"\nlocalDateTime = \"" + localDateTime + "\" }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, chatroom, text, localDateTime);
    }

    @Override
    public boolean equals(Object object)
    {
        Message message = (Message)object;
        if (id == message.getId() && user.equals(message.getUser()) && chatroom.equals(message.getChatroom())
            && text.equals(message.getText()) && localDateTime.equals(message.getLocalDateTime()))
            return true;
        else
            return false;
    }

}