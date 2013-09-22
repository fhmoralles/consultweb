package br.com.consultweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.libutils.jpa.Repository;

@Local
public interface LogOperacaoRepository extends Repository<LogOperacao> {

}
