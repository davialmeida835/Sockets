# Sistema de Mensagens via Socket TCP

Este projeto implementa um sistema cliente-servidor em Java utilizando sockets TCP, com o objetivo de enviar e receber mensagens a partir de um conjunto pré-definido armazenado em um arquivo.

## Estrutura do Projeto

O sistema é composto por três classes principais:

* `Servidor`: Responsável por carregar as mensagens e aceitar conexões de múltiplos clientes.
* `ClienteHandler`: Trata cada cliente conectado individualmente, processando solicitações e enviando respostas.
* `Cliente`: Interface do usuário cliente para solicitar mensagens específicas ou aleatórias.

## Funcionalidades

* Leitura de mensagens a partir de um arquivo (`mensagens.txt`) incluído no classpath.
* Comunicação TCP entre cliente e servidor.
* Solicitação de mensagens por índice ou de forma aleatória.
* Encerramento controlado da conexão por parte do cliente.
* Suporte a múltiplos clientes simultâneos utilizando `Thread`.

## Pré-requisitos

* Java 8 ou superior.
* Arquivo `mensagens.txt` com uma mensagem por linha, localizado no diretório `resources/com/example/` do classpath.

## Como Executar

### 1. Compilação

Certifique-se de que as três classes estão dentro do mesmo pacote (`com.example`) e que o arquivo `mensagens.txt` esteja no local correto dentro do classpath.

```bash
javac com/example/Servidor.java com/example/ClienteHandler.java com/example/Cliente.java
```

### 2. Execução do Servidor

Inicie o servidor:

```bash
java com.example.Servidor
```

A aplicação carregará as mensagens e ficará aguardando conexões na porta `12345`.

### 3. Execução do Cliente

Em outro terminal, execute o cliente:

```bash
java com.example.Cliente
```

O cliente permitirá ao usuário:

* Informar o número da mensagem desejada (0 para uma mensagem aleatória).
* Indicar se deseja encerrar a conexão após a mensagem ser recebida.

### Exemplo de Execução

```
Digite o número da mensagem (0 para aleatória): 2
Deseja encerrar a conexão após essa mensagem? (true/false): false
Mensagem recebida: Seja bem-vindo ao sistema!
```

## Tratamento de Erros

* Caso o número informado esteja fora do intervalo válido, o cliente receberá uma mensagem de erro.
* Problemas na conexão são tratados com mensagens de erro no terminal.

## Observações

* O servidor mantém-se ativo indefinidamente, aceitando conexões enquanto estiver em execução.
* Cada cliente é tratado em uma thread separada, permitindo múltiplas conexões simultâneas.

## Estrutura Esperada do Arquivo `mensagens.txt`

```txt
Mensagem 1
Mensagem 2
Mensagem 3
...
```

## Autores

Davi Rodrigues: https://github.com/davialmeida835<br>
Josias Carneiro: https://github.com/josiascta 
