package br.com.bibliotecaDev.modelos;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa{
    private String matricula;
    private List<Emprestimo> emprestimosAtivos;

    public Usuario(String nome, String email, String matricula) {
        super(nome, email);
        this.matricula = matricula;
        this.emprestimosAtivos=new ArrayList<>();
    }

    public void addEmprestimo(Emprestimo emprestimo){
        this.emprestimosAtivos.add(emprestimo);
    }

    public void listarEmprestimos() {
        if (this.emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado!");
        } else {
            this.emprestimosAtivos.forEach(item -> System.out.println(item.getLivro().toString()));
        }
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Usuário: " + this.getNome() + " | E-mail: " + this.getEmail() + " | Matrícula: "+this.getMatricula());
    }
}
