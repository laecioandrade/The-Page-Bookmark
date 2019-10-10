package br.com.laecio.projectpdsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import br.com.laecio.projectpdsi.model.Book;

public class RegisterPages extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText numeroLido, titulo, autor, numeroPages;
    private DatabaseReference databaseReference;
    private Book book = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        titulo = findViewById(R.id.editTextAtualizaTitulo);
        autor = findViewById(R.id.editTextAtualizarAutor);
        numeroPages = findViewById(R.id.editTextAtualizarQuantidadePages);
        numeroLido = findViewById(R.id.editTextQuantidadeLido);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btnAtualizar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        Bundle extras;
        extras = getIntent().getExtras();

        if (getIntent().hasExtra("book")) {
            book = new Gson().fromJson(extras.getString("book"), Book.class);

            numeroLido.setText(book.getQuantityLido());
            titulo.setText(book.getTitulo());
            autor.setText(book.getAuthor());
            numeroPages.setText(book.getQuantityPages());

        }

    }

    private void update() {
        String key = databaseReference.child("users").child(auth.getUid()).child("book").push().getKey();

        Book j = new Book();
        j.setQuantityLido(numeroLido.getText().toString());
        j.setTitulo(titulo.getText().toString());
        j.setAuthor(autor.getText().toString());
        j.setQuantityPages(numeroPages.getText().toString());


        if (book == null) {
            j.setId(book.getId());
            databaseReference.child("users").child(auth.getUid()).child("book").child(key).setValue(j);
        } else {
            j.setId(book.getId());
            databaseReference.child("users").child(auth.getUid()).child("book").child(book.getId()).setValue(j);
        }


        finish();
    }

}
