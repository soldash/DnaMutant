CREATE TABLE public.dna_log_model
(
    dna text COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL DEFAULT nextval('"DNA_LOG_MODEL_ID_seq"'::regclass),
    mutant boolean NOT NULL,
    CONSTRAINT "DNA_LOG_MODEL_pkey" PRIMARY KEY (id)
)


ALTER TABLE public.dna_log_model
    OWNER to admin;
