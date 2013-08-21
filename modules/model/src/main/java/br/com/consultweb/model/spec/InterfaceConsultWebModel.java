package br.com.consultweb.model.spec;

import java.io.Serializable;

public interface InterfaceConsultWebModel<T extends Serializable> {
	
	void create(T c);

	T retrieve(Long id);

	T update(T c) throws Exception;

	void delete(T c);

	void refresh(T c);	
	
}
