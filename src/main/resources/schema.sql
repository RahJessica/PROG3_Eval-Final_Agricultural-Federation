CREATE DATABASE federation_db;

CREATE USER federation_user WITH PASSWORD '123456';

GRANT CONNECT ON DATABASE federation_db TO federation_user;

GRANT USAGE ON SCHEMA public TO federation_user;

alter database federation_db owner TO federation_user;

grant usage, select on all sequences in schema public to federation_user;

CREATE TABLE federation (
                            id_federation SERIAL PRIMARY KEY,
                            nom_federation VARCHAR(50)
);
d

CREATE TABLE collectivite (
                              id_collectivite SERIAL PRIMARY KEY,
                              nom_collectivite VARCHAR(50),
                              ville VARCHAR(50),
                              specialite VARCHAR(50),
                              creation_date DATE,
                              autorisation BOOLEAN,
                              numero VARCHAR(50),
                              id_federation INT,
                              CONSTRAINT fk_collectivite_federation
                                  FOREIGN KEY (id_federation)
                                      REFERENCES federation(id_federation)
);

CREATE TABLE member (
                        id_membre SERIAL PRIMARY KEY,
                        telephone VARCHAR(20),
                        email VARCHAR(150),
                        date_adhesion DATE,
                        nom_membre VARCHAR(50),
                        prenom_membre VARCHAR(50),
                        date_naissance DATE,
                        genre VARCHAR(20),
                        adresse VARCHAR(150),
                        metier VARCHAR(50),
                        id_collectivite INT,
                        CONSTRAINT fk_membre_collectivite
                            FOREIGN KEY (id_collectivite)
                                REFERENCES collectivite(id_collectivite)
);


CREATE TABLE financial_account (
                                   id_account serial PRIMARY KEY,
                                   collectivity_id int NOT NULL,
                                   type VARCHAR NOT NULL, -- CASH, BANK, MOBILE
                                   balance NUMERIC NOT NULL,

    -- Bank
                                   holder_name VARCHAR,
                                   bank_name VARCHAR,
                                   bank_code VARCHAR,
                                   branch_code VARCHAR,
                                   account_number VARCHAR,
                                   rib_key VARCHAR,

    -- Mobile
                                   mobile_service VARCHAR,
                                   mobile_number VARCHAR
);

CREATE TABLE cotisation (
                            id_cotisation SERIAL PRIMARY KEY,
                            montant DECIMAL(15,2),
                            date_paiement DATE,
                            type_cotisation VARCHAR(50),
                            mode_paiement VARCHAR(50),
                            id_collectivite INT,
                            id_membre INT,
                            CONSTRAINT fk_cotisation_collectivite
                                FOREIGN KEY (id_collectivite)
                                    REFERENCES collectivite(id_collectivite),
                            CONSTRAINT fk_cotisation_membre
                                FOREIGN KEY (id_membre)
                                    REFERENCES member(id_membre)
);

CREATE TABLE activite (
                          id_activite SERIAL PRIMARY KEY,
                          type_activite VARCHAR(50),
                          date_activite DATE,
                          obligatoire BOOLEAN,
                          description TEXT,
                          id_federation INT,
                          id_collectivite INT,
                          CONSTRAINT fk_activite_federation
                              FOREIGN KEY (id_federation)
                                  REFERENCES federation(id_federation),
                          CONSTRAINT fk_activite_collectivite
                              FOREIGN KEY (id_collectivite)
                                  REFERENCES collectivite(id_collectivite)
);

CREATE TABLE presence (
                          id_presence SERIAL PRIMARY KEY,
                          present BOOLEAN,
                          excuse BOOLEAN,
                          motif_excuse TEXT,
                          id_membre INT,
                          id_activite INT,
                          CONSTRAINT fk_presence_membre
                              FOREIGN KEY (id_membre)
                                  REFERENCES member(id_membre),
                          CONSTRAINT fk_presence_activite
                              FOREIGN KEY (id_activite)
                                  REFERENCES activite(id_activite)
);

