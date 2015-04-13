package school.cs492;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Represents the adapter for the viewPager.
 *
 * @author fiorfe01
 */
public class PagerAdapter extends FragmentPagerAdapter {

    /**
     * Constructor.
     *
     * @param fm
     */
    public PagerAdapter(FragmentManager fm, Context context) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        ImageFragment imageFragment;

        Bundle bundle = new Bundle();

        // changes the displayed menu item picture
        switch (position) {

            case 0:

                // TODO pass a directory for first picture from server
                bundle.putString("menu_item_url", "https://veggiedivaskitchen.files.wordpress.com/2012/03/lentil-bean-soup.jpg");

                imageFragment = new ImageFragment();

                imageFragment.setArguments(bundle);

                return imageFragment;

            case 1:

                // TODO pass a directory for second picture from server
                bundle.putString("menu_item_url", "https://mydinnertoday.files.wordpress.com/2010/01/img_6926.jpg");

                imageFragment = new ImageFragment();

                imageFragment.setArguments(bundle);

                return imageFragment;

            case 2:

                // TODO pass a directory for third picture from server
                bundle.putString("menu_item_url", "https://voguevegetarian.files.wordpress.com/2012/08/mexican-red-lentil-bean-soup.jpg");

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