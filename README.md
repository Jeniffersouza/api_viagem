# API RESTful para Agência de Viagens

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar destinos de viagem, permitindo cadastro, listagem, pesquisa, avaliação e exclusão de destinos.

---

## **Funcionalidades**

A API permite:
1. **Cadastrar Destinos de Viagem**
2. **Listar Todos os Destinos**
3. **Pesquisar Destinos por Nome **
4. **Visualizar Informações Detalhadas de um Destino**
5. **Excluir um Destino**
6. **Listar usuario**
7. **Cadastrar usuario**
   

---

## **Como Rodar o Projeto**

### **Pré-requisitos**
1. **Java Development Kit (JDK) 21** ou superior.
2. **Maven** (para gerenciamento de dependências).
3. **Postman** ou **Insomnia** (para testar os endpoints).

### **Passos para Rodar**
1. Clone o repositório:
   ```bash
   git clone https://github.com/Jeniffersouza/api_viagem.git
   cd api_viagem


Compile e rode o projeto usando o Maven:

mvn spring-boot:run

O servidor estará disponível na URL:

http://localhost:8081

--- POR FAVOR USAR OS EXEMPLOS DO BODY, NÃO TEM MUITAS VALIDAÇÕES AINDA
Endpoints Disponíveis


1. Cadastrar usuario
Método: POST
URL: /api/usuarios/cadastrar
Body (JSON):
{
  "username": "novo_usuario",
  "password": "senha_forte",
  "perfil": "ADMIN"
}


----
2. Cadastrar destino
Método: POST
URL: /api/destinos
Auth: basic com o username e password  q vc cadastrou, admin/user (tanto faz)
Body (JSON):
{
  "nome": "criciuma",
  "localizacao": "Brasil",
  "descricao": "capital do oeste de sc",
  "avaliacaoMedia": 7.5,
  "numeroAvaliacoes": 2
}


3. Listar Todos os Destinos
Método: GET
URL: /api/destinos

3. Pesquisar Destino por Nome 
Método: GET
URL: /api/destinos/pesquisa
Query Parameters:
nome 

exemplo:

http://localhost:8081/api/destinos/pesquisa?query=chapeco



4. Visualizar Detalhes de um Destino
Método: GET
URL: /api/destinos/{id}
exemplo:
http://localhost:8081/api/destinos/1




5. Excluir um Destino
Método: DELETE
Auth: basic com o usuario q vc cadastrou, admin/user (tanto faz)
URL: /api/destinos/delete/{id}
http://localhost:8081/api/destinos/delete/1

-----


Tecnologias Utilizadas
Java 21
Spring Boot
Maven
