Feature: Gerenciamento de avaliações de cidades

  Scenario: Buscar avaliação existente por código
    Given existe uma avaliação de cidade com código 1
    When o serviço busca a avaliação com código 1
    Then a avaliação retornada deve ter código 1

  Scenario: Lançar exceção ao buscar avaliação inexistente
    Given não existe uma avaliação de cidade com código 1
    When o serviço busca a avaliação com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada

  Scenario: Criar uma nova avaliação de cidade
    Given uma nova avaliação com código 1 e notas para escola, segurança e saúde
    When o serviço salva a avaliação
    Then a avaliação criada não deve ser nula
    And deve ter código 1

  Scenario: Atualizar uma avaliação existente
    Given existe uma avaliação de cidade com código 1
    When o serviço atualiza os dados da avaliação com código 1
    Then a avaliação atualizada deve ter código 1
    And não deve ser nula

  Scenario: Deletar uma avaliação existente
    Given existe uma avaliação de cidade com código 1
    When o serviço deleta a avaliação com código 1
    Then a avaliação deve ser removida com sucesso

  Scenario: Lançar exceção ao deletar avaliação inexistente
    Given não existe uma avaliação de cidade com código 1
    When o serviço tenta deletar a avaliação com código 1
    Then uma exceção do tipo EntityNotFoundException deve ser lançada
