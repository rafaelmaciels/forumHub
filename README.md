# Desafio FórumHub API
API REST para uma plataforma de fórum de discussões, desenvolvida em Java com o framework Spring Boot. O projeto permite que usuários autenticados gerenciem tópicos e respostas, implementando um CRUD completo, um sistema de perfis de usuário e segurança robusta com tokens JWT.

*Por: Maria Eduarda Campos*
## Funcionalidades

-   **CRUD Completo de Tópicos**:
    -   Criação, listagem paginada, detalhamento, atualização e exclusão de tópicos.
-   **Gerenciamento de Respostas**:
    -   Permite publicar respostas em tópicos existentes.
-   **Gerenciamento de Usuários e Perfis**:
    -   Registro de novos usuários.
    -   Sistema flexível de perfis (Roles) para controle de acesso.
-   **Segurança e Autorização**:
    -   Autenticação stateless via token JWT.
    -   Endpoints protegidos que exigem autenticação.
    -   Regras de autorização (ex: somente o autor de um tópico pode editá-lo ou excluí-lo).
-   **Validações e Regras de Negócio**:
    -   Validação de dados de entrada e prevenção de tópicos duplicados.
    -   Relacionamentos consistentes entre Tópicos, Cursos, Usuários e Respostas.

## Tecnologias Utilizadas

-   **Linguagem**: Java 21
-   **Framework**: Spring Boot 3
-   **Segurança**: Spring Security
-   **Persistência**: Spring Data JPA / Hibernate
-   **Banco de Dados**: MySQL
-   **Migrações**: Flyway
-   **Autenticação**: JSON Web Token (JWT) com a biblioteca `java-jwt` da Auth0
-   **Validação**: Bean Validation
-   **Build/Dependências**: Maven
-   **Utilitários**: Lombok

## Endpoints da API

| Verbo HTTP | Endpoint                       | Descrição                                                              | Acesso    |
|------------|--------------------------------|------------------------------------------------------------------------|-----------|
| `POST`     | `/usuarios`                    | Registra um novo usuário no sistema.                                   | Público   |
| `POST`     | `/login`                       | Autentica um usuário (com email e senha) e retorna um token JWT.       | Público   |
| `POST`     | `/topicos`                     | Cria um novo tópico. O autor é o usuário autenticado.                  | Protegido |
| `GET`      | `/topicos`                     | Lista todos os tópicos (com paginação e ordenação).                    | Protegido |
| `GET`      | `/topicos/{id}`                | Detalha um tópico específico e suas respostas.                         | Protegido |
| `PUT`      | `/topicos/{id}`                | Atualiza um tópico (somente o autor).                                  | Protegido |
| `DELETE`   | `/topicos/{id}`                | Exclui um tópico (somente o autor).                                    | Protegido |
| `POST`     | `/topicos/{topicoId}/respostas`| Publica uma nova resposta em um tópico específico.                     | Protegido |

