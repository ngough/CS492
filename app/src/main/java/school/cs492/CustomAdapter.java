package school.cs492;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lingxi on 4/10/2015.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] foods) {
        super(context, R.layout.menu_item_card_view, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater buckyInflater = LayoutInflater.from(getContext());
        View customView = buckyInflater.inflate(R.layout.menu_item_card_view, parent, false);

        String singleFoodItem = getItem(position);
        TextView buckyText = (TextView) customView.findViewById(R.id.menu_item_name);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.menu_item_pic);

        buckyText.setText(singleFoodItem);
        buckysImage.setImageResource(R.drawable.soup);

        return customView;
    }
}
