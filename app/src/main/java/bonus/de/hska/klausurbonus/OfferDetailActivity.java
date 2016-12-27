package bonus.de.hska.klausurbonus;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;
import bonus.de.hska.klausurbonus.view.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.view.persistence.contentprovider.OfferPlannerContentProvider;

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

    private int offerId;

    private Menu menu;

    private ContentObserver favoriteContentObserver;

    private boolean isFavorite;

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
                ContentValues values = new ContentValues();
                values.put(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME, offer.getTime());
                values.put(OfferPlannerContract.FavoriteOffer.COLUMN_NAME_OFFER_ID, offerId);

                getContentResolver().insert(Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), values);
            }
            else {
                getContentResolver().delete(Uri.parse(OfferPlannerContentProvider.FAVORITE_OFFERS_URI), OfferPlannerContract.FavoriteOffer.COLUMN_NAME_TIME + " = ?", new String[] {offer.getTime()});
            }
        }

        return super.onOptionsItemSelected(item);
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
                        item.setIcon(getDrawable(R.drawable.ic_favorite_marked));
                    }
                    else {
                        isFavorite = false;
                        item.setIcon(getDrawable(R.drawable.ic_favorite));
                    }
                }
                else {
                    isFavorite = false;
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
