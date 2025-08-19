# Desafio Projedata – Gestão de Funcionários (estrutura simplificada)

Projeto reestruturado conforme solicitado, com **pasta `src`** contendo o arquivo `Main.java` e as subpastas `main/` e `test/`.
Dentro de `src/main/` estão as pastas **`app/`**, **`model/`**, **`service/`** e **`util/`** (sem o nível `java/`).

## Estrutura final
```
.
├── pom.xml
├── README.md
└── src
    ├── Main.java
    ├── main
    │   ├── app
    │   │   └── Principal.java
    │   ├── model
    │   │   ├── Pessoa.java
    │   │   └── Funcionario.java
    │   ├── service
    │   │   └── FuncionarioService.java
    │   └── util
    │       └── FormatadorUtil.java
    └── test
        └── FuncionarioServiceTest.java
```


## Requisitos
- JDK 17 (temurin 17.0.16 recomendado)
- Maven 3.9+
- IntelliJ IDEA Community Edition 2025.2 (opcional)

## Como rodar
```bash

# compilar e executar testes
mvn clean test

# build do projeto
mvn clean package

# executar o programa (classe Main no pacote default)
java -cp target/classes Main
```

# Desafio Projedata – Gestão de Funcionários

Este repositório contém uma solução em Java para o desafio de gestão de
funcionários descrito no enunciado.  O objetivo é praticar conceitos
fundamentais de orientação a objetos, coleções, **Java Streams**,
formatação de datas/valores e testes unitários com **JUnit 5**.  O
projeto foi estruturado para ser aberto diretamente no **IntelliJ IDEA
Community Edition 2025.2** usando o **JDK Temurin 17.0.16** e o
**sistema de build Maven**.


### Pacotes e classes

- **br.com.empresa.model**: contém as entidades de domínio `Pessoa` e
  `Funcionario`.
- **br.com.empresa.service**: implementa as regras de negócio no
  `FuncionarioService`, incluindo inserção, remoção, agrupamento,
  ordenação e cálculos diversos.
- **br.com.empresa.util**: utilitário `FormatadorUtil` para
  formatação de datas e valores numéricos no padrão brasileiro.
- **br.com.empresa.app**: classe `Principal` que exerce o papel de
  ponto de entrada do programa e demonstra todas as operações
  solicitadas no enunciado.
- **src/test/java**: testes unitários em JUnit 5 que validam as
  principais funcionalidades da camada de serviço.

## Compilação e execução

1. **Clonar o repositório** ou copiar os arquivos para o diretório
   desejado.  Por exemplo, se o caminho local for
   `C:\Users\mathe\IdeaProjects\test_projedata`, coloque os arquivos
   deste projeto dentro dessa pasta.

2. **Abrir no IntelliJ IDEA**: abra a pasta `test_projedata` como um
   projeto Maven.  O IDE reconhecerá automaticamente as dependências e
   configurará a JDK 17 se você tiver o Temurin 17.0.16 instalado.

3. **Construir o projeto** pela linha de comando:

   ```sh
   mvn clean package
   ```

   Isso compilará todas as classes e executará os testes unitários.  O
   resultado será um JAR sem manifesto gerado em
   `target/test_projedata-1.0-SNAPSHOT.jar`.

4. **Executar a aplicação**:

   Você pode rodar a classe principal diretamente com o `java`:

   ```sh
   java -cp target/classes br.com.empresa.app.Principal
   ```

   A saída no console mostrará cada passo do desafio, incluindo a
   inserção dos funcionários, aplicação do aumento, agrupamentos,
   aniversariantes, pessoa mais velha, ordenação alfabética, soma dos
   salários e cálculo de salários mínimos recebidos.

5. **Executar os testes**:

   Para rodar apenas os testes unitários, use:

   ```sh
   mvn test
   ```


