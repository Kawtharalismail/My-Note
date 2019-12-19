package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<NoteModel> noteModels;
    private Context context;
    public NoteAdapter(Context context,List<NoteModel> noteModels){
        this.context=context;
        this.noteModels=noteModels;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, final int position) {

        final NoteModel model=noteModels.get(position);
        holder.itemImage.setImageDrawable(model.getItemImage());
        holder.itemText.setText(model.getItemText());
        holder.itemSwitch.setChecked(model.getItemSwitch());
        holder.isLongPressed=model.getLongPressed();
        holder.itemDescriptionText=model.getItemDescriptionText();
        holder.id=position;
        holder.itemConstrainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.menu.setVisibility(View.VISIBLE);
                return true;
            }
        });
        holder.itemConstrainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.menu.setVisibility(View.INVISIBLE);
            }
        });

        holder.itemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView.isChecked())
                    holder.itemConstrainLayout.setBackgroundResource(R.color.noteItemBackground);
                else
                    holder.itemConstrainLayout.setBackgroundResource(android.R.color.white);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editNoteIntent=new Intent(holder.edit.getContext(),ShowNote.class);
                editNoteIntent.putExtra("position",position);
                Toast.makeText(holder.edit.getContext(), "in :"+position,
                        Toast.LENGTH_LONG).show();


                holder.edit.getContext().startActivity(editNoteIntent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(holder.edit.getContext(), "in :"+holder.id,
                        Toast.LENGTH_LONG).show();
                noteModels.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,noteModels.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemText;
        String itemDescriptionText;
        Switch itemSwitch;
        Boolean   isLongPressed;
        ConstraintLayout itemConstrainLayout;
        LinearLayout menu;
        ImageButton delete,edit;
        int id;

        NoteViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemImage=itemView.findViewById(R.id.itemImage);
            itemText=itemView.findViewById(R.id.itemText);
            itemSwitch=itemView.findViewById(R.id.itemswitch);
            itemConstrainLayout=itemView.findViewById(R.id.itemConstraint);
            menu=itemView.findViewById(R.id.menu_id);
            delete=itemView.findViewById(R.id.delete_icon);
            edit=itemView.findViewById(R.id.edit_icon);
            isLongPressed=false;

        }
    }


}
