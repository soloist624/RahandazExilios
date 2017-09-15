package rahandaz.exilios.com.rahandaz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import rahandaz.exilios.com.rahandaz.Models.DataModel;
import rahandaz.exilios.com.rahandaz.Models.ProjectModel;
import rahandaz.exilios.com.rahandaz.TaskHolderFragment.TotalGetTaskHolderFragment;
import rahandaz.exilios.com.rahandaz.network.NetworkManager;
import rahandaz.exilios.com.rahandaz.network.RequestPackage;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener , TotalGetTaskHolderFragment.TotalGetTaskCallbacks {

    ViewPager mViewPager;
    TabLayout tabLayout;
    ProgressBar progressBar;
    TotalGetTaskHolderFragment totalGetTaskHolderFragment;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private static final String TAG_LOGIN_TASK_HOLDER_FRAGMENT = "login_task_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        progressBar = (ProgressBar) findViewById(R.id.fetching_progress);
        tabLayout = (TabLayout) findViewById(R.id.main_toolbar);

        android.app.FragmentManager fm = getFragmentManager();
        totalGetTaskHolderFragment = (TotalGetTaskHolderFragment) fm.findFragmentByTag(TAG_LOGIN_TASK_HOLDER_FRAGMENT);
        if (totalGetTaskHolderFragment == null) {
            totalGetTaskHolderFragment = new TotalGetTaskHolderFragment();
            fm.beginTransaction().add(totalGetTaskHolderFragment, TAG_LOGIN_TASK_HOLDER_FRAGMENT).commit();
        }

        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(mViewPager);

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerLayout.setBackgroundResource(R.color.toolbar_color);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[6];

        drawerItem[0] = new DataModel(1, "فرهنگ و هنر");
        drawerItem[1] = new DataModel(2, "صنایع دستی");
        drawerItem[2] = new DataModel(3, "فیلم و مستند");
        drawerItem[3] = new DataModel(4, "تکنولوژی");
        drawerItem[4] = new DataModel(5, "کسب و کار");
        drawerItem[5] = new DataModel(6, "خیریه");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.drawer_list_item, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        taskExecutor(0);
    }

    private void taskExecutor (int category){
        if (NetworkManager.isConnected(this)){
            requestData(NetworkManager.baseUrl+ "/projects/all", category);
        }else {
            Toast.makeText(this, "عدم دسترسی به شبکه!", Toast.LENGTH_LONG).show();
        }
    }

    private void requestData(String uri, int cat) {

        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("cat_id", cat+"");
        totalGetTaskHolderFragment.loginExecutor(p);
//        AsyncLoginService asyncLoginService = (AsyncLoginService) new AsyncLoginService().execute(p);
    }

    @Override
    public void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(ArrayList<ProjectModel> result) {
        progressBar.setVisibility(View.INVISIBLE);
        mViewPager.setAdapter(new SamplePagerAdapter(result, getSupportFragmentManager()));
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Toast.makeText(MainActivity.this, "he he he"+position , Toast.LENGTH_LONG).show();
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(mNavigationDrawerItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.empty, R.string.empty);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        ArrayList <ProjectModel> dataModelsOne = new ArrayList<>();
        ArrayList <ProjectModel> dataModelsTwo = new ArrayList<>();
        ArrayList <ProjectModel> dataModelsThree = new ArrayList<>();
        ArrayList <ProjectModel> dataModelsFour = new ArrayList<>();

        public SamplePagerAdapter(ArrayList <ProjectModel> projects, FragmentManager fm) {
            super(fm);
            for (ProjectModel model : projects) {
                switch (model.getCatType()){
                    case 0:
                        dataModelsOne.add(model);
                        break;
                    case 1:
                        dataModelsTwo.add(model);
                        break;
                    case 2:
                        dataModelsThree.add(model);
                        break;
                    case 3:
                        dataModelsFour.add(model);
                        break;
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Top";
                case 1:
                    return "Recents";
                case 2:
                    return "Trends";
                default:
                    return "More";
            }
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            SampleFragment temp = null;
            switch (position){
                case 0:
                    temp = new SampleFragment();
                    temp.putYourData(dataModelsOne);
                    return temp;
                case 1:
                    temp = new SampleFragment();
                    temp.putYourData(dataModelsTwo);
                    return temp;
                case 2:
                    temp = new SampleFragment();
                    temp.putYourData(dataModelsThree);
                    return temp;
                case 3:
                    temp = new SampleFragment();
                    temp.putYourData(dataModelsFour);
                    return temp;
            }
            return  null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
