package com.app.cbfc.ui.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cbfc.R;
import com.app.cbfc.menu.DrawerAdapter;
import com.app.cbfc.menu.DrawerItem;
import com.app.cbfc.menu.SimpleItem;
import com.app.cbfc.ui.fragment.AddCustomerFragment;
import com.app.cbfc.ui.fragment.AddEmployeeFragment;
import com.app.cbfc.util.SharedPrefManager;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.app.cbfc.menu.DrawerAdapter;

import java.util.Arrays;

public class AdminDashboard extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_Home = 0;
    private static final int POS_Add_Employee = 1;
    private static final int POS_Add_Customber = 2;
    private static final int POS_Employee_list = 3;
    private static final int POS_Customber_list = 4;
    private static final int POS_Logout = 5;

    SharedPrefManager sharedPrefManager;
    Toolbar mToolbar;
    private SlidingRootNav slidingRootNav;
    TextView mNavAmountTv;
    private Drawable[] screenIcons;
    private String[] screenTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        getIds();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(mToolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        mNavAmountTv = slidingRootNav.getLayout().findViewById(R.id.nav_account_balance);
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();


        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_Home).setChecked(true),
                createItemFor(POS_Add_Employee),
                createItemFor(POS_Add_Customber),
                createItemFor(POS_Employee_list),
                createItemFor(POS_Customber_list),
                createItemFor(POS_Logout)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    private void getIds() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(android.R.color.darker_gray))
                .withTextTint(color(android.R.color.darker_gray))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_Home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        } else if (position == POS_Add_Employee) {
            AddEmployeeFragment employeeFragment = new AddEmployeeFragment();
            UpdateFragment(employeeFragment, "add_employee");
            //Toast.makeText(this, "Add C", Toast.LENGTH_SHORT).show();
        } else if (position == POS_Add_Customber) {
            AddCustomerFragment addCustomerFragment = new AddCustomerFragment();
            UpdateFragment(addCustomerFragment, "add_customber");
            //Toast.makeText(this, "My Order", Toast.LENGTH_SHORT).show();
        } else if (position == POS_Logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            sharedPrefManager.logout(getApplicationContext());
            finishAffinity();
        }

        slidingRootNav.closeMenu();
    }

    public void UpdateFragment(Fragment fragment, String CURRENT_TAG) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.card_flip_right_in,R.anim.card_flip_right_out,R.anim.card_flip_left_in,R.anim.card_flip_left_out);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_holder, fragment, CURRENT_TAG);
        fragmentTransaction.addToBackStack(CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void updateFragment(Fragment fragment, String CURRENT_TAG) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.card_flip_right_in,R.anim.card_flip_right_out,R.anim.card_flip_left_in,R.anim.card_flip_left_out);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_holder, fragment, CURRENT_TAG);
        fragmentTransaction.addToBackStack(CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
