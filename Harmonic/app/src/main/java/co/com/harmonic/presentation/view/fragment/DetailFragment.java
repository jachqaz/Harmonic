package co.com.harmonic.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.com.harmonic.R;

public class DetailFragment extends Fragment {
    static private View view_help;
    private ImageView ivPhoto_Instructor_Detail;
    private TextView tvText_Instructor_Detail;
    private RatingBar stars_Detail;
    private TextView tvBiography_Detail;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment getInstance(View view) {
        view_help = view;
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ivPhoto_Instructor_Detail = view.findViewById(R.id.ivPhoto_Instructor_Detail);
        tvText_Instructor_Detail = view.findViewById(R.id.tvText_Instructor_Detail);
        stars_Detail = view.findViewById(R.id.stars_Detail);
        tvBiography_Detail = view.findViewById(R.id.tvBiography_Detail);
        //helps
        ImageView imageView = view_help.findViewById(R.id.ivPhoto_Instructor);
        TextView textView = view_help.findViewById(R.id.tvText_Instructor);
        RatingBar ratingBar = view_help.findViewById(R.id.stars);
        TextView textView1 = view_help.findViewById(R.id.tvBiography);
        //Operations
        Glide.with(view).load(imageView.getDrawable()).into(ivPhoto_Instructor_Detail);
        tvText_Instructor_Detail.setText(textView.getText());
        stars_Detail.setRating(ratingBar.getRating());
        tvBiography_Detail.setText(textView1.getText());
        return view;
    }
}
