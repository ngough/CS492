package school.cs492;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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

    private PagerAdapter pagerAdapter;

    private ArrayList<String> scannedQRs = new ArrayList<String>();

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
        pagerAdapter = new PagerAdapter(fragmentManager, this);
        viewPager.setAdapter(pagerAdapter);

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

                // TODO call intent to go to main menu
                return true;

            case R.id.opt_go_to_main:

                // TODO call intent to go to main menu
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Sets the ingredients card. TODO add argument that takes the text content
     */
    private void setIngredientsCard() {

        CardView ingredientsCard = (CardView) findViewById(R.id.ingredients_card);

        TextView ingredientsTitle = (TextView) ingredientsCard.findViewById(R.id.custom_card_title);

        TextView ingredientsContent = (TextView) ingredientsCard.findViewById(R.id.custom_card_content);

        ingredientsTitle.setText(R.string.ingredients_title);

        // TODO use the argument to set the content text
        ingredientsContent.setText("Insert content here");
    }

    /**
     * Sets the nutritional facts card. TODO add argument that takes the text content
     */
    private void setNutritionalFactsCard() {

        CardView nutritionalFactsCard = (CardView) findViewById(R.id.nutritional_facts_card);

        TextView nutritionalFactsTitle = (TextView) nutritionalFactsCard.findViewById(R.id.custom_card_title);

        TextView nutritionalFactsContent = (TextView) nutritionalFactsCard.findViewById(R.id.custom_card_content);

        nutritionalFactsTitle.setText(R.string.nutritional_facts_title);

        // TODO use the argument to set the content text
        nutritionalFactsContent.setText("Insert content here");
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
}