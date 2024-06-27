INSERT INTO tb_pessoa_fisica (nome, data_nascimento, cpf) values ('Waldston', '1993-11-06', '05415724583')
INSERT INTO tb_pessoa_fisica (nome, data_nascimento, cpf) values ('João', '1990-04-25', '98765432109')
INSERT INTO tb_pessoa_fisica (nome, data_nascimento, cpf) values ('Pedro', '1988-03-15', '78945612302')
INSERT INTO tb_pessoa_fisica (nome, data_nascimento, cpf) values ('Maria', '1985-07-12', '12345678901')
INSERT INTO tb_pessoa_fisica (nome, data_nascimento, cpf) values ('Ana', '1976-09-30', '45678912345')


INSERT INTO tb_pessoa_juridica (nome,  cnpj) values ('Maria Produções', '132456000123')
INSERT INTO tb_pessoa_juridica (nome, cnpj) values ('Vidrex', '98765432000100');
INSERT INTO tb_pessoa_juridica (nome, cnpj) values ('Comércio Varejo', '45678900012345');


INSERT INTO tb_endereco (cep, cidade, bairro, rua, numero, pessoa_fisica_id) values ('49044-090', 'Aracaju', 'Santa Maria', 'Rua 2', '154', 1 )
INSERT INTO tb_endereco (cep, cidade, bairro, rua, numero, pessoa_fisica_id) values ('40956-040', 'Aracaju', 'São Conrado', 'Rua Mariana', '4567', 2 )

INSERT INTO tb_produtos (nome, valor, juridica_id) values ('Vidro temperado', 234.54, 2)
INSERT INTO tb_produtos (nome, valor, juridica_id) values ('Luminária', 56.78, 1)
INSERT INTO tb_produtos (nome, valor, juridica_id) values ('Lapis Grafite', 3.55, 3)