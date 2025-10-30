package br.com.minibank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    private Conta criarContaComSaldo(BigDecimal saldoInicial) {
        Cliente cliente = new Cliente("Ana", "111", "ana@example.com");
        Conta conta = new Conta("0001", cliente);
        conta.depositar(saldoInicial);
        return conta;
    }

    @Test
    @DisplayName("Deve permitir depósito com valor positivo")
    void devePermitirDeposito() {
        Cliente cliente = new Cliente("Ana", "111", "ana@example.com");
        Conta conta = new Conta("0001", cliente);

        conta.depositar(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("100.00"), conta.getSaldo());
    }

    @Test
    @DisplayName("Deve rejeitar depósito com valor inválido")
    void deveRejeitarDepositoInvalido() {
        Cliente cliente = new Cliente("Ana", "111", "ana@example.com");
        Conta conta = new Conta("0001", cliente);

        assertThrows(IllegalArgumentException.class, () -> conta.depositar(new BigDecimal("-10")));
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(null));
    }

    @Test
    @DisplayName("Deve sacar quando houver saldo")
    void deveSacarQuandoHouverSaldo() {
        Conta conta = criarContaComSaldo(new BigDecimal("200.00"));

        conta.sacar(new BigDecimal("50.00"));

        assertEquals(new BigDecimal("150.00"), conta.getSaldo());
    }

    @Test
    @DisplayName("Deve impedir saque com saldo insuficiente")
    void deveImpedirSaqueComSaldoInsuficiente() {
        Conta conta = criarContaComSaldo(new BigDecimal("30.00"));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> conta.sacar(new BigDecimal("50.00")));

        assertEquals("Saldo insuficiente", exception.getMessage());
    }

    @Test
    @DisplayName("Deve transferir valores entre contas distintas")
    void deveTransferirValores() {
        Conta origem = criarContaComSaldo(new BigDecimal("200.00"));
        Conta destino = new Conta("0002", new Cliente("Beatriz", "222", "bia@example.com"));

        origem.transferirPara(destino, new BigDecimal("50.00"));

        assertEquals(new BigDecimal("150.00"), origem.getSaldo());
        assertEquals(new BigDecimal("50.00"), destino.getSaldo());
    }

    @Test
    @DisplayName("Deve impedir transferir para mesma conta")
    void deveImpedirTransferenciaMesmaConta() {
        Conta conta = criarContaComSaldo(new BigDecimal("100.00"));

        assertThrows(IllegalArgumentException.class, () -> conta.transferirPara(conta, new BigDecimal("10.00")));
    }
}
