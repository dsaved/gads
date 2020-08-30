package com.dsaved.gadsleaderboard;

import android.view.View;

/**
 * Created by User on 11/3/2016. ${PACKAGE_NAME}
 building Zonemingle
 */

public class RecyclerView_OnClickListener {
    /**
     * Interface for Item Click over Recycler View Items
     **/
    public interface OnClickListener {
        public void OnItemClick(View view, int position);
    }
}