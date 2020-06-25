package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CargoMike;
import br.edu.faculdadedelta.modelo.FuncionarioMike;
import br.edu.faculdadedelta.util.Conexao;

public class FuncionarioDAOMike {

	public void incluir(FuncionarioMike funcionario) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO funcionarios (nome_func, cpf_func, matricula_func, id_cargo_func, salario_func, data_admissao_func) "
				+ "	VALUES (?,?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, funcionario.getNome().trim());
			ps.setString(2, funcionario.getCpf().trim());
			ps.setString(3, funcionario.getMatricula().trim());
			ps.setLong(4, funcionario.getCargo().getId());
			ps.setDouble(5, funcionario.getSalario());
			ps.setDate(6, new java.sql.Date(funcionario.getAdmissao().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public void alterar(FuncionarioMike funcionario) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE funcionarios "
				+ "	SET nome_func = ?, "
				+ " cpf_func = ? , "
				+ " matricula_func = ?, "
				+ " id_cargo_func = ?, "
				+ " salario_func = ?, "
				+ " data_admissao_func = ? "
				+ " WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, funcionario.getNome().trim());
			ps.setString(2, funcionario.getCpf().trim());
			ps.setString(3, funcionario.getMatricula().trim());
			ps.setLong(4, funcionario.getCargo().getId());
			ps.setDouble(5, funcionario.getSalario());
			ps.setDate(6, new java.sql.Date(funcionario.getAdmissao().getTime()));
			ps.setLong(7, funcionario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public void excluir(FuncionarioMike funcionario) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM funcionarios WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, funcionario.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	
	public List<FuncionarioMike> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT f.id AS idFuncionario,"
				+ " f.nome_func AS nome, "
				+ " f.cpf_func AS cpf, "
				+ " f.matricula_func AS mat,"
				+ " f.salario_func AS salario,"
				+ " f.data_admissao_func AS admissao,"
				+ " c.id AS idCar, "
				+ " c.desc_cargo_func AS cargo "
				+ "	FROM funcionarios f "
				+ " INNER JOIN cargos_func c ON f.id = c.id";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<FuncionarioMike> listaRetorno = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				FuncionarioMike func = new FuncionarioMike();
				func.setId(rs.getLong("idFuncionario"));
				func.setNome(rs.getString("nome").trim());
				func.setCpf(rs.getString("cpf").trim());
				func.setMatricula(rs.getString("mat"));
				func.setSalario(rs.getDouble("salario"));
				func.setAdmissao(rs.getDate("admissao"));
				
				CargoMike cargo = new CargoMike();
				cargo.setId(rs.getLong("idCar"));
				cargo.setCargo(rs.getString("cargo").trim());
				
				func.setCargo(cargo);
				listaRetorno.add(func);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
}
