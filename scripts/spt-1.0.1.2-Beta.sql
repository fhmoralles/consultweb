--31/08/2013
------------
--Necessário para importação da ACISI nesse momento
CREATE TABLE associadoendereco
(
  id bigint NOT NULL,
  id_associado bigint NOT NULL,
  tipoendereco integer not null,
  cep character varying(10) NOT NULL DEFAULT ''::character varying,
  endereco character varying(255) NOT NULL DEFAULT ''::character varying,
  bairro character varying(100) NOT NULL DEFAULT ''::character varying,
  cidade character varying(50) NOT NULL DEFAULT ''::character varying,
  estado character varying(2) NOT NULL DEFAULT ''::character varying,
  complemento character varying(100) NOT NULL DEFAULT ''::character varying,
  telefone character varying(20) NOT NULL DEFAULT ''::character varying,
  fax character varying(20) NOT NULL DEFAULT ''::character varying,
  numero character varying(10) NOT NULL DEFAULT ''::character varying,
  CONSTRAINT pk_associadoendereco PRIMARY KEY (id),
  CONSTRAINT uk_associadoendereco_tipo UNIQUE (id_associado, tipoendereco)
);
--Registros das versões rodadas
CREATE TABLE versao
(
  id bigint NOT NULL,
  numero character varying(20) NOT NULL DEFAULT ''::character varying,
  CONSTRAINT pk_versao PRIMARY KEY (id)
);
--9 Digitos não cabem: Conta básica.
ALTER TABLE entidade ALTER COLUMN cep TYPE character varying(10);
--AssociadoEndereco com FK para Associado
ALTER TABLE associadoendereco
  ADD CONSTRAINT fk_associadoendereco_associado FOREIGN KEY (id_associado) REFERENCES associado (id) ON UPDATE CASCADE ON DELETE CASCADE;
-- Retirada a restrição do nome, pois pode fazer consulta de c.p.f. que não existe ainda
ALTER TABLE contraparte
   ALTER COLUMN nome DROP NOT NULL;
--Inciar o contador
SELECT setval('public.identificador', 3921, true);
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.1.2-Beta');
