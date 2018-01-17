package co.com.harmonic.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.com.harmonic.R;
import co.com.harmonic.presentation.presenter.AboutPresenter;
import co.com.harmonic.presentation.presenter.interfaces.AboutContract;
import co.com.harmonic.presentation.view.activity.MainActivity;
import co.com.harmonic.presentation.view.adapter.InstructorAdapter;
import co.com.harmonic.presentation.view.adapter.InstrumentAdapter;

public class AboutFragment extends Fragment implements AboutContract.View {
    static private View view_help;
    static private ImageView imageView;
    private AboutContract.UserActionsListener mActionListener;
    private RecyclerView rvInstrumenstList;
    private RecyclerView rvInstructorsList;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment getInstance(View help) {
        view_help = help;
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        mActionListener = new AboutPresenter(this);
        //Set ImageView
        imageView = view.findViewById(R.id.iv_Instrumento);
        ImageView image_help = view_help.findViewById(R.id.ivPhoto_Instrument);
        Glide.with(view).load(image_help.getDrawable()).into(imageView);
        //RecyclerView Instrumentos
        rvInstrumenstList = view.findViewById(R.id.rvInstrumenstList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvInstrumenstList.setLayoutManager(layoutManager);
        InstrumentAdapter instrumentAdapter = new InstrumentAdapter(mActionListener.getAllInstruments());
        instrumentAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_help) {
                goToAboutFragment(view_help);
            }
        });
        rvInstrumenstList.setAdapter(instrumentAdapter);
        mActionListener.loadAllInstruments();
        //RecyclerView Instructores
        TextView id = view_help.findViewById(R.id.tvInstrument);
        rvInstructorsList = view.findViewById(R.id.rvInstructorsList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvInstructorsList.setLayoutManager(layoutManager);
        InstructorAdapter instructorAdapter = new InstructorAdapter(mActionListener.getAllInstructors(), true);
        instructorAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_help) {
                goToDetailFragment(view_help);
            }
        });
        rvInstructorsList.setAdapter(instructorAdapter);
        mActionListener.loadAllInstructors(id.getText().toString().toLowerCase());
        return view;
    }

    @Override
    public void refreshInstruments() {
        rvInstrumenstList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void refreshInstructors() {
        rvInstructorsList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void goToAboutFragment(View view_help) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(AboutFragment.getInstance(view_help), false);
    }

    @Override
    public void goToDetailFragment(View view_help) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(DetailFragment.getInstance(view_help), true);
    }

}
