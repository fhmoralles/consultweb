package br.com.consultweb.repository.cadastro.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.libutils.jpa.Repository;

@Local
public interface ContraparteRepository extends Repository<Contraparte> {

	public Contraparte retrieveByCpf(String cpf);
	
}
