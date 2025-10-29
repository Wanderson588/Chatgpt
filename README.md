# MiniBank

MiniBank é um projeto Java que implementa um sistema bancário simplificado. Ele provê o
cadastro de clientes, abertura de contas e operações financeiras básicas com validações
robustas para garantir a consistência dos dados.

## Funcionalidades
- Cadastro de clientes com identificador único e validação de nome, documento e e-mail.
- Abertura de contas vinculadas a clientes já cadastrados.
- Operações de depósito, saque e transferência entre contas com validação de valores e
  verificação de saldo disponível.
- Consulta das contas e clientes cadastrados com coleções imutáveis para evitar
  modificações externas.

## Estrutura do projeto
```
├── pom.xml                # Configuração do Maven e dependências (JUnit 5)
├── src
│   ├── main
│   │   └── java
│   │       └── br/com/minibank
│   │           ├── Banco.java   # Gerencia clientes e contas
│   │           ├── Cliente.java # Representa um cliente do banco
│   │           └── Conta.java   # Representa uma conta bancária
│   └── test
│       └── java
│           └── br/com/minibank
│               ├── BancoTest.java   # Testes das regras do Banco
│               ├── ContaTest.java   # Testes de operações da Conta
│               └── ClienteTest.java # Testes de criação de clientes
```

## Pré-requisitos
- [Java 17](https://adoptium.net/) ou superior instalado e disponível no `PATH`.
- [Apache Maven 3.8+](https://maven.apache.org/) instalado.

## Executando os testes
Para validar o comportamento do sistema, execute os testes automatizados:

```bash
mvn test
```

## Exemplo de uso
```java
Banco banco = new Banco();
Cliente cliente = new Cliente("Maria", "12345678900", "maria@example.com");
banco.adicionarCliente(cliente);

Conta conta = banco.abrirConta("0001", cliente.getDocumento());
conta.depositar(new BigDecimal("150.00"));
conta.sacar(new BigDecimal("50.00"));
```

Esse exemplo demonstra o fluxo básico de cadastro de cliente, abertura de conta e
execução de operações financeiras.
