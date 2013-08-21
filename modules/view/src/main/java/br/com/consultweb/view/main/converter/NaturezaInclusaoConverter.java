package br.com.consultweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.consultweb.domain.servico.NaturezaInclusao;

@FacesConverter(value = "naturezaInclusaoConverter", forClass = NaturezaInclusao.class)
public class NaturezaInclusaoConverter extends
		AbstractEntityConverter<NaturezaInclusao> {

	public NaturezaInclusaoConverter() {
		super(NaturezaInclusao.class);
	}

}
