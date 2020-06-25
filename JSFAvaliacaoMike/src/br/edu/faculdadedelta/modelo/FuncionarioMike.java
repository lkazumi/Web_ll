package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class FuncionarioMike {

	private Long id;
	private String nome;
	private String cpf;
	private String matricula;
	private CargoMike cargo;
	private double salario;
	private Date admissao;
	private double salarioAtt;
	
	
	public FuncionarioMike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuncionarioMike(Long id, String nome, String cpf, String matricula, CargoMike cargo, double salario,
			Date admissao, double salarioAtt) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.cargo = cargo;
		this.salario = salario;
		this.admissao = admissao;
		this.salarioAtt = salarioAtt;
	}

	public double getSalarioAtt() {

		int anoAd = getAdmissao().getYear();
		int atual = new Date().getYear();
		double sal = salario;
		int cont = 0;
		if(anoAd != atual) {
			for(int i = 0; anoAd < atual; i++ ) {
				 sal = (sal * 0.02) + sal;		
				 cont += 1;
				 anoAd++;
			}
			salarioAtt = sal;
			
		if(getCargo().getId() == 3) {
			for(int j = 0; j <= cont;) {
				sal = (sal * 0.015) + sal;
				j += 5;
			}
			salarioAtt = sal;
		}
		
		}else {
		
		salarioAtt = salario;		
		}return salarioAtt;
	}

	public void setSalarioAtt(double salarioAtt) {
		this.salarioAtt = salarioAtt;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public CargoMike getCargo() {
		return cargo;
	}
	public void setCargo(CargoMike cargo) {
		this.cargo = cargo;
	}
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public Date getAdmissao() {
		return admissao;
	}
	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioMike other = (FuncionarioMike) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
