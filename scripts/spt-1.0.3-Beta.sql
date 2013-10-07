--06/10/2013
------------
--Inclusao tabela de emails
CREATE TABLE public.emailevento
(
   id bigint NOT NULL DEFAULT nextval('identificador'), 
   email character varying(1000) NOT NULL, 
   evento character varying(255) NOT NULL, 
   CONSTRAINT pk_emailseventos PRIMARY KEY (id), 
   CONSTRAINT uk_email_evento UNIQUE (email, evento)
);
--Apargar tabela criada anteriormente
drop table emails;
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.3-Beta');
