package com.dicksonmully6gmail.myrestaurants.util;

/**
 * Created by dickson on 6/11/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
