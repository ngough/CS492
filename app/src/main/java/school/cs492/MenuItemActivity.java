package school.cs492;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class MenuItemActivity extends ActionBarActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_activity);

        viewPager = (ViewPager) findViewById(R.id.pager);

        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new MyAdapter(fragmentManager));
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            ImageFragment imageFragment;

            Bundle bundle = new Bundle();

            switch (position) {

                case 0:

                    bundle.putString("menu_item_url", "http://www.queposfishadventure.com/tuna.jpg");

                    imageFragment = new ImageFragment();

                    imageFragment.setArguments(bundle);

                    return imageFragment;

                case 1:

                    bundle.putString("menu_item_url", "http://images.politico.com/global/news/111205_tuna_ap_328.jpg");

                    imageFragment = new ImageFragment();

                    imageFragment.setArguments(bundle);

                    return imageFragment;

                case 2:

                    bundle.putString("menu_item_url", "http://www.sustainablesushi.net/wp-content/uploads/2008/12/tuna-yellowfin.jpg");

                    imageFragment = new ImageFragment();

                    imageFragment.setArguments(bundle);

                    return imageFragment;
            }

            return null;
        }

        @Override
        public int getCount() {

            return 3;
        }
    }
}