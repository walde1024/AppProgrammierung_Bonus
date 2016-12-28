package bonus.de.hska.klausurbonus;

import android.app.AlarmManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import bonus.de.hska.klausurbonus.domain.model.Offer;
import bonus.de.hska.klausurbonus.service.OfferReminderService;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.persistence.contentprovider.OfferPlannerContentProvider;

public class OfferDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int OFFER_LOADER = 0;
    private static final int FAVORITE_OFFER_LOADER = 1;

    private TextView title;
    private TextView category;
    private TextView time;
    private TextView room;
    private TextView teacher;
    private ImageView icon;
    private FrameLayout iconFrame;

    private Offer offer;

    private Menu menu;

    private int offerId;

    private ContentObserver favoriteContentObserver;

    private boolean isFavorite;
    private boolean thereIsOtherFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        title = (TextView) findViewById(R.id.offer_detail_title);
        category = (TextView) findViewById(R.id.offer_detail_category);
        time = (TextView) findViewById(R.id.offer_detail_time);
        room = (TextView) findViewById(R.id.offer_detail_room);
        teacher = (TextView) findViewById(R.id.offer_detail_teacher);
        icon = (ImageView) findViewById(R.id.offer_detail_icon);
        iconFrame = (FrameLayout) findViewById(R.id.offer_detail_icon_frame);

        offerId = getIntent().getExtras().getInt(Offer.ID_KEY);

        Handler handler = new Handler(Looper.myLooper());

        getContentResolver().registerContentObserver(Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), true, favoriteContentObserver = new ContentObserver(handler) {
            @Override
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                getLoaderManager().restartLoader(FAVORITE_OFFER_LOADER, null, OfferDetailActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.offer_detail, menu);
        this.menu = menu;

        getLoaderManager().initLoader(OFFER_LOADER, null, this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            if (!isFavorite) {

                if (!thereIsOtherFavorite) {
                    setCurrentOfferAsFavorite();
                    setReminder();
                }
                else {
                    showReplaceFavoriteConfirmDialog();
                }
            }
            else {
                getContentResolver().delete(Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME + " = ?", new String[] {offer.getTime()});
                removeReminder();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void showReplaceFavoriteConfirmDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Favorit setzen")
            .setMessage("Es existiert bereits ein Favorit für diese Uhrzeit.")
            .setPositiveButton("Ersetzen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setCurrentOfferAsFavorite();
                }
            })
            .setNegativeButton("Abbrechen", null)
            .show();
    }

    private void setCurrentOfferAsFavorite() {
        ContentValues values = new ContentValues();
        values.put(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME, offer.getTime());
        values.put(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_OFFER_ID, offerId);

        getContentResolver().insert(Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), values);

        setReminder();
    }

    private void setReminder() {
        String time = offer.getTime();
        String[] timeSplitted = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplitted[0])); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplitted[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, -5);

        //calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, 10);

        Intent reminderServiceIntent = new Intent(this, OfferReminderService.class);
        reminderServiceIntent.putExtra(Offer.ID_KEY, offerId);

        reminderServiceIntent.setData(Uri.parse(offer.getTime()));
        PendingIntent pi = PendingIntent.getService(this, 0, reminderServiceIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    private void removeReminder() {
        Intent reminderServiceIntent = new Intent(this, OfferReminderService.class);
        reminderServiceIntent.setData(Uri.parse(offer.getTime()));
        PendingIntent pi = PendingIntent.getService(this, 0, reminderServiceIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        am.cancel(pi);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case OFFER_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI + "/" + offerId), null, null, null, null);

            case FAVORITE_OFFER_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), null, OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME + " = ?", new String[]{offer.getTime()}, null);
        }

        throw new IllegalArgumentException("Wrong loader id");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case OFFER_LOADER:
                cursor.moveToFirst();
                String title = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TITLE));
                String category = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_CATEGORY));
                String room = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_ROOM));
                String time = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME));
                String teacher = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TEACHER));

                offer = new Offer(title, category, time, room, teacher);

                getLoaderManager().initLoader(FAVORITE_OFFER_LOADER, null, this);
                setDetailData();
                break;

            case FAVORITE_OFFER_LOADER:
                MenuItem item = menu.findItem(R.id.action_favorite);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int id = cursor.getInt(cursor.getColumnIndex(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_OFFER_ID));
                    if (id == offerId) {
                        isFavorite = true;
                        thereIsOtherFavorite = false;
                        item.setIcon(getDrawable(R.drawable.ic_favorite_marked));
                    }
                    else {
                        thereIsOtherFavorite = true;
                        isFavorite = false;
                        item.setIcon(getDrawable(R.drawable.ic_favorite));
                    }
                }
                else {
                    isFavorite = false;
                    thereIsOtherFavorite = false;
                    item.setIcon(getDrawable(R.drawable.ic_favorite));
                }
                break;
        }

    }

    private void setDetailData() {
        title.setText(offer.getTitle());
        category.setText(offer.getCategory());
        time.setText(offer.getTime());
        room.setText(offer.getRoom());
        teacher.setText(offer.getTeacher());
        icon.setImageDrawable(OfferResources.getIconForOffer(this, offer));
        iconFrame.setBackgroundColor(OfferResources.getColorForOffer(offer));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(favoriteContentObserver);
    }
}
