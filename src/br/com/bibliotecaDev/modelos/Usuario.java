package br.com.bibliotecaDev.modelos;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa{
    private String matricula;
    private List<Emprestimo> emprestimosAtivos;
    private  int limiteEmprestimos = 5;

    public Usuario(String nome, String email, String matricula) {
        super(nome, email);
        this.matricula = matricula;
        this.emprestimosAtivos=new ArrayList<>();
    }

    public boolean livroDisponivel(String livro) {
        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.getLivro().getTitulo().equals(livro)) {
                return true;
            }
        }
        return false;
    }

    public void addEmprestimo(Emprestimo emprestimo){
        if (emprestimosAtivos.size()>=limiteEmprestimos){
            emprestimosAtivos.remove(0);
        }
        this.emprestimosAtivos.add(emprestimo);
    }

    public void listarEmprestimos() {
        if (this.emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado!");
        } else {
            this.emprestimosAtivos.forEach(item -> System.out.println(item.getLivro().toString()));
        }
    }

    public void deletarEmprestimo(Livro livro){
        emprestimosAtivos.removeIf(l -> l.getLivro().getTitulo().equals(livro.getTitulo()));
    }

    public String getMatricula() {
        return matricula;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Usuário: " + this.getNome() + " | E-mail: " + this.getEmail() + " | Matrícula: "+this.getMatricula());
    }
}