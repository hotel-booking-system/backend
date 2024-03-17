# Book Now

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

