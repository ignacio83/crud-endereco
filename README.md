# Introdução
Provê serviços REST para cadastro, alteração, remoção e consulta de endereços dos usuários.

#Tecnologias
Esse projeto utiliza spring-boot, spring-mvc, spring-data, spring-boot-actuator com banco de dados em memória (hsqldb).

#Arquitetura
Arquitetura em 3 camadas (controller, service, repository).

#Integrações
Esse projeto depende do serviço REST de busca de CEP exposto pelo projeto [busca-cep](https://github.com/ignacio83/busca-cep).

#Como iniciar o projeto
mvn spring-boot:run -Dserver.port=8080 -Dbusca.cep.url=http://localhost:8081

Ajuste os parâmetros server.port e busca.cep.url para valores de sua preferência. Caso a URL do serviço de busca cep seja inválida, não será possível
incluir ou alterar um endereço. Verifique a saúde do serviço acessando http://localhost:8080/health, caso a comunicação com o serviço de CEP não seja possível
o status final do serviço será DOWN.

#Acessando a aplicação
http://localhost:8080/

A página inicial da aplicação é uma documentação escrita em swagger, os serviços listados podem ser utilizados sem
necessidade dessa interface HTML.

#Healthcheck
http://localhost:8080/health

#Usuários previamente cadastrados
* Id: 1 - João da Silva
* Id: 2 - Maria Benedita

#Endereços previamente cadastrados
* Id: 1 - 01001001, Praça da Sé, 1, São Paulo, SP
* Id: 2 - 01002001, Rua Direita, 20, AP 22, Sé, São Paulo, SP
* Id: 3 - 01001001, Praça da Sé, 2, Sé, São Paulo, SP