Feature: Gerenciamento de usuários

  Scenario: Buscar usuário existente por código
    Given existe um usuário com código 1 e nome "User 1"
    When o serviço busca o usuário com código 1
    Then o usuário retornado deve ter código 1
    And o nome deve ser "User 1"

  Scenario: Lançar exceção ao buscar usuário inexistente
    Given não existe um usuário com código 1
    When o serviço busca o usuário com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada

  Scenario: Criar um novo usuário
    Given um novo usuário com código 1 e nome "User 1"
    When o serviço salva o usuário
    Then o usuário criado não deve ser nulo
    And deve ter código 1

  Scenario: Atualizar um usuário existente
    Given existe um usuário com código 1
    When o serviço atualiza o usuário com código 1
    Then o usuário atualizado deve ter código 1
    And não deve ser nulo

  Scenario: Lançar exceção ao atualizar usuário inexistente
    Given não existe um usuário com código 1
    When o serviço tenta atualizar o usuário com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada

  Scenario: Deletar um usuário existente
    Given existe um usuário com código 1
    When o serviço deleta o usuário com código 1
    Then o usuário deve ser removido com sucesso

  Scenario: Lançar exceção ao deletar usuário inexistente
    Given não existe um usuário com código 1
    When o serviço tenta deletar o usuário com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada
