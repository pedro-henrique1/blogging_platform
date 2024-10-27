# Blogging Platform API

Aqui está o texto ampliado:

Uma API RESTful robusta com operações CRUD completas voltada para uma plataforma de blog pessoal. Esta API permite que
os usuários realizem operações fundamentais: Criar, Ler, Atualizar e Excluir postagens de blog, oferecendo um conjunto
de funcionalidades essenciais para gerenciar o conteúdo de forma eficiente e organizada. A API foi projetada com foco em
boas práticas e convenções RESTful, garantindo facilidade de uso e manutenção, além de possibilitar uma comunicação
clara entre o cliente e o servidor por meio de métodos HTTP intuitivos e consistentes.

# Funcionalidades

- Crie uma nova postagem no blog
- Atualizar uma postagem de blog existente
- Excluir uma postagem de blog existente
- Obtenha uma única postagem no blog
- Obter todos os posts do blog
- Filtrar postagens de blog por um termo de pesquisa

# Requisitos

- É necessario ter o [java](https://www.java.com/download/ie_manual.jsp) instalado em sua maquina para rodar o programa
  e também o [maven](https://maven.apache.org/install.html).
- A aplicação pode ser executada tanto por meio do [docker](https://docs.docker.com/engine/install/), facilitando o
  isolamento e a consistência do ambiente, quanto diretamente pela sua IDE de desenvolvimento, dependendo da sua
  preferência e necessidade.

# Instalação

# Instalação

- Clone o repositório

```git
  git clone https://github.com/pedro-henrique1/blogging_platform
  cd blogging_platform
```

- Configure as variáveis para a conexão do banco

```
  cd src/main/resources
  
  cp example.application.properties  application.properties
```

- Construção e execução com Docker:

```
  docker build -t java .
  docker compose up --build
```

# Como Fazer Requisições

- Para criação de um post

 ``` 
POST /posts
{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}

```

- Para atualização de um post

 ``` 
PUT /posts/1
{
  "title": "My Updated Blog Post",
  "content": "This is the updated content of my first blog post.",
  "category": "Technology",
  "tags": [
    "Tech",
    "Programming"
  ]
}

```

- Para deletar um post

``` 
    DELETE /posts/1
```

- Obter Todos os Posts do Blog

 ```  
GET /posts
[
  {
    "id": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "category": "Technology",
    "tags": [
      "Tech",
      "Programming"
    ],
    "createdAt": "2021-09-01T12:00:00Z",
    "updatedAt": "2021-09-01T12:00:00Z"
  },
  {
    "id": 2,
    "title": "My Second Blog Post",
    "content": "This is the content of my second blog post.",
    "category": "Technology",
    "tags": [
      "Tech",
      "Programming"
    ],
    "createdAt": "2021-09-01T12:30:00Z",
    "updatedAt": "2021-09-01T12:30:00Z"
  }
]

```

- Para filtra post por termo

``` 
  GET /posts?term=tech
```











