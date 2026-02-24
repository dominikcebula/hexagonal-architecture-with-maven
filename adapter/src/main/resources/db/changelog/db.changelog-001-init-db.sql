CREATE SEQUENCE employment_info_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE employment_info
(
    id                  BIGINT PRIMARY KEY DEFAULT nextval('employment_info_seq'),
    name                VARCHAR(100),
    industry            VARCHAR(64),
    yearly_income       NUMERIC(19, 2),
    currency            VARCHAR(3),
    number_of_employees INTEGER,
    email               VARCHAR(100),
    phone_number        VARCHAR(50),
    website             VARCHAR(150)
);

CREATE SEQUENCE applicants_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE applicants
(
    id                 BIGINT PRIMARY KEY DEFAULT nextval('applicants_seq'),
    first_name         VARCHAR(64),
    last_name          VARCHAR(64),
    birth_date         DATE,
    credit_score       INTEGER,
    employment_info_id BIGINT,
    email              VARCHAR(100),
    phone_number       VARCHAR(50),
    CONSTRAINT fk_employment FOREIGN KEY (employment_info_id) REFERENCES employment_info (id)
);

CREATE SEQUENCE loan_applications_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE loan_applications
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('loan_applications_seq'),
    applicant_id    BIGINT,
    amount          NUMERIC,
    currency        VARCHAR(3),
    terms_in_months INTEGER,
    status          VARCHAR(10),
    CONSTRAINT fk_applicant FOREIGN KEY (applicant_id) REFERENCES applicants (id)
);
