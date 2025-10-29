package br.com.minibank;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Controla o cadastro de clientes e contas do banco.
 */
public class Banco {
    private final Map<String, Cliente> clientesPorDocumento = new HashMap<>();
    private final Map<String, Conta> contasPorNumero = new HashMap<>();

    public void adicionarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "Cliente é obrigatório");
        String documento = cliente.getDocumento();
        if (clientesPorDocumento.containsKey(documento)) {
            throw new IllegalArgumentException("Cliente com documento já cadastrado");
        }
        clientesPorDocumento.put(documento, cliente);
    }

    public Conta abrirConta(String numero, String documentoCliente) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta é obrigatório");
        }
        if (documentoCliente == null || documentoCliente.isBlank()) {
            throw new IllegalArgumentException("Documento do cliente é obrigatório");
        }
        if (contasPorNumero.containsKey(numero)) {
            throw new IllegalArgumentException("Conta já existente");
        }
        Cliente titular = clientesPorDocumento.get(documentoCliente);
        if (titular == null) {
            throw new IllegalStateException("Cliente não encontrado");
        }
        Conta conta = new Conta(numero, titular);
        contasPorNumero.put(numero, conta);
        return conta;
    }

    public Optional<Conta> buscarConta(String numero) {
        return Optional.ofNullable(contasPorNumero.get(numero));
    }

    public Collection<Cliente> getClientes() {
        return Collections.unmodifiableCollection(clientesPorDocumento.values());
    }

    public Collection<Conta> getContas() {
        return Collections.unmodifiableCollection(contasPorNumero.values());
    }
}
