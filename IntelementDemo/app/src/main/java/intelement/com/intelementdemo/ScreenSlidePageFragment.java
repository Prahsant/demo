package intelement.com.intelementdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prashantp on 13/2/16.
 */
public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        int id = getArguments().getInt("id");

        TextView txtScreenId = (TextView) rootView.findViewById(R.id.txtScreenId);
        txtScreenId.setText("Screen " + id);

        RelativeLayout layout = (RelativeLayout)rootView.findViewById(R.id.screenLayout);
        layout.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = getArguments().getInt("id");

        Toast.makeText(getContext(), "Page " + id, Toast.LENGTH_SHORT).show();

    }
}
