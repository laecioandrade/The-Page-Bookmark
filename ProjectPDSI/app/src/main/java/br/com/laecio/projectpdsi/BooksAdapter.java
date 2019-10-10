package br.com.laecio.projectpdsi;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.List;

import br.com.laecio.projectpdsi.model.Book;

public class BooksAdapter extends RecyclerView.Adapter<RowHolderBook>{
    private List<Book> feedItemList;
    private Context mContext;
    private FirebaseAuth auth;
    private String authId;

    DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();

    public BooksAdapter(Context context, List<Book> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        auth = FirebaseAuth.getInstance();
        authId = auth.getUid();
        //this.preferencias = new Preferencias(mContext);
    }

    @Override
    public RowHolderBook onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, null);
        RowHolderBook mh = new RowHolderBook(v);

        return mh;
    }

    public void onBindViewHolder(final RowHolderBook feedListRowHolder, int position) {
        final Book feedItem = feedItemList.get(position);
        float ver = 0;
        if(Integer.parseInt(feedItem.getQuantityLido())>0){
            ver = Float.parseFloat(feedItem.getQuantityLido()) / Float.parseFloat(feedItem.getQuantityPages()) * 100;
        }
        int quantidadeLidoo = (int) ver;
        feedListRowHolder.titulo.setText(Html.fromHtml(feedItem.getTitulo()));
        feedListRowHolder.autor.setText(Html.fromHtml(feedItem.getAuthor()));

        //Ajeitar
        feedListRowHolder.quantidadeLido.setProgress(quantidadeLidoo);
        feedListRowHolder.quantidadeP.setText(Html.fromHtml(quantidadeLidoo+"% Concluído"));
        feedListRowHolder.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, RegisterPages.class);
                i.putExtra("book", new Gson().toJson(feedItem));
                mContext.startActivity(i);
            }
        });
        feedListRowHolder.btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Apagado com sucesso!", Toast.LENGTH_SHORT).show();
                referenciaFirebase.child("users").child(authId).child("book").child(feedItem.getId()).removeValue();

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

}

