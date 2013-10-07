--08/09/2013
------------
--Inclusao de log de operacao
CREATE TABLE public.logoperacao
(
   id bigint NOT NULL, 
   id_operador bigint, 
   operacao character varying(255), 
   descricao character varying(5000), 
   CONSTRAINT pk_logoperacao PRIMARY KEY (id)
); 
-- Inclusao do dispositivo no protocolo
ALTER TABLE protocolo
  ADD COLUMN dispositivo integer;
-- Emails para resumo
CREATE TABLE emails
(
  id bigint NOT NULL,
  evento character varying(255),
  email character varying(255),
  CONSTRAINT pk_emails PRIMARY KEY (id)
);
-- Inclusao de hora do Log
ALTER TABLE logoperacao
  ADD COLUMN datahoraoperacao timestamp default now();
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.2.3-Beta');
