package br.com.consultweb.model.servico.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.model.parametros.spec.LogOperacaoModel;
import br.com.consultweb.model.servico.spec.NaturezaInclusaoModel;
import br.com.consultweb.repository.servico.spec.NaturezaInclusaoRepository;

@Stateless(name = "naturezaInclusaoModel")
public class NaturezaInclusaoModelImpl implements NaturezaInclusaoModel {

	@EJB
	private NaturezaInclusaoRepository naturezaInclusaoRepository;

	@EJB
	private LogOperacaoModel logOperacaoModel;
	
	@Override
	public void create(NaturezaInclusao c) {
		naturezaInclusaoRepository.create(c);
	}

	@Override
	public NaturezaInclusao retrieve(Long id) {
		return naturezaInclusaoRepository.retrieve(id);
	}

	@Override
	public NaturezaInclusao update(NaturezaInclusao c) {
		return naturezaInclusaoRepository.update(c);
	}

	@Override
	public NaturezaInclusao update(NaturezaInclusao c, Operador o)
			throws Exception {

		/* Inserindo Log */
		LogOperacao logOperacao = new LogOperacao();
		logOperacao.setDescricao(c.toString());
		if(c.getId() == null || c.getId() <= 0) {
			logOperacao.setOperacao("INSERIR");
		} else {
			logOperacao.setOperacao("ALTERAR");
		}
		logOperacao.setOperador(o);
		logOperacao = logOperacaoModel.update(logOperacao);
		
		return this.update(c);
	}
	
	@Override
	public void delete(NaturezaInclusao c) {
		
		/* Attach the bean */
		c = naturezaInclusaoRepository.update(c);
		
		/* Remove */
		naturezaInclusaoRepository.delete(c);
	}

	@Override
	public void delete(NaturezaInclusao c, Operador o) throws Exception {
	
		/* Inserindo Log */
		LogOperacao logOperacao = new LogOperacao();
		logOperacao.setDescricao(c.toString());
		logOperacao.setOperacao("EXCLUIR");
		logOperacao.setOperador(o);
		logOperacao = logOperacaoModel.update(logOperacao);
		
		this.delete(c);
	}
	
	@Override
	public void refresh(NaturezaInclusao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NaturezaInclusao> getAll() {
		return naturezaInclusaoRepository.getAll();
	}

	@Override
	public List<NaturezaInclusao> getNaturezaInclusaoPorDescricao(
			String descricao) {
		return naturezaInclusaoRepository.getNaturezaInclusaoPorDescricao(descricao);
	}

}
