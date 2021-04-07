INSERT INTO resume (RSM_PATH, RSM_EXT, RSM_NAME, RSM_SIZE)
VALUES ('...', '/////', 'first1', 100);

INSERT INTO image (IMG_PATH) VALUES ('///');
INSERT INTO event_type (EVT_NAME, EVT_DESCRIPTION) VALUES ('///', '///');
INSERT INTO event (EV_SHORT_DESCRIPTION, EV_FULL_DESCRIPTION, EV_LOCATION) VALUES ('//', '//', '//');

INSERT INTO candidate (RSM_ID, CN_FIRST_NAME, CN_LAST_NAME, CN_PHONE, CN_EMAIL, CN_SKYPE, CN_ENGLISH_LEVEL,
                       CN_EXPERTISE,
                       CN_EXPERIENCE, CN_EDUCATION, CN_LOCATION)
VALUES (1, 'John2', 'Smith2', '+380969337641', 'john27@gmial.com', 'John27',
        'Intermediate', 'Java', 'absent', 'KNTU', 'Kiev');

INSERT INTO interview_time (EV_ID, CN_ID, EMP_ID)
VALUES (1, 1, 1)