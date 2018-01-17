package co.com.harmonic.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import co.com.harmonic.R;
import co.com.harmonic.presentation.presenter.AuthPresenter;
import co.com.harmonic.presentation.presenter.interfaces.AuthContract;
import co.com.harmonic.presentation.view.fragment.GeneralFragment;
import co.com.harmonic.presentation.view.fragment.LoginFragment;

public class AuthActivity extends AppCompatActivity implements AuthContract.View, NavigationView.OnNavigationItemSelectedListener {
    private AuthContract.UserActionsLister mActionsListener;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionsListener = new AuthPresenter(this);

        final DrawerLayout drawer = findViewById(R.id.auth_activity);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    onBackPressed();
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        mActionsListener.goToFirstFragment();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void goToLoginFragment() {
        replaceFragment(LoginFragment.getInstance(), false);
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void goToAboutFragment(){
//        AboutFragment aboutFragment = AboutFragment.getInstance();
//        replaceFragment(aboutFragment, false);
//    }

    private void goToGeneralFragment(){
        GeneralFragment generalFragment = GeneralFragment.getInstance();
        replaceFragment(generalFragment, false);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            //goToGeneralFragment();
        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_categories) {
            // goToAboutFragment();
        } else if (id == R.id.nav_configuration) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_login) {
            //goToLoginFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //TODO Arreglar excepción en closeDrawer().
        // java.lang.NullPointerException: Attempt to invoke virtual method 'void android.support.v4.widget.DrawerLayout.closeDrawer(int)' on a null object reference
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
