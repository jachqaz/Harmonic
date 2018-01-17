package co.com.harmonic.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.harmonic.R;
import co.com.harmonic.presentation.presenter.GeneralPresenter;
import co.com.harmonic.presentation.presenter.interfaces.GeneralContract;
import co.com.harmonic.presentation.view.activity.MainActivity;
import co.com.harmonic.presentation.view.adapter.InstructorAdapter;
import co.com.harmonic.presentation.view.adapter.InstrumentAdapter;
import co.com.harmonic.presentation.view.adapter.ViewPagerAdapter;

public class GeneralFragment extends Fragment implements GeneralContract.View {
    private RecyclerView rvInstrumenstList;
    private RecyclerView rvInstructorsList;
    private ViewPager viewPager;
    private GeneralContract.UserActionsListener mActionListener;

    public GeneralFragment() {        // Required empty public constructor
    }

    public static GeneralFragment getInstance() {
        return new GeneralFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ((MainActivity) getActivity()).setEnableBackbutton(false);
        final View view = inflater.inflate(R.layout.fragment_general, container, false);
        mActionListener = new GeneralPresenter(this);
        //ViewPager
        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        //RecyclerView Instrumentos
        rvInstrumenstList = view.findViewById(R.id.rvInstrumenstList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvInstrumenstList.setLayoutManager(layoutManager);
        InstrumentAdapter instrumentAdapter = new InstrumentAdapter(mActionListener.getAllInstruments());
        instrumentAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_help) {
                goToAbouthFragment(view_help);
            }
        });
        rvInstrumenstList.setAdapter(instrumentAdapter);
        mActionListener.loadAllInstruments();
        //RecyclerView Instructuores
        rvInstructorsList = view.findViewById(R.id.rvInstructorsList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvInstructorsList.setLayoutManager(layoutManager);
        InstructorAdapter instructorAdapter = new InstructorAdapter(mActionListener.getAllInstructors(), false);
        instructorAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_help) {
                goToDetailFragment(view_help);
            }
        });
        rvInstructorsList.setAdapter(instructorAdapter);
        mActionListener.loadAllInstructors();
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
    public void goToAbouthFragment(View view_help) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(AboutFragment.getInstance(view_help), true);
    }

    @Override
    public void goToDetailFragment(View view_help) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(DetailFragment.getInstance(view_help), true);
    }


}
