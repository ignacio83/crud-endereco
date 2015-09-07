# Introdução
Provê serviços REST para cadastro, alteração, remoção e consulta de endereços dos usuários.

#Tecnologias
Esse projeto utiliza spring-boot, spring-mvc, spring-data, spring-boot-actuator com banco de dados em memória (hsqldb).

#Arquitetura
Arquitera em 3 camadas (controller, service, repository).

#Dependências
Esse projeto depende do serviço REST de busca de CEP exposto pelo projeto [busca-cep](https://github.com/ignacio83/busca-cep).

#Como iniciar o projeto
mvn spring-boot:run -Dserver.port=8080

#Acessando a aplicação
http://localhost:8080/

A página inicial da aplicação é uma documentação escrita em swagger, os serviços listados podem ser utilizados sem
necessidade dessa interface HTML.

#Usuários previamente cadastrados
* Id: 1 - João da Silva
* Id: 2 - Maria Benedita

#Endereços previamente cadastrados
* Id: 1 - 01001001, Praça da Sé, 1, São Paulo, SP
* Id: 2 - 01002001, Rua Direita, 20, AP 22, Sé, São Paulo, SP
* Id: 3 - 01001001, Praça da Sé, 2, Sé, São Paulo, SP