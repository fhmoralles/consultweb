package br.com.consultweb.view.main.security;

import org.picketlink.idm.impl.api.model.SimpleUser;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.types.TipoOperador;

public class ConsultUser extends SimpleUser {

    private static final long serialVersionUID = 1L;

    private final Operador operador;
    
    /* Permissões dos Menus de Cadastro */
    private Boolean menuCadastros;
    private Boolean subMenuEntidade;
    private Boolean subMenuAssociado;
    private Boolean subMenuContraparte;
    private Boolean subMenuProduto;
    private Boolean subMenuRamoAtividade;
    private Boolean subMenuRelatoriosCadastros;
    
    /* Permissões de Menus de Serviços */
    private Boolean menuServicos;
    private Boolean subMenuNaturezaInclusao;
    private Boolean subMenuConsultar;
    private Boolean subMenuIncluir;
    private Boolean subMenuExcluir;
    private Boolean subMenuCarta;
    private Boolean subMenuRelatoriosServicos;

    /* Permissões de Menus de Faturamento */
    private Boolean menuFaturamento;
    private Boolean subMenuFaturamentos;
    private Boolean subMenuRelatoriosFaturamento;
    
    /* Permissões de Menus de Auditoria */
    private Boolean menuAuditoria;
    private Boolean subMenuLogAuditoria;
    private Boolean subMenuProtocolo;
    private Boolean subMenuRelatoriosAuditoria;

    /* Permissões de Parametros */
    private Boolean menuParametros;
    private Boolean subMenuConfiguracoes;
    private Boolean subMenuOperadores;
    private Boolean subMenuPerfil;
    private Boolean subMenuRelatoriosParametros;
    
    
    public ConsultUser(Operador operador) {
        super(operador.getCodigo().toString());
        this.operador = operador;
        
        boolean operadorAdminEntidadeAssociado = ((operador.getTipoOperador() == TipoOperador.ENTIDADE) || (operador.getTipoOperador() == TipoOperador.ADMIN) || (operador.getTipoOperador() == TipoOperador.ASSOCIADO));
        boolean operadorAdminEntidade = ((operador.getTipoOperador() == TipoOperador.ENTIDADE) || (operador.getTipoOperador() == TipoOperador.ADMIN));
        boolean operadorAdmin = (operador.getTipoOperador() == TipoOperador.ADMIN);
        
        this.menuCadastros = operadorAdminEntidade;
        this.subMenuEntidade = operadorAdmin;
        this.subMenuAssociado = operadorAdminEntidade;
        this.subMenuContraparte = operadorAdmin;
        this.subMenuProduto = operadorAdmin;
        this.subMenuRamoAtividade = operadorAdminEntidade;
        this.subMenuRelatoriosCadastros = operadorAdminEntidade;

        this.menuServicos = operadorAdminEntidadeAssociado;
        this.subMenuNaturezaInclusao = operadorAdminEntidade;
        this.subMenuConsultar = (operador.getTipoOperador() == TipoOperador.ASSOCIADO) ? operador.getPerfil().getConsultar() : operadorAdminEntidade;
        this.subMenuIncluir = (operador.getTipoOperador() == TipoOperador.ASSOCIADO) ? operador.getPerfil().getIncluir() : operadorAdminEntidade;
        this.subMenuExcluir = (operador.getTipoOperador() == TipoOperador.ASSOCIADO) ? operador.getPerfil().getExcluir() : operadorAdminEntidade;
        this.subMenuCarta = (operador.getTipoOperador() == TipoOperador.ASSOCIADO) ? operador.getPerfil().getCarta() : operadorAdminEntidade;
        this.subMenuRelatoriosServicos = operadorAdminEntidade;
        
        this.menuFaturamento = operadorAdminEntidade;
        this.subMenuFaturamentos = operadorAdminEntidade;
        this.subMenuRelatoriosFaturamento = operadorAdminEntidade;

        this.menuAuditoria = operadorAdminEntidadeAssociado;
        this.subMenuLogAuditoria = operadorAdminEntidadeAssociado;
        this.subMenuProtocolo = operadorAdminEntidadeAssociado;
        this.subMenuRelatoriosAuditoria = operadorAdminEntidade;

        this.menuParametros = operadorAdminEntidade;
        this.subMenuConfiguracoes = operadorAdmin;
        this.subMenuOperadores = operadorAdminEntidade;
        this.subMenuPerfil = operadorAdminEntidade;
        this.subMenuRelatoriosParametros = operadorAdminEntidade;
        
    }

    public Operador getOperador() {
        return operador;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getMenuCadastros() {
		return menuCadastros;
	}

	public Boolean getSubMenuEntidade() {
		return subMenuEntidade;
	}

	public Boolean getSubMenuAssociado() {
		return subMenuAssociado;
	}

	public Boolean getSubMenuContraparte() {
		return subMenuContraparte;
	}

	public Boolean getSubMenuProduto() {
		return subMenuProduto;
	}

	public Boolean getSubMenuRelatoriosCadastros() {
		return subMenuRelatoriosCadastros;
	}

	public Boolean getMenuServicos() {
		return menuServicos;
	}

	public Boolean getSubMenuConsultar() {
		return subMenuConsultar;
	}

	public Boolean getSubMenuIncluir() {
		return subMenuIncluir;
	}

	public Boolean getSubMenuExcluir() {
		return subMenuExcluir;
	}

	public Boolean getSubMenuCarta() {
		return subMenuCarta;
	}

	public Boolean getSubMenuRelatoriosServicos() {
		return subMenuRelatoriosServicos;
	}

	public Boolean getMenuFaturamento() {
		return menuFaturamento;
	}

	public Boolean getSubMenuFaturamentos() {
		return subMenuFaturamentos;
	}

	public Boolean getSubMenuRelatoriosFaturamento() {
		return subMenuRelatoriosFaturamento;
	}

	public Boolean getMenuAuditoria() {
		return menuAuditoria;
	}

	public Boolean getSubMenuLogAuditoria() {
		return subMenuLogAuditoria;
	}

	public Boolean getSubMenuRelatoriosAuditoria() {
		return subMenuRelatoriosAuditoria;
	}

	public Boolean getMenuParametros() {
		return menuParametros;
	}

	public Boolean getSubMenuConfiguracoes() {
		return subMenuConfiguracoes;
	}

	public Boolean getSubMenuOperadores() {
		return subMenuOperadores;
	}

	public Boolean getSubMenuPerfil() {
		return subMenuPerfil;
	}

	public Boolean getSubMenuRelatoriosParametros() {
		return subMenuRelatoriosParametros;
	}

	public Boolean getSubMenuNaturezaInclusao() {
		return subMenuNaturezaInclusao;
	}

	public Boolean getSubMenuRamoAtividade() {
		return subMenuRamoAtividade;
	}

	public Boolean getSubMenuProtocolo() {
		return subMenuProtocolo;
	}
	

}
