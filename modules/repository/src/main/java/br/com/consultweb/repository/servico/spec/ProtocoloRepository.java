package br.com.consultweb.repository.servico.spec;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.domain.servico.resumo.MovimentacoesProtocolosDia;
import br.com.libutils.jpa.Repository;

@Local
public interface ProtocoloRepository extends Repository<Protocolo> {
	
	MovimentacoesProtocolosDia getMovimentacoesProtocolosDia(Date dataMovimento) throws SQLException;
	
}
