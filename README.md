<h2>API REST de Gerenciamento de Consultas<h2>

Durante o projeto foram desenvolvidos:

* CRUD de Médicos
* CRUD de Consultas
* Entidade de consultório
* Classes Exceptions
* Entre outras classes...



Ferramentas e Bibliotecas utilizadas:

* Java 11
* Intellij IDEA Community Edition
* Postman para executar as requisições
* MapStruct para a conversão automática dos objetos DTO para entidade
* Lombok para criação automática de alguns métodos
* Banco de dados H2 (que é um banco em memória)
* Spring-data-jpa para mapeamento das entidades



PARA ACESSAR O BANCO DE DADOS H2:

* O arquivo aplication.properties foi alterado
* Caminho para acessar o banco: http://localhost:8080/h2-console (só rodar o projeto e nesta página clicar em conectar que ele já entra)



Foi criado um arquivo "script.sql" que já tem algumas inserções nas tabelas CONSULTORIO e MEDICO

Foi criado o arquivo "exemplo-cria-consulta.json" para mostrar um exemplo de como criar uma "consulta" pelo postman 
IMPORTANTE LEMBRAR QUE PARA CRIAR UMA CONSULTA PRECISA TER REGISTRO NAS TABELAS "MEDICO" e "CONSULTORIO"


ROTAS UTILIZADAS NO POSTMAN PARA CONSULTA: 

GET

* Mostra uma consulta: http://localhost:8080/api/consulta/1
* Lista todas as consultas: http://localhost:8080/api/consulta
* Lista consultas com filtro de médicos: http://localhost:8080/api/consulta/filtro/1

POST

* Criar uma consulta: http://localhost:8080/api/consulta

PUT

* Atualizar consulta: http://localhost:8080/api/consulta/1

DELETE

* Deletar uma consulta: http://localhost:8080/api/consulta/1




ROTAS UTILIZADAS NO POSTMAN PARA MEDICO: 

GET

* Mostra um médico: http://localhost:8080/api/medico/1
* Lista todos os médicos: http://localhost:8080/api/medico

POST

* Criar um médico: http://localhost:8080/api/medico

PUT

* Atualizar médico: http://localhost:8080/api/medico/1

DELETE

* Deletar um médico: http://localhost:8080/api/medico/1