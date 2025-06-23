# JAVAPROJECT

Utilizando algumas tecnologias do java para fazer uma interface e a conexão do banco de dados de maneira tradicional.

## Tecnologias Utilizadas

- **Java** - Linguagem principal do projeto.
- **JavaFX** - Biblioteca para construção da interface gráfica (GUI).
- **Maven** - Gerenciador de dependências e automação de build.

## Banco de Dados

- **PostgreSQL** - Sistema de gerenciamento de banco de dados relacional utilizado para persistência de dados.

## Dependências Principais

Este projeto utiliza as seguintes bibliotecas e frameworks:

- **JavaFX** (interfaces gráficas):
    - `javafx-controls`
    - `javafx-fxml`, `javafx-media`, `javafx-swing`
- **JUnit 5** (testes):
    - `junit-jupiter-api`
    - `junit-jupiter-engine`
- **PostgreSQL Driver**:
    - `postgresql`
- **dotenv-java**:
    - `dotenv-java` - para gerenciamento de variáveis de ambiente

> As dependências são gerenciadas atrevés do Maven. Execute "mvn clean install" para baixar e configurar automaticamente.



## ⚙️ Pré-requisitos

Antes de rodar o projeto, se faz necessário ter em sua máquina, instalado:

- [JDK (24.0.1 ou superior)](https://www.oracle.com/java/technologies/downloads/)
- [Maven (3.9.10 ou superior)](https://maven.apache.org/download.cgi)
- [Git (2.50.0 ou superior)](https://git-scm.com/downloads)



## ▶️ Instalação do projeto

1. **Clone o repositório:**
   - Vá no seu terminal, escolha o diretório de sua preferência e execute:

````
   git clone thisRepository
````

2. **Acesse o diretório criado:**
````
   cd .\Diretory
````

5. **Compile o projeto com o Maven:**
````bash
   mvn compile
````

5. **Execute o projeto:**
   - Abra sua IDE preferida e importe o projeto.
   - Navegue até o arquivo `App.java` em:


````
  src\main\java\com\java\java\application
````

- Execute a classe `App.java`.

6. **Alternativa sem IDE (Windows):**
    - No diretório raiz do projeto, execute o atalho ou o arquivo .bat:
```
   run-javaProject.bat
````

7. **(Opcional) Execute no Terminal de Comando:**
    - Para iniciar o projeto no terminal de comando, se faz necessário está no diretório raiz do projeto e executar:
```bash
   mvn clean javafx:run
````



## 🐧 Estrutura de Diretório

Abaixo está a estrutura do projeto, com uma breve descrição de cada diretório e sua responsabilidade:

```
src/
└── main/
    ├── java/
    │   └── com.java.java/
    │       ├── application/            # Classe principal para iniciar o projeto
    │       ├── components/             # Elementos dinâmicos para a View com funções de interação
    │       ├── controllers/            # Controllers das telas (Home, User, Movie)
    │       ├── model/                  # Entidades do projeto
    │       │   └── enums/              # Enums utilizadas nas entidades
    │       ├── repositories/           # Classes de acesso ao banco de dados
    │       ├── service/                # Lógica de negócio do projeto
    │       ├── utils/                  # Utilitários como conexão com banco
    │       └── module-info.java        # Configuração do módulo Java
    │
    └── resources/
        ├── com.java.java.application/   # Views em arquivos FXML
        └── static/
            ├── css/                 # Arquivos de estilo (CSS)
            │   ├── components/
            │   ├── default.css
            ├── fonts.Gabarito/      # Fontes utilizadas no projeto
            └── images/
                └── icon/            # Imagens utilizadas na aplicação

```

## 🫂 Contribuições

Contribuições são muito bem-vindas! 

## 🙏 Agradecimentos

A realização deste projeto só foi possível graças ao apoio constante.