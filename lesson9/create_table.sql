CREATE TABLE public.t_user
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    age integer NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.t_user
    OWNER to postgres;

GRANT INSERT, SELECT, UPDATE ON TABLE public.t_user TO otus;


GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO otus;