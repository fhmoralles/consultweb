package br.com.consultweb.repository.servico.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.domain.servico.resumo.MovimentacoesProtocolosDia;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "protocoloRepository")
public class ProtocoloRepositoryImpl extends
		AbstractConsultWebRepository<Protocolo> implements ProtocoloRepository {

    @Resource(name = "ConsultWebDS", mappedName = "java:/ConsultWebDS")
    private DataSource dataSource;
	
    
	public ProtocoloRepositoryImpl() {
		super(Protocolo.class);
	}

	@Override
	public MovimentacoesProtocolosDia getMovimentacoesProtocolosDia(Date dataMovimento) throws SQLException {

		MovimentacoesProtocolosDia movimentacoesProtocolosDia = null;
		
		Connection conn = dataSource.getConnection();
		java.sql.Date dataSqlMov = new java.sql.Date(dataMovimento.getTime());
		PreparedStatement ps = conn.prepareStatement("select count(id_restricao) \"restricoes\", count(id_consulta) \"consultas\", count(id_exclusao) \"exclusoes\" from protocolo where date(datageracao) = ?");
		ps.setDate(1, dataSqlMov);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			movimentacoesProtocolosDia = new MovimentacoesProtocolosDia(rs.getInt("restricoes"), rs.getInt("consultas"), rs.getInt("exclusoes"));
		}
		
		return movimentacoesProtocolosDia;
	}

}
