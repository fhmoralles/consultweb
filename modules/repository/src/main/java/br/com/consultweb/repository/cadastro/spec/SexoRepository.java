package br.com.consultweb.repository.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.Sexo;
import br.com.libutils.jpa.Repository;

@Local
public interface SexoRepository extends Repository<Sexo> {
	
	List<Sexo> getSexos();
	
}
