package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.qedum.simplyposted.R;

public class MainActivity extends BaseActivity {

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    protected boolean isHomeAsUpEnabledShown(){
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_settings:
                startActivity(SettingsActivity.getLaunchIntent(this));
                return true;

            case R.id.action_scheduled_posts:
                startActivity(PostsActivity.getLaunchIntent(this));
                return true;

            case R.id.action_about_share:
                share();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void share() {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_text));
        shareIntent.setType("text/plain");
        String title = getString(R.string.menu_action_share);
        try {
            Intent chooser = Intent.createChooser(shareIntent, title);
            startActivity(chooser);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.no_share_app, Toast.LENGTH_SHORT).show();
        }
    }
}
