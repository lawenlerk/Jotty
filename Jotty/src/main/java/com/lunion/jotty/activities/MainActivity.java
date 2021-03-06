package com.lunion.jotty.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lunion.jotty.R;
import com.lunion.jotty.fragments.HistoryFragment;
import com.lunion.jotty.fragments.NavigationDrawerFragment;
import com.lunion.jotty.fragments.OverviewFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, OverviewFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                OverviewFragment overviewFragment = (OverviewFragment) fragmentManager.findFragmentByTag(OverviewFragment.class.getName());
                if (overviewFragment == null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, OverviewFragment.newInstance(), OverviewFragment.class.getName())
                            .commit();
                    Log.d(MainActivity.class.getName(), "Creating OverviewFragment");
                }
                break;
            case 1:
                HistoryFragment historyFragment = (HistoryFragment) fragmentManager.findFragmentByTag(HistoryFragment.class.getName());
                if (historyFragment == null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, HistoryFragment.newInstance(), HistoryFragment.class.getName())
                            .commit();
                    Log.d(MainActivity.class.getName(), "Creating HistoryFragment");
                }
                break;
            case 2:
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_overview);
                break;
            case 2:
                mTitle = getString(R.string.title_history);
                break;
            case 3:
                mTitle = getString(R.string.title_settings);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_transaction:
                addNewTransaction();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewTransaction() {
        startActivity(new Intent(this, TransactionActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
