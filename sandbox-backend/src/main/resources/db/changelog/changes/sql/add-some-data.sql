INSERT INTO resume (RSM_PATH, RSM_EXT, RSM_NAME, RSM_SIZE)
VALUES ('...', '///', 'first', 100);

INSERT INTO candidate (RSM_ID, CN_FIRST_NAME, CN_LAST_NAME, CN_PHONE, CN_EMAIL, CN_SKYPE, CN_ENGLISH_LEVEL,
                       CN_EXPERTISE,
                       CN_EXPERIENCE, CN_EDUCATION, CN_LOCATION)
VALUES (8, 'John2', 'Smith2', '+380969337641', 'john27@gmial.com', 'John27',
        'Intermediate', 'Java', 'absent', 'KNTU', 'Kiev');

INSERT INTO event (ev_id) VALUES (2);
INSERT INTO employee (emp_id) VALUES (2);

INSERT INTO interview_time (EV_ID, CN_ID, EMP_ID)
VALUES (2, 9, 2)