package com.senai.monitoramentoAgua.Service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.senai.monitoramentoAgua.dao.UnidadeConsumidoraDao;
import com.senai.monitoramentoAgua.model.UnidadeConsumidora;

@Stateless
public class UnidadeConsumidoraService {

	@Inject
	private UnidadeConsumidoraDao unidadeConsumidoraDao;
	
	public void incluirUnidade(UnidadeConsumidora unidadeConsumidora) {
		unidadeConsumidoraDao.cadastrar(unidadeConsumidora);
	}
	
	public void atualizarUnidade(Long idUnidade, UnidadeConsumidora unidadeConsumidora) {
		unidadeConsumidora.setId(idUnidade);
		unidadeConsumidoraDao.atualizar(unidadeConsumidora);
	}
	
	public void excluirUnidade(Long idUnidade) {
		unidadeConsumidoraDao.excluir(unidadeConsumidoraDao.buscarPorId(idUnidade));
	}
	
	public List<UnidadeConsumidora> getUnidadesByIdUsuario(Long idUsuario){
		return unidadeConsumidoraDao.getUnidadesByIdUsuario(idUsuario);
	}
	
}
