# TDD + Caixa Eletrônico

[![Build Status](https://travis-ci.org/acdcjunior/tdd-spring-boot-caixa-eletronico.svg?branch=master)](https://travis-ci.org/acdcjunior/tdd-spring-boot-caixa-eletronico)

![atm tdd](src/main/resources/static/assets/img/banco-logo.png)
![atm tdd](src/main/resources/static/assets/img/atm796x597.png)

## Tecnologias/Ferramentas usadas

- Spring Boot
- Gradle
- Flyway
- Spring Data
- Hibernate
- H2
- Tomcat
- Java 8

## Como executar

Na pasta raiz do projeto (a pasta onde está o `build.gradle`), use:

    gradlew bootRun
    
**Em seguida, acesse: http://127.0.0.1:8080/**
    
Observe que, para rodar, a única exigência é o Java 8. Caso não tenha o Gradle instalado, por exemplo, um será baixado automaticamente.

<br>
    
### Suporte Gradle no IntelliJ
- O plugin 'idea' nao presta, pois soh cria _file-based_, nao _directory-based_.
- Quando voce dah open->import as gradle, ele nao pega direito os `build` dirs, e um make do intellij (<kbd>ctrl+f9</kbd>)
cria um diretorio `out` bizonho na raiz
    - Quando eu dei open sem nada e ele, quando abriu, ofereceu pra importar como gradle, funcionou
     tudo (os filhos gerando tudo na pasta `build`, independentemente de eu ter usado via Intellij ou via gradle direto)
    - Preciso confirmar pra saber se eh repetitivel
