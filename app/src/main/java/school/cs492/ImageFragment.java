package school.cs492;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Represents a viewPager Fragment that contains a menu item image.
 */
public class ImageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Activity activity = getActivity();

        View view = LayoutInflater.from(activity)
                .inflate(R.layout.image_fragment, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.menu_item_container);

        // get the image directory
        String strtext = getArguments().getString("menu_item_url");

        // set the image to the ImageView
        Picasso.with(activity).load(strtext).fit().centerInside().into(imageView);

        return view;
    }
}