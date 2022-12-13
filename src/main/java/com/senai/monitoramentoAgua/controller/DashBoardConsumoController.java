package com.senai.monitoramentoAgua.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.senai.monitoramentoAgua.Service.ConsumoService;
import com.senai.monitoramentoAgua.model.UnidadeConsumidora;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;

@Named
@ViewScoped
public class DashBoardConsumoController implements Serializable{

	private static final long serialVersionUID = 1L;

	private UnidadeConsumidora unidadeConsumidora;
		
	private BarChartModel barModelConsumoUltimoAno;
	
	private LineChartModel lineModelConsumoDiario;
	
	private LocalDate dataAtual;
	
	private String mesConsulta;
	
	private Double consumoTotalUltimoAno;
	
	private Double consumoTotalMesPesquisa;
	
	private Double consumoDataAtual;
	
	@Inject
	private ConsumoService consumoService;

	@PostConstruct
	public void init() throws IOException {
		this.unidadeConsumidora = (UnidadeConsumidora) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("unidade");
		if(Objects.isNull(unidadeConsumidora)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("unidades-consumidoras.xhtml");
		}
		dataAtual = LocalDate.now();
		consumoDataAtual = consumoService.getConsumoData(unidadeConsumidora.getId(), dataAtual);
		createLineModelConsumoDiario(dataAtual);
		createBarModelConsumoUltimoAno();
	}
	
	public void pesquisarConsumoMes() {
		consumoTotalMesPesquisa = 0.0;
		String [] mesEAno = mesConsulta.split("/");
		int mes = Integer.parseInt(mesEAno[0]);
		int ano = Integer.parseInt(mesEAno[1]);
		
		if(mes < 1 || mes > 12) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mes inválido!", null));
			return;
		}
		
		if(ano < 2015 || ano > dataAtual.getYear()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ano inválido!", null));
			return;
		}
		
		LocalDate dataPesquisa = LocalDate.of(ano, mes, 1);
		
		consumoTotalMesPesquisa = consumoService.getConsumoTotalPorMesEAno(unidadeConsumidora.getId(), dataPesquisa);
		
		createLineModelConsumoDiario(dataPesquisa);
	}
	
	private void createLineModelConsumoDiario(LocalDate mesPesquisa) {
		lineModelConsumoDiario = new LineChartModel();
		ChartData data = new ChartData();
		
        LineChartDataSet dataSet = new LineChartDataSet();
		int comprimentoDoMes = mesPesquisa.getMonth().maxLength();
        
        List<Object> valores = consumoService.getConsumoDiario(unidadeConsumidora.getId(), mesPesquisa);
        List<String> labels = new ArrayList<>();
        for(int i = 0; i<comprimentoDoMes; i++) {
        	labels.add(Integer.toString(i+1));
        }
        
        dataSet.setData(valores);
        dataSet.setLabel("Consumo (m³)");
        dataSet.setBorderColor("rgb(75, 192, 192)");

        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        
        lineModelConsumoDiario.setData(data);
	}


	private void createBarModelConsumoUltimoAno() {
		consumoTotalUltimoAno = 0.0;
		barModelConsumoUltimoAno = new BarChartModel();
		ChartData data = new ChartData();
		
		LocalDate dataAtual;
		
		List<Number> valores = new ArrayList<>();
		for(int i = 12; i > 0; i--) {
			dataAtual = this.dataAtual;
			Double consumo = consumoService.getConsumoTotalPorMesEAno(unidadeConsumidora.getId(), dataAtual.minusMonths(i));
			consumoTotalUltimoAno += consumo;
			valores.add(consumo);
		}
		
		List<String> labels = new ArrayList<>();
		for(int i = 12; i > 0; i--) {
			dataAtual = this.dataAtual;
			String mes = dataAtual.minusMonths(i).getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
			mes += " " + dataAtual.minusMonths(i).getYear();
			labels.add(mes);
		}
	
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
        bgColor.add("rgba(25, 179, 218, 1)");
		
		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Consumo (m³)");
		barDataSet.setData(valores);
		barDataSet.setBackgroundColor(bgColor);
		data.addChartDataSet(barDataSet);
		data.setLabels(labels);
		barModelConsumoUltimoAno.setData(data);
		
	}





	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public BarChartModel getBarModelConsumoUltimoAno() {
		return barModelConsumoUltimoAno;
	}
	
	public void setBarModelConsumoUltimoAno(BarChartModel barModelConsumoUltimoAno) {
		this.barModelConsumoUltimoAno = barModelConsumoUltimoAno;
	}

	public LineChartModel getLineModelConsumoDiario() {
		return lineModelConsumoDiario;
	}

	public void setLineModelConsumoDiario(LineChartModel lineModelConsumoDiario) {
		this.lineModelConsumoDiario = lineModelConsumoDiario;
	}

	public String getMesConsulta() {
		return mesConsulta;
	}

	public void setMesConsulta(String mesConsulta) {
		this.mesConsulta = mesConsulta;
	}

	public Double getConsumoTotalUltimoAno() {
		return consumoTotalUltimoAno;
	}

	public void setConsumoTotalUltimoAno(Double consumoTotalUltimoAno) {
		this.consumoTotalUltimoAno = consumoTotalUltimoAno;
	}

	public Double getConsumoTotalMesPesquisa() {
		return consumoTotalMesPesquisa;
	}

	public void setConsumoTotalMesPesquisa(Double consumoTotalMesPesquisa) {
		this.consumoTotalMesPesquisa = consumoTotalMesPesquisa;
	}

	public Double getConsumoDataAtual() {
		return consumoDataAtual;
	}

	public void setConsumoDataAtual(Double consumoDataAtual) {
		this.consumoDataAtual = consumoDataAtual;
	}

	public LocalDate getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	
}
