INSERT INTO ROLE (RL_ID, RL_NAME, RL_DESCRIPTION, RL_CREATED_AT, RL_UPDATED_AT) VALUES (1, 'Admin', 'Администратор имеющий права создавать анонсы', NOW(), NOW());
INSERT INTO ROLE (RL_ID, RL_NAME, RL_DESCRIPTION, RL_CREATED_AT, RL_UPDATED_AT) VALUES (2, 'Super Admin', 'Супер Администратор имеющий все права администратора + может изменять статус кандидата и назначать интервью', NOW(), NOW());