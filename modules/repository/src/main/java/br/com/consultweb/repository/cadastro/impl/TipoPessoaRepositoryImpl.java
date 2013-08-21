package br.com.consultweb.repository.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.types.TipoPessoa;
import br.com.consultweb.repository.cadastro.spec.TipoPessoaRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "tipoPessoaRepository")
public class TipoPessoaRepositoryImpl extends
		AbstractConsultWebRepository<TipoPessoa> implements
		TipoPessoaRepository {

	public TipoPessoaRepositoryImpl() {
		super(TipoPessoa.class);
	}

	@Override
	public List<TipoPessoa> getTiposPessoa() {

		List<TipoPessoa> tiposPessoas = new ArrayList<TipoPessoa>();
		
		for(TipoPessoa tipoPessoa : TipoPessoa.values() ) {
			tiposPessoas.add(tipoPessoa);
		}
		
		return tiposPessoas;

	}

	
}
