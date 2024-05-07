CREATE
DATABASE  IF NOT EXISTS `labGlassware`;
USE `student_tracker`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `lab_glassware`;
DROP TABLE IF EXISTS `glass_joint`;

CREATE TABLE `student`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) DEFAULT NULL,
    `last_name`  VARCHAR(45) DEFAULT NULL,
    `email`      VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE lab_glassware
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name                  VARCHAR(255) NOT NULL,
    material              VARCHAR(255),
    manufacturer          VARCHAR(255),
    location              VARCHAR(255),
    status                VARCHAR(255),
    purchase_date         DATE,
    calibration_date      DATE,
    last_maintenance_date DATE,
    price                 DECIMAL(19, 2),
    provider              VARCHAR(255),
    capacity_ml           INT
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE glass_joint
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    type             VARCHAR(50)  NOT NULL,
    size_designation VARCHAR(255) NOT NULL,
    lab_glassware_id BIGINT,
    FOREIGN KEY (lab_glassware_id) REFERENCES lab_glassware (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE USERS (
                       USERNAME NVARCHAR2(128) PRIMARY KEY,
                       PASSWORD NVARCHAR2(128) NOT NULL,
                       ENABLED CHAR(1) CHECK (ENABLED IN ('Y','N') ) NOT NULL
);


CREATE TABLE AUTHORITIES (
                             USERNAME NVARCHAR2(128) NOT NULL,
                             AUTHORITY NVARCHAR2(128) NOT NULL
);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_FK1 FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME) ENABLE;



INSERT INTO users (username, password, enabled) VALUES
                                                    ('User', '{noop}123', true),
                                                    ('Manager', '{noop}1234', true),
                                                    ('Admin', '{noop}12345', true);

INSERT INTO authorities (username, authority) VALUES
                                                  ('User', 'ROLE_USER'),
                                                  ('Manager', 'ROLE_MANAGER'),
                                                  ('Admin', 'ROLE_ADMIN');
-- / BCRYPT
INSERT INTO users (username, password, enabled) VALUES
                                                    ('User', '{bcrypt}$2a$10$eFMKuSrI805o5QXiCbLU8uG0ZskOvjUeQiWq78JCM1HQlKbWkQXgS', true),
                                                    ('Manager', '{bcrypt}$2a$10$ZjKzaS7Yfk9QcvLs2f40buk15MI/amXP4OiXIk9gS9t5tg1DX0RD2', true),
                                                    ('Admin', '{bcrypt}$2a$10$6sGEsmILZa203/tlVvETtu8sT3JiCR4uNJSnor1WqeutsKWbq6oICS', true);

INSERT INTO authorities (username, authority) VALUES
                                                  ('User', 'ROLE_USER'),
                                                  ('Manager', 'ROLE_MANAGER'),
                                                  ('Admin', 'ROLE_ADMIN');

