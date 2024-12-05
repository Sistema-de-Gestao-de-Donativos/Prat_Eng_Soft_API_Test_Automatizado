# Projeto para Teste Automatizado de API

Este projeto tem como objetivo realizar testes automatizados de APIs, com base na cobertura das informações fornecidas no Swagger de cada microserviço.

## Configuração do Ambiente

1. **Java**: Certifique-se de que o Java está instalado em sua máquina.
2. **VSCode**: Baixe as extensões necessárias para o desenvolvimento em Java.
3. **Execução de Testes**:
   - Se a configuração estiver correta, um botão "play" aparecerá ao lado de cada teste na classe `TestCase`.
   - Para rodar os testes via terminal, utilize as tasks do `gradlew`. Atualmente, existe uma task para cada microserviço. Execute o seguinte comando para visualizar as tasks disponíveis:
     ```bash
     ./gradlew tasks
     ```
   - Isso exibirá as 5 tasks para cada microserviço.

## Visualização de Relatórios

Para visualizar o relatório, execute a task `AllureServe`, que abrirá o relatório no navegador. Caso deseje exportar o relatório, baixe o Allure Report em sua máquina e execute a task `generateAllureReportByName`.

## Casos de Testes para Cada Microserviço

### Abrigo

**Casos Felizes**
1. Listar todos os Abrigos
2. Listar Abrigo pelo código
3. Listar Abrigo pelo Nome
4. Listar Abrigo pelo Nome e pelo Código
5. Criar um Abrigo

**Casos de Erros**
1. Criação de Abrigo sem informar o nome
2. Criação de Abrigo sem informar o telefone
3. Criação de Abrigo sem informar o e-mail
4. Criação de Abrigo sem informar o país
5. Criação de Abrigo sem informar o estado
6. Criação de Abrigo sem informar a cidade
7. Criação de Abrigo sem informar o bairro
8. Criação de Abrigo sem informar o endereço
9. Criação de Abrigo sem informar o número
10. Validar erro ao tentar criar Abrigo sem informar o token
11. Validar erro ao tentar criar Abrigo informando um token inválido

### Centro de Distribuição

**Casos Felizes**
1. Listar todos os Centros de Distribuição
2. Listar Centro de Distribuição pelo código
3. Criar um Centro de Distribuição

**Casos de Erros**
1. Criação de Centro de Distribuição sem informar o nome
2. Criação de Centro de Distribuição sem informar o telefone
3. Criação de Centro de Distribuição sem informar o e-mail
4. Criação de Centro de Distribuição sem informar o país
5. Criação de Centro de Distribuição sem informar o estado
6. Criação de Centro de Distribuição sem informar a cidade
7. Criação de Centro de Distribuição sem informar o bairro
8. Criação de Centro de Distribuição sem informar o endereço
9. Criação de Centro de Distribuição sem informar o número
10. Validar erro ao tentar criar Centro de Distribuição sem informar o token
11. Validar erro ao tentar criar Centro de Distribuição informando um token inválido

### Usuário

**Casos Felizes**
1. Listar todos os Usuários
2. Listar Usuário pelo código
3. Criar um Usuário

**Casos de Erros**
1. Criação de Usuário sem informar o nome
2. Criação de Usuário sem informar o e-mail
3. Criação de Usuário sem informar a senha

### Estoque

**Casos Felizes**
1. Listar todos os Estoques
2. Listar Estoque pelo código
3. Criar um Estoque

**Casos de Erros**
1. Criação de Estoque sem informar o nome
2. Criação de Estoque sem informar a quantidade
3. Criação de Estoque sem informar a data de validade

### Doação

**Casos Felizes**
1. Listar todas as Doações
2. Listar Doação pelo código
3. Criar uma Doação

**Casos de Erros**
1. Criação de Doação sem informar o nome do doador
2. Criação de Doação sem informar a quantidade
3. Criação de Doação sem informar a data

### Pedidos

**Casos Felizes**
1. Listar todos os Pedidos
2. Listar Pedido pelo código
3. Criar um Pedido

**Casos de Erros**
1. Criação de Pedido sem informar o nome
2. Criação de Pedido sem informar a quantidade
3. Criação de Pedido sem
