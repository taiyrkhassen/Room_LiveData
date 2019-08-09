package com.example.recyclerholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {
    ArrayList<Note> arrayNote;
    private onNoteClickListener clickListener;
    public NotesAdapter(ArrayList<Note> arrayNote) {
        this.arrayNote = arrayNote;
    }

    public void setClickListener(onNoteClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface onNoteClickListener{
        void onClickItem(int position);
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_item, viewGroup, false);
        return new NotesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder notesHolder, int i) {
        Note note = arrayNote.get(i);
        notesHolder.textViewDate.setText(note.getDate());
        notesHolder.textViewDescription.setText(note.getDescription());
        notesHolder.textViewTitle.setText(note.getTitle());
        int colorId;
        int priority = note.getPriority();
        switch (priority){
            case 1:
                colorId = notesHolder.itemView.getResources().getColor(R.color.HighPriority);
                break;
            case 2:
                colorId = notesHolder.itemView.getResources().getColor(R.color.MidlePriority);
                break;
            default:
                colorId = notesHolder.itemView.getResources().getColor(R.color.LowPriority);
                break;
        }
        notesHolder.textViewTitle.setBackgroundColor(colorId);
    }

    @Override
    public int getItemCount() {
        return arrayNote.size();
    }


    class NotesHolder extends RecyclerView.ViewHolder {
         TextView textViewTitle;
         TextView textViewDate;
         TextView textViewDescription;
        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener!=null){
                        clickListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

    }

}

