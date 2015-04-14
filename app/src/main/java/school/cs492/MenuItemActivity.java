package school.cs492;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Represents the Menu Item Activity. It displays menu items pictures, ingredients, nutritional facts, and allergy information.
 *
 * @author fiorfe01
 */
public class MenuItemActivity extends ActionBarActivity {

    private ViewPager viewPager;

    private TextView menuItemTitle;

    private FragmentManager fragmentManager;

    private Toolbar toolbar;

    private ArrayList<String> scannedQRs = new ArrayList<String>();

    private String picPath = "https://veggiedivaskitchen.files.wordpress.com/2012/03/lentil-bean-soup.jpg"; //plan b

    /**
     * Gets the resource string identifier.
     *
     * @param context
     * @param name
     * @return string identifier for resource
     */
    public static int getStringIdentifier(Context context, String name) {

        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // set a toolbar to replace the action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // instantiate the viewPager for menu item picture gallery
        viewPager = (ViewPager) findViewById(R.id.pager);
        fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new PagerAdapter(fragmentManager, this));

        // set the circle page indicator for the viewPager
        CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.pager_indicator);
        circleIndicator.setViewPager(viewPager);
        final float density = getResources().getDisplayMetrics().density;
        circleIndicator.setRadius(4 * density);
        circleIndicator.setStrokeWidth(0);
        circleIndicator.setPageColor(getResources().getColor(R.color.background_grey));

        // set the menu item title TODO pass the title of the specific menu item
        menuItemTitle = (TextView) findViewById(R.id.menu_item_title);
        menuItemTitle.setText("Insert Menu Item Title Here");

        // set picture path from qr scan
        setPicPath();

        // set the menu item cards TODO pass the text data to this constructor
        setIngredientsCard();
        setNutritionalFactsCard();
        setAllergyInformationCard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent, intent_scanned;

        // handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_scan_new_item:

                intent = new Intent(this, CameraScanActivity.class);
                // Toss around the array list around between MenuItemActivity and CameraScanActivity.
                intent.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent);
                return true;

            case R.id.opt_scan_new_item:

                intent = new Intent(this, CameraScanActivity.class);
                // Toss around the array list around between MenuItemActivity and CameraScanActivity.
                intent.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent);
                return true;

            case R.id.action_scanned_list:

                intent_scanned = new Intent(this, SavedMenuItemsList.class);
                intent_scanned.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent_scanned.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent_scanned);
                return true;

            case R.id.opt_scanned_list:

                intent_scanned = new Intent(this, SavedMenuItemsList.class);
                intent_scanned.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent_scanned.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent_scanned);
                return true;

            case R.id.action_go_to_main:

                Intent intent_main = new Intent(this, MainMenuActivity.class);
                intent_main.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent_main.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent_main);

                return true;

            case R.id.opt_go_to_main:

                Intent intent_main2 = new Intent(this, MainMenuActivity.class);
                intent_main2.putExtra("SCANNED_QR_ITEM", scannedQRs);
                intent_main2.putExtra("CALLER", ActivityID.MenuItemActivity);
                startActivity(intent_main2);

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Returns true if arrayList already has that element.
     */
    public boolean contains(String picPath) {

        boolean result = false;

        for (int i = 0; i < scannedQRs.size(); i++) {

            if (scannedQRs.get(i).equals(picPath)) {

                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Sets the picture path from the qr scan.
     */
    private void setPicPath() {

        int callingActivity = getIntent().getIntExtra("CALLER", 0);

        switch (callingActivity) {

            case ActivityID.CameraScanActivity:

                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_CAM");

                picPath = getIntent().getExtras().getString("QR_RESULT");

                if (!contains(picPath)) {

                    scannedQRs.add(picPath);
                }

                break;

            case ActivityID.SavedMenuItemsList:

                scannedQRs = getIntent().getStringArrayListExtra("SCANNED_QR_LIST");

                picPath = getIntent().getExtras().getString("QR_RESULT");

                break;
        }
    }

    /**
     * Gets the restaurant data path used to get the data from resource.
     *
     * @return restaurant + "_" + dish
     */
    private String getRestaurantDataPath() {

        String restaurant = picPath.substring(23, 34);

        String dish = picPath.substring(35, 40);

        return restaurant + "_" + dish;
    }

    /**
     * Sets the ingredients card.
     */
    private void setIngredientsCard() {

        CardView ingredientsCard = (CardView) findViewById(R.id.ingredients_card);

        TextView ingredientsTitle = (TextView) ingredientsCard.findViewById(R.id.custom_card_title);

        TextView ingredientsContent = (TextView) ingredientsCard.findViewById(R.id.custom_card_content);

        ingredientsTitle.setText(R.string.ingredients_title);

        int ingredientsPath = getStringIdentifier(this, "ingredients_" + getRestaurantDataPath());

        ingredientsContent.setText(getString(ingredientsPath));
    }

    /**
     * Sets the nutritional facts card.
     */
    private void setNutritionalFactsCard() {

        CardView nutritionalFactsCard = (CardView) findViewById(R.id.nutritional_facts_card);

        TextView nutritionalFactsTitle = (TextView) nutritionalFactsCard.findViewById(R.id.custom_card_title);

        TextView nutritionalFactsContent = (TextView) nutritionalFactsCard.findViewById(R.id.custom_card_content);

        nutritionalFactsTitle.setText(R.string.nutritional_facts_title);

        int nutritionalFactsPath = getStringIdentifier(this, "nutrition_" + getRestaurantDataPath());

        nutritionalFactsContent.setText(getString(nutritionalFactsPath));
    }

    /**
     * Sets the allergy information card. TODO add argument that takes the text content
     */
    private void setAllergyInformationCard() {

        CardView allergyInformationCard = (CardView) findViewById(R.id.allergy_info_card);

        TextView allergyInformationTitle = (TextView) allergyInformationCard.findViewById(R.id.custom_card_title);

        TextView allergyInformationContent = (TextView) allergyInformationCard.findViewById(R.id.custom_card_content);

        allergyInformationTitle.setText(R.string.allergy_info_title);

        // TODO use the argument to set the content text
        allergyInformationContent.setText("Insert content here");
    }

    /**
     * Represents the adapter for the viewPager.
     *
     * @author fiorfe01
     */
    class PagerAdapter extends FragmentPagerAdapter {

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
//                    bundle.putString("menu_item_url", "https://veggiedivaskitchen.files.wordpress.com/2012/03/lentil-bean-soup.jpg");

                    bundle.putString("menu_item_url", picPath + "pic1.jpg");

                    imageFragment = new ImageFragment();

                    imageFragment.setArguments(bundle);

                    return imageFragment;

                case 1:

                    // TODO pass a directory for second picture from server
//                  bundle.putString("menu_item_url", "https://mydinnertoday.files.wordpress.com/2010/01/img_6926.jpg");

                    bundle.putString("menu_item_url", picPath + "pic2.jpg");
                    imageFragment = new ImageFragment();

                    imageFragment.setArguments(bundle);

                    return imageFragment;

                case 2:

                    // TODO pass a directory for third picture from server
//                    bundle.putString("menu_item_url", "https://voguevegetarian.files.wordpress.com/2012/08/mexican-red-lentil-bean-soup.jpg");

                    bundle.putString("menu_item_url", picPath + "pic3.jpg");
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