# Needles Signals

## Descrição:
Needles Signals, sua plataforma de SINAIS preferida. <br/>
Nossa aplicação já está no ar, e pode ser utilizada na seguinte url: https://jovial-jelly-c45c9d.netlify.app/ 

## Versao:
- v0.0.1

## Tecnologias:
- Java 17
- H2
- SpringBoot v3.0.10 (web/ validation/ devtools/ jdbc/ lombok/ security/)
- Docker

## Instruções para Executar o Projeto:

Certifique-se de ter o Java 17 instalado em sua máquina antes de prosseguir.

1. Clone o repositório para a sua máquina local:
    ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
2. Navegue até o diretório do projeto:
    ```bash
    cd seu-repositorio
3. Execute o build do projeto usando o Maven:
    ```bash
    mvnw.cmd clean install
4. Após o build ser concluído com sucesso, você pode executar a aplicação SpringBoot:
      ```bash 
   mvnw.cmd spring-boot:run
   Isso iniciará a aplicação e você poderá acessar os endpoints mencionados na seção "ENDPOINTS" via http://localhost:8080.
   
5. Para criar uma imagem Docker da aplicação, use o seguinte comando:
    ```bash
    docker build -t nome-da-imagem .

6. Execute o contêiner Docker:
    ```bash
    docker run -p 8080:8080 nome-da-imagem
    Agora você pode acessar a aplicação SpringBoot no Docker via http://localhost:8080.   

## ENDPOINTS:
### Authentication
-  <b>Endpoint responsável por:</b> Cadastrar um novo <b>Usuário</b> no sistema.<br/>
```http
  POST /api/v1/auth/register
```
| Parâmetro   | Tipo           | Descrição                                | 
|:------------|:---------------|:-----------------------------------------| 
| `firstName` | `@body string` | **Obrigatório**. Nome do usuário.        | 
| `lastName`  | `@body string` | **Obrigatório**. Ultimo Nome do usuário. | 
| `email`     | `@body string` | **Obrigatório**. Email do usuário.       | 
| `password`  | `@body string` | **Obrigatório**. Senha do usuário.       | 

-  <b>Endpoint responsável por:</b> Logar/Autenticar um <b>Usuário</b> existente no sistema.<br/>
```http
  POST /api/v1/auth/authenticate
```
| Parâmetro   | Tipo           | Descrição                                | 
|:------------|:---------------|:-----------------------------------------| 
| `email`     | `@body string` | **Obrigatório**. Email do usuário.       | 
| `password`  | `@body string` | **Obrigatório**. Senha do usuário.       | 

### User
-  <b>Endpoint responsável por:</b> Recuperar um <b>Usuário</b> existente no sistema.<br/>
```http
  GET /api/v1/user/{userId}
```
| Parâmetro | Tipo                  | Descrição                       | 
|:----------|:----------------------|:--------------------------------| 
| `userId`  | `@path-variable long` | **Obrigatório**. Id do usuário. | 

-  <b>Endpoint responsável por:</b> Atualizar um <b>Usuário</b> existente no sistema.<br/>
```http
  PUT /api/v1/user/{userId}
```
| Parâmetro   | Tipo                  | Descrição                            | 
|:------------|:----------------------|:-------------------------------------| 
| `userId`    | `@path-variable long` | **Obrigatório**. Id do usuário.      | 
| `firstName` | `@body string`        | **Obrigatório**. Novo primeiro nome. | 
| `lastName`  | `@body string`        | **Obrigatório**. Novo ultimo nome.   | 
| `email`     | `@body string`        | **Obrigatório**. E-mail do usuário.  | 

### Active

-  <b>Endpoint responsável por:</b> Cadastrar um novo <b>Ativo</b> no sistema.<br/>
```http
  POST /api/v1/finance-active/create
