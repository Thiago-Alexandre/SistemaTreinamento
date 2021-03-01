# SistemaTreinamento

## API Rest para Gerenciamento de Pessoas em Treinamento

### Problema

A ProWay vai realizar um treinamento para uma grande empresa de TI de Blumenau, especializada em softwares de gestão. O treinamento será realizado em 2 etapas e as pessoas serão divididas em salas com lotação variável. Serão realizados também dois intervalos de café em 2 espaços distintos. Você precisa criar o sistema que gerenciará este evento.
A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa. Para estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala entre as duas etapas do treinamento.
Ao consultar uma pessoa cadastrada no treinamento, o sistema deverá retornar à sala em que a pessoa ficará em cada etapa e o espaço onde ela realizará cada intervalo de café.
Ao consultar uma sala cadastrada ou um espaço de café, o sistema deverá retornar uma lista das pessoas que estarão naquela sala ou espaço em cada etapa do evento.

#### Requisitos obrigatórios:

* O cadastro de pessoas, com nome e sobrenome;
* O cadastro das salas do evento, com nome e lotação;
* O cadastro dos espaços de café com lotação;
* A consulta de cada pessoa;
* A consulta de cada sala e espaço;

### Implementação

O Sistema foi desenvolvido na linguagem Java, utilizando Spring Data e Spring Web. A modelagem de dados foi projetada utilizando JPA. Já foi gerado o .jar do projeto, o qual pode-se ser executado. Para isso, é necessário possuir um banco de dados como configurado no projeto (arquivo *application.properties*):

```
spring.datasource.url=jdbc:mariadb://localhost:3306/controle_treinamento
spring.datasource.username=root
spring.datasource.password=thi123
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Obs: É possível alterar o banco e gerar um novo .jar do projeto.

O sistema consiste em uma API Rest, com rotas para salvar novas pessoas, salas, espaços de café e etapas. Ao adicionar esses dados, o sistema defini as lotações das pessoas. Ese processo automático só será executado quando houver pelo menos 1 de cada dado. A partir daí, pode-se adicionar quantas pessoas, salas, espaços de café e etapas forem necessários.

### Uso da API

Com o servidor em execução, basta acessar os endPoint a seguir para consumir a API:

<table>
	<thead>
		<th>EndPoint</th>
		<th>Método HTTP</th>
		<th>Formato JSON para envio</th>
		<th>Resultado</th>
	</thead>
	<tbody>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/pessoa"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma lista de pessoas salvas, mostrando apenas seu nome e sobrenome</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/pessoa"</td>
			<td>POST</td>
			<td>{"nome":"(Texto)","sobrenome":"(Texto)"}</td>
			<td>Salvará uma nova pessoa e retornará seus dados salvos</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/pessoa/ + (Numero Inteiro)"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma pessoa salva, mostrando seu nome, sobrenome e uma lista contendo a sala e o espaço de café que estará em cada etapa salva</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/sala"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma lista de salas salvas, mostrando apenas seu nome</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/sala"</td>
			<td>POST</td>
			<td>{"nome":"(Texto)"}</td>
			<td>Salvará uma nova sala e retornará seus dados salvos</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/sala/ + (Numero Inteiro)"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma sala salva, mostrando seu nome e uma lista contendo as pessoas que estarão presentes em cada etapa salva</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/etapa"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma lista de etapas salvas, mostrando apenas seu nome</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/etapa"</td>
			<td>POST</td>
			<td>{"nome":"(Texto)"}</td>
			<td>Salvará uma nova etapa e retornará seus dados salvos</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/etapa/ + (Numero Inteiro)"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma etapa salva, mostrando seu nome</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/espaco"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará uma lista de espaços de café salvos, mostrando apenas seu nome</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/espaco"</td>
			<td>POST</td>
			<td>{"nome":"(Texto)"}</td>
			<td>Salvará um novo espaço de café e retornará seus dados salvos</td>
		</tr>
		<tr>
			<td>"http://127.0.0.1:8000/SistemaTreinamento/espaco/ + (Numero Inteiro)"</td>
			<td>GET</td>
			<td>Não há</td>
			<td>Retornará um espaço de café salvo, mostrando seu nome e uma lista contendo as pessoas que estarão presentes em cada etapa salva</td>
		</tr>
	</tbody>
</table>