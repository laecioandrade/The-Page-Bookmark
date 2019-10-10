package br.com.laecio.projectpdsi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.laecio.projectpdsi.model.Book;

public class RegisterBook extends AppCompatActivity {
    private EditText titulo, autor, quantidadePg;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Book book = new Book();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        titulo = findViewById(R.id.editTextTitulo);
        autor = findViewById(R.id.editTextAutor);
        quantidadePg = findViewById(R.id.editTextPaginas);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book.setTitulo(titulo.getText().toString());
                book.setAuthor(autor.getText().toString());
                book.setQuantityPages(quantidadePg.getText().toString());
                book.setQuantityLido("0");

                book.setUser_id(auth.getUid());
                String key = databaseReference.child("users").child(auth.getUid()).child("book").push().getKey();
                book.setId(key);
                databaseReference.child("users").child(auth.getUid()).child("book").child(key).setValue(book);

                Toast.makeText(RegisterBook.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(RegisterBook.this, Init.class));
            }
        });
    }

}
