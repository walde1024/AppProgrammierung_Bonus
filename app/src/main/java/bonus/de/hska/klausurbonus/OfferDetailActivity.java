package bonus.de.hska.klausurbonus;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;
import bonus.de.hska.klausurbonus.view.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.view.persistence.db.OfferPlannerContentProvider;

public class OfferDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView title;
    private TextView category;
    private TextView time;
    private TextView room;
    private TextView teacher;
    private ImageView icon;
    private FrameLayout iconFrame;

    private Offer offer;

    private int offerId;

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

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI + "/" + offerId), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TITLE));
        String category = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_CATEGORY));
        String room = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_ROOM));
        String time = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME));
        String teacher = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TEACHER));

        offer = new Offer(title, category, time, room, teacher);

        setDetailData();
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
}
