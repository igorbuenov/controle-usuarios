# Controle de usuários

## Sobre o projeto

Afim de colocar em prática os conhecimento adquiridos nos cursos de Java básico da Loiane Groner e Java do Nélio Alves, utilizei um escopo fornecido pelo desenvolvedor Lucas Carrilho no seguinte arquivo: https://docs.google.com/document/d/12ek1Wsd_ibuwTOjHtLPZwEWdy5-A7cRoO2Bf-v5G1_s/edit
, a partir da página 6. Um projeto onde se aborda conhecimentos sobre os conceitos:
- Orientação a objetos (POO)
- Java io
- Stream e lambda
- Exceptions
- Boas práticas de código
- MVC

O objetivo proposto é de criar uma aplicação onde se pode cadastrar um usuário utilizando como base um formulário de perguntas pré-definidas, armazená-los em arquivos com extensão .txt, com a seguinte regra, 1 arquivo para cada usuário salvo no seguinte formato: 01-IGORBUENOVENTURA.txt. 
#### Exemplo:
##### Perguntas:
- ![image](https://github.com/igorbuenov/controle-usuarios/assets/98808773/85015614-4dad-4b8b-a5eb-13aea3509fb5)
##### Armazenamento:
- ![image](https://github.com/igorbuenov/controle-usuarios/assets/98808773/30fad396-a793-42df-9027-72981733e098)



### FUNCIONALIDADES DO PROGRAMA:

- Cadastrar usuário
- Listar todos os usuários cadastrados
- Cadastrar nova pergunta no formulário
- Deletar pergunta do formulário
- Pesquisar usuário por nome, idade ou email

### REGRAS DE NEGÓCIO:

OBS: Para todas as regras de cadastros de usuários não atendidas, uma exception é lançada exceto para a listagem de perguntas.

#### Cadastros de usuários:
  - Listar todas as perguntas em ordem numérica de forma automática. 
  - Nome completo, conter 10 ou mais caracteres.
  - Email padronizado: "exemplo@email.com".
  - Idade maior ou igual a 18 anos.
  - Altura sempre fornecida pelo padrão brasileiro: 1,75.

#### Listar usuários cadastrados:
  - Apenas o nome dos usuários e em ordem numérica: 01 - Igor Bueno Ventura

#### Cadastrar nova pergunta:
  - Informe a nova pergunta e salve-a no arquivo onde já estão as questões pré-definidas.

#### Deletar uma pergunta:

OBS: Obrigatóriamente as perguntas pré-definidas não podem ser deletadas!

  - Liste as perguntas cadastradas e informe o número da pergunta a ser deletada. Lembrando que se o número digitado for de uma das perguntas pré definidas, o programa não deve permitir que a mesma seja deletada.

#### Pesquisar usuário por nome, idade ou email:
  - Informe a palavra ou parte dela que deseja pesquisar e retorne os dados dos usuários que contenham a palavra digitada.
  - ![image](https://github.com/igorbuenov/controle-usuarios/assets/98808773/5b1ed8a1-247d-4e53-b35b-44c3b6349809)

### INFORMAÇÕES TÉCNICAS

O projeto foi estruturado em um padrão MVC para melhor manipulação dos dados, fácil manutenção e também visando um um upgrade de implementação futuramente, onde pretendo integrar um banco de dados e outras funcionalidades que a linguagem pode proporcionar.
A estrutura de pastas ficou da seguinte forma:

![image](https://github.com/igorbuenov/controle-usuarios/assets/98808773/86bd0f3d-7207-45d4-93ee-54229cfa814d)


- application: Onde o programa é iniciado por uma única chamada do método Layout.index() dentro da classe App.
- layout(view): Onde fica a visualização de todas as funcionalidades do programa para o usuário final na classe Layout.
- model: Onde ocorre o desenvolvimento do sistema e toda a manipulação de dados.

#### Considerações sobre exceptions e helpers
- Exceptions: utilizei exceptions personalizadas para atender as regras de negócios proposta pelo programa.
- Helpers: a intenção foi de criar métodos auxiliares para utilizar em algumas partes que o programa precisasse como por exemplo para o salvamento de arquivos onde encontrei o problema de palavras acentuadas ou com caracteres que existem apenas no nosso idioma e fiz o método regexStringFormatting() na classe FilesHelpers, que remove a acentuação, troca as letras como "ç" e as substitui por "c" e também remove os espaços. Para fins de testes o seguinte nome foi informado, Finalização Teste: 
  - Antes da implementação do método o arquivo era salvo da seguinte forma: 01-FINALIZAOTESTE.txt
  - Após a implementação, ficou da seguinte maneira: 01-FINALIZACAOTESTE.txt
 
### CONSIDERAÇÕES FINAIS:
O programa ainda tem muita margem para crescimento e mudança. Estou ativamente estudando e vou buscar implentar novas soluções de desenvolvimento de acordo que vou conhecendo mais a fundo a linguagem e ferramentas de desenvolvimento. Já está pronto para uso mas longe de ser finalizado,
ainda pretendo utilizar o JavaFX para se ter uma interface visual mais agradável podendo utilizar um pouco de conceitos UX, e também será feita um integração com banco e dados para que o programa se torne um utilitário mais próximo de um uso real. E é só o começo!

## Autor
#### Igor Bueno Ventura
Linkedin: https://www.linkedin.com/in/igorbuenov/

e-mail: ib_ventura@hotmail.com
