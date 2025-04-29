Feature: Gerenciamento de usuários via API REST

  Scenario: Listar todos os usuários
    Given existem usuários chamados "Alice" e "Bob"
    When uma requisição GET é feita para "/users"
    Then o status da resposta deve ser 200 OK
    And o corpo da resposta deve conter uma lista com 2 usuários
    And o primeiro usuário deve ter o nome "Alice"
    And o segundo usuário deve ter o nome "Bob"

  Scenario: Criar um novo usuário
    Given um novo usuário com nome "Charlie" e role "USER"
    When uma requisição POST é feita para "/users" com os dados do usuário
    Then o status da resposta deve ser 201 Created
    And o corpo da resposta deve conter code 1, nome "Charlie" e role "USER"

  Scenario: Atualizar um usuário existente
    Given existe um usuário com código 1
    When uma requisição PUT é feita para "/users/1" com nome "Alice Updated" e role "USER"
    Then o status da resposta deve ser 200 OK
    And o corpo da resposta deve conter nome "Alice Updated" e role "USER"

  Scenario: Tentar atualizar um usuário inexistente
    Given não existe um usuário com código 1
    When uma requisição PUT é feita para "/users/1"
    Then o status da resposta deve ser 404 Not Found
    And o corpo da resposta deve conter a mensagem "Usuário com código 1 não encontrado"

  Scenario: Deletar um usuário existente
    Given existe um usuário com código 1
    When uma requisição DELETE é feita para "/users/1"
    Then o status da resposta deve ser 204 No Content

  Scenario: Tentar deletar um usuário inexistente
    Given não existe um usuário com código 1
    When uma requisição DELETE é feita para "/users/1"
    Then o status da resposta deve ser 404 Not Found
    And o corpo da resposta deve conter a mensagem "Usuário com código 1 não encontrado"
