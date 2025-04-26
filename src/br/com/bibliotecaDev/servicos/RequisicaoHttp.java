package br.com.bibliotecaDev.servicos;

import br.com.bibliotecaDev.modelos.LivroGoogleBooks;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RequisicaoHttp {

    public LivroGoogleBooks  requisicaoHttp(String tituloBusca) throws IOException, InterruptedException {
        String tituloFormatado = URLEncoder.encode(tituloBusca, StandardCharsets.UTF_8);
        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + tituloFormatado;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        GoogleBooksResponse resposta = new Gson().fromJson(response.body(), GoogleBooksResponse.class);

        if (resposta.getItems() != null && !resposta.getItems().isEmpty()) {
            VolumeInfo info = resposta.getItems().get(0).getVolumeInfo();

            String titulo = info.getTitle();
            String autor = (info.getAuthors() != null && !info.getAuthors().isEmpty()) ? info.getAuthors().get(0) : "Desconhecido";
            String ano = (info.getPublishedDate() != null) ? info.getPublishedDate() : "Desconhecido";
            String editora = (info.getPublisher() != null) ? info.getPublisher() : "Desconhecida";
            String descricao = (info.getDescription() != null) ? info.getDescription() : "Sem descrição";

            return new LivroGoogleBooks(titulo, autor, ano, editora, descricao);
        }

        return null; // Nenhum livro encontrado
    }
}
