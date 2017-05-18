# loja-api

Api pode ser acessada https://fiap-ws-loja.herokuapp.com/application.wadl

# /produtos

`curl 'https://fiap-ws-loja.herokuapp.com/produtos'`

# /simular-compra

`curl -X POST -H "Content-Type: application/json" -d '[{"codigo": 1}, {"codigo": 8}]' 'https://fiap-ws-loja.herokuapp.com/simular-compra'`

# /efetuar-compra

`curl -X POST -H "Content-Type: application/json" -d '{"documento": "12345678912","tipoDocumento": "CPF", "nomeCliente": "Teste", "listaProdutos": [{"codigo": 1}, {"codigo": 2}]}' 'https://fiap-ws-loja.herokuapp.com/efetuar-compra'`

