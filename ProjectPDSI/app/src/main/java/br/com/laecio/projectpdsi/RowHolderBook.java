package br.com.laecio.projectpdsi;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowHolderBook extends RecyclerView.ViewHolder {

    protected TextView titulo, autor, quantidadeP;
    protected ImageButton btnAdicionar, btnRemover;
    protected ProgressBar quantidadeLido;


    public RowHolderBook(@NonNull View itemView) {
        super(itemView);
        this.titulo = (TextView) itemView.findViewById(R.id.textViewName);
        this.autor = (TextView) itemView.findViewById(R.id.textViewAuthor);
        this.quantidadeP = (TextView) itemView.findViewById(R.id.textViewUrl);
        this.quantidadeLido = (ProgressBar) itemView.findViewById(R.id.determinateBar);
        this.btnAdicionar = (ImageButton) itemView.findViewById(R.id.btnAdicionar);
        this.btnRemover = (ImageButton) itemView.findViewById(R.id.btnRemover);
    }
}
