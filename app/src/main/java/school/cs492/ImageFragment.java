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

    private View view;

    private ImageView imageView;

    private String strtext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Activity activity = getActivity();

        view = LayoutInflater.from(activity)
                .inflate(R.layout.image_fragment, container, false);

        imageView = (ImageView) view.findViewById(R.id.menu_item_container);

        strtext = getArguments().getString("menu_item_url");

        // use library to add the picture into the imageView
        Picasso.with(activity).load(strtext).into(imageView);

        return view;
    }
}