package br.com.minibank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Representa uma conta bancária básica com operações de crédito e débito.
 */
public class Conta {
    private final String numero;
    private final Cliente titular;
    private BigDecimal saldo;

    public Conta(String numero, Cliente titular) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio");
        }
        this.numero = numero.trim();
        this.titular = Objects.requireNonNull(titular, "Titular é obrigatório");
        this.saldo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        BigDecimal valorValidado = validarValorMonetario(valor);
        saldo = saldo.add(valorValidado);
    }

    public void sacar(BigDecimal valor) {
        BigDecimal valorValidado = validarValorMonetario(valor);
        debitar(valorValidado);
    }

    public void transferirPara(Conta destino, BigDecimal valor) {
        Objects.requireNonNull(destino, "Conta de destino é obrigatória");
        if (this.equals(destino)) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }
        BigDecimal valorValidado = validarValorMonetario(valor);
        debitar(valorValidado);
        destino.depositar(valorValidado);
    }

    private void debitar(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        saldo = saldo.subtract(valor);
    }

    private BigDecimal validarValorMonetario(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        BigDecimal ajustado = valor.setScale(2, RoundingMode.HALF_EVEN);
        if (ajustado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        return ajustado;
    }
}
