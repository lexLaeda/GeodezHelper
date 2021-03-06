package com.example.geodezhelper.interfaces.forActivity;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.res.ResourcesCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.geodezhelper.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class SingleFragmentItemActivity extends SingleFragmentActivity {
    @Override
    protected FloatingActionButton createFAB() {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_list);
        fab.hide();
        return fab;
    }

    @Override
    protected ActionBar createToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar !=null){
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_back_toolbar, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        return supportActionBar;
    }

}
