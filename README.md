# API RESTful para Agência de Viagens

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar destinos de viagem, permitindo cadastro, listagem, pesquisa, avaliação e exclusão de destinos.

---

## **Funcionalidades**

A API permite:
1. **Cadastrar Destinos de Viagem**
2. **Listar Todos os Destinos**
3. **Pesquisar Destinos por Nome ou Localização**
4. **Visualizar Informações Detalhadas de um Destino**
5. **Avaliar um Destino**
6. **Excluir um Destino**

---

## **Como Rodar o Projeto**

### **Pré-requisitos**
1. **Java Development Kit (JDK) 21** ou superior.
2. **Maven** (para gerenciamento de dependências).
3. **Postman** ou **Insomnia** (para testar os endpoints).

### **Passos para Rodar**
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/api-viagem.git
   cd api-viagem


Compile e rode o projeto usando o Maven:

mvn spring-boot:run

O servidor estará disponível na URL:

http://localhost:8080


Endpoints Disponíveis
1. Cadastrar Destino
Método: POST
URL: /api/destinos
Body (JSON):

{
  "nome": "Paris",
  "localizacao": "França",
  "descricao": "A cidade das luzes",
  "avaliacaoMedia": 0.0,
  "numeroAvaliacoes": 0
}


2. Listar Todos os Destinos
Método: GET
URL: /api/destinos

3. Pesquisar Destino por Nome ou Localização
Método: GET
URL: /api/destinos/search
Query Parameters:
nome ou localizacao

exemplo:

http://localhost:8080/api/destinos/search?nome=Paris

4. Visualizar Detalhes de um Destino
Método: GET
URL: /api/destinos/{id}
exemplo:
http://localhost:8080/api/destinos/1


5. Avaliar um Destino
Método: PATCH
URL: /api/destinos/{id}/avaliacao
Query Parameters:
nota: Nota de 1 a 10.

http://localhost:8080/api/destinos/1/avaliacao?nota=8.5


6. Excluir um Destino
Método: DELETE
URL: /api/destinos/{id}

http://localhost:8080/api/destinos/1


Tecnologias Utilizadas
Java 21
Spring Boot
Maven
