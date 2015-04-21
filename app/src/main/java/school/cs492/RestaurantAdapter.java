package school.cs492;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Represents the adapter for the RestaurantListActivity.
 *
 * @author fiorfe01
 */
public class RestaurantAdapter extends ArrayAdapter<String> {

    private ArrayList<String> scannedQrs;

    private String[] paths;

    private String[] names;

    public RestaurantAdapter(Context context, String[] paths, String[] names, ArrayList<String> scannedQrs) {

        super(context, R.layout.restaurant_card_view, names);

        this.scannedQrs = scannedQrs;

        this.paths = paths;

        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater buckyInflater = LayoutInflater.from(getContext());
        View customView = buckyInflater.inflate(R.layout.restaurant_card_view, parent, false);

        TextView buckyText = (TextView) customView.findViewById(R.id.rest_name);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.rest_pic);

        buckyText.setText(names[position]);

        Picasso.with(getContext()).load(paths[position]).into(buckysImage);

        return customView;
    }
}