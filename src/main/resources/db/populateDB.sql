DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM users;
DELETE
FROM dishes;
DELETE
FROM menus;
DELETE
FROM restaurants;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('admin', 'admin@email.ru', '{noop}password'),
       ('user', 'user@email.ru', '{noop}password'),
       ('user2', 'user2@email.ru', '{noop}password');

INSERT INTO USER_ROLES (USER_ID, ROLE)
VALUES (100000, 'ADMIN'),
       (100000, 'USER'),
       (100001, 'USER'),
       (100002, 'USER');

--IDs 100003, 100004, 100005
INSERT INTO RESTAURANTS (NAME)
VALUES ('Ollis'),
       ('DoDo'),
       ('PizzaHut');

--IDs 100006 - 100010
INSERT INTO MENUS (DATE, RESTAURANT_ID)
VALUES ('2020-03-25', 100003),
       ('2020-03-24', 100003),
       ('2020-03-23', 100003),
       ('2020-03-25', 100004),
       ('2020-03-24', 100004);

--IDs 100011 - 100018
INSERT INTO DISHES (MENU_ID, NAME, PRICE)
VALUES (100006, 'pizza_ollis_1', 45000),
       (100006, 'pizza_ollis_2', 60000),
       (100007, 'pizza_ollis_3', 80000),
       (100007, 'pizza_ollis_4', 30000),
       (100007, 'pizza_ollis_5', 25000),
       (100008, 'pizza_ollis_6', 48000),
       (100009, 'pizza_DoDo_1', 36000),
       (100009, 'pizza_DoDo_2', 50000);

--IDs 100019 - 100024
INSERT INTO VOTES (DATE, USER_ID, RESTAURANT_ID)
VALUES ('2020-03-25', 100000, 100003),
       ('2020-03-25', 100001, 100003),
       ('2020-03-24', 100000, 100004),
       ('2020-03-24', 100001, 100003),
       ('2020-03-23', 100000, 100004),
       ('2020-03-23', 100001, 100004);



