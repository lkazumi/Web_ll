package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CargoMike;
import br.edu.faculdadedelta.util.Conexao;

public class CargoDAOMike {

	public void incluir(CargoMike cargo) throws Exception { 
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO cargos_func (desc_cargo_func) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cargo.getCargo().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public void alterar(CargoMike cargo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE cargos_func SET desc_cargo_func = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cargo.getCargo().trim());
			ps.setLong(2, cargo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public void excluir(CargoMike cargo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM cargos_func WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, cargo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public CargoMike pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, desc_cargo_func FROM cargos_func WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		CargoMike retorno = new CargoMike();
		ResultSet rs = null;
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularCargo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
	
	public List<CargoMike> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, desc_cargo_func FROM cargos_func";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<CargoMike> listaRetorno = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				listaRetorno.add(popularCargo(rs));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	
	private CargoMike popularCargo(ResultSet rs) throws SQLException {
		CargoMike cargo = new CargoMike();
		cargo.setId(rs.getLong("id"));
		cargo.setCargo(rs.getString("desc_cargo_func"));
		return cargo;
	}
}
