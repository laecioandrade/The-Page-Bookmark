package br.com.laecio.projectpdsi.model;

public class Book {
    private String id;
    private String titulo;
    private String author;
    private String quantityPages;
    private String quantityLido;
    private String user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuantityPages(String s) {
        return getQuantityPages();
    }

    public void setQuantityPages(String quantityPages) {
        this.quantityPages = quantityPages;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getQuantityLido() {
        return quantityLido;
    }

    public void setQuantityLido(String quantityLido) {
        this.quantityLido = quantityLido;
    }

    public String getQuantityPages() {
        return quantityPages;
    }
}
