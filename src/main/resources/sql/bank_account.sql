DROP TABLE IF EXISTS bank_account CASCADE;
CREATE TABLE IF NOT EXISTS bank_account (
        id           SERIAL PRIMARY KEY,
        user_id      INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
        iban         VARCHAR(34) UNIQUE NOT NULL,
        balance      NUMERIC(15, 2) NOT NULL DEFAULT 0,
        created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);