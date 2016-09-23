package de.comdirect.collabothon2016.collabothon2016.activity;

import android.net.Uri;
import android.os.Bundle;
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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.event.GroupSelectedEvent;
import de.comdirect.collabothon2016.collabothon2016.fragment.GroupDetailsFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.LeaderboardFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.MyGroupsFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.OverviewFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.PieChartFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.PortfolioFragment;
import de.comdirect.collabothon2016.collabothon2016.fragment.VotingFragment;
import de.comdirect.collabothon2016.collabothon2016.model.Group;
import de.comdirect.collabothon2016.collabothon2016.service.GroupService;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MyGroupsFragment.OnFragmentInteractionListener,
        OverviewFragment.OnFragmentInteractionListener,
        PortfolioFragment.OnFragmentInteractionListener,
        VotingFragment.OnFragmentInteractionListener,
        LeaderboardFragment.OnFragmentInteractionListener,
        PieChartFragment.OnFragmentInteractionListener {

    @BindView(R.id.nav_drawer_content)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private GroupService groupService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        groupService = GroupService.init(GroupService.ENDPOINT);

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
            case R.id.nav_item_isin_browser:
                onShowIsinBrowser();
                break;
            case R.id.nav_item_account:
            case R.id.nav_item_open_wallet:
            case R.id.nav_item_about_and_help:
            case R.id.nav_item_change_avatar:
            case R.id.nav_item_isin_favorites:
            case R.id.nav_item_notifications:
            case R.id.nav_item_software_licenses:
//                Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unknown nav item selected.");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onShowGroupOverview() {
        getSupportActionBar().setTitle("My Groups");
        MyGroupsFragment frag = MyGroupsFragment.newInstance(null, null);
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

    private void onShowGroupDetails(Group group) {
        GroupSelectedEvent event = new GroupSelectedEvent();
        event.group = group;
        EventBus.getDefault().postSticky(event);

        getSupportActionBar().setTitle(group.groupName);
        GroupDetailsFragment frag = GroupDetailsFragment.newInstance(null, null);
        String tag = frag.getClass().getSimpleName();

        FragmentManager fm = getSupportFragmentManager();
        boolean hasBackStackEntries = fm.getBackStackEntryCount() > 0;

        FragmentTransaction ft = fm.beginTransaction();
//        if (hasBackStackEntries) {
        ft.addToBackStack(null);
//        }
        ft.replace(R.id.nav_drawer_content, frag, tag);
        ft.commit();
    }

    private void onShowIsinBrowser() {
//        getSupportActionBar().setTitle("ISIN Browser");
//        VotingFragment frag = VotingFragment.newInstance(null, null);
//        String tag = frag.getClass().getSimpleName();
//
//        FragmentManager fm = getSupportFragmentManager();
//        boolean hasBackStackEntries = fm.getBackStackEntryCount() > 0;
//
//        FragmentTransaction ft = fm.beginTransaction();
//        if (hasBackStackEntries) {
//            ft.addToBackStack(null);
//        }
//        ft.replace(R.id.nav_drawer_content, frag, tag);
//        ft.commit();
    }

//    public void onShowGroupDetails(int groupId) {
//        GroupSelectedEvent event = new GroupSelectedEvent();
//        event.groupId = groupId;
//
////        Response<ResponBody> body = groupService.getGroup(id);
////        body.body();
//
//
//        EventBus.getDefault().postSticky(event);
////        EventBus.getDefault().getStickyEvent(GroupSelectedEvent.class).id
//
//        getSupportActionBar().setTitle("GRUPPENNAMMMMMEEE");
//        GroupDetailsFragment frag = GroupDetailsFragment.newInstance(null, null);
//        String tag = frag.getClass().getSimpleName();
//
//        FragmentManager fm = getSupportFragmentManager();
//        boolean hasBackStackEntries = fm.getBackStackEntryCount() > 0;
//
//        FragmentTransaction ft = fm.beginTransaction();
//        if (hasBackStackEntries) {
//            ft.addToBackStack(null);
//        }
//        ft.replace(R.id.nav_drawer_content, frag, tag);
//        ft.commit();
//    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void groupItemSelected(Group group) {
        onShowGroupDetails(group);
    }

}
