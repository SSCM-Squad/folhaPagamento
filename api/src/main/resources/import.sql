INSERT INTO empresas (cnpj, razao_social) VALUES ("123456789","XP_INC")
INSERT INTO funcionarios (adicional_periculosidade, ajuda_de_custo, dependentes, funcao, horas_extras, jornada_de_trabalho, nome, salario, valor_plano_de_saude) VALUES (30.00, 300.00, 2, "Desenvolvedor Jr.", 10, 176, "Jerry", 3500.00, 50.00)
UPDATE funcionarios SET empresa_id = 1 WHERE id = 1