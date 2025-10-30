package br.com.minibank;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa um cliente do banco com identificador único e dados básicos de contato.
 */
public class Cliente {
    private final UUID id;
    private final String nome;
    private final String documento;
    private final String email;

    public Cliente(String nome, String documento, String email) {
        this.id = UUID.randomUUID();
        this.nome = validarTexto(nome, "Nome");
        this.documento = validarTexto(documento, "Documento");
        this.email = validarEmail(email);
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio");
        }
        return valor.trim();
    }

    private String validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        String valor = email.trim();
        if (!valor.contains("@") || valor.startsWith("@") || valor.endsWith("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        return valor;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return documento.equals(cliente.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }
}