```
| Parâmetro                          | Tipo               | Descrição                                                                    | 
|:-----------------------------------|:-------------------|:-----------------------------------------------------------------------------| 
| `name`                             | `@body string`     | **Obrigatório**. Nome.                                                       | 
| `description`                      | `@body string`     | **Obrigatório**. Descrição.                                                  | 
| `category`                         | `@body string`     | **Obrigatório**. Categoria.                                                  | 
| `time_offer`                       | `@body string`     | **Obrigatório**. Tempo para ação.                                            | 
| `variant.value`                    | `@body bigdecimal` | **Obrigatório**. Valor da variante adicionada.                               | 
| `variant.variation`                | `@body float`      | **Obrigatório**. Valor crescente ou decrescente comparado há ultima variant. | 
| `variant.signal.signal_sale.force` | `@body string`     | **Obrigatório**. Venda forte.                                                | 
| `variant.signal.signal_buy.force`  | `@body string`     | **Obrigatório**. Compra forte.                                               | 

-  <b>Endpoint responsável por:</b> Recuperar um <b>Ativo</b> e sua <b>Variante</b> existente no sistema.<br/>

```http
  GET /api/v1/finance-active/filter
```
| Parâmetro   | Tipo                    | Descrição                                 | 
|:------------|:------------------------|:------------------------------------------| 
| `category`  | `@request-param string` | **Obrigatório**. Categoria desejada.      | 
| `name`      | `@request-param string` | **Obrigatório**. Nome para filtro.        | 
| `timeOffer` | `@request-param string` | **Obrigatório**. Tempo para compra/venda. | 
| `signal.`   | `@request-param string` | **Obrigatório**. Sinal.                   | 
| `order.`    | `@request-param string` | **Obrigatório**. Ordenação (ASC/ DESC).   | 

-  <b>Endpoint responsável por:</b> Recuperar atualizar um <b>Ativo</b> existente no sistema.<br/>

```http
  PUT /api/v1/finance-active/{name}/{timeOffer}
```
| Parâmetro     | Tipo                    | Descrição                                 | 
|:--------------|:------------------------|:------------------------------------------| 
| `name`        | `@path-variable string` | **Obrigatório**. Nome do ativo. sdads     | 
| `timeOffer`   | `@path-variable string` | **Obrigatório**. Tempo para compra/venda. | 
| `name`        | `@body string`          | **Obrigatório**. Novo nome do ativo.      | 
| `description` | `@body string`          | **Obrigatório**. Nova descrição do ativo. | 
| `category`    | `@body string`          | **Obrigatório**. Nova categoria do ativo. | 

-- **Obs**.:


| TimeOffer     | Descrição                                             | 
|:--------------|:------------------------------------------------------| 
| `ONE_MINUTE`  | **Obrigatório**. Oferta esperada de um minuto.        | 
| `FIVE_MINUTE` | **Obrigatório**. Oferta esperada de cinco minuto.     | 
| `ONE_HOUR`    | **Obrigatório**. Oferta esperada de uma hora.         | 
| `ONE_DAY`     | **Obrigatório**. Oferta esperada de um dia.           | 
| `UNDEFINED`   | **Obrigatório**. Oferta esperada de tempo indefinido. | 

| Signal    | Descrição                          | 
|:----------|:-----------------------------------| 
| `DEFAULT` | **Obrigatório**. Entrada comum.    | 
| `WARNING` | **Obrigatório**. Entrada baixa.    | 
| `BUY`     | **Obrigatório**. Possivel entrada. | 
| `STRONG`  | **Obrigatório**. Entrada alta.     | 

| Category               | Descrição                                  | 
|:-----------------------|:-------------------------------------------| 
| `FINANCIAL_STOCKS`     | **Obrigatório**. Ações Financeiras.        | 
| `FINANCIAL_CURRENCY`   | **Obrigatório**. Moeda Financeira.         | 
| `FINANCIAL_BMF_FUTURE` | **Obrigatório**. Futuro Financeiro do BMF. | 
| `FINANCIAL_TITLE`      | **Obrigatório**. Título Financeiro.        | 

### INFO
Desenvolvedor: Victor Hugo Arruda
Contato: (https://beacons.ai/tor_hugo)
