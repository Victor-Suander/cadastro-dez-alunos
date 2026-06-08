# Sistema de Cadastro de Alunos

Programa de terminal que permite cadastrar, listar, buscar e remover alunos. Cada aluno tem nome, idade e curso, e o sistema guarda no máximo 10 alunos por execução.

## Tecnologia

- Java 25
- Aplicação de terminal (sem interface gráfica)

## Como executar

Com o JDK 25 instalado, basta rodar o arquivo diretamente:

```
java src/cadastro_dez_alunos.java
```

## O que o sistema faz

O menu principal tem 5 opções:

1. Cadastrar aluno
2. Listar alunos
3. Buscar aluno pelo nome
4. Remover aluno
5. Sair

## Observação

Os dados ficam apenas na memória durante a execução. Ao fechar o programa, tudo é perdido.