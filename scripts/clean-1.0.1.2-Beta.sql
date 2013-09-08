--Delete das tabelas
delete from protocolo;
delete from exclusao;
delete from consulta;
delete from restricao;
delete from faturamento;
delete from operador;
delete from associado;
delete from parametros;
delete from produto;
delete from ramoatividade;
delete from perfil;
delete from naturezainclusao;
delete from entidade;
delete from contraparte;
--Inciar o contador
SELECT setval('public.identificador', 1, true);