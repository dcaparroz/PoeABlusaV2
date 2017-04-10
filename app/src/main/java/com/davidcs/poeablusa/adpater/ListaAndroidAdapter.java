package com.davidcs.poeablusa.adpater;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import com.davidcs.poeablusa.R;
import com.davidcs.poeablusa.model.Usuario;


/**
 * Created by Momberg on 23/02/2017.
 */

public class ListaAndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private List<Usuario> usuarios;

    public ListaAndroidAdapter(Context context, List<Usuario> tarefas){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.usuarios = tarefas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new AndroidItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        AndroidItemHolder androidItemHolder = (AndroidItemHolder) holder;
        androidItemHolder.tilNomeUsuario.setText(usuarios.get(position).getNome());
        androidItemHolder.tilFrio.setText(usuarios.get(position).getTemperatura().getFrio());
        androidItemHolder.tilCalor.setText(String.valueOf(usuarios.get(position).getTemperatura().getCalor()));
        androidItemHolder.tilChuva.setText(String.valueOf(usuarios.get(position).getTemperatura().getChuva()));
        androidItemHolder.tilPeriodo.setText(String.valueOf(usuarios.get(position).getPeriodo()));
        androidItemHolder.id.setText(String.valueOf(usuarios.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    private class AndroidItemHolder extends RecyclerView.ViewHolder{

        TextView tilNomeUsuario, tilFrio, tilCalor, tilChuva, tilPeriodo, id;

        public AndroidItemHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            tilNomeUsuario = (TextView) itemView.findViewById(R.id.tilNomeUsuario);
            tilFrio = (TextView) itemView.findViewById(R.id.tilFrio);
            tilCalor = (TextView) itemView.findViewById(R.id.tilCalor);
            tilChuva = (TextView) itemView.findViewById(R.id.tilChuva);

        }
    }

}
