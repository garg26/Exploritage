package simplifii.framework.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by robin on 11/14/16.
 */

public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(getViewID(), null);
        initViews();
        return v;

    }

    public View findView(int id) {
        return v.findViewById(id);
    }

    protected void setOnClickListener(int... viewIds) {
        for (int i : viewIds) {
            findView(i).setOnClickListener(this);
        }
    }

    protected abstract int getViewID();

    protected abstract void initViews();

    @Override
    public abstract void onClick(View view);
}
