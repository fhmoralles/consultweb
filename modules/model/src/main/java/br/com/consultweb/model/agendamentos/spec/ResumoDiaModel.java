package br.com.consultweb.model.agendamentos.spec;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Timer;

@Local
public interface ResumoDiaModel extends Serializable {
	
	void resumoServicosDia(Timer time);
	
}
