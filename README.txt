# XY-INC(Backend Customizável)

Este projeto surgiu da necessidade de criação de um Backend customizável para aplicações Mobile. 
Com ele será possível utilizar serviços REST dinamicamente sem que o programador Mobile necessite conhecer o backend.

## Instalação

- Efetuar download dos fontes.
- Rodar o comando mvn clean install para gerar o war.
- Configurar o datasource no WildFly da seguinte maneira:

	 <datasource jndi-name="java:jboss/datasources/default" pool-name="default" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/xy_inc</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>senha</password>
                    </security>
                    <statement>
                        <prepared-statement-cache-size>10</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
	
	<drivers>
					<driver name="mysql" module="com.sql.mysql">
						<driver-class>com.mysql.jdbc.Driver</driver-class>
					</driver>
	</drivers>
	
- Realizar deploy da aplicação no WildFly 8.1

- Acessar o endereço http://<ip-do-servidor>:<porta-do-servidor>/xy-inc

## Formas de uso

1- Criar os modelos e seus atributos através da interface Web ou utilizando os serviços citados abaixo:
Realizar operação GET no serviço http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/modelos/novo para incluir um modelo informando os atributos(nome, descricao e link)
Realizar operação GET no serviço http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/atributos/novo para incluir um atributo a um Modelo que poderá ser selecionado.

2- Para cada modelo criado é informado um link, este link serve para distinguir os registros que serão manipulados via RESTFUL.
Para manipular os dados na base basta utilizar os respectivos metodos:
POST http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/servico/<link-do-modelo-criado>     (Insere os registros na base formato esperado JSON)
GET http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/servico/<link-do-modelo-criado>      (Exibe todos registros para determinado modelo)
GET http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/servico/<link-do-modelo-criado>/<id>  (Exibe o registro de ID informado, retorna BAD REQUEST(400) caso não exista)
PUT http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/servico/<link-do-modelo-criado>      (Atualiza o registro informado via JSON )
DELETE http://<ip-do-servidor>:<porta-do-servidor>/xy-inc/servico/<link-do-modelo-criado>/<id>  (Deleta o registro referente ao id informado)

A string no formato JSON devera ser inputada no cabeçalho HTTP utilizando o nome de parametro "objeto_json" .
Existe uma Classe java que gera uma string no formato JSON para realizar testes, a mesma pode ser encontrada em xy-inc\src\test\java\br\com\xy_inc\test\UtilTest.java


## Histórico

23/10/2015 Commit da versão inicial do Software
26/10/2015 Commit final da versão requisitada.

## Créditos

Leonardo Henrique Lages Pereira

## Licença

GPLv3