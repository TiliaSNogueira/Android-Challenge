# Desafio Android do Jeitto

## Bem-vindo/a ao desafio de Android do Jeitto.
O objetivo deste desafio é avaliar suas habilidades e conhecimentos de arquiteturas, bibliotecas e ferramentas de desenvolvimento de aplicativos para Android.

Antes de iniciar, leia todo este guia e tenha certeza de que entendeu o desafio proposto. Acima de tudo, tire o máximo de proveito do tempo que gastar com este desafio para aprender e aprimorar seus conhecimentos! Seu esforço não será em vão.

Esse desafio deve ser feito e entregue no prazo de 1 - 2 semanas, contando a partir do momento em que ele é recebido. Caso precise de uma extensão, entre em contato conosco que iremos avaliar seu caso, mas entenda que a extensão será concedida somente em casos excepcionais. Mesmo que você não consiga terminar o projeto todo a tempo, mande o código para avaliação mesmo assim, e é importante que ele esteja funcionando, mesmo que incompleto.

Em caso de dúvida, se tiver alguma sugestão, ou encontrar algum problema, fique à vontade para entrar em contato conosco nos e-mails rafael.caetano@jeitto.com.br ou fabio.rachid@jeitto.com.br.

Boa sorte!

#
## Desafio: Criar um aplicativo de listagem de notícias sobre exploração espacial.<br><br>

## **Design e comportamento:**<br>

- O design das telas do aplicativo pode ser encontrado na pasta ___Design___. É importante ressaltar que o design não precisa ser idêntico ao que especificamos, você pode fazer alterações pequenas relacionadas ao estilo dos componentes (campo de busca, cards...), mas sem alterar o conteúdo ou comportamento deles, como descrito a seguir. Lembre-se de que o desafio possui um prazo, então procure balancear a qualidade do código e da UI e o tempo usado. Entender e atingir esse equilíbrio são habilidades importantes que usamos no dia-a-dia da empresa.
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

## **Uso de bibliotecas**

- Você pode usar bibliotecas que já são ou se tornaram parte do ecossistema Android para desenvolvimento. Exemplos de bibliotecas que podem ser usadas:
   - Bibliotecas de widgets do próprio Google (que possui botões, campos de texto e etc), **mas a construção de layouts e estilização devem ser suas**;
   - Retrofit ou outra para fazer chamadas para a API;
   - Componentes que estão no Android Jetpack, como o ViewModel;
   - Glide ou Picasso para download e renderização de imagens;
   - RxJava ou outra para programação reativa

e etc. Porém, não queremos que você use algumas bibliotecas que entreguem soluções prontas, como por exemplo uma biblioteca que crie uma lista configurada automaticamente para você, ou uma biblioteca que te forneça um elemento visual, como o da listagem, já pronto, sem que você tenha que construir o layout.

Se surgir alguma dúvida quanto ao que pode ou não ser usado, não hesite em nos contatar.

<br><br>
## **Como nos enviar seu desafio pronto**
- Para participar:
   - Faça um fork deste repositório e submeta as alterações apenas para a sua cópia. 
   - O seu README deve ter todas as instruções sobre o que deve ser configurado e como rodar o projeto. 
   - **O código deve compilar e rodar.** Caso as instruções estejam claras, e o projeto não compile, iremos entrar em contato com você. Caso o README esteja sem qualquer instrução sobre como rodar o projeto e ele não compile, não será possível garantir que analisaremos e testaremos o projeto.
   - Todo o código deve ser feito em inglês. 
   - Não faça um PR para este repositório, apenas envie o link para rafael.caetano@jeitto.com.br e/ou fabio.rachid@jeitto.com.br

O que **esperamos** ver:
- Java ou Kotlin
- Projeto organizado com arquitetura
- Tratamento de sucesso e erro ao buscar no backend
- Comentários em código, **mas lembre que os comentários no código devem ser auxiliares, o código deve ser auto-explicativo**

O que **nos impressionará**:
- Kotlin
- Arquitetura MVVM
- Componentes do Jetpack
- Componentização de views customizadas
- Testes unitários

Perguntas que devem ser respondidas
- Quais foram os principais desafios durante o desenvolvimento?
- O que você escolheu como arquitetura/bibliotecas e por que?
- O que falta desenvolver / como poderiamos melhorar o que você entregou, caso não tenha tido tempo suficiente para terminar o desafio?

Caso tenha escrito ou desenhado algo, como diagramas, ou usado alguma outra ferramenta como o Postman para testar a API, e sentir que isso pode contribuir para explicar melhor seu código, fique à vontade para citar ou colocar no README do seu projeto. **Nosso foco principal será avaliar o código e sua compreensão do que fez**, mas estamos interessados também em entender seu processo de pensamento e estruturação da solução também.

## **Código de conduta**

Esperamos que o código seja feito 100% por você. Não há problema nenhum em estudar referências, pesquisar no Stack Overflow e tirar dúvidas com colegas, porém todo o código deve ser feito completamente por você, sozinho/a. Esperamos que, além de chegar a uma solução, você consiga **explicar** as decisões tomadas.

## **Observações**

- A solução é importante, mas o mais importante para nós é entender se você se atentou a alguns pontos, como os mencionados anteriormente, e se você entendeu e consegue explicar o seu código.
- Sabemos que desafios como esse podem te deixar ansioso/a, mas não se preocupe! O intuito não é fazer uma avaliação calculista e extremamente detalhista sobre seu código, mas sim conversarmos e discutirmos sua implementação e sua forma de pensar :). 
