package br.com.consultweb.view.servico.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.types.TipoPessoa;
import br.com.webutils.ui.AbstractFacesBean;
import br.com.webutils.ui.filter.Filter;

public class ConsultaFilter extends AbstractFacesBean implements Filter {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ConsultaFilter.class);
	
	/* Filtros */
	private Entidade entidade;
	private Boolean entidadeDisabled;

	private Associado associado;
	private Boolean associadoDisabled;

	private Produto produto;

	private TipoPessoa tipoPessoa;

	private Contraparte contraparte;
	private String cpfPattern;
	private String labelCpfPattern;
	private String labelContraparteNome;

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		this.setEntidade(null);
		this.setEntidadeDisabled(false);
		this.setAssociado(null);
		this.setAssociadoDisabled(false);
		this.setProduto(null);
		this.setTipoPessoa(null);
		this.setContraparte(null);
		
		this.setCpfPattern(StringUtils.EMPTY);
		this.setLabelContraparteNome(StringUtils.EMPTY);
		this.setLabelContraparteNome(StringUtils.EMPTY);
	}

	@Override
	public String getValidationMessage() {
		return StringUtils.EMPTY;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Contraparte getContraparte() {
		return contraparte;
	}

	public void setContraparte(Contraparte contraparte) {
		this.contraparte = contraparte;
	}

	public Boolean getEntidadeDisabled() {
		return entidadeDisabled;
	}

	public void setEntidadeDisabled(Boolean entidadeDisabled) {
		this.entidadeDisabled = entidadeDisabled;
	}

	public Boolean getAssociadoDisabled() {
		return associadoDisabled;
	}

	public void setAssociadoDisabled(Boolean associadoDisabled) {
		this.associadoDisabled = associadoDisabled;
	}

	public String getCpfPattern() {
		return cpfPattern;
	}

	public void setCpfPattern(String cpfPattern) {
		this.cpfPattern = cpfPattern;
	}

	public String getLabelCpfPattern() {
		return labelCpfPattern;
	}

	public void setLabelCpfPattern(String labelCpfPattern) {
		this.labelCpfPattern = labelCpfPattern;
	}

	public String getLabelContraparteNome() {
		return labelContraparteNome;
	}

	public void setLabelContraparteNome(String labelContraparteNome) {
		this.labelContraparteNome = labelContraparteNome;
	}

}
