--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: wgn_mover; Type: SCHEMA; Schema: -; Owner: postgres
--

--CREATE SCHEMA wgn_mover;


ALTER SCHEMA wgn_mover OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cargo; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.cargo (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    number bigint NOT NULL
);


ALTER TABLE wgn_mover.cargo OWNER TO postgres;

--
-- Name: cargo_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.cargo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.cargo_id_seq OWNER TO postgres;

--
-- Name: cargo_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.cargo_id_seq OWNED BY wgn_mover.cargo.id;


--
-- Name: line; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.line (
    id bigint NOT NULL,
    number bigint NOT NULL,
    station_id bigint
);


ALTER TABLE wgn_mover.line OWNER TO postgres;

--
-- Name: line_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.line_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.line_id_seq OWNER TO postgres;

--
-- Name: line_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.line_id_seq OWNED BY wgn_mover.line.id;


--
-- Name: station; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.station (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE wgn_mover.station OWNER TO postgres;

--
-- Name: station_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.station_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.station_id_seq OWNER TO postgres;

--
-- Name: station_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.station_id_seq OWNED BY wgn_mover.station.id;


--
-- Name: status; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.status (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE wgn_mover.status OWNER TO postgres;

--
-- Name: status_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.status_id_seq OWNER TO postgres;

--
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.status_id_seq OWNED BY wgn_mover.status.id;


--
-- Name: type; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.type (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE wgn_mover.type OWNER TO postgres;

--
-- Name: type_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.type_id_seq OWNER TO postgres;

--
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.type_id_seq OWNED BY wgn_mover.type.id;


--
-- Name: wagon; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.wagon (
    id bigint NOT NULL,
    weight double precision NOT NULL,
    index bigint,
    cargo_id bigint,
    line_id bigint,
    status_id bigint
);


ALTER TABLE wgn_mover.wagon OWNER TO postgres;

--
-- Name: wagon_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.wagon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.wagon_id_seq OWNER TO postgres;

--
-- Name: wagon_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.wagon_id_seq OWNED BY wgn_mover.wagon.id;


--
-- Name: wagon_passport; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.wagon_passport (
    id bigint NOT NULL,
    capacity double precision,
    number bigint NOT NULL,
    weight double precision,
    type_id bigint NOT NULL
);


ALTER TABLE wgn_mover.wagon_passport OWNER TO postgres;

--
-- Name: wagon_passport_id_seq; Type: SEQUENCE; Schema: wgn_mover; Owner: postgres
--

CREATE SEQUENCE wgn_mover.wagon_passport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wgn_mover.wagon_passport_id_seq OWNER TO postgres;

--
-- Name: wagon_passport_id_seq; Type: SEQUENCE OWNED BY; Schema: wgn_mover; Owner: postgres
--

ALTER SEQUENCE wgn_mover.wagon_passport_id_seq OWNED BY wgn_mover.wagon_passport.id;


--
-- Name: wagon_wp_id; Type: TABLE; Schema: wgn_mover; Owner: postgres
--

CREATE TABLE wgn_mover.wagon_wp_id (
    wagon_passport_id bigint,
    wagon_id bigint NOT NULL
);


ALTER TABLE wgn_mover.wagon_wp_id OWNER TO postgres;

--
-- Name: cargo id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.cargo ALTER COLUMN id SET DEFAULT nextval('wgn_mover.cargo_id_seq'::regclass);


--
-- Name: line id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.line ALTER COLUMN id SET DEFAULT nextval('wgn_mover.line_id_seq'::regclass);


--
-- Name: station id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.station ALTER COLUMN id SET DEFAULT nextval('wgn_mover.station_id_seq'::regclass);


--
-- Name: status id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.status ALTER COLUMN id SET DEFAULT nextval('wgn_mover.status_id_seq'::regclass);


--
-- Name: type id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.type ALTER COLUMN id SET DEFAULT nextval('wgn_mover.type_id_seq'::regclass);


--
-- Name: wagon id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon ALTER COLUMN id SET DEFAULT nextval('wgn_mover.wagon_id_seq'::regclass);


--
-- Name: wagon_passport id; Type: DEFAULT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_passport ALTER COLUMN id SET DEFAULT nextval('wgn_mover.wagon_passport_id_seq'::regclass);


--
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);


--
-- Name: line line_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.line
    ADD CONSTRAINT line_pkey PRIMARY KEY (id);


--
-- Name: station station_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.station
    ADD CONSTRAINT station_pkey PRIMARY KEY (id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: type type_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: wagon_passport uk_3b32orqd2ocyvshbkak79y66x; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_passport
    ADD CONSTRAINT uk_3b32orqd2ocyvshbkak79y66x UNIQUE (number);


--
-- Name: type uk_3tg65hx29l2ser69ddfwvhy4h; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.type
    ADD CONSTRAINT uk_3tg65hx29l2ser69ddfwvhy4h UNIQUE (name);


--
-- Name: cargo uk_88gal0a3mo60juma0e8jde5rd; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.cargo
    ADD CONSTRAINT uk_88gal0a3mo60juma0e8jde5rd UNIQUE (number);


--
-- Name: line uk_apuh8jcy6s2br7ut0w9541yiw; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.line
    ADD CONSTRAINT uk_apuh8jcy6s2br7ut0w9541yiw UNIQUE (number);


--
-- Name: cargo uk_dto78eyvgn9hmu3hilogkj1xv; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.cargo
    ADD CONSTRAINT uk_dto78eyvgn9hmu3hilogkj1xv UNIQUE (name);


--
-- Name: station uk_gnneuc0peq2qi08yftdjhy7ok; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.station
    ADD CONSTRAINT uk_gnneuc0peq2qi08yftdjhy7ok UNIQUE (name);


--
-- Name: status uk_reccgx9nr0a8dwv201t44l6pd; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.status
    ADD CONSTRAINT uk_reccgx9nr0a8dwv201t44l6pd UNIQUE (name);


--
-- Name: wagon_passport wagon_passport_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_passport
    ADD CONSTRAINT wagon_passport_pkey PRIMARY KEY (id);


--
-- Name: wagon wagon_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon
    ADD CONSTRAINT wagon_pkey PRIMARY KEY (id);


--
-- Name: wagon_wp_id wagon_wp_id_pkey; Type: CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_wp_id
    ADD CONSTRAINT wagon_wp_id_pkey PRIMARY KEY (wagon_id);


--
-- Name: wagon fk5tnfnac4xevru7t8h6te8w7wu; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon
    ADD CONSTRAINT fk5tnfnac4xevru7t8h6te8w7wu FOREIGN KEY (cargo_id) REFERENCES wgn_mover.cargo(id);


--
-- Name: wagon_wp_id fk7742ul03vfpm372hq77neqkyu; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_wp_id
    ADD CONSTRAINT fk7742ul03vfpm372hq77neqkyu FOREIGN KEY (wagon_passport_id) REFERENCES wgn_mover.wagon_passport(id);


--
-- Name: line fk9mayxiwmwrvbs98ajhyuunijh; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.line
    ADD CONSTRAINT fk9mayxiwmwrvbs98ajhyuunijh FOREIGN KEY (station_id) REFERENCES wgn_mover.station(id);


--
-- Name: wagon fkcywpvmanvrq6a9gsgbbocosvf; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon
    ADD CONSTRAINT fkcywpvmanvrq6a9gsgbbocosvf FOREIGN KEY (line_id) REFERENCES wgn_mover.line(id);


--
-- Name: wagon fketm8k6p5mg6gtrdi3bf963ykt; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon
    ADD CONSTRAINT fketm8k6p5mg6gtrdi3bf963ykt FOREIGN KEY (status_id) REFERENCES wgn_mover.status(id);


--
-- Name: wagon_wp_id fkijf0546r0bpx6x13529uhaj23; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_wp_id
    ADD CONSTRAINT fkijf0546r0bpx6x13529uhaj23 FOREIGN KEY (wagon_id) REFERENCES wgn_mover.wagon(id);


--
-- Name: wagon_passport fks2l0ccpv22xr94lwoefy6eob5; Type: FK CONSTRAINT; Schema: wgn_mover; Owner: postgres
--

ALTER TABLE ONLY wgn_mover.wagon_passport
    ADD CONSTRAINT fks2l0ccpv22xr94lwoefy6eob5 FOREIGN KEY (type_id) REFERENCES wgn_mover.type(id);


--
-- PostgreSQL database dump complete
--

