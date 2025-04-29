Feature: Gerenciamento de cidades

  Scenario: Buscar cidade existente por código
    Given existe uma cidade com código 1 e nome "City 1"
    When o serviço busca a cidade com código 1
    Then a cidade retornada deve ter código 1
    And o nome deve ser "City 1"

  Scenario: Lançar exceção ao buscar cidade inexistente
    Given não existe uma cidade com código 1
    When o serviço busca a cidade com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada

  Scenario: Criar uma nova cidade
    Given uma nova cidade com código 1 e nome "City 1"
    When o serviço salva a cidade
    Then a cidade criada não deve ser nula
    And deve ter código 1

  Scenario: Atualizar uma cidade existente
    Given existe uma cidade com código 1
    When o serviço atualiza a cidade com código 1
    Then a cidade atualizada deve ter código 1
    And não deve ser nula

  Scenario: Lançar exceção ao atualizar cidade inexistente
    Given não existe uma cidade com código 1
    When o serviço tenta atualizar a cidade com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada

  Scenario: Deletar uma cidade existente
    Given existe uma cidade com código 1
    When o serviço deleta a cidade com código 1
    Then a cidade deve ser removida com sucesso

  Scenario: Lançar exceção ao deletar cidade inexistente
    Given não existe uma cidade com código 1
    When o serviço tenta deletar a cidade com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada
