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
 * Represents an individual fragment that contains a picture.
 *
 * @author fiorfe01
 */
public class ImageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Activity activity = getActivity();

        View view = LayoutInflater.from(activity)
                .inflate(R.layout.image_fragment, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.menu_item_container);

        String strtext = getArguments().getString("menu_item_url");

        // use library to add the picture into the imageView
        Picasso.with(activity).load(strtext).fit().centerCrop().into(imageView);

        return view;
    }
}