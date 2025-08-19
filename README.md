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

> **Observação**: os pacotes foram ajustados para `app`, `model`, `service` e `util`. O arquivo `Main.java` fica no pacote **default** e delega a execução para `app.Principal`.

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

## O que foi alterado
- Removido o nível `src/main/java` e configurado o Maven para **compilar a partir de `src/`**, excluindo `src/test/` dos fontes de produção.
- Ajustados **packages** e **imports** de todas as classes para `app`, `model`, `service` e `util`.
- Movido o teste `FuncionarioServiceTest.java` para `src/test/` (arquivo diretamente na pasta, como solicitado).
- Criado `src/Main.java` que chama `app.Principal` (ponto de entrada).

---

# Desafio Projedata – Gestão de Funcionários

Este repositório contém uma solução em Java para o desafio de gestão de
funcionários descrito no enunciado.  O objetivo é praticar conceitos
fundamentais de orientação a objetos, coleções, **Java Streams**,
formatação de datas/valores e testes unitários com **JUnit 5**.  O
projeto foi estruturado para ser aberto diretamente no **IntelliJ IDEA
Community Edition 2025.2** usando o **JDK Temurin 17.0.16** e o
**sistema de build Maven**.

## Estrutura de pastas

O projeto segue a convenção padrão de um projeto Maven:

```
test_projedata/
├── .idea/               # diretório gerado pelo IntelliJ (mantido vazio aqui)
├── pom.xml              # definição do projeto Maven
├── README.md            # este arquivo com instruções de uso
└── src/
    ├── main/
    │   └── java/
    │       └── br/com/empresa/
    │           ├── app/
    │           │   └── Principal.java
    │           ├── model/
    │           │   ├── Funcionario.java
    │           │   └── Pessoa.java
    │           ├── service/
    │           │   └── FuncionarioService.java
    │           └── util/
    │               └── FormatadorUtil.java
    └── test/
        └── java/
            └── br/com/empresa/service/
                └── FuncionarioServiceTest.java
```

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

## Observações

- O diretório `.idea` está incluído apenas como um marcador.  Ao
  importar o projeto no IntelliJ, essa pasta será povoada com as
  configurações específicas do seu ambiente.  Ela não contém arquivos
  relevantes ao código fonte.
- Todas as operações de manipulação da lista de funcionários utilizam
  **Streams** e **Lambdas** para ilustrar uma abordagem funcional.
- As formatações de data (dd/MM/yyyy) e de números (separador de
  milhares como ponto e decimal como vírgula) estão concentradas em
  `FormatadorUtil`.

Boa sorte com o desafio e boas práticas de codificação!
