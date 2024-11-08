--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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
-- Name: ordenar_tabla(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.ordenar_tabla() RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
-- Sustituye 'nombre_tabla' y 'nombre_indice' por los nombres correctos    
	CLUSTER  listc USING listc_pkey;     
-- Para asegurar que las estadÃ­sticas se actualicen despuÃ©s del CLUSTER    
	ANALYZE listc;
END; 
$$;


ALTER FUNCTION public.ordenar_tabla() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cita (
    ncita integer NOT NULL,
    motivo character varying(128) NOT NULL,
    day smallint NOT NULL,
    month smallint NOT NULL,
    year smallint NOT NULL,
    turno character(1) NOT NULL,
    docid integer NOT NULL,
    code integer NOT NULL,
    idlist integer NOT NULL,
    CONSTRAINT cita_turno_check CHECK ((turno = ANY (ARRAY['1'::bpchar, '2'::bpchar, '3'::bpchar])))
);


ALTER TABLE public.cita OWNER TO postgres;

--
-- Name: cita_ncita_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cita_ncita_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cita_ncita_seq OWNER TO postgres;

--
-- Name: cita_ncita_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cita_ncita_seq OWNED BY public.cita.ncita;


--
-- Name: eodonto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eodonto (
    code integer NOT NULL,
    email character varying(30),
    name character varying(20) NOT NULL,
    apaterno character varying(20) NOT NULL,
    amaterno character varying(20) NOT NULL
);


ALTER TABLE public.eodonto OWNER TO postgres;

--
-- Name: eport; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eport (
    idport smallint NOT NULL,
    nombre character varying(20),
    apaterno character varying(20),
    amaterno character varying(20),
    idlista integer NOT NULL
);


ALTER TABLE public.eport OWNER TO postgres;

--
-- Name: listc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.listc (
    id integer NOT NULL,
    day smallint NOT NULL,
    month smallint NOT NULL,
    year smallint NOT NULL
);


ALTER TABLE public.listc OWNER TO postgres;

--
-- Name: ncones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ncones (
    code integer NOT NULL,
    nro integer NOT NULL
);


ALTER TABLE public.ncones OWNER TO postgres;

--
-- Name: nconpa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nconpa (
    docid integer NOT NULL,
    nro integer NOT NULL
);


ALTER TABLE public.nconpa OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    docid integer NOT NULL,
    email character varying(30),
    name character varying(20) NOT NULL,
    apaterno character varying(20) NOT NULL,
    amaterno character varying(20) NOT NULL
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- Name: cita ncita; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita ALTER COLUMN ncita SET DEFAULT nextval('public.cita_ncita_seq'::regclass);


--
-- Data for Name: cita; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cita (ncita, motivo, day, month, year, turno, docid, code, idlist) FROM stdin;
1	RevisiÃ³n dental	5	10	2023	1	46785412	20201101	1
2	Limpieza dental	7	11	2023	2	57321456	20201102	2
3	ExtracciÃ³n de muela	15	9	2023	3	45678901	20201103	3
4	Control ortodoncia	20	12	2023	1	48753219	20201104	4
5	Empaste dental	25	10	2023	2	65432178	20201105	5
6	RevisiÃ³n dental	8	11	2023	3	47125836	20201106	6
7	Limpieza dental	13	9	2023	1	49876354	20201107	7
8	Control ortodoncia	17	12	2023	2	59761234	20201108	8
9	Empaste dental	22	10	2023	3	47658123	20201109	9
10	RevisiÃ³n dental	30	11	2023	1	51239874	20201110	10
\.


--
-- Data for Name: eodonto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eodonto (code, email, name, apaterno, amaterno) FROM stdin;
20201101	andres.garcia1@ucsm.edu.pe	Andres	Garcia	Torres
20201102	sofia.martinez2@ucsm.edu.pe	Sofia	Martinez	Vidal
20201103	carlos.rodriguez1@ucsm.edu.pe	Carlos	Rodriguez	Perez
20201104	ana.gomez2@ucsm.edu.pe	Ana	Gomez	Lopez
20201105	juan.fernandez1@ucsm.edu.pe	Juan	Fernandez	Soto
20201106	maria.ramos2@ucsm.edu.pe	Maria	Ramos	Gonzalez
20201107	pablo.ramirez1@ucsm.edu.pe	Pablo	Ramirez	Mora
20201108	laura.suarez2@ucsm.edu.pe	Laura	Suarez	Castro
20201109	javier.torres1@ucsm.edu.pe	Javier	Torres	Molina
20201110	veronica.diaz2@ucsm.edu.pe	Veronica	Diaz	Navarro
20201111	nom.11@ucsm.edu.pe	nom11	apaterno11	amaterno11
20201112	nom.12@ucsm.edu.pe	nom12	apaterno12	amaterno12
20201113	nom.13@ucsm.edu.pe	nom13	apaterno13	amaterno13
20201114	nom.14@ucsm.edu.pe	nom14	apaterno14	amaterno14
20201115	nom.15@ucsm.edu.pe	nom15	apaterno15	amaterno15
20201116	nom.16@ucsm.edu.pe	nom16	apaterno16	amaterno16
20201117	nom.17@ucsm.edu.pe	nom17	apaterno17	amaterno17
20201118	nom.18@ucsm.edu.pe	nom18	apaterno18	amaterno18
20201119	nom.19@ucsm.edu.pe	nom19	apaterno19	amaterno19
20201120	nom.20@ucsm.edu.pe	nom20	apaterno20	amaterno20
\.


--
-- Data for Name: eport; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eport (idport, nombre, apaterno, amaterno, idlista) FROM stdin;
1	Carlos	Perez	Gomez	1
2	Ana	Lopez	Martinez	2
3	Pedro	Garcia	Hernandez	3
4	Luis	Ramirez	Diaz	4
5	Maria	Fernandez	Sanchez	5
6	Sofia	Vega	Flores	6
7	Raul	Soto	Cruz	7
8	Daniel	Rojas	PeÃ±a	8
9	Isabel	Morales	Vargas	9
10	Jorge	Rivera	Ortiz	10
11	nom11	apat11	amat11	11
12	nom12	apat12	amat12	12
13	nom13	apat13	amat13	13
14	nom14	apat14	amat14	14
15	nom15	apat15	amat15	15
16	nom16	apat16	amat16	16
17	nom17	apat17	amat17	17
18	nom18	apat18	amat18	18
19	nom19	apat19	amat19	19
20	nom20	apat20	amat20	20
\.


--
-- Data for Name: listc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.listc (id, day, month, year) FROM stdin;
1	5	10	2023
2	7	11	2023
3	15	9	2023
4	20	12	2023
5	25	10	2023
6	8	11	2023
7	13	9	2023
8	17	12	2023
9	22	10	2023
10	30	11	2023
11	6	10	2023
12	9	11	2023
13	20	9	2023
14	24	12	2023
15	26	10	2023
16	9	11	2023
17	19	9	2023
18	16	12	2023
19	29	10	2023
20	30	11	2023
\.


--
-- Data for Name: ncones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ncones (code, nro) FROM stdin;
20201101	987456122
20201102	946512231
20201103	997845122
20201104	968979124
20201105	987153215
20201106	968548595
20201107	987456413
20201109	987851941
20201109	987465132
20201110	99841551
20201111	968594974
20201112	928917235
20201113	969871321
20201114	936969172
20201115	978894323
20201116	981651712
20201117	987165123
20201118	986517913
20201119	987984561
20201120	999946516
\.


--
-- Data for Name: nconpa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nconpa (docid, nro) FROM stdin;
46785412	959554888
57321456	954789435
45678901	967961567
48753219	926158799
65432178	936153264
47125836	998741532
49876354	985654132
59761234	989901568
47658123	923654889
51239874	97846542
18599511	289165136
78541212	198789456
78894513	987456415
84569814	897945645
45789815	987123546
87945616	987564587
78945817	987565321
45789518	987135478
12389719	959284165
12348920	939392825
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (docid, email, name, apaterno, amaterno) FROM stdin;
46785412	cristian.mendez@gmail.com	Cristian	Mendez	Gutierrez
57321456	lucia.mendoza@gmail.com	Lucia	Mendoza	Alvarez
45678901	marcos.rojas@gmail.com	Marcos	Rojas	PeÃ±a
48753219	elena.vargas@gmail.com	Elena	Vargas	Ortiz
65432178	jose.rivera@gmail.com	Jose	Rivera	Ruiz
47125836	laura.ramirez@gmail.com	Laura	Ramirez	Lopez
49876354	felipe.martinez@gmail.com	Felipe	Martinez	Diaz
59761234	gabriela.gomez@gmail.com	Gabriela	Gomez	Fernandez
47658123	diego.perez@gmail.com	Diego	Perez	Sanchez
51239874	camila.moreno@gmail.com	Camila	Moreno	Vega
18599511	nom.11@hotmail.com	nom11	apat11	amat11
78541212	nom.12@hotmail.com	nom12	apat12	amat12
78894513	nom.13@hotmail.com	nom13	apat13	amat13
84569814	nom.14@hotmail.com	nom14	apat14	amat14
45789815	nom.15@hotmail.com	nom15	apat15	amat15
87945616	nom.16@hotmail.com	nom16	apat16	amat16
78945817	nom.17@hotmail.com	nom17	apat17	amat17
45789518	nom.18@hotmail.com	nom18	apat18	amat18
12389719	nom.19@hotmail.com	nom19	apat19	amat19
12348920	nom.20@hotmail.com	nom20	apat20	amat20
\.


--
-- Name: cita_ncita_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cita_ncita_seq', 1, false);


--
-- Name: cita cita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (ncita);


--
-- Name: eodonto eodonto_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eodonto
    ADD CONSTRAINT eodonto_email_key UNIQUE (email);


--
-- Name: eodonto eodonto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eodonto
    ADD CONSTRAINT eodonto_pkey PRIMARY KEY (code);


--
-- Name: eport eport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eport
    ADD CONSTRAINT eport_pkey PRIMARY KEY (idport);


--
-- Name: listc listc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.listc
    ADD CONSTRAINT listc_pkey PRIMARY KEY (id);


--
-- Name: ncones ncones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ncones
    ADD CONSTRAINT ncones_pkey PRIMARY KEY (code, nro);


--
-- Name: nconpa nconpa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nconpa
    ADD CONSTRAINT nconpa_pkey PRIMARY KEY (docid, nro);


--
-- Name: paciente paciente_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_email_key UNIQUE (email);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (docid);


--
-- Name: index_citas_anual; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_citas_anual ON public.cita USING btree (year);


--
-- Name: index_citas_diario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_citas_diario ON public.cita USING btree (day, month, year);


--
-- Name: index_citas_mensual; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_citas_mensual ON public.cita USING btree (month, year);


--
-- Name: index_edocita; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_edocita ON public.cita USING btree (code);


--
-- Name: index_pacita; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_pacita ON public.cita USING btree (docid);


--
-- Name: cita cita_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_code_fkey FOREIGN KEY (code) REFERENCES public.eodonto(code);


--
-- Name: cita cita_docid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_docid_fkey FOREIGN KEY (docid) REFERENCES public.paciente(docid);


--
-- Name: cita cita_idlist_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_idlist_fkey FOREIGN KEY (idlist) REFERENCES public.listc(id);


--
-- Name: eport eport_idlista_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eport
    ADD CONSTRAINT eport_idlista_fkey FOREIGN KEY (idlista) REFERENCES public.listc(id);


--
-- Name: ncones ncones_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ncones
    ADD CONSTRAINT ncones_code_fkey FOREIGN KEY (code) REFERENCES public.eodonto(code);


--
-- Name: nconpa nconpa_docid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nconpa
    ADD CONSTRAINT nconpa_docid_fkey FOREIGN KEY (docid) REFERENCES public.paciente(docid);


--
-- PostgreSQL database dump complete
--