CREATE TABLE poste (
                       id_poste SERIAL PRIMARY KEY,
                       nom_poste VARCHAR(50),
                       id_membre INT,
                       CONSTRAINT fk_poste_membre
                           FOREIGN KEY (id_membre)
                               REFERENCES member(id_membre)
);

CREATE TABLE mandat (
                        id_mandat SERIAL PRIMARY KEY,
                        date_debut DATE,
                        date_fin DATE,
                        type_mandat VARCHAR(50)
);

CREATE TABLE affecter (
                          id_poste INT,
                          id_mandat INT,
                          PRIMARY KEY (id_poste, id_mandat),
                          CONSTRAINT fk_affecter_poste
                              FOREIGN KEY (id_poste)
                                  REFERENCES poste(id_poste),
                          CONSTRAINT fk_affecter_mandat
                              FOREIGN KEY (id_mandat)
                                  REFERENCES mandat(id_mandat)
);

CREATE TABLE collectivity_transaction (
                                          id_transaction SERIAL PRIMARY KEY,
                                          id_collectivite INT NOT NULL,
                                          id_membre INT NOT NULL,
                                          id_account INT NOT NULL,
                                          montant NUMERIC NOT NULL,
                                          mode_paiement VARCHAR(50),
                                          date_creation DATE,

                                          CONSTRAINT fk_transaction_collectivite
                                              FOREIGN KEY (id_collectivite)
                                                  REFERENCES collectivite(id_collectivite),

                                          CONSTRAINT fk_transaction_membre
                                              FOREIGN KEY (id_membre)
                                                  REFERENCES membre(id_membre),

                                          CONSTRAINT fk_transaction_account
                                              FOREIGN KEY (id_account)
                                                  REFERENCES financial_account(id_account)
);

INSERT INTO collectivity_transaction (
    id_collectivite,
    id_membre,
    id_account,
    montant,
    mode_paiement,
    date_creation
) VALUES
      (3, 1, 1, 100000, 'CASH', '2026-01-01'),
      (3, 2, 1, 100000, 'CASH', '2026-01-01'),
      (3, 3, 1, 100000, 'CASH', '2026-01-01'),
      (3, 4, 1, 100000, 'CASH', '2026-01-01'),
      (3, 5, 1, 100000, 'CASH', '2026-01-01'),
      (3, 6, 1, 100000, 'CASH', '2026-01-01'),
      (3, 7, 1, 60000,  'CASH', '2026-01-01'),
      (3, 8, 1, 90000,  'CASH', '2026-01-01');

INSERT INTO collectivity_transaction (
    id_collectivite,
    id_membre,
    id_account,
    montant,
    mode_paiement,
    date_creation
) VALUES
(2, 9,  3, 60000,  'CASH', '2026-01-01'),
(2, 10, 3, 90000,  'CASH', '2026-01-01'),
(2, 11, 3, 100000, 'CASH', '2026-01-01'),
(2, 12, 3, 100000, 'CASH', '2026-01-01'),
(2, 13, 3, 100000, 'CASH', '2026-01-01'),
(2, 14, 3, 100000, 'CASH', '2026-01-01'),

(2, 15, 4, 40000,  'MOBILE', '2026-01-01'),
(2, 16, 4, 60000,  'MOBILE', '2026-01-01');

create type activity_type as enum ('MEETING', 'TRAINING', 'OTHER');
CREATE TABLE collectivity_activity (
                                       id_activity VARCHAR(100) PRIMARY KEY,

                                       label VARCHAR(255) NOT NULL,

                                       activity_type activity_type NOT NULL,

                                       collectivity_id INTEGER NOT NULL,

                                       executive_date DATE,

                                       week_ordinal INTEGER,

                                       day_of_week VARCHAR(20),

                                       CONSTRAINT fk_collectivity_activity
                                           FOREIGN KEY (collectivity_id)
                                               REFERENCES collectivite(id_collectivite)
);