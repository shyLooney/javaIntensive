INSERT INTO Chat.User(user_login, user_password)
VALUES	('Ivan Kuzmich', '123'),
		('Roman Romanovich', 'qwerty'),
		('Vlad Vladix', 'world'),
		('Anton Kalmyk', '123'),
		('Mutant Glam', '123');

INSERT INTO chat.chatroom (room_name, room_owner_id) VALUES
    ( 'CEHAT', (SELECT id from chat.user WHERE user_login='Ivan Kuzmich') ),
    ( '7A', (SELECT id from chat.user WHERE user_login='Roman Romanovich' ) ),
    ( 'parents chat', (SELECT id from chat.user WHERE user_login='Vlad Vladix' ) ),
    ( 'hobbies', (SELECT id from chat.user WHERE user_login='Anton Kalmyk' ) ),
    ( 'favourites', (SELECT id from chat.user WHERE user_login='Mutant Glam' ) );


INSERT INTO chat.message (message_text, date_time, author_id, room_id) VALUES
    ( 'aaaaaaaaaaaa', '2022-10-06 11:11:11', (SELECT id from chat.user WHERE user_login='Vlad Vladix' ), (SELECT id from chat.Chatroom WHERE room_name='7A') ),
    ( 'aboba', '2022-10-06 22:22:22', (SELECT id from chat.user WHERE user_login='Ivan Kuzmich'), (SELECT id from chat.Chatroom WHERE room_name='CEHAT') ),
    ( 'wedding vase', '2022-10-06 9:42:07', (SELECT id from chat.user WHERE user_login='Mutant Glam' ), (SELECT id from chat.Chatroom WHERE room_name='favourites') ),
    ( 'buy a garage', '2022-10-06 21:42:07', (SELECT id from chat.user WHERE user_login='Vlad Vladix' ), (SELECT id from chat.Chatroom WHERE room_name='parents chat') ),
    ( 'try catch', '2022-10-06 17:00:00', (SELECT id from chat.user WHERE user_login='Mutant Glam' ), (SELECT id from chat.Chatroom WHERE room_name='favourites') ),
    ( 'sema hi!', '2022-10-05 17:21:42', (SELECT id from chat.user WHERE user_login='Anton Kalmyk' ), (SELECT id from chat.Chatroom WHERE room_name='hobbies') );