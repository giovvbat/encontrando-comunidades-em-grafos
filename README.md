# Encontrando comunidades em grafos

Este repositório contém uma implementação em Java do algoritmo de Girvan-Newman,
um algoritmo de agrupamento hierárquico divisivo.

A implementação foi feita por [Giovanna Batista](https://github.com/giovvbat/) 
e [Pedro Pinho](https://github.com/pedropinho60) para a disciplina de Grafos na UFRN.

## Requisitos

Para executar o algoritmo, é necessário ter o Java instalado. Foi utilizada a versão
Java 22 para o desenvolvimento.

## Compilação e execução

Primeiro, clone o repositório localmente:
```bash
git clone https://github.com/giovvbat/encontrando-comunidades-em-grafos
```

Acesse a pasta do repositório:
```bash
cd encontrando-comunidades-em-grafos
```

Compile o programa com **javac**
```bash
javac src/*.java
```
Esse comando irá compilar todos os arquivos na pasta *src*

Após compilar, é possível executar um dos testes escolhidos da pasta *tests*. Por exemplo,
para executar o teste 1:
```bash
java src.Main tests/teste1.txt
```

Por fim, os resultados do teste serão mostrados na linha de comando e salvos em um arquivo
na pasta *results*.
