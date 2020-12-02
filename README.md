# Desafio Android do Jeitto

## Bem vindo ao desafio de Android do Jeitto.
O objetivo deste desafio é avaliar suas habilidades e conhecimentos de arquiteturas, bibliotecas e ferramentas de desenvolvimento de aplicativos para Android.

Antes de iniciar, leia todo este guia e tenha certeza de que entendeu o desafio proposto. Acima de tudo, tire o máximo de proveito do tempo que gastar com este desafio para aprender e aprimorar seus conhecimentos! Seu esforço não será em vão.

Em caso de dúvida ou se tiver alguma sugestão, entre em contato conosco.

Boa sorte!
#
## Desafio: Criar um aplicativo de listagem de notícias sobre exploração espacial.<br><br>

## **Deseign e comportamento:**<br>

- O design das telas do aplicativo pode ser encontrado na pasta ___Design___
- A primeira tela do aplicativo contém uma toolbar com título, um campo de pesquisa e a listagem de notícias.
    - Título da tela: ___Notícias___
    - Ao entrar na tela, deve ser exibido um loading em tela cheia enquanto os dados não são retornados.
    - Ao entrar na tela, deve ser mostrada uma lista de notícias com no máximo 15 itens retornados da API.
    - Cada item da lista contém a imagem, título e o nome do site fonte da notícia. (`imageUrl, title e newsSite` respectivamente)
    - Ao chegar no fim da lista, é feita uma paginação, realizando uma nova requisição a API e carregando mais 15 itens.
    - O campo de pesquisa suporta no máximo 10 caracteres.
    - Ao digitar no campo de pesquisa, deve ser feita uma filtragem nos **títulos** dos itens já carregados, fazendo com que a lista mostre somente os itens cujo os títulos contenham o conteúdo digitado no campo.
    - Ao apagar todo o conteúdo do campo de pesquisa, devem ser exibidos todos os itens carregados anteriormente.
- Ao clicar em uma notícia, é apresentado um Dialog com os detalhes e as opções ___Voltar___ e ___Ler artigo___
    - O Dialog contém o título, sumário e imagem da notícia. (`title, summary e imageUrl` respectivamente)
    - Ao clicar em ___Voltar___ ou ao clicar fora do Dialog, o mesmo é fechado.
    - Ao clicar em ___Ler artigo___, deve ser aberto o navegador padrão do dispositivo no link contido no campo `url` retornado pela API.
- Fluxos alternativos
    - Caso a API retorne um erro (HTTP 4xx ou 5xx), deve ser exibido um dialog de erro.
        - Título: ___Não foi possível carregar as notícias___
        - Mensagem: De acordo com o campo `message` do retorno de erro.
        - Botões:
            - ___OK___: O aplicativo é fechado.
<br><br>

## **API e retornos**:<br>

- Os dados devem ser consumidos da API da [Spaceflight News API](https://www.spaceflightnewsapi.net/)
    - URL Base: `https://spaceflightnewsapi.net`
    - Listagem de notícias: `GET /api/v2/articles`
    - Parâmetros (query): 
        - `_limit` (`integer`) - Número máximo de notícias retornadas.
        - `_start` (`integer`) - Pula uma quantidade específica de notícias retornadas (Útil para paginação)
    - Exemplo de retorno de sucesso:
        ```JSON
        [
            {
                "id": "5fc7182a07d383001ba86473",
                "title": "Soyuz rocket launches Emirati military satellite after lengthy delay",
                "url": "https://spaceflightnow.com/2020/12/02/....",
                "imageUrl": "https://mk0spaceflightnoa02a.../2020/11/vs24_quick1.jpg",
                "newsSite": "Spaceflight Now",
                "summary": "After months of delays caused by launch vehicle issues and the coronavirus...",
                "publishedAt": "2020-12-02T04:29:30.000Z",
                "updatedAt": "2020-12-02T04:29:30.633Z",
                "featured": false,
                "launches": [],
                "events": []
            },
            ...
        ]
        ```
    - Exemplo de retorno de erro:
        ```JSON
        {
            "statusCode": 0,
            "error": "string",
            "message": "string"
        }
        ```
<br><br>
## **Como nos enviar seu desafio pronto**
- Para participar você deverá fazer um fork deste repositório e submeter as alterações apenas para a sua cópia. Não faça um PR para este repositório, apenas envie um link para o avaliador que está em contato com você.

O que **queremos** ver:
- Java ou Kotlin
- Projeto organizado com arquitetura
- Tratamento de sucesso e erro ao buscar no backend

O que **gostariamos** ver:
- Kotlin
- Arquitetura MVVM
- Componentes do Jetpack
- Testes unitários
- Comentários em código

Perguntas que devem ser respondidas
- Quais foram os principais desafios durante o desenvolvimento?
- O que você escolheu como arquitetura/bibliotecas e por que?
- O que falta desenvolver / como poderiamos melhorar o que você entregou?