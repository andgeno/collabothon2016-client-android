package de.comdirect.collabothon2016.collabothon2016.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.fragment.GroupOverviewFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.VotingFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        GroupOverviewFragment.OnFragmentInteractionListener,
        VotingFragment.OnFragmentInteractionListener {

    @BindView(R.id.nav_drawer_content)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    //    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        // FIXME Hoehe ist gehackt fuer das HTC One M9 -> gefixt! Wir haben jetzt LinearLayout
  //      lp.setMargins(0, 160, 0, 0);
//        lp.setMargins(0, UiUtils.getStatusBarHeight(this), 0, 0);

        onShowGroupOverview();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_item_group_overview:
                onShowGroupOverview();
                break;
            case R.id.nav_item_voting:
                onShowVoting();
                break;
            case R.id.nav_item_send:
            case R.id.nav_item_share:
                Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unknown nav item selected.");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onShowGroupOverview() {
        GroupOverviewFragment frag = GroupOverviewFragment.newInstance(null, null);
        String tag = frag.getClass().getSimpleName();

        FragmentManager fm = getSupportFragmentManager();
        boolean hasBackStackEntries = fm.getBackStackEntryCount() > 0;

        FragmentTransaction ft = fm.beginTransaction();
        if (hasBackStackEntries) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.nav_drawer_content, frag, tag);
        ft.commit();
    }

    private void onShowVoting() {
        VotingFragment frag = VotingFragment.newInstance(null, null);
        String tag = frag.getClass().getSimpleName();

        FragmentManager fm = getSupportFragmentManager();
        boolean hasBackStackEntries = fm.getBackStackEntryCount() > 0;

        FragmentTransaction ft = fm.beginTransaction();
        if (hasBackStackEntries) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.nav_drawer_content, frag, tag);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}
