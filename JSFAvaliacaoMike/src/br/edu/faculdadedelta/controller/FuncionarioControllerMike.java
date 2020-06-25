package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.faculdadedelta.dao.FuncionarioDAOMike;
import br.edu.faculdadedelta.modelo.CargoMike;
import br.edu.faculdadedelta.modelo.FuncionarioMike;
import br.edu.faculdadedelta.util.FacesUtil;
@ManagedBean
@SessionScoped
public class FuncionarioControllerMike {

	private static final String PAGINA_CADASTRO_CONTRATO = "cadastroFuncionario.xhtml";
	private static final String PAGINA_LISTA_CONTRATO = "listaFuncionario.xhtml";

	private FuncionarioMike func = new FuncionarioMike();
	private FuncionarioDAOMike dao = new FuncionarioDAOMike();
	private CargoMike cargoSelecionado = new CargoMike();

	public FuncionarioMike getFunc() {
		return func;
	}

	public void setFunc(FuncionarioMike func) {
		this.func = func;
	}

	public CargoMike getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(CargoMike cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}

	public void limparCampos() {
		func = new FuncionarioMike();
		cargoSelecionado = new CargoMike();
	}

	public String salvar() throws Exception {
		try {

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dt = sdf.parse("01/01/1990");
				if (func.getAdmissao().after(dt) && func.getAdmissao().before(new Date())) {
					if (func.getId() == null) {
						func.setCargo(cargoSelecionado);
						dao.incluir(func);
						FacesUtil.m("Inclusão realizada com sucesso!");
						limparCampos();
					} else {
						func.setCargo(cargoSelecionado);
						dao.alterar(func);
						FacesUtil.m("Alteração realizada com sucesso!");
						limparCampos();
					}
				} else {
					FacesUtil.m("Data Invalida");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				FacesUtil.m("Erro ao realizar a operação. " + e.getMessage());
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação. " + e.getMessage());
		}

		return PAGINA_CADASTRO_CONTRATO;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(func);
			FacesUtil.m("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação. " + e.getMessage());
		}
		return PAGINA_LISTA_CONTRATO;
	}

	public String editar() {
		cargoSelecionado = func.getCargo();
		return PAGINA_CADASTRO_CONTRATO;
	}

	public List<FuncionarioMike> getLista() throws Exception {
		List<FuncionarioMike> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			FacesUtil.m("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}

}
