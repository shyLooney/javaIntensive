CREATE SCHEMA IF NOT EXISTS Chat;

CREATE TABLE IF NOT EXISTS Chat.User(
    id SERIAL PRIMARY KEY,
	user_login TEXT UNIQUE NOT NULL,
    user_password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Chat.Chatroom (
    id SERIAL PRIMARY KEY,
    room_name TEXT NOT NULL UNIQUE,
    room_owner_id INT NOT NULL,
    CONSTRAINT fk_users FOREIGN KEY (room_owner_id) REFERENCES Chat.User(id)
);

CREATE TABLE IF NOT EXISTS Chat.Message (
    id SERIAL PRIMARY KEY,
    author_id INT,
    room_id INT,
    message_text TEXT,
    date_time timestamp,
    CONSTRAINT fk_users FOREIGN KEY(author_id) REFERENCES Chat.User(id),   
    CONSTRAINT fk_chat_rooms FOREIGN KEY(room_id) REFERENCES Chat.Chatroom(id)
);