package com.ntg.seqaya.seqayamvpclean.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.manufacturer.ManufacturerClick;
import com.ntg.seqaya.seqayamvpclean.presentation.manufacturer.ManufactureFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.cart.CartFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.favourites.FavouritesFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.orderhistory.OrderHistoryFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.products.ProductsFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.settings.SettingsFragment;
import com.ntg.seqaya.seqayamvpclean.utils.LocalManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements CartItemsCountListener, ManufacturerClick {

    public static String ORDER = "order";
    @BindView(R.id.include)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupBottomNavigation();

        bottomNavigationView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ManufactureFragment.newInstance())
                .commit();
    }

    private void setupBottomNavigation() {
        AHBottomNavigationItem catalog = new AHBottomNavigationItem(getString(R.string.catalog),
                R.drawable.ic_bottle);
        AHBottomNavigationItem cart = new AHBottomNavigationItem(getString(R.string.shopping_cart),
                R.drawable.ic_shopping_cart);
        AHBottomNavigationItem history = new AHBottomNavigationItem(getString(R.string.history),
                R.drawable.ic_order_history);
        AHBottomNavigationItem fav = new AHBottomNavigationItem(getString(R.string.favourite),
                R.drawable.ic_fav);
        AHBottomNavigationItem settings = new AHBottomNavigationItem(getString(R.string.settings),
                R.drawable.ic_settings);

        bottomNavigationView.addItem(catalog);
        bottomNavigationView.addItem(cart);
        bottomNavigationView.addItem(history);
        bottomNavigationView.addItem(fav);
        bottomNavigationView.addItem(settings);

        bottomNavigationView.setAccentColor(getResources().getColor(R.color.colorAccent));
        bottomNavigationView.setInactiveColor(getResources().getColor(R.color.gray));

        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        bottomNavigationView.setOnTabSelectedListener((
                (position, wasSelected) -> {
                    switch (position) {
                        case 0:
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container,
                                            ManufactureFragment.newInstance())
                                    .commit();
                            return true;

                        case 1:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, CartFragment.newInstance(),
                                            "CartFragment").commit();
                            return true;

                        case 2:
                            getFragmentManager().beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, new OrderHistoryFragment()).commit();
                            return true;

                        case 3:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, new FavouritesFragment()).commit();
                            return true;

                        case 4:
                            getFragmentManager()
                                    .beginTransaction().addToBackStack(null)
                                    .replace(R.id.container, SettingsFragment.newInstance())
                                    .commit();
                            return true;
                        default:
                            return false;
                    }
                }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("CartFragment");
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showCartItemsCountNotification(int count) {
        AHNotification notification = new AHNotification.Builder()
                .setText(count + "")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }

    @Override
    public void onCartItemsCountChanged(int count) {
        if (count == 0) {
            bottomNavigationView.setNotification("", 1);
        } else {
            showCartItemsCountNotification(count);
        }
    }

    @Override
    public void clickListener(boolean b) {
        if (b) {
            bottomNavigationView.setVisibility(View.VISIBLE);
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, ProductsFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.onAttach(base, "ar"));
    }
}

