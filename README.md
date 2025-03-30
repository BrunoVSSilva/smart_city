
Olá, bem vindo ao sistema de avaliação e smartcities, olhe avaliações ou crie avaliações se você for o admin ;)

Para utilização, é necessário colocar as validações do postman
as URLs são padrões são:

get:
localhost:8080/avaliacao
localhost:8080/cidade

post:
localhost:8080/avaliacao
localhost:8080/cidade

formato do JSON para realizar o CREATE
{
"schoolAvaliation": "",
"securityAvaliation": "",
"healthAvaliation": "",
"cityId": 1
}

put:
localhost:8080/avaliacao/{codigo do item que quer ser alterado}
localhost:8080/cidade/{codigo do item que quer ser alterado}

formato do JSON para realizar o UPDATE
{
"schoolAvaliation": "",
"securityAvaliation": "",
"healthAvaliation": "",
"cityId": 1
}

delete:
localhost:8080/avaliacao/{codigo do item que quer ser alterado}
localhost:8080/cidade/{codigo do item que quer ser alterado}
