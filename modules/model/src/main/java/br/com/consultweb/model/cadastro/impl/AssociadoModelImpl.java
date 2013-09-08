package br.com.consultweb.model.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.types.TipoProduto;
import br.com.consultweb.model.cadastro.spec.AssociadoModel;
import br.com.consultweb.repository.cadastro.spec.AssociadoRepository;

@Stateless(name = "associadoModel")
public class AssociadoModelImpl implements AssociadoModel {

	@EJB
	private AssociadoRepository associadoRepository;

	@Override
	public void create(Associado c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Associado retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Associado update(Associado c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Associado c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Associado c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Associado localizarPorAssociadoEEntidade(Integer codigoAssociado, Entidade entidade) {
		return associadoRepository.localizarPorAssociadoEEntidade(codigoAssociado, entidade);
	}

	@Override
	public List<Produto> getConsultasAssociado(Associado associado) {

		List<Produto> consultas = new ArrayList<Produto>();
		
		if(associado != null && associado.getId() != null) {
			
			/* Merge para colocar no estado Persisted */
			associado = associadoRepository.update(associado);
			
			if(associado.getProdutos() != null) {
				for(Produto aux : associado.getProdutos()) {
					if(aux.getTipoProduto() == TipoProduto.CONSULTA) {
						consultas.add(aux);
					}
				}
			}
		}
		
		return consultas;
	}

	@Override
	public List<Associado> getAssociadosPorFantasia(String fantasia) {
		return associadoRepository.getAssociadosPorFantasia(fantasia);
	}

	
}
