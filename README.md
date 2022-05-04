# folhaPagamentos
[![Testes e Build do projeto](https://github.com/SSCM-Squad/folhaPagamento/actions/workflows/maven.yml/badge.svg)](https://github.com/SSCM-Squad/folhaPagamento/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/SSCM-Squad/folhaPagamento/branch/main/graph/badge.svg?token=D3V49G24W7)](https://codecov.io/gh/SSCM-Squad/folhaPagamento)

## Pré-requisitos
* Java  - versão 17 ou mais recente
* Maven - para build e dependências
* MySQL - versão 8 ou mais recente

## Como gerar o arquivo .jar
Dentro da pasta do projeto _(folhaPagamento/api)_ execute o comando `mvn clean package`

<div align- "center">
<img src="https://user-images.githubusercontent.com/52303788/166613915-b7b91acf-fd27-46a4-b4e0-89477cb79e7e.PNG" width="700px" />
</div>


Após rodar o comando a cima o arquivo **.jar** estará disponivel na pastas **_target_**.


<div align- "center">
<img src="https://user-images.githubusercontent.com/52303788/166615700-0bc9106b-f877-4e9b-a02e-57931d926de8.png" width="450px" />
</div>

## Como executar o arquivo .jar

Dentro da pasta do projeto _(folhaPagamento/api/target)_ execute o comando:

`java -jar -DDB_URL=jdbc:mysql://localhost:3306/api_pagamento?createDatabaseIfNotExist=true -DDB_USERNAME=root -DDB_PASSWORD=$uzuki121090 api-0.0.1-SNAPSHOT.jar`

Atenção: Não se esqueça de alterar a senha da String de conexão com o banco de dados SQL

<div align- "center">
<img src="https://user-images.githubusercontent.com/52303788/166616527-02e42976-203d-4eb1-b839-50923635103d.png" width="600px" />
</div>

## Como usar a aplicação

Para utilizar a aplicação **localmente** de uma forma amigavel é possivel utilizar o **_Swager_** acessando a segunte URL:

http://localhost:8080/swagger-ui.html#/

<div align- "center">
<img src="https://user-images.githubusercontent.com/52303788/166617220-59e053fa-e37f-40b8-a35d-7c8351e5b750.png" width="400px" />
</div>

