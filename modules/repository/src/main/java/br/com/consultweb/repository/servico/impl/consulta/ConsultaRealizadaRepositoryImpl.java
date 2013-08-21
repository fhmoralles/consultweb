package br.com.consultweb.repository.servico.impl.consulta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaRealizadaRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "consultaRealizadaRepository")
public class ConsultaRealizadaRepositoryImpl extends
		AbstractConsultWebRepository<ConsultaRealizada> implements
		ConsultaRealizadaRepository {

	protected ConsultaRealizadaRepositoryImpl() {
		super(ConsultaRealizada.class);
	}

	@Override
	public List<ConsultaRealizada> gerarConsultaRealizadasPadrao(
			ConsultaTipo consultaTipo) {

		List<ConsultaRealizada> consultaRealizadas = new ArrayList<ConsultaRealizada>();

		/* Gerar data de Inicio e Fim */
		Calendar dataFim = Calendar.getInstance();
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.add(Calendar.DATE, -90);
		

		/* Gerar as consultas realizadas no período de 90 dias */
		Query query = getEntityManager().createNamedQuery("queryConsultas");
		query.setParameter("cpf", consultaTipo.getConsulta().getContraparte()
				.getCpf());
		query.setParameter("dataInicio", dataInicio.getTime());
		query.setParameter("dataFim", dataFim.getTime());

		@SuppressWarnings(value = "unchecked")
		List<Consulta> queryConsultas = query.getResultList();
		
		if (queryConsultas.size() > 0) {
			
			int indice = 1;
			Iterator<Consulta> ite = queryConsultas.iterator();
			
			while(ite.hasNext()) {
				
				Consulta consulta = ite.next();
				
				ConsultaRealizada consultaRealizada = new ConsultaRealizada();
				consultaRealizada.setIndice(indice++);
				consultaRealizada.setDataConsulta(consulta.getDataConsulta());
				consultaRealizada.setCredor(consulta.getAssociado().getRazaoSocial());
				consultaRealizada.setLocalidade("");
				consultaRealizada.setEntidade(consulta.getAssociado().getEntidade().getRazaoSocial());
				consultaRealizada.setConsultaTipo(consultaTipo);
				consultaRealizadas.add(consultaRealizada);
				
			}
			
		}

		return consultaRealizadas;
	}

}
