CREATE TABLE not_matches (
    id SERIAL PRIMARY KEY,
    matching_user_id INT NOT NULL,
    matched_user_id INT NOT NULL
);

ALTER TABLE not_matches
    ADD CONSTRAINT fk_matching_user_id
        FOREIGN KEY (matching_user_id) REFERENCES app_user(id);

ALTER TABLE not_matches
    ADD CONSTRAINT fk_matched_user_id
        FOREIGN KEY (matched_user_id) REFERENCES app_user(id);