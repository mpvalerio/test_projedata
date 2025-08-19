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

- **src/main/model**: contém as entidades de domínio `Pessoa` e
  `Funcionario`.
- **src/main/service**: implementa as regras de negócio no
  `FuncionarioService`, incluindo inserção, remoção, agrupamento,
  ordenação e cálculos diversos.
- **src/main/util**: utilitário `FormatadorUtil` para
  formatação de datas e valores numéricos no padrão brasileiro.
- **src/main/app**: classe `Principal` que exerce o papel de
  ponto de entrada do programa e demonstra todas as operações
  solicitadas no enunciado.
- **src/test**: testes unitários em JUnit 5 que validam as
  principais funcionalidades da camada de serviço.

## Compilação e execução

1. **Executar a aplicação**:

   Você pode rodar a classe principal diretamente com o `java`:

   ```sh
   java -cp target/classes Main
   ```

   A saída no console mostrará cada passo do desafio, incluindo a
   inserção dos funcionários, aplicação do aumento, agrupamentos,
   aniversariantes, pessoa mais velha, ordenação alfabética, soma dos
   salários e cálculo de salários mínimos recebidos.



