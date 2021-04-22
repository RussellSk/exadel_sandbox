ALTER TABLE candidate
    CHANGE COLUMN CN_EXPERIENCE CN_OTHER_SKILLS TEXT DEFAULT NULL,
    CHANGE COLUMN CN_EXPERTISE CN_MAIN_SKILL TEXT NOT NULL;

ALTER TABLE candidate
    DROP COLUMN CN_LOCATION,
    DROP COLUMN CN_EDUCATION;

ALTER TABLE candidate
    ADD CN_COUNTRY VARCHAR(50) NOT NULL
    AFTER CN_OTHER_SKILLS,
    ADD CN_CITY VARCHAR(50) NOT NULL
    AFTER CN_COUNTRY,
    ADD CN_INSTITUTION VARCHAR(150) DEFAULT NULL
    AFTER CN_CITY,
    ADD CN_FACULTY VARCHAR(150) DEFAULT NULL
    AFTER CN_INSTITUTION,
    ADD CN_SPECIALITY VARCHAR(150) DEFAULT NULL
    AFTER CN_FACULTY,
    ADD CN_GRADUATION_DATE DATE DEFAULT NULL
    AFTER CN_FACULTY;