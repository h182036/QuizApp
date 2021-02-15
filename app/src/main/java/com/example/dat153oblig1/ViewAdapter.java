package com.example.dat153oblig1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;
import android.view.ViewGroup;

import java.util.ArrayList;

import android.view.View;
import android.util.Log;

import com.example.dat153oblig1.ui.Activites.MainActivity;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>  {

    Context context;
    ArrayList<Katt> katter;


    /**
     * Constructor for the adapter.
     *
     * @param ct
     */
    public ViewAdapter(Context ct, ArrayList<Katt> katter) {
        context = ct;
        this.katter = katter;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.katt_row, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Sets the individual elements in the list.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.myText.setText(katter.get(position).getNavn());
        holder.myImage.setImageDrawable(katter.get(position).getImg());


        /**
         * Holding in on the element makes it pop up and you can delete the element.
         */
        holder.itemView.setOnLongClickListener(v -> {
            System.out.println("You longed clicked with: " + position);
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Log.e("Answer", "Yes");
                            MainActivity.SQLiteHelper.deleteData(katter.get(position).getId());
                            katter.remove(position);
                            notifyDataSetChanged();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            Log.e("Answer", "No");
                            break;
                    }
                }
            };
            AlertDialog.Builder ab = new AlertDialog.Builder(context);
            ab.setMessage("Delete?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
            return true;
        });


    }

    /**
     * returns the size of the list.
     *
     * @return The size of the list
     */

    @Override
    public int getItemCount() {
        return katter.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.katt_navn);
            myImage = itemView.findViewById(R.id.imageKatt);
        }
    }
}
