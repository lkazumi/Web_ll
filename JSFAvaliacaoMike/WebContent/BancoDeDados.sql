CREATE TABLE funcionarios
(
id serial NOT NULL,
nome_func character (50),
cpf_func character (11),
matricula_func character (8),
id_cargo_func integer,
salario_func numeric(10,2),
data_admissao_func date
);

CREATE TABLE cargos_func
(
id serial NOT NULL,
desc_cargo_func character (50)
);