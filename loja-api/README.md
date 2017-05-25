# loja-api

Api pode ser acessada https://fiap-ws-loja.herokuapp.com/application.wadl

Para acesso mais amigável você pode usar a api do swagger que disponibilizamos.

Copie e cole o endereço `https://fiap-ws-loja.herokuapp.com/swagger.json` em `http://petstore.swagger.io/` depois clique em `"Explore"`

# /produtos

Siga os passos abaixo para executar a api:

 - Clique em `GET /produtos`.
 - Clique `"Try it out"`
 - Depois clique em `"Execute"`
 - O resultado será exibido em Response -> Details

# /simular-compra

Siga os passos abaixo para executar a api:

 - Clique em `POST /simular-compra`
 - Clique `"Try it out"`
 - Copie e cole `[{"codigo": 1}, {"codigo": 8}]` em **"Example Value"**
 - Depois clique em `"Execute"`
 - O resultado será exibido em Response -> Details


# /efetuar-compra

Siga os passos abaixo para executar a api:

 - Clique em `POST /simular-compra`
 - Clique `"Try it out"`
 - Copie e cole `{"documento": "12345678912","tipoDocumento": "CPF", "nomeCliente": "Teste", "listaProdutos": [{"codigo": 1}, {"codigo": 2}]}` em **"Example Value"**
 - Depois clique em `"Execute"`
 - O resultado será exibido em Response -> Details 
