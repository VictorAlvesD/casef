
insert into telefone (id, codArea, numero) values(12, '63', '98478-3692');
insert into telefone (id, codArea, numero) values(22, '62', '99999-9688');
insert into telefone (id, codArea, numero) values(32, '63', '99928-9652');
insert into telefone (id, codArea, numero) values(42, '63', '98147-9522');
insert into telefone (id, codArea, numero) values(52, '62', '94856-9959');
insert into telefone (id, codArea, numero) values(62, '61', '98477-9772');
insert into telefone (id, codArea, numero) values(72, '99', '99978-9699');

insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(13, 'Alameda 18', '88', 'Próximo ao mercado', '108 Norte', 'Palmas', '74985-888', 'TOCANTINS'); 
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(23, 'Alameda 4', '12', 'Próximo a praça', '106 Sul', 'Palmas', '74985-333', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(33, 'Alameda 3', '13', 'Não tem', '804 Norte', 'Palmas', '70805-388', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(43, 'Alameda 10', '58', 'Próximo ao mercado', '604 Norte', 'Palmas', '70084-885', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(53, 'Alameda 12', '85', 'Próximo a escola', '303 Norte', 'Palmas', '72585-858', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(63, 'Alameda 7', '42', 'Portão Azul', '406 Norte', 'Palmas', '74585-558', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(73, 'Alameda 8', '38', 'Murro verde e Portão preto', '504 Norte', 'Palmas', '74854-666', 'TOCANTINS');
insert into endereco (id, logradouro, numero, complemento, bairro, cidade, cep, estado)
values(83, 'Alameda 3', '37', 'Próximo ao mercado BIG', '502 Norte', 'Palmas', '74885-698', 'TOCANTINS');

insert into administrador (id, nome, email, senha, cpf, matricula) 
values(102687, 'Victor Alves', 'victoralves@unitins.br', 'Vi$tor@345', '753.369.654-98', 159);
insert into administrador (id, nome, email, senha, cpf, matricula) 
values(226873, 'Aline barros', 'alineb@unitins.br', '9856@345', '023.396.147-08', 111);
insert into administrador (id, nome, email, senha, cpf, matricula) 
values(385473, 'Gloria Groover', 'gg@unitins.br', '*859645', '063.369.688-05', 257);

insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(19, 'Victor Alves', 'victoralves@unitins.br', 'Vi$987@345', '753.001.654-98', '2000-05-04');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(29, 'Bruno barros', 'bbruno@unitins.br', '98ww45', '023.396.158-08', '1995-03-01');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(39, 'Dany Bananinha', 'bananas@unitins.br', '*877756', '522.369.528-05', '1997-01-04');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(59, 'Eliana do SBT', 'eliana@unitins.br', '!E$%r56', '001.311.102-15', '1998-02-02');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(49, 'Ana Vitoria', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(69, 'Ana ', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');
insert into cliente (id, nome, email, senha, cpf, dataNascimento) 
values(79, 'Vitoria', 'vitoriadaana@unitins.br', 'cdrrr56', '099.389.963-11', '1998-06-06');


insert into cliente_telefone (id_cliente, id_telefone) values(19, 12);
insert into cliente_telefone (id_cliente, id_telefone) values(29, 22);
insert into cliente_telefone (id_cliente, id_telefone) values(29, 32);
insert into cliente_telefone (id_cliente, id_telefone) values(39, 42);
insert into cliente_telefone (id_cliente, id_telefone) values(49, 52);
insert into cliente_telefone (id_cliente, id_telefone) values(59, 62);
insert into cliente_telefone (id_cliente, id_telefone) values(59, 72);

insert into cliente_endereco (id_cliente, id_endereco) values(79, 83);
insert into cliente_endereco (id_cliente, id_endereco) values(79, 73);
insert into cliente_endereco (id_cliente, id_endereco) values(19, 13);
insert into cliente_endereco (id_cliente, id_endereco) values(29, 23);
insert into cliente_endereco (id_cliente, id_endereco) values(39, 33);
insert into cliente_endereco (id_cliente, id_endereco) values(49, 43);
insert into cliente_endereco (id_cliente, id_endereco) values(59, 53);
insert into cliente_endereco (id_cliente, id_endereco) values(69, 63);

insert into pix (id, chavePix) values(35, 'carlos@gmail.com');
insert into pix (id, chavePix) values(45, '9998563254');

insert into boletoBancario (id, banco, numeroBoleto, dataVencimento) 
values(11, 'Bradesco', '36985214789', '2026-05-03');
insert into boletoBancario (id, banco, numeroBoleto, dataVencimento) 
values(21, 'Next', '036985523', '2028-05-04');
insert into boletoBancario (id, banco, numeroBoleto, dataVencimento) 
values(31, 'Nubank', '951555887', '2024-02-05');

insert into cartaoCredito (id, bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values(100, 'Visa', '111225589', '123', '2024-03-08');
insert into cartaoCredito (id, bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values(111, 'Alguma', '111225589', '444', '2024-03-08');
insert into cartaoCredito (id, bandeira, numeroCartao, codigoSeguranca, dataVencimento) 
values(122, 'Visa', '111225589', '569', '2024-03-08');

insert into pagamento (id, tipo, valor) 
values(81, 'Avista', 12.5);
insert into pagamento (id, tipo, valor) 
values(71, 'Aprazo', 125.59);
insert into pagamento (id, tipo, valor) 
values(61, 'Avista', 125);
insert into pagamento (id, tipo, valor) 
values(51, 'Aprazo', 155);
insert into pagamento (id, tipo, valor) 
values(41, 'Avista', 189.5);
insert into pagamento (id, tipo, valor) 
values(31, 'Avista', 125);
insert into pagamento (id, tipo, valor) 
values(21, 'Aprazo', 155);
insert into pagamento (id, tipo, valor) 
values(11, 'Avista', 189.5);

insert into pagamento_boletobancario(id_boletoBancario, id_pagamento)
values(11, 81);
insert into pagamento_boletobancario(id_boletoBancario, id_pagamento)
values(21, 71);
insert into pagamento_boletobancario(id_boletoBancario, id_pagamento)
values(31, 61);

insert into pagamento_cartaocredito(id_cartaocredito, id_pagamento)
values(111, 51);
insert into pagamento_cartaocredito(id_cartaocredito, id_pagamento)
values(100, 41);
insert into pagamento_cartaocredito(id_cartaocredito, id_pagamento)
values(122, 31);

insert into pagamento_pix(id_pix, id_pagamento)
values(35, 21);
insert into pagamento_pix(id_pix, id_pagamento)
values(45, 11);