# Projeto para teste Automatizado de API

Todos os cenários de tesates se baseam na cobertura daquilo que é informado no swagger de cada Micro serviço.


Casos de testes para cada microserviço

ABRIGO 14 casos de Teste:
Casos Feliz
1 Listar todos os Abrigos
2 Listar Abrigo pelo codigo 
3 Listar Abrigo pelo Nome
4 Listar Abrigo pelo Nome e pelo Codigo
5 Criar um Abrigo
Casos de Erros
1 Criacao de Abrigo sem informar o nome do Abrigo
2 Criacao de Abrigo sem informar o Telefone do Abrigo
3 Criacao de Abrigo sem informar o E-mail do Abrigo
4 Criacao de Abrigo sem informar o Pais do Abrigo
5 Criacao de Abrigo sem informar o Estado do Abrigo
6 Criacao de Abrigo sem informar a Cidade do Abrigo
7 Criacao de Abrigo sem informar o Bairro do Abrigo
8 Criacao de Abrigo sem informar o Endereço do Abrigo
9 criar sem informar numero

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

