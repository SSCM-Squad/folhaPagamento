INSERT INTO empresas (cnpj, razao_social) VALUES ("123456789","XP_INC")
INSERT INTO funcionarios (adicional_periculosidade, ajuda_de_custo, dependentes, funcao, horas_extras, jornada_de_trabalho, nome, salario, valor_plano_de_saude, cpf, tipo) VALUES (true, 300.00, 2, "Desenvolvedor Jr.", 10, 176, "Jerry", 3500.00, 50.00,"22233344480", "COLABORADOR")
UPDATE funcionarios SET empresa_id = 1 WHERE id = 1

INSERT INTO funcionarios (adicional_periculosidade, ajuda_de_custo, dependentes, funcao, horas_extras, jornada_de_trabalho, nome, salario, valor_plano_de_saude, empresa_id, cpf, tipo) VALUES (false, 300.00, 0, "Desenvolvedor Pleno", 20, 176, "Kramer", 6500.00, 50.00, 1, "33333333360", "COLABORADOR")

INSERT INTO holerites (cargo_funcionario, cnpj_empresa, cod, data, nome_empresa, nome_funcionario, valor_fgts, base_inss, base_irpf, dependentes, salario_base, total_descontos, total_vencimentos, valor_liquido) VALUES ("Gerente de projetos", "123456789000", "030", "2022-04-18", "Poseidon", "Lucas Silva", 20000.00, 22000.00, 21000.00, 3, 18500.00, 6500.00, 27000.00, 20500.00)
INSERT INTO detalhes (desconto, descricao, referencia, vencimento, holerite_id) VALUES (6500.00, "FGTS", "30 Dias", 27000.00 , 1)
INSERT INTO detalhes (desconto, descricao, referencia, vencimento, holerite_id) VALUES (1800.00, "INSS", "--", 2800.00 , 1)

UPDATE holerites SET funcionario_id = 1 WHERE id = 1