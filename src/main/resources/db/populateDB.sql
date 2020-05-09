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
VALUES ('admin', 'admin@email.ru', 'password'),
       ('user', 'user@email.ru', 'password');

INSERT INTO USER_ROLES (USER_ID, ROLE)
VALUES (100000, 'ROLE_ADMIN'),
       (100000, 'ROLE_USER'),
       (100001, 'ROLE_USER');

--IDs 100002, 100003, 100004
INSERT INTO RESTAURANTS (NAME)
VALUES ('Ollis'),
       ('DoDo'),
       ('PizzaHut');

--IDs 100005 - 100009
INSERT INTO MENUS (DATE, RESTAURANT_ID)
VALUES ('2020-03-25', 100002),
       ('2020-03-24', 100002),
       ('2020-03-23', 100002),
       ('2020-03-25', 100003),
       ('2020-03-24', 100003);

--IDs 100010 - 100017
INSERT INTO DISHES (MENU_ID, NAME, PRICE)
VALUES (100005, 'pizza_ollis_1', 45000),
       (100005, 'pizza_ollis_2', 60000),
       (100006, 'pizza_ollis_3', 80000),
       (100006, 'pizza_ollis_4', 30000),
       (100006, 'pizza_ollis_5', 25000),
       (100007, 'pizza_ollis_6', 48000),
       (100008, 'pizza_DoDo_1', 36000),
       (100008, 'pizza_DoDo_2', 50000);

--IDs 100018 - 100023
INSERT INTO VOTES (DATE, USER_ID, RESTAURANT_ID)
VALUES ('2020-03-25', 100000, 100002),
       ('2020-03-25', 100001, 100002),
       ('2020-03-24', 100000, 100003),
       ('2020-03-24', 100001, 100002),
       ('2020-03-23', 100000, 100003),
       ('2020-03-23', 100001, 100003);



