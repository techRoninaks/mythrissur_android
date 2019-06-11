package com.roninaks.mythri.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.roninaks.mythri.R;


public class MainActivity<drawable> extends AppCompatActivity {
    BottomNavigationView navigation;
    Fragment fragment;
    Button button;
    MenuItem prevItem;
    boolean flag=false, uriPresentFlag=false;
    Uri appLinkData;
    Bundle bundle;
    public String path;
    ImageView imageView;
    WebView webView;
    String master_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prevItem = null;
        bundle=new Bundle();
        path = "";
        master_url =getString(R.string.master_url);
        //checking internet connection
        if (!isConnected(MainActivity.this)) {

            setContentView(R.layout.activity_error);

            //add custom action bar
            this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar);
            getSupportActionBar().setElevation(0);

            //reload button
            button = findViewById(R.id.refresh);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                    startActivity(getIntent());
                }
            });
//            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        } else {
            setContentView(R.layout.activity_main);
            //custom action bar
            this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar);
           getSupportActionBar().setElevation(0);
            webView=findViewById(R.id.webview);
            imageView=(ImageView) findViewById(R.id.menuid);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showPopup(findViewById(R.id.navigation_close));
                    toggle("test", flag);
                    flag = !flag;
                }
            });
            //add bottom navigation
            navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setItemIconTintList(null);
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                View activeLabel = item.findViewById(R.id.largeLabel);

                if (activeLabel instanceof TextView) {
                    activeLabel.setPadding(0, 0, 0, 0);
                }
                if(i == menuView.getChildCount()-1){
                    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                    View icon = item.findViewById(android.support.design.R.id.icon);
                    icon.setPadding(0, 0, 30, 0);
                    View label = item.findViewById(android.support.design.R.id.smallLabel);
                    ViewGroup.LayoutParams labelParams = label.getLayoutParams();
                    labelParams.height = 0;
                    labelParams.width = 0;
                    label.setLayoutParams(labelParams);

                }

            }

            //handle navigation selected items
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            Intent appLinkIntent = getIntent();
            String appLinkAction = appLinkIntent.getAction();
            Uri appLinkData = appLinkIntent.getData();
            if(appLinkData == null){
                navigation.setSelectedItemId(R.id.navigation_home);
            }else{
                handleUri(appLinkData);
            }

        }

    }

    //bottom navigation

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            if(prevItem != null)
                setDefaultIcon(prevItem);
            prevItem = item;
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    item.setIcon(R.drawable.home_14xhdpi_min);

                    path = uriPresentFlag ? path : master_url +"home.html?m_view=1";
                    break;
                case R.id.navigation_volunteer:
                    item.setIcon(R.drawable.volunteerselected_19xhdpi_min);
                    path = uriPresentFlag ? path : master_url +"cate_listing.html?cat_type=volunteer&pg_no=1&m_view=1";
                    break;
                case R.id.navigation_donate:
                    item.setIcon(R.drawable.donateselected_17xhdpi_min);
                    path = uriPresentFlag ? path : master_url +"cate_listing.html?cat_type=donate&pg_no=1&m_view=1";
                    break;

                case R.id.navigation_profile:
                    item.setIcon(R.drawable.profileselected_21xhdpi_min);
                    path = uriPresentFlag ? path : master_url +"profile.html?m_view=1";
                    break;
                /*case R.id.navigation_close:
                    toggle("test",flag,item);
                    flag = !flag;
                    break;*/
            }

            uriPresentFlag = false;
            setWebContent(path);
