package com.example.horinoa.mytoolbartemplate;

/*20150910 Gitってみた*/

import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import static com.example.horinoa.mytoolbartemplate.MyConst.*;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private SearchView mSearchView;
    private DrawerItem[] mDrawerItems  = DRAWER_ITEMS;
    private Menu mainmenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerAdapter adapter = new DrawerAdapter(this, mDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*DrawerLayout-itemクリックしたとき走るメソッド,*/
                selectitem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null){
            MyDialogShowFragment mainFragment = new MyDialogShowFragment();
            FragmentManager ft = getFragmentManager();
            ft.beginTransaction().replace(R.id.container,mainFragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mainmenu = menu;
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setIcon(R.drawable.ic_search_white_36dp);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint(getString(R.string.please_serch_string));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String namelike) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(final String query) {
                mSearchView.clearFocus();
                //ここからSerchVie処理書く
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //物理キー押した時強制的にメニュー表示
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int action = event.getAction();
        final int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_UP) {
            // メニュー表示
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                if (mainmenu != null) {
                    mainmenu.performIdentifierAction(R.id.overflow_options, 0);
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            return true;
        } else if (id == R.id.end) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "onStop");
    }

    //NavigaterDrawe選択時処理
    protected void selectitem(int position) {
        mDrawerLayout.closeDrawers();
        Fragment fragment;
        switch (position) {
            case 0:
                //実行する内容
                fragment = new MyDialogShowFragment();
                break;
            case 1:
                //実行する内容
                fragment = new MyListFragment();
                break;
            case 2:
                fragment = new WebApiFragment();
                break;
            default:
                return;
        }
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.container);
        layout.removeAllViewsInLayout();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
