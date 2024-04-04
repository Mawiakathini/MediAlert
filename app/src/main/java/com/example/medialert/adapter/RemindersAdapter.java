package com.example.medialert.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medialert.R;
import com.example.medialert.model.Reminder;
import com.example.medialert.util.ClickHandler;
import com.example.medialert.util.Constants;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.VH> {

    private static final String TAG = "RemindersAdapter";
    private List<Reminder> reminders;
    private ClickHandler<Reminder> clickHandler;

    public RemindersAdapter(List<Reminder> reminders, ClickHandler<Reminder> clickHandler) {
        this.reminders = reminders;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminderitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(
                reminders.get(position)
        );
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        private TextView timestamp, medicineName, dose, whenConsumed;

        private CardView doneButton;


        public VH(@NonNull View itemView) {
            super(itemView);

            timestamp = itemView.findViewById(R.id.timestamp);
            medicineName = itemView.findViewById(R.id.medicineName);
            dose = itemView.findViewById(R.id.dose);
            whenConsumed = itemView.findViewById(R.id.whenConsumed);
            doneButton = itemView.findViewById(R.id.doneButton);
        }

        public void bind(Reminder reminder) {
            dose.setText(reminder.getDose().getDose() + " " + reminder.getDose().getForm());
            timestamp.setText(reminder.getTimestamp().getHour() + ":" + reminder.getTimestamp().getMinute());
            medicineName.setText(reminder.getMedicineName());
            whenConsumed.setText(
                    reminder.getWhenConsumed()
            );

            doneButton.setOnClickListener(v -> clickHandler.onClick(reminder));
        }

    }
}
