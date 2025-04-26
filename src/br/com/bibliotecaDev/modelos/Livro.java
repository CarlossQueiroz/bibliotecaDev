package br.com.bibliotecaDev.modelos;

public class Livro {
    private String titulo;
    private String autor;
    private String ano;
    private String editora;
    private String descricao;

  public Livro(LivroGoogleBooks livro){
        this.titulo=livro.title();
        this.autor= livro.authors();
        this.ano = livro.publishedDate();
        this.editora = livro.publisher();
        this.descricao = livro.description();
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + " | Ano: " + ano + " | Editora: " + editora+"\nDescrição: " + descricao + "\n";
    }
}
