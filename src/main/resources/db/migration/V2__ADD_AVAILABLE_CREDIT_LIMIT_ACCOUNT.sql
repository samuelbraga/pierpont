ALTER TABLE accounts
    ADD COLUMN available_credit_limit DECIMAL NOT NULL DEFAULT 0;