//            setFragment(fragment,bundle);
            return true;
        }
    };


    /**
     * add menu item animation
     * @param value =this contain any string
     * @param visible=this contain current state of popupmenu
     */

    public void toggle(String value,boolean visible) {
        try {
            if (visible) {
                imageView.setImageResource(R.drawable.menu_25xhdpi_min);
                AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.menu_animatable);
//                BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
//                View icon = ((BottomNavigationItemView) menuView.getChildAt(4)).findViewById(android.support.design.R.id.icon);
//                icon.setPadding(0, 8, 30, 0);
//                icon.setZ(5.0f);
//                icon.setClipToOutline(false);
                drawable.start();
            } else {
                showPopup(findViewById(R.id.navigation_close));
                imageView.setImageResource(R.drawable.close_23xhdpi_min);
                AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.menu_animatable);
//                imageView.setImageResource(R.drawable.close_23xhdpi_min);
//                BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
//                View icon = ((BottomNavigationItemView) menuView.getChildAt(4)).findViewById(android.support.design.R.id.icon);
//                icon.setPadding(0, 8, 0, 0);
//                icon.setZ(5.0f);
//                icon.setClipToOutline(false);
                drawable.start();

            }
        }catch (Exception e){
            Toast.makeText(this,e+"Found",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * this function is used for checking internet connecting
     * @param context=this contain current activity variable
     * @return
     */

    public boolean isConnected(Context context){

        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile!=null && mobile.isConnectedOrConnecting()) || (wifi!=null && wifi.isConnectedOrConnecting())){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    /**
     * this function is used for showing popup menu
     * @param v
     */

    public void showPopup(View v){
        try {

            PopupMenu popupMenu=new PopupMenu(MainActivity.this,v);
            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                @Override
                public void onDismiss(PopupMenu menu) {
                    toggle("test",flag);
                    flag = !flag;
                }
            });
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    path = master_url;
                    switch (item.getItemId()) {
                        case R.id.event:
//                       fragment=new Events();
                            path = path.concat("cate_listing.html?cat_type=events&m_view=1");
                            break;
                        case R.id.initiative:
                            path = path.concat("cate_listing.html?cat_type=janananma&m_view=1");
                            break;
                        case R.id.care:
                            path = path.concat("cate_listing.html?cat_type=care_centers&m_view=1");
                            break;
                        case R.id.raise:
                            path = path.concat("forms.html?cat_type=raise_your_voice&m_view=1");
                            break;
                        case R.id.signup:
                            path = path.concat("profile.html?m_view=1");
                            break;
                        case R.id.contact:
                            path = path.concat("contact.html?m_view=1");
                            break;
                        case R.id.about:
                            path = path.concat("about.html?m_view=1");
                            break;
                    }
                    setWebContent(path);
                    return true;
                }
            });
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.show();
        }catch (Exception e){
            Toast.makeText(this,e+"Found",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * this function is used for set navigation items
     * @param menuItem
     */

    private void setDefaultIcon(final MenuItem menuItem){
        try {
            switch (menuItem.getItemId()){
                case R.id.navigation_home: //Home
                {
                    menuItem.setIcon(R.drawable.home_unselected_15xhdpi_min);
                }
                break;
                case R.id.navigation_volunteer: //Volunteer
                {
                    menuItem.setIcon(R.drawable.volunteer_20xhdpi_min);
                }

                break;
                case R.id.navigation_donate://Donate
                {
                    menuItem.setIcon(R.drawable.donate_18xhdpi_min);
                }
                break;
                case R.id.navigation_profile: //Profile
                {
                    menuItem.setIcon(R.drawable.profile_22xhdpi_min);
                }
                break;
              /*  case R.id.navigation_close: //menu
                {
                    menuItem.setIcon(R.drawable.menu_25xhdpi_min);
                    menuItem.getIcon();

                }
                break;*/
            }
        }catch (Exception e){
            Toast.makeText(this,e+"Found",Toast.LENGTH_LONG).show();
        }

    }

    /***
     * Sets bottom navigation icons according to change in webview url
     * @param url - Changed url
     */
    private void setPageIcon(String url) {
        Menu menu = navigation.getMenu();
        setDefaultIcon(prevItem);
        if(url.contains("home.html")){
            MenuItem item = menu.getItem(0);
            item.setIcon(R.drawable.home_14xhdpi_min);
            prevItem = item;
        }else if(url.contains("profile.html")){
            MenuItem item = menu.getItem(3);
            item.setIcon(R.drawable.profileselected_21xhdpi_min);
            prevItem = item;
        }else if(url.contains("cate_listing.html")){
            if(url.contains("cat_type=donate")){
                MenuItem item = menu.getItem(2);
                item.setIcon(R.drawable.donateselected_17xhdpi_min);
                prevItem = item;
            }
            else if(url.contains("cat_type=volunteer")) {
                MenuItem item = menu.getItem(1);
                item.setIcon(R.drawable.volunteerselected_19xhdpi_min);
                prevItem = item;
            }
        }
    }

    /***
     *This function is used for handle the incoming url
     * @param appLinkData - This contains the link coming into the activity
     */
    private void handleUri(Uri appLinkData) {
        try {
            uriPresentFlag = true;
            String uri = appLinkData.toString();
            path = uri.contains("m_view") ? uri : uri.contains("?") ? (uri.contains("&") ? uri.concat("&m_view=1") : uri.concat("m_view=1")) : uri.concat("?m_view=1");
            if (uri.contains("home.html")) {
                navigation.setSelectedItemId(R.id.navigation_home);
            } else if (uri.contains("cate_listing.html") && uri.contains("cat_type=donate")) {
                navigation.setSelectedItemId(R.id.navigation_donate);
            } else if (uri.contains("profile.html")) {
                navigation.setSelectedItemId(R.id.navigation_profile);
            } else if (uri.contains("cate_listing.html") && uri.contains("cat_type=volunteer")) {
                navigation.setSelectedItemId(R.id.navigation_volunteer);
            } else if (uri.contains("about.html")) {
                setWebContent(path);
            } else if (uri.contains("cate_listing") && uri.contains("cat_type=janananma")) {
                setWebContent(path);
            } else if (uri.contains("cate_listing") && uri.contains("cat_type=care_center")) {
                setWebContent(path);
            }else if (uri.contains("cate_listing") && uri.contains("cat_type=events")) {
                setWebContent(path);
            }else if (uri.contains("forms.html") && uri.contains("cat_type=raise_your_voice")) {
                setWebContent(path);
            }else if (uri.contains("contact.html")) {
                setWebContent(path);
            }else if (uri.contains("forms.html") && (uri.contains("cat_type=volunteer") || uri.contains("cat_type=register")) ) {
                setWebContent(path);
            }
        }catch (Exception e){
            Toast.makeText(this,e+"Found",Toast.LENGTH_LONG).show();
        }
    }


    private void setWebContent(String path){
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });
        try {
            webView.loadUrl(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                setPageIcon(url);
            }
        });

    }
}
