# Projeto para teste Automatizado de API

Todos os cenários de testes se baseiam na cobertura daquilo que é informado no swagger de cada Micro serviço.

## Casos de testes para cada microserviço

### ABRIGO
**Casos Felizes**
1. Listar todos os Abrigos
2. Listar Abrigo pelo código
3. Listar Abrigo pelo Nome
4. Listar Abrigo pelo Nome e pelo Código
5. Criar um Abrigo

**Casos de Erros**
1. Criação de Abrigo sem informar o nome do Abrigo
2. Criação de Abrigo sem informar o Telefone do Abrigo
3. Criação de Abrigo sem informar o E-mail do Abrigo
4. Criação de Abrigo sem informar o País do Abrigo
5. Criação de Abrigo sem informar o Estado do Abrigo
6. Criação de Abrigo sem informar a Cidade do Abrigo
7. Criação de Abrigo sem informar o Bairro do Abrigo
8. Criação de Abrigo sem informar o Endereço do Abrigo
9. Criação de Abrigo sem informar o número
10. Validar erro ao tentar criar Abrigo sem informar o Token
11. Validar erro ao tentar criar Abrigo informando um token invalido

### CENTRO DE DISTRIBUIÇÃO
Casos Felizes
1. Listar todos os Centros de Distribuição
2. Listar Centro de Distribuição pelo código
3. Criar um Centro de Distribuição

**Casos de Erros**
1. Criação de Centro de Distribuição sem informar o nome do Centro
2. Criação de Centro de Distribuição sem informar o Telefone do Centro
3. Criação de Centro de Distribuição sem informar o E-mail do Centro
4. Criação de Centro de Distribuição sem informar o País do Centro
5. Criação de Centro de Distribuição sem informar o Estado do Centro
6. Criação de Centro de Distribuição sem informar a Cidade do Centro
7. Criação de Centro de Distribuição sem informar o Bairro do Centro
8. Criação de Centro de Distribuição sem informar o Endereço do Centro
9. Criação de Centro de Distribuição sem informar o número
10. Validar erro ao tentar criar Centro de Distribuição sem informar o Token
11. Validar erro ao tentar criar Centro de Distribuição informando um token invalido

### USUÁRIO
Casos Felizes
1. Listar todos os Usuários
2. Listar Usuário pelo código
3. Criar um Usuário

**Casos de Erros**
1. Criação de Usuário sem informar o nome do Usuário
2. Criação de Usuário sem informar o E-mail do Usuário
3. Criação de Usuário sem informar a Senha do Usuário

### ESTOQUE
Casos Felizes
1. Listar todos os Estoques
2. Listar Estoque pelo código
3. Criar um Estoque

**Casos de Erros**
1. Criação de Estoque sem informar o nome do Estoque
2. Criação de Estoque sem informar a quantidade do Estoque
3. Criação de Estoque sem informar a data de validade do Estoque

### DOAÇÃO
Casos Felizes
1. Listar todas as Doações
2. Listar Doação pelo código
3. Criar uma Doação

**Casos de Erros**
1. Criação de Doação sem informar o nome do Doador
2. Criação de Doação sem informar a quantidade da Doação
3. Criação de Doação sem informar a data da Doação

### PEDIDOS
Casos Felizes
1. Listar todos os Pedidos
2. Listar Pedido pelo código
3. Criar um Pedido

**Casos de Erros**
1. Criação de Pedido sem informar o nome do Pedido
2. Criação de Pedido sem informar a quantidade do Pedido
3. Criação de Pedido sem informar a data do Pedido




abrigop
bug retornando 200 no Post ao inves de 201


Centro Distribuicao
Caso Feliz


bug retornando 200 no Post ao inves de 201
bug ao informar o nome como vazio "" ele deixa, não dá erro

usuario
retirar a validacao de nome como chave unica, nomes podem ser repitidos

estoque
deixa dar baixa numa quantidade minima( tipo tem 10 e deixa dar baixa em 9 quantidade, nao sei se isso vale)
ele retorna 200 caso faça uma consulta num estoque de um cd que nao existe, retorna vazio
ele retorna 200 caso tente achar um item que nao existe no estouqe, retorna vazio
deixa icluir item sem informar a data de validade

doacao
bug que nao está retornando o body na 2xx do post doacao

pedidos 