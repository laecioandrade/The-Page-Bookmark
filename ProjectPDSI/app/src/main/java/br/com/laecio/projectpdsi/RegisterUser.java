package br.com.laecio.projectpdsi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.prefs.Preferences;

import br.com.laecio.projectpdsi.model.User;

public class RegisterUser extends AppCompatActivity {
    private EditText name, email, pass;
    private FloatingActionButton register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        name = findViewById(R.id.editTextNamee);
        pass = findViewById(R.id.editTextPasswordd);
        email = findViewById(R.id.editTextEmaill);

        register = findViewById(R.id.fab);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setNome(name.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPass(pass.getText().toString());

                createUser(u);
            }
        });

    }

    private void createUser(User user){
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPass()).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterUser.this, "Usuário cadastro com sucesso!", Toast.LENGTH_SHORT).show();

                    openLogin();
                }else{
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        Toast.makeText(RegisterUser.this, "Escolha uma senha que contenha letras e números!", Toast.LENGTH_SHORT).show();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(RegisterUser.this, "O e-mail indicado não é válido!", Toast.LENGTH_SHORT).show();
                    }catch (FirebaseAuthUserCollisionException e){
                        Toast.makeText(RegisterUser.this, "Já existe uma conta com esse e-mail!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void openLogin(){
        //startActivity(new Intent(RegisterUser.this, Login.class));
        startActivity(new Intent(RegisterUser.this, Init.class));

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home)
            this.finish();

        return true;
    }

}
