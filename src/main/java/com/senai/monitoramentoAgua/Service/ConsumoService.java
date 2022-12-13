package com.senai.monitoramentoAgua.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.senai.monitoramentoAgua.dao.ConsumoDao;
import com.senai.monitoramentoAgua.model.Consumo;

@Stateless
public class ConsumoService {	
	
	@Inject
	private ConsumoDao consumoDao;
	
	public Double getConsumoTotalPorMesEAno(Long idUnidade, Long ano, Long mes){
		List<Consumo> listaConsumo = consumoDao.getConsumoPorMesEAno(idUnidade, ano, mes);
		
		Double consumoTotal = listaConsumo.stream()
					.mapToDouble(Consumo::getVolumeConsumo)
					.sum();
		
		return consumoTotal;
	}
	
	public Double getConsumoTotalPorMesEAno(Long idUnidade, LocalDate data) {
		return getConsumoTotalPorMesEAno(idUnidade, new Long(data.getYear()), new Long(data.getMonthValue()));
	}
	
	public Double getConsumoData(Long idUnidade, LocalDate localDate){
		List<Consumo> listaConsumo = consumoDao.getConsumoPorData(idUnidade, localDate);
		
		Double consumoTotal = listaConsumo.stream()
				.mapToDouble(Consumo::getVolumeConsumo)
				.sum();
		
		return consumoTotal;
	}
	
	public List<Object> getConsumoDiario(Long idUnidade, LocalDate data){
		
		Long ano = new Long(data.getYear());
		Long mes = new Long(data.getMonthValue());
		int comprimentoDoMes = data.getMonth().maxLength();
		
		List<Consumo> listaConsumo = consumoDao.getConsumoPorMesEAno(idUnidade, ano, mes);

		Double [] arr = new Double[comprimentoDoMes];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0.0;
		}
		
		listaConsumo.stream().forEach(p-> {
			for(int i = 0; i<comprimentoDoMes; i++) {
				if(p.getDataConsumo().getDayOfMonth() == i+1) {
					arr[i] += p.getVolumeConsumo();
					break;
				}
			}
		});
		
		List<Object> consumoDiario = Arrays.asList(arr);
		
		return consumoDiario;
	}
	
}
