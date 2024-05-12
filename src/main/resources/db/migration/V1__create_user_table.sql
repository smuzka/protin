CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(10),
  experience VARCHAR(255),
  education VARCHAR(255),
  preferences VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);
