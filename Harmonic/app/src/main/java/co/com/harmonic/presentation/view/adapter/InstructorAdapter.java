package co.com.harmonic.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.com.harmonic.R;
import co.com.harmonic.domain.model.Instructor;

/**
 * Created by Rodolhan on 16/12/2017.
 */

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> implements View.OnClickListener {
    private List<Instructor> dataSet;
    private View.OnClickListener listener;
    private Boolean acceso;

    public InstructorAdapter(List<Instructor> dataSet, Boolean acceso) {
        super();
        this.dataSet = dataSet;
        this.acceso = acceso;
    }

    @Override
    public InstructorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_instructor_item, parent, false);
        view.setOnClickListener(this);
        return new InstructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstructorViewHolder holder, int position) {
        Instructor instructor = dataSet.get(position);
        Glide.with(holder.itemView)
                .load(instructor.getImage())
                .into(holder.ivPhoto_Instructor);
        holder.tvText_Instructor.setText(instructor.getNombre());
        holder.stars.setRating(Float.parseFloat(instructor.getRating()));
        if (acceso == false) {
            holder.tvText_Instructor1.setText(instructor.getApodo());
            holder.tvText_Instructor2.setText(instructor.getRol());
        } else {
            holder.tvText_Instructor1.setText("$" + instructor.getPrecio() + "/Clase");
            holder.tvText_Instructor2.setText("");
        }
        holder.tvBiography.setText(instructor.getBiografia());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class InstructorViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivPhoto_Instructor;
        public TextView tvText_Instructor;
        public TextView tvText_Instructor1;
        public TextView tvText_Instructor2;
        public RatingBar stars;
        public TextView tvBiography;

        public InstructorViewHolder(View itemView) {
            super(itemView);
            ivPhoto_Instructor = itemView.findViewById(R.id.ivPhoto_Instructor);
            tvText_Instructor = itemView.findViewById(R.id.tvText_Instructor);
            tvText_Instructor1 = itemView.findViewById(R.id.tvText_Instructor1);
            tvText_Instructor2 = itemView.findViewById(R.id.tvText_Instructor2);
            stars = itemView.findViewById(R.id.stars);
            tvBiography = itemView.findViewById(R.id.tvBiography);
        }
    }
}
