package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.CargoDAOMike;
import br.edu.faculdadedelta.modelo.CargoMike;
import br.edu.faculdadedelta.util.FacesUtil;
@ManagedBean
@SessionScoped
public class CargoControllerMike {

	private static final String PAGINA_CADASTRO_PROCESSO = "cadastroCargo.xhtml";
	private static final String PAGINA_LISTA_PROCESSO = "listaCargo.xhtml";
	
	private CargoMike car = new CargoMike();
	private CargoDAOMike dao = new CargoDAOMike();

	public CargoMike getCar() {
		return car;
	}

	public void setCar(CargoMike car) {
		this.car = car;
	}

	public void limparCampos() {
		car = new CargoMike();
	}
	
	public String salvar() throws Exception {
		try {
			if (car.getId() == null) {
				dao.incluir(car);
				FacesUtil.m("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(car);
				FacesUtil.m("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação: "+e.getMessage());
		}
		return PAGINA_CADASTRO_PROCESSO;
	}
	
	public String excluir() throws Exception {
		try {
			dao.excluir(car);
			FacesUtil.m("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação: "+e.getMessage());			
		}
		return PAGINA_LISTA_PROCESSO;
	}
	
	public String editar() {
		return PAGINA_CADASTRO_PROCESSO;
	}
	
	public List<CargoMike> getLista() throws Exception {
		List<CargoMike> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação: "+e.getMessage());			
		}
		return listaRetorno;
	}
}
