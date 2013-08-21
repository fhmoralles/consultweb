package br.com.consultweb.view.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.webutils.MessageUtil;

@Named
@SessionScoped
public class UserSettingsUI implements Serializable {
    
    private static final long serialVersionUID = 1;
    
    private static final List<SelectItem> langItems;
    private String lang = "pt_BR";
    
    private String title;
    
    static {
		langItems = new ArrayList<SelectItem>();
		langItems.add(new SelectItem("pt_BR", "Portugues (Brasil)"));
    }
    
    public List<SelectItem> getLangItems() {
    	return langItems;
    }
    
    public String getLang() {
    	return lang;
    }
    
    public void setLang(String lang) {
    	this.lang = lang;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
    	return MessageUtil.getMessage("layout.title");
    }
    
    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
    	this.title = title;
    }
    
}
