package br.com.bibliotecaDev.controller;

import br.com.bibliotecaDev.modelos.Emprestimo;
import br.com.bibliotecaDev.modelos.Livro;
import br.com.bibliotecaDev.modelos.LivroGoogleBooks;
import br.com.bibliotecaDev.modelos.Usuario;
import br.com.bibliotecaDev.servicos.RequisicaoHttp;
import br.com.bibliotecaDev.utilitarios.Leitura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UsuarioController {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Optional<Usuario> usuario = Optional.empty();
    private EmprestimoController emprestimoController = new EmprestimoController();
    private Leitura scanner = new Leitura();

    public boolean loginUsuario(String matricula){
        this.usuario = listaUsuarios.stream().filter(u->u.getMatricula().equals(matricula)).findFirst();

        return usuario.isPresent();
    }

    public void logout(){
        this.usuario = Optional.empty();
        System.out.println("Logout realizado coom sucesso!");
    }

    public String lerDado(String mensagem){
        System.out.println("Digite "+ mensagem +":");
        return scanner.lerString();
    }

    public void cadastrarUsuario() {
        System.out.println("\n*** CADASTRO DE USUÁRIO ***");
        String nome = lerDado("o nome");
        String email = lerDado("o e-mail");
        String matricula = lerDado("a matrícula");

        if (!emailValidacao(email)){
            System.out.println("E-mail inválido.");
        } else if (emailExistente(email)) {
            System.out.println("E-mail já cadastrado.");
        } else if (verificarMatricula(matricula)) {
            System.out.println("Matrícula já cadastrada.");
        }
        else{
            listaUsuarios.add(new Usuario(nome,email,matricula));
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }

    public void listarUsuarios() {
        System.out.println("\n*** LISTA DE USUÁRIOS ***");
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            listaUsuarios.forEach(usuario -> usuario.exibirInformacoes());
        }
    }

    public LivroGoogleBooks buscarLivro(){
        String tituloLivro = lerDado("título do livro");
        try {
            LivroGoogleBooks dadosLivro = new RequisicaoHttp().requisicaoHttp(tituloLivro);
            return dadosLivro;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void consultarLivro(){
        LivroGoogleBooks dadosLivro = buscarLivro();
        if (dadosLivro != null){
            Livro livro= new Livro(dadosLivro);
            System.out.println(livro);
        }
        else{
            System.out.println("Livro não encontrado!");
        }
    }

    public void realizarEmprestimo(){
        LivroGoogleBooks dadosLivro = buscarLivro();
        if (dadosLivro==null) return;

        Livro livro = new Livro(buscarLivro());
        boolean disponivel = emprestimoController.livroDisponivel(livro.getTitulo());

        if (disponivel) {
            System.out.println("Livro indisponível para empréstimo. Tente outro!");
        }else{
            Emprestimo emprestimo = emprestimoController.emprestimoLivro(livro,usuario.get());
            usuario.get().addEmprestimo(emprestimo);
            System.out.println("Livro emprestado com sucesso!");
        }
    }

    public void listarMeusEmprestimos(){
        if (usuario.isPresent()){
            System.out.println("*** MEUS EMPRÉSTIMOS ***");
            usuario.get().listarEmprestimos();
        }
    }

    public void listarTodosEmprestimos(){
        if (emprestimoController.getLivrosEmprestados().isEmpty()){
            System.out.println("Nenhum livro emprestado.");
        }
        else {
            System.out.println("*** LIVROS EMPRESTADOS ***");
            emprestimoController.getLivrosEmprestados().forEach(item-> System.out.println(item.toString()));
        }
    }

    public boolean emailValidacao(String email){
        if (email == null) return false;
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public boolean emailExistente(String email){
        return email != null && listaUsuarios.stream().anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email));
    }

    public boolean verificarMatricula(String matricula){
        return matricula != null && listaUsuarios.stream().anyMatch(usuario -> usuario.getMatricula().equalsIgnoreCase(matricula));
    }
}