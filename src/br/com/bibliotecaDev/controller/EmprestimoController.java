package br.com.bibliotecaDev.controller;

import br.com.bibliotecaDev.modelos.Emprestimo;
import br.com.bibliotecaDev.modelos.Livro;
import br.com.bibliotecaDev.modelos.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoController {
    private List<Emprestimo> livrosEmprestados = new ArrayList<>();

    public Emprestimo emprestimoLivro(Livro livro, Usuario usuario) {
        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo = new Emprestimo(usuario, livro, hoje);
        this.livrosEmprestados.add(emprestimo);
        return emprestimo;
    }

    public boolean livroDisponivel(String livro) {
        for (Emprestimo e : livrosEmprestados) {
            if (e.getLivro().getTitulo().equals(livro)) {
                return true;
            }
        }
        return false;
    }

    public List<Emprestimo> getLivrosEmprestados() {
        return livrosEmprestados;
    }
}