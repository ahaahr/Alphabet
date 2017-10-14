package golf.alphabet.com.alphabet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hannes on 2017-10-14.
 */

public class SessionsFragment extends Fragment {

    public static SessionsFragment newInstance(int position) {
        SessionsFragment fragment =  new SessionsFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sessions_fragment, container, false);

        return view;
    }
}
