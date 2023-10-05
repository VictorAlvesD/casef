
insert into telefone (id, codArea, numero) values(1, '63', '98478-3692');
insert into telefone (id, codArea, numero) values(2, '62', '99999-9688');
insert into telefone (id, codArea, numero) values(3, '63', '99928-9652');
insert into telefone (id, codArea, numero) values(4, '63', '98147-9522');
insert into telefone (id, codArea, numero) values(5, '62', '94856-9959');
insert into telefone (id, codArea, numero) values(6, '61', '98477-9772');
insert into telefone (id, codArea, numero) values(7, '99', '99978-9699');

insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(1, 'Alameda 18', '88', 'Próximo ao mercado', '108 Norte', 'Palmas', '74985-888', 'TOCANTINS'); 
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(2, 'Alameda 4', '12', 'Próximo a praça', '106 Sul', 'Palmas', '74985-333', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(3, 'Alameda 3', '13', 'Não tem', '804 Norte', 'Palmas', '70805-388', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(4, 'Alameda 10', '58', 'Próximo ao mercado', '604 Norte', 'Palmas', '70084-885', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(5, 'Alameda 12', '85', 'Próximo a escola', '303 Norte', 'Palmas', '72585-858', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(6, 'Alameda 7', '42', 'Portão Azul', '406 Norte', 'Palmas', '74585-558', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(7, 'Alameda 8', '38', 'Murro verde e Portão preto', '504 Norte', 'Palmas', '74854-666', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(8, 'Alameda 3', '37', 'Próximo ao mercado BIG', '502 Norte', 'Palmas', '74885-698', 'TOCANTINS');

insert into administrador (id, nome, email, senha, cpf, matricula) 
values(1, 'Victor Alves', 'victoralves@unitins.br', 'Vi$tor@345', '753.369.654-98', 159);
insert into administrador (id, nome, email, senha, cpf, matricula) 
values(2, 'Aline barros', 'alineb@unitins.br', '9856@345', '023.396.147-08', 111);
insert into administrador (id, nome, email, senha, cpf, matricula) 
values(3, 'Gloria Groover', 'gg@unitins.br', '*859645', '063.369.688-05', 257);

insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(1, 'Victor Alves', 'victoralves@unitins.br', 'Vi$987@345', '753.001.654-98', '2000-05-04');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(2, 'Bruno barros', 'bbruno@unitins.br', '98ww45', '023.396.158-08', '1995-03-01');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(3, 'Dany Bananinha', 'bananas@unitins.br', '*877756', '522.369.528-05', '1997-01-04');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(5, 'Eliana do SBT', 'eliana@unitins.br', '!E$%r56', '001.311.102-15', '1998-02-02');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(4, 'Ana Vitoria', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(6, 'Ana ', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(7, 'Vitoria', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');


insert into cliente_telefone (id_cliente, id_telefone) values(1, 1);
insert into cliente_telefone (id_cliente, id_telefone) values(2, 2);
insert into cliente_telefone (id_cliente, id_telefone) values(2, 3);
insert into cliente_telefone (id_cliente, id_telefone) values(3, 4);
insert into cliente_telefone (id_cliente, id_telefone) values(4, 5);
insert into cliente_telefone (id_cliente, id_telefone) values(5, 6);
insert into cliente_telefone (id_cliente, id_telefone) values(5, 7);

insert into cliente_endereco (id_cliente, id_endereco) values(7, 8);
insert into cliente_endereco (id_cliente, id_endereco) values(7, 7);
insert into cliente_endereco (id_cliente, id_endereco) values(1, 1);
insert into cliente_endereco (id_cliente, id_endereco) values(2, 2);
insert into cliente_endereco (id_cliente, id_endereco) values(3, 3);
insert into cliente_endereco (id_cliente, id_endereco) values(4, 4);
insert into cliente_endereco (id_cliente, id_endereco) values(5, 5);
insert into cliente_endereco (id_cliente, id_endereco) values(6, 6);