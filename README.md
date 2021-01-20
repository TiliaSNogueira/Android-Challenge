## Space Flight

# Aplicativo que lista notícias relacionadas à exploração espacial consumindo dados da Spaceflight News API. 


#Apresentação do aplicativo:
O aplicativo é composto de 2 activities (uma tela de abertura e uma tela principal).
Quando o usuário abre o aplicativo, a tela de abertura é exibida durante 2 segundos e em seguida a tela principal é exibida.

Na activity main (tela principal) é exibida uma progressbar enquanto os dados estão sendo requisitados na API. Assim que o retorno é obtido, a progressbar é dispensada e a lista de notícias é exibida para o usuário. Se ocorre erro durante a requisição, é exibido um alert dialog dizendo que não foi possivel carregar as notícias.

No quesito interação com a lista, temos seguintes comportamentos:
1. Clique no item: ao clicar em um item da lista, é exibido um fragment dialog com imagem, título e sumário da notícia (com scroll para rolar o texto so smuario se for longo). Nele também encontramos dois botões, o botão "VOLTAR" que retorna para a lista completa, e o botão "LER MAIS" que abre o navegador do dispositivo exibindo a notícia completa no site em que foi publicada.
2. Pesquisa : ao clicar na barra de pesquisa, a lista é filtrada, exibindo os itens cujo título contém as letras digitadas na barra.
3. Rolagem : a requisição foi configurada pra buscar 15 notícias por vez, então quando o usuário desliza a lista para baixo é necessário trazer mais notícias além das 15 primeiras. Cada vez que a lista exibe um último item desse conjunto de 15 notícias, é feita uma nova requisição, e mais 15 notícias são buscadas. Isso permite que o usuário tenha acesso a uma lista "infinita".

# Tecnologias usadas: 
- Kotlin
- MVVM
- Retrofit para requisição à API







 
 
 
 
