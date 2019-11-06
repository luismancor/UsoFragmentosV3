package com.example.usofragmentosv3.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.usofragmentosv3.AdicionalFragmento;
import com.example.usofragmentosv3.ContactosFragmento;
import com.example.usofragmentosv3.ContenedorMusicas;
import com.example.usofragmentosv3.MusicaFragmento;
import com.example.usofragmentosv3.OtroFragmento;
import com.example.usofragmentosv3.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //Se agrega una accion a cada fragmento segun su posicion
        switch (position){
            case 0:
                ContactosFragmento frmContactos = new ContactosFragmento();
                return frmContactos;
            case 1:
                ContenedorMusicas frmMusicas = new ContenedorMusicas();
                return frmMusicas;
            case 2:
                OtroFragmento frmOtro = new OtroFragmento();
                return  frmOtro;
            case 3:
                AdicionalFragmento frmAdicional = new AdicionalFragmento();
                return  frmAdicional;

        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}