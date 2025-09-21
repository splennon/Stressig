
CREATE SCHEMA short;

CREATE TABLE short.adjectives (
    id integer NOT NULL,
    de character varying(100) NOT NULL,
    variations character varying(100),
    en character varying(100) NOT NULL,
    rank integer NOT NULL
);

CREATE TABLE short.adverbs (
    id integer NOT NULL,
    de character varying(100) NOT NULL,
    variations character varying(100),
    en character varying(100) NOT NULL,
    rank integer NOT NULL
);

CREATE TABLE short.nouns (
    id integer NOT NULL,
    root character varying(100) NOT NULL,
    de character varying(100) NOT NULL,
    article character varying(7) NOT NULL,
    en text NOT NULL,
    plural character varying(100),
    rank integer NOT NULL,
    CONSTRAINT nouns_article_check CHECK (((article)::text = ANY (ARRAY[('der'::character varying)::text, ('die'::character varying)::text, ('das'::character varying)::text, ('der/die'::character varying)::text, ('der/das'::character varying)::text, ('die/das'::character varying)::text])))
);

CREATE TABLE short.verbs (
    id integer NOT NULL,
    de character varying(100) NOT NULL,
    en character varying(100) NOT NULL,
    rank integer NOT NULL,
    praesens_ich character varying(100),
    praesens_du character varying(100),
    praesens_er_sie_es character varying(100),
    praeteritum_ich character varying(100),
    partizip_ii character varying(100),
    konjunktiv_ii_ich character varying(100),
    imperativ_singular character varying(100),
    imperativ_plural character varying(100),
    hilfsverb character varying(10)
);
