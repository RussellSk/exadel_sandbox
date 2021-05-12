-- Super Admin Permissions

SELECT @ROLE_ID := RL_ID FROM ROLE WHERE RL_NAME = 'Super Admin' LIMIT 1;
SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'employee_create' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'employee_update' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'employee_view' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'employee_delete' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_create' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_update' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_view' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_delete' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'permission_view' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());


-- Admin Permissions
SELECT @ROLE_ID := RL_ID FROM ROLE WHERE RL_NAME = 'Admin' LIMIT 1;
SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'employee_view' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_create' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_update' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_delete' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'role_delete' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());

SELECT @PERMISSION_ID := PMN_ID FROM PERMISSION WHERE PMN_NAME = 'permission_view' LIMIT 1;
INSERT INTO ROLE_PERMISSION (RL_ID, PMN_ID, RP_CREATED_AT, RP_UPDATED_AT) VALUES (@ROLE_ID, @PERMISSION_ID, NOW(), NOW());