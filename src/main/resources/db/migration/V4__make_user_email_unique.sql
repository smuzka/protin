ALTER TABLE app_user
    ADD CONSTRAINT unique_email
        UNIQUE (email);