package com.exploritage.util;

import android.support.v4.app.Fragment;

/**
 * Created by ajay on 18/2/17.
 */

public interface HomeFragmentListener {
    void addFragment(Fragment fragment,boolean isAddToBackStack);
    void setTitle(String title);
    void setSubTitle(String subtitle);
}
