--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Ubuntu 16.2-1.pgdg22.04+1)
-- Dumped by pg_dump version 16.2 (Ubuntu 16.2-1.pgdg22.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.card (
    id integer NOT NULL,
    buyer_id integer,
    card_no character varying(255),
    card_type character varying(255),
    cvv integer,
    expiry timestamp(6) without time zone,
    CONSTRAINT card_card_type_check CHECK (((card_type)::text = ANY ((ARRAY['VISA'::character varying, 'MASTERCARD'::character varying, 'RUPAY'::character varying])::text[])))
);


ALTER TABLE public.card OWNER TO postgres;

--
-- Name: card_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.card_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.card_id_seq OWNER TO postgres;

--
-- Name: card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.card_id_seq OWNED BY public.card.id;


--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    id integer NOT NULL,
    buyer_id integer,
    date_added timestamp(6) without time zone,
    total_amount integer
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cart_id_seq OWNER TO postgres;

--
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;


--
-- Name: cart_item_ids; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart_item_ids (
    cart_id integer NOT NULL,
    item_ids integer
);


ALTER TABLE public.cart_item_ids OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    address character varying(255),
    age integer,
    cart_id integer,
    date_joined timestamp(6) without time zone,
    email character varying(255),
    gender character varying(255),
    name character varying(255),
    notification boolean,
    password character varying(255),
    phone_number character varying(255),
    username character varying(255)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id integer NOT NULL,
    cart_id integer,
    date_added timestamp(6) without time zone,
    ordered_id integer,
    price double precision,
    product_id integer,
    quantity integer
);


ALTER TABLE public.item OWNER TO postgres;

--
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_id_seq OWNER TO postgres;

--
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;


--
-- Name: ordered; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordered (
    id integer NOT NULL,
    buyer_id integer,
    delivery_charge double precision,
    delivery_date timestamp(6) without time zone,
    order_date timestamp(6) without time zone,
    payment_mode smallint,
    payment_status character varying(255),
    quantity integer,
    shipping_address character varying(255),
    total_cost double precision,
    CONSTRAINT ordered_payment_mode_check CHECK (((payment_mode >= 0) AND (payment_mode <= 2)))
);


ALTER TABLE public.ordered OWNER TO postgres;

--
-- Name: ordered_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordered_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ordered_id_seq OWNER TO postgres;

--
-- Name: ordered_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordered_id_seq OWNED BY public.ordered.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    category character varying(255),
    date_added timestamp(6) without time zone,
    description character varying(255),
    image bytea,
    item_id integer,
    name character varying(255),
    price double precision,
    product_status character varying(255),
    rating integer,
    seller_id integer,
    stock integer,
    sub_category bytea,
    views integer,
    CONSTRAINT product_category_check CHECK (((category)::text = ANY ((ARRAY['SPORTS'::character varying, 'FASHION'::character varying, 'ELECTRONICS'::character varying])::text[]))),
    CONSTRAINT product_product_status_check CHECK (((product_status)::text = ANY ((ARRAY['AVAILABLE'::character varying, 'OUT_OF_STOCK'::character varying])::text[])))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: seller; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seller (
    id integer NOT NULL,
    address character varying(255),
    age integer,
    date_joined timestamp(6) without time zone,
    email character varying(255),
    gender character varying(255),
    name character varying(255),
    password character varying(255),
    phone_number character varying(255),
    rating integer,
    username character varying(255)
);


ALTER TABLE public.seller OWNER TO postgres;

--
-- Name: seller_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seller_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seller_id_seq OWNER TO postgres;

--
-- Name: seller_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seller_id_seq OWNED BY public.seller.id;


--
-- Name: card id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card ALTER COLUMN id SET DEFAULT nextval('public.card_id_seq'::regclass);


--
-- Name: cart id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);


--
-- Name: ordered id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered ALTER COLUMN id SET DEFAULT nextval('public.ordered_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: seller id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller ALTER COLUMN id SET DEFAULT nextval('public.seller_id_seq'::regclass);


--
-- Name: card card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pkey PRIMARY KEY (id);


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- Name: ordered ordered_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered
    ADD CONSTRAINT ordered_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: seller seller_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT seller_pkey PRIMARY KEY (id);


--
-- Name: card uk_2gifx4rwq94tfkasgtoaxpdah; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT uk_2gifx4rwq94tfkasgtoaxpdah UNIQUE (card_no);


--
-- Name: seller uk_3gnjncn8l4no25wl7pyjqrx3p; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT uk_3gnjncn8l4no25wl7pyjqrx3p UNIQUE (username);


--
-- Name: seller uk_3slg2gbtwwcmki7wa5j4k5107; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT uk_3slg2gbtwwcmki7wa5j4k5107 UNIQUE (phone_number);


--
-- Name: seller uk_crgbovyy4gvgsum2yyb3fbfn7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT uk_crgbovyy4gvgsum2yyb3fbfn7 UNIQUE (email);


--
-- Name: customer uk_dwk6cx0afu8bs9o4t536v1j5v; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_dwk6cx0afu8bs9o4t536v1j5v UNIQUE (email);


--
-- Name: customer uk_irnrrncatp2fvw52vp45j7rlw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_irnrrncatp2fvw52vp45j7rlw UNIQUE (username);


--
-- Name: customer uk_rosd2guvs3i1agkplv5n8vu82; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_rosd2guvs3i1agkplv5n8vu82 UNIQUE (phone_number);


--
-- Name: customer_username_email_phonenumber; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX customer_username_email_phonenumber ON public.customer USING btree (username, email, phone_number);


--
-- Name: seller_username_email_phonenumber; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX seller_username_email_phonenumber ON public.seller USING btree (username, email, phone_number);


--
-- Name: cart_item_ids fk8dgfi3x8mtaid2rewcyyylwx2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item_ids
    ADD CONSTRAINT fk8dgfi3x8mtaid2rewcyyylwx2 FOREIGN KEY (cart_id) REFERENCES public.cart(id);


--
-- PostgreSQL database dump complete
--

