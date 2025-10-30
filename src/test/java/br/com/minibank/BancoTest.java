package br.com.minibank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    @DisplayName("Deve adicionar clientes únicos")
    void deveAdicionarClientesUnicos() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("Paulo", "999", "paulo@example.com");

        banco.adicionarCliente(cliente);

        assertEquals(1, banco.getClientes().size());
        assertTrue(banco.getClientes().contains(cliente));
    }

    @Test
    @DisplayName("Não deve permitir clientes duplicados")
    void naoDevePermitirClientesDuplicados() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("Paulo", "999", "paulo@example.com");
        banco.adicionarCliente(cliente);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> banco.adicionarCliente(new Cliente("Outro", "999", "outro@example.com")));

        assertEquals("Cliente com documento já cadastrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve abrir conta para cliente existente")
    void deveAbrirContaParaClienteExistente() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("Lia", "333", "lia@example.com");
        banco.adicionarCliente(cliente);

        Conta conta = banco.abrirConta("123", "333");

        assertEquals(cliente, conta.getTitular());
        assertTrue(banco.buscarConta("123").isPresent());
    }

    @Test
    @DisplayName("Não deve abrir conta para cliente inexistente")
    void naoDeveAbrirContaParaClienteInexistente() {
        Banco banco = new Banco();

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> banco.abrirConta("123", "000"));

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Não deve abrir contas duplicadas")
    void naoDeveAbrirContasDuplicadas() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("Lia", "333", "lia@example.com");
        banco.adicionarCliente(cliente);
        banco.abrirConta("123", "333");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> banco.abrirConta("123", "333"));

        assertEquals("Conta já existente", exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar dados obrigatórios ao abrir conta")
    void deveValidarDadosObrigatoriosAoAbrirConta() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("Lia", "333", "lia@example.com");
        banco.adicionarCliente(cliente);

        assertThrows(IllegalArgumentException.class, () -> banco.abrirConta("   ", "333"));
        assertThrows(IllegalArgumentException.class, () -> banco.abrirConta("123", "   "));
    }
}
