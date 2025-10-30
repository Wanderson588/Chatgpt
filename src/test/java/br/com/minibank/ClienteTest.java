package br.com.minibank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    @DisplayName("Deve criar cliente válido")
    void deveCriarClienteValido() {
        Cliente cliente = new Cliente("Maria", "12345678900", "maria@example.com");

        assertNotNull(cliente.getId());
        assertEquals("Maria", cliente.getNome());
        assertEquals("12345678900", cliente.getDocumento());
        assertEquals("maria@example.com", cliente.getEmail());
    }

    @Test
    @DisplayName("Deve rejeitar nome em branco")
    void deveRejeitarNomeEmBranco() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Cliente("   ", "123", "teste@example.com"));

        assertTrue(exception.getMessage().contains("Nome"));
    }

    @Test
    @DisplayName("Deve rejeitar email inválido")
    void deveRejeitarEmailInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Cliente("João", "123", "email-invalido"));

        assertEquals("Email inválido", exception.getMessage());
    }
}
