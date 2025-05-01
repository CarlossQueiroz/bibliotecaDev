package br.com.bibliotecaDev.main;

import br.com.bibliotecaDev.controller.EmprestimoController;
import br.com.bibliotecaDev.controller.UsuarioController;
import br.com.bibliotecaDev.utilitarios.Leitura;

public class MenuOpcoes {

    private Leitura scanner;
    private UsuarioController user;
    private EmprestimoController emprestimo;

    public MenuOpcoes(){
        this.scanner= new Leitura();
        this.user=new UsuarioController();
        this.emprestimo=new EmprestimoController();
    }

    public void menuPrincipal() {
        int opcao;

        do {
            System.out.println("**** Bem-vindo à BibliotecaDEV ****");
            System.out.println("(1) Cadastrar usuário\n(2) Listar usuários\n(3) Consultar Livro\n(4) Livros emprestados\n(5) Login\n(0) Sair");
            opcao = scanner.lerInt();
            switch (opcao){
                case 1:
                    user.cadastrarUsuario();
                    break;
                case 2:
                    user.listarUsuarios();
                    break;
                case 3:
                    user.consultarLivro();
                    break;
                case 4:
                    user.listarTodosEmprestimos();
                    break;
                case 5:
                    login();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (opcao != 0);
    }

    public String lerString(String mensagem){
        System.out.println("Digite "+ mensagem +":");
        return scanner.lerString();
    }

    public void login() {
        String matricula = lerString("sua matrícula");
        if (user.loginUsuario(matricula)){
            System.out.println("Login efetuado com sucesso!");
            menuUsuario();
        }
        else {
            System.out.println("Matrícula inválida. Tente outra!");
        }
    }

    public void menuUsuario(){
        int opcao;

        do{

            System.out.println("(1) Pegar livro\n(2) Consultar empréstimos\n(3) Sair");
            System.out.println("Escolha uma opção:");
            opcao = scanner.lerInt();

            switch (opcao) {
                case 1:
                    user.realizarEmprestimo();
                    break;
                case 2:
                    user.listarMeusEmprestimos();
                    break;
                case 3:
                    user.logout();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }while (opcao!=3);
    }

}