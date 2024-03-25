# Book Now

## Documentação

- Swagger UI disponível na URL: http://localhost:8585/api/v1/swagger-ui.html
- http://localhost:8585/api/v1/swagger-ui/index.html
- Descrição do OpenAPI disponível na URL
    - Formato JSON: http://localhost:8585/api/v1/v3/api-docs
    - Formato YAML: http://localhost:8585/api/v1/v3/api-docs.yaml

## Executar a aplicação com Docker

* Utilizando wsl2

1. Abrir o prompt de comando
2. Ir no diretório que está o arquivo docker-compose.yaml
3. Abrir o wsl: `wsl`
4. Iniciar o docker: `sudo service docker start`
5. Criar imagem:
6. Executar o arquivo docker compose: `docker compose up -d`
7. Pausar o docker: `sudo service docker stop`
8. Reiniciar o docker: `sudo service docker restart`

### Backlog detalhado

1. Criar projeto Java Spring com as dependências mínimas necessárias.
2. Configurar a aplicação e executar

| Método | Base path                          | Descrição                                       |
|:-------|:-----------------------------------|:------------------------------------------------|
| POST   | /login                             | Autenticar usuário                              |
| POST   | /logout                            | Sair da aplicação                               |
| POST   | /users                             | cadastro de usuário                             |
| GET    | /users                             | listar usuários                                 |
| GET    | /users/{id}                        | buscar usuário (filtro simples)                 |
| PUT    | /users/{id}                        | edição/atualização do usuário                   |
| DELETE | /users/{id}                        | exclução do usuário                             |
| POST   | /users/{id}                        | ativar/desativar usuário                        |
| GET    | /users?username=xx                 | filtrar usuário por username/email              |
| POST   | /hoteis                            | cadastro de hotel                               |
| GET    | /hoteis                            | listar hotéis                                   |
| GET    | /hoteis/{id}                       | buscar hotel (filtro simples)                   |
| PUT    | /hoteis/{id}                       | edição/atualização do hotel                     |
| DELETE | /hoteis/{id}                       | exclução do hotel                               |
| POST   | /hoteis/{id}                       | ativar/desativar hotel                          |
| GET    | /hoteis?name=xx                    | filtrar hotel por nome/localização              |
| POST   | /bedrooms                          | cadastro de quarto                              |
| GET    | /bedrooms                          | listar quartos                                  |
| GET    | /bedrooms/{id}                     | buscar quarto (filtro simples)                  |
| PUT    | /bedrooms/{id}                     | edição/atualização do quarto                    |
| DELETE | /bedrooms/{id}                     | exclução do quarto                              |
| POST   | /bedrooms/{id}                     | ocupar/desocupar quarto                         |
| GET    | /bedrooms?number=xx                | filtrar quarto por ocupado/desocupado/número    |
| GET    | /hoteis/{id}/bedrooms              | buscar hotel e selecionar os quartos desocupado |
| GET    | /hoteis/{id}/bedrooms/{id}/reserva | reserva/cancelamento de hotel                   |
| GET    | /hoteis/{id}?date=00/00/0000       | filtrar datas                                   |

## Links

- [Integração com docker-compose no Spring Boot 3.1](https://medium.com/@valdemarjuniorr/integra%C3%A7%C3%A3o-com-docker-compose-no-spring-boot-3-1-b0bea36c9549)
- [Using Docker Compose in Spring Boot 3](https://howtodoinjava.com/spring-boot/spring-boot-docker-compose/)
- [Beans Validation - Solucionando problemas de integridade dos dados persistentes](https://www.devmedia.com.br/beans-validation-solucionando-problemas-de-integridade-dos-dados-persistentes/27733)

---

### Get Production-ready with Spring Boot Actuator

* **Spring Boot Actuator**: provides Spring Boot's production-ready features
    * Monitor and manage your application in your production
* **Spring Boot Starter Actuator**: Starter to add Spring Boot Actuator to your application
    * spring-boot-starter-actuator
* Provides a number of endpoints:
    * **beans**: Complete list of Spring beans in your app
    * **health**: Application health information
    * **metrics**: Application metrics
    * **mappings**: Details around Request Mappings
    * and a lot more ...






