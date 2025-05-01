package br.com.bibliotecaDev.modelos;

import java.time.LocalDate;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo.plusDays(14);
    }

    public Usuario getUsuario() { return usuario; }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Usuário: " + usuario.getNome() + " | Livro: " + livro.getTitulo() + " | Data de empréstimo: " + dataEmprestimo + " | Data de devolução=" + dataDevolucao;
    }
}
