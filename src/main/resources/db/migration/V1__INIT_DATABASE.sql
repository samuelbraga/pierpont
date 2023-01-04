CREATE TABLE IF NOT EXISTS accounts (
    id                  BIGINT      GENERATED ALWAYS AS IDENTITY,
    document_number     VARCHAR(50) UNIQUE NOT NULL,

    created_at          TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC'),

    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX document_number_idx ON accounts(document_number);

CREATE TABLE IF NOT EXISTS operation_types (
    id                  BIGINT      GENERATED ALWAYS AS IDENTITY,
    description         VARCHAR(50) NOT NULL,

    created_at          TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC'),

    PRIMARY KEY(id)
);

INSERT INTO operation_types(id, description) OVERRIDING SYSTEM VALUE VALUES(1, 'CASH_PURCHASE');
INSERT INTO operation_types(id, description) OVERRIDING SYSTEM VALUE VALUES(2, 'INSTALLMENT_PURCHASE');
INSERT INTO operation_types(id, description) OVERRIDING SYSTEM VALUE VALUES(3, 'WITHDRAWAL');
INSERT INTO operation_types(id, description) OVERRIDING SYSTEM VALUE VALUES(4, 'PAYMENT');

CREATE TABLE IF NOT EXISTS transactions (
    id                  BIGINT      GENERATED ALWAYS AS IDENTITY,
    account_id          BIGINT      NOT NULL,
    operation_type_id   BIGINT      NOT NULL,
    amount              DECIMAL     NOT NULL,

    created_at          TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC'),

    PRIMARY KEY(id),

    FOREIGN KEY (account_id)
        REFERENCES accounts(id),
    FOREIGN KEY (operation_type_id)
        REFERENCES operation_types(id)
);