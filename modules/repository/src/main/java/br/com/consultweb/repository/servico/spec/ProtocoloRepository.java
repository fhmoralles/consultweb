package br.com.consultweb.repository.servico.spec;

import java.util.Calendar;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.libutils.jpa.Repository;

@Local
public interface ProtocoloRepository extends Repository<Protocolo> {
	
	Integer getQtdeProtocolosConsultaDiaAnterior(Calendar dataResumo);

	Integer getQtdeProtocolosRestricaoDiaAnterior(Calendar dataResumo);

	Integer getQtdeProtocolosExclusaoDiaAnterior(Calendar dataResumo);
	
}
