--Entidade
insert into entidade (id, codigo, razaosocial, fantasia, cnpj) values ( 1, 1, 'ASSOCIACAO COML E INDL IVAIPORA', 'ASSOCIACAO COML E INDL IVAIPORA', '77.777.506/0001-36');
--Ramo de Atividade
insert into ramoatividade values ( 2, 'Ramo de Atividade' );
--Produtos
INSERT INTO produto VALUES (1, 1, 'Produto Consulta Padrão', 0, 0.00);
INSERT INTO produto VALUES (2, 2, 'Produto Inclusão', 1, 0.00);
INSERT INTO produto VALUES (3, 3, 'Produto Excluir', 2, 0.00);
INSERT INTO produtoconsulta (id, tipoconsulta, id_produto, tipoconsultaabrangencia) VALUES (1, 0, 1, 1);
-- Natureza Inclusao
insert into naturezainclusao (id, descricao) values (1, 'Comercio Varejista');
-- Parametros
insert into parametros (id, diasrestricao, id_naturezainclusao, anosvigencia, id_produtoincluir, id_produtoexcluir) values (1, 10, 1, 5, 2, 3);
-- Perfil
insert into perfil values (1, 'Completo', TRUE, TRUE, TRUE, TRUE);
--Associados
insert into associado (id, codigo, tipopessoa, cnpj, fantasia, razaosocial, status, id_entidade, id_ramoatividade) values (1, 1, 1, '77.777.506/0001-36', 'ASSOCIACAO COML E INDL IVAIPORA', 'ASSOCIACAO COML E INDL IVAIPORA', 0, 1, 2);
insert into associadoendereco (id, id_associado, tipoendereco, cep, endereco, bairro, cidade, estado, complemento, numero) values (1, 1, 0, '', '', '', 'IVAIPORA', 'PR', '', '');
insert into associadoproduto (id_associado, id_produto) values (1, 1),(1, 2),(1, 3);
insert into associadodispositivo (id, dispositivo, id_associado) values (3, 0, 1),(4, 1, 1);
insert into operador (id, codigo, nome, tipooperador, status, id_associado, id_perfil) values (5, 1, 'ASSOCIACAO COML E INDL IVAIPORA', 1, 0, 1, 1);
insert into operadordispositivo (id, senha, id_operador, id_associadodispositivo) values (6, '1234', 5, (select id from associadodispositivo where id_associado = 1 and dispositivo = 0)),(7, '1234', 5, (select id from associadodispositivo where id_associado = 1 and dispositivo = 1));
insert into operadorhorario (id, diasemana, horainicio, horatermino, id_operador) values (8, 1, 0, 24, 5),(9, 2, 0, 24, 5),(10, 3, 0, 24, 5),(11, 4, 0, 24, 5),(12, 5, 0, 24, 5),(13, 6, 0, 24, 5),(14, 7, 0, 24, 5);
