package com.app.cbfc.ui.activity;

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
import com.app.cbfc.ui.fragment.AddEmployeeFragment;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;
import java.util.List;

public class EmployeeDashboard extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{
    private static final int POS_Home = 0;
    private static final int POS_Profile = 1;
    private static final int POS_Order = 2;
    private static final int POS_LOGOUT = 3;

    Toolbar mToolbar;
    private SlidingRootNav slidingRootNav;
    TextView mNavAmountTv;
    private Drawable[] screenIcons;
    private String[] screenTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                createItemFor(POS_Profile),
                createItemFor(POS_Order),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

    }
    private void getIds()
    {

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
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
            AddEmployeeFragment employeeFragment = new AddEmployeeFragment();
            updateFragment(employeeFragment,"add_employee");
        }else if(position==POS_Profile)
        {
            Toast.makeText(this, "Add C", Toast.LENGTH_SHORT).show();
        }else if(position==POS_Order)
        {
            Toast.makeText(this, "My Order", Toast.LENGTH_SHORT).show();
        }
        else if(position==POS_LOGOUT)
        {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }

        slidingRootNav.closeMenu();
    }

    public void updateFragment(Fragment fragment ,String CURRENT_TAG){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.card_flip_right_in,R.anim.card_flip_right_out,R.anim.card_flip_left_in,R.anim.card_flip_left_out);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_holder, fragment, CURRENT_TAG);
        fragmentTransaction.addToBackStack(CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
