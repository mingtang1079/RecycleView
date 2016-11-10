
/**
 * Copyright 2015 Patrice Mueller. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package qinshi.mylibrary.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class Navigator {
    @NonNull
    protected final FragmentManager mFragmentManager;

    @IdRes
    protected final int mDefaultContainer;

    /**
     * This constructor should be only called once per
     *
     * @param fragmentManager Your FragmentManger
     * @param defaultContainer Your container id where the fragments should be placed
     */
    public Navigator(@NonNull final FragmentManager fragmentManager, @IdRes final int defaultContainer) {
        mFragmentManager = fragmentManager;
        mDefaultContainer = defaultContainer;
    }

    /**
     * @return the current active fragment. If no fragment is active it return null.
     */
    public Fragment getActiveFragment() {
        if (mFragmentManager.getBackStackEntryCount() == 0) {
            Log.e("tag", "没有gragmen");
            return null;
        }
        String tag = mFragmentManager
                .getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();
        Log.e("tag", tag);
        return mFragmentManager.findFragmentByTag(tag);
    }

    /**
     * Pushes the fragment, and add it to the history (BackStack)
     *
     * @param fragment the fragment which
     */
    public void goTo(final Fragment fragment) {
        mFragmentManager.beginTransaction()
                .addToBackStack(getName(fragment))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(mDefaultContainer, fragment, getName(fragment))
                .commit();
        mFragmentManager.executePendingTransactions();
    }

    /**
     * This is just a helper method which returns the simple name of
     * the fragment.
     * @param fragment that get added to the history (BackStack)
     * @return the simple name of the given fragment
     */
    protected String getName(final Fragment fragment) {
        return fragment.getClass().getSimpleName();
    }

    /**
     * Set the new root fragment. If there is any entry in the history (BackStack)
     * it will be deleted.
     *
     * @param startFragment the new root fragment
     */
    public void setRootFragment(final Fragment startFragment) {

        this.showFragment(startFragment);
    }

    /**
     * Replace the current fragment with the given one, without to add it to backstack.
     * So when the users navigates away from the given fragment it will not appaer in
     * the history.
     *
     * @param fragment the new fragment
     */
    private void showFragment(Fragment fragment) {


        int i = 0;
        for (Fragment f :
                mFragmentManager.getFragments()) {
            mFragmentManager.beginTransaction().hide(f).commit();
            Log.e("tag", String.valueOf(i++));

        }

        if (fragment.isAdded()) {
            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            mFragmentManager.beginTransaction().add(mDefaultContainer, fragment).commit();
        }
    }

    /**
     * Goes one entry back in the history
     */
    public void goOneBack() {
        mFragmentManager.popBackStackImmediate();
    }

    /**
     * @return The current size of the history (BackStack)
     */
    public int getSize() {
        return mFragmentManager.getBackStackEntryCount();
    }

    /**
     * @return True if no Fragment is in the History (BackStack)
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Goes the whole history back until to the first fragment in the history.
     * It would be the same if the user would click so many times the back button until
     * he reach the first fragment of the app.
     */
    public void gotToTheRootFragmentBack() {
        for (int i = 0; i <= mFragmentManager.getBackStackEntryCount(); ++i) {
            goOneBack();
        }
    }

    /**
     * Clears the whole history so it will no BackStack entry there any more.
     */
    public void clearHistory() {
        //noinspection StatementWithEmptyBody - it works as wanted
        while (mFragmentManager.popBackStackImmediate()) ;
    }
}
