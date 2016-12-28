package bonus.de.hska.klausurbonus;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import bonus.de.hska.klausurbonus.domain.model.Offer;
import bonus.de.hska.klausurbonus.view.offer.OnOfferCardClickListener;
import bonus.de.hska.klausurbonus.persistence.DatabaseReader;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;
import bonus.de.hska.klausurbonus.persistence.contentprovider.OfferPlannerContentProvider;
import bonus.de.hska.klausurbonus.persistence.db.OfferPlannerDbHelper;

public class MainActivity extends AppCompatActivity implements OnOfferCardClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView offersAtEightThirty;
    private RecyclerView offersAtNine;
    private RecyclerView offersAtTen;
    private RecyclerView offersAtEleven;
    private RecyclerView offersAtThirteen;
    private RecyclerView offersAtFourteen;
    private RecyclerView offersAtFifteen;
    private RecyclerView offersAtSixteen;

    private OfferSummaryCursorAdapter offersAtEightThirtyAdapter;
    private OfferSummaryCursorAdapter offersAtNineAdapter;
    private OfferSummaryCursorAdapter offersAtTenAdapter;
    private OfferSummaryCursorAdapter offersAtElevenAdaper;
    private OfferSummaryCursorAdapter offersAtThirteenAdapter;
    private OfferSummaryCursorAdapter offersAtFourteenAdapter;
    private OfferSummaryCursorAdapter offersAtFifteenAdapter;
    private OfferSummaryCursorAdapter offersAtSixteenAdapter;

    private OfferPlannerDbHelper helper;
    private SQLiteDatabase db;

    private Cursor eightThirtyCursor;
    private Cursor nineCursor;
    private Cursor tenCursor;
    private Cursor elevenCursor;
    private Cursor thirteenCursor;
    private Cursor fourteenCursor;
    private Cursor fifteenCursor;
    private Cursor sixteenCursor;

    private final int EIGHT_THIRTY_LOADER = 0;
    private final int NINE_LOADER = 1;
    private final int TEN_LOADER = 2;
    private final int ELEVEN_LOADER = 3;
    private final int THIRTEEN_LOADER = 4;
    private final int FOURTEEN_LOADER = 5;
    private final int FIFTEEN_LOADER = 6;
    private final int SIXTEEN_LOADER = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        helper = new OfferPlannerDbHelper(this);
        db = helper.getReadableDatabase();

        offersAtEightThirty = (RecyclerView) findViewById(R.id.offers_at_eight_thirty_recycler);
        offersAtEightThirty.setHasFixedSize(true);
        offersAtEightThirty.setLayoutManager(this.createHorizontalLayoutManager());
        eightThirtyCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_EIGHT_THIRTY);
        offersAtEightThirty.setAdapter(offersAtEightThirtyAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtNine = (RecyclerView) findViewById(R.id.offers_at_nine_recycler);
        offersAtNine.setHasFixedSize(true);
        offersAtNine.setLayoutManager(this.createHorizontalLayoutManager());
        nineCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_NINE);
        offersAtNine.setAdapter(offersAtNineAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtTen = (RecyclerView) findViewById(R.id.offers_at_ten_recycler);
        offersAtTen.setHasFixedSize(true);
        offersAtTen.setLayoutManager(this.createHorizontalLayoutManager());
        tenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_TEN);
        offersAtTen.setAdapter(offersAtTenAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtEleven = (RecyclerView) findViewById(R.id.offers_at_eleven_recycler);
        offersAtEleven.setHasFixedSize(true);
        offersAtEleven.setLayoutManager(this.createHorizontalLayoutManager());
        elevenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_ELEVEN);
        offersAtEleven.setAdapter(offersAtElevenAdaper = new OfferSummaryCursorAdapter(this, this));

        offersAtThirteen = (RecyclerView) findViewById(R.id.offers_at_thirteen_recycler);
        offersAtThirteen.setHasFixedSize(true);
        offersAtThirteen.setLayoutManager(this.createHorizontalLayoutManager());
        thirteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_THIRTEEN);
        offersAtThirteen.setAdapter(offersAtThirteenAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtFourteen = (RecyclerView) findViewById(R.id.offers_at_fourteen_recycler);
        offersAtFourteen.setHasFixedSize(true);
        offersAtFourteen.setLayoutManager(this.createHorizontalLayoutManager());
        fourteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_FOURTEEN);
        offersAtFourteen.setAdapter(offersAtFourteenAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtFifteen = (RecyclerView) findViewById(R.id.offers_at_fifteen_recycler);
        offersAtFifteen.setHasFixedSize(true);
        offersAtFifteen.setLayoutManager(this.createHorizontalLayoutManager());
        fifteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_FIFTEEN);
        offersAtFifteen.setAdapter(offersAtFifteenAdapter = new OfferSummaryCursorAdapter(this, this));

        offersAtSixteen = (RecyclerView) findViewById(R.id.offers_at_sixteen_recycler);
        offersAtSixteen.setHasFixedSize(true);
        sixteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_SIXTEEN);
        offersAtSixteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtSixteen.setAdapter(offersAtSixteenAdapter = new OfferSummaryCursorAdapter(this, this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLoaderManager().restartLoader(EIGHT_THIRTY_LOADER, null, this);
        getLoaderManager().restartLoader(NINE_LOADER, null, this);
        getLoaderManager().restartLoader(TEN_LOADER, null, this);
        getLoaderManager().restartLoader(ELEVEN_LOADER, null, this);
        getLoaderManager().restartLoader(THIRTEEN_LOADER, null, this);
        getLoaderManager().restartLoader(FOURTEEN_LOADER, null, this);
        getLoaderManager().restartLoader(FIFTEEN_LOADER, null, this);
        getLoaderManager().restartLoader(SIXTEEN_LOADER, null, this);
    }

    private RecyclerView.LayoutManager createHorizontalLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public void onClickOfferCard(int offerId) {
        Intent intent = new Intent(this, OfferDetailActivity.class);
        intent.putExtra(Offer.ID_KEY, offerId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        eightThirtyCursor.close();
        nineCursor.close();
        tenCursor.close();
        elevenCursor.close();
        thirteenCursor.close();
        fourteenCursor.close();
        fifteenCursor.close();
        sixteenCursor.close();

        helper.close();
        db.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderIndex, Bundle bundle) {
        String selection = OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME + " = ?";

        switch (loaderIndex) {
            case EIGHT_THIRTY_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_EIGHT_THIRTY}, null);
            case NINE_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_NINE}, null);
            case TEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_TEN}, null);
            case ELEVEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_ELEVEN}, null);
            case THIRTEEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_THIRTEEN}, null);
            case FOURTEEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_FOURTEEN}, null);
            case FIFTEEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_FIFTEEN}, null);
            case SIXTEEN_LOADER:
                return new CursorLoader(this, Uri.parse(OfferPlannerContentProvider.OFFERS_URI_FAVORITE_FIRST), null, selection, new String[]{DatabaseReader.TIME_SIXTEEN}, null);
        }

        throw new IllegalArgumentException("Unsupported index");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case EIGHT_THIRTY_LOADER:
                offersAtEightThirtyAdapter.setCursor(cursor);
                offersAtEightThirtyAdapter.notifyDataSetChanged();
                break;
            case NINE_LOADER:
                offersAtNineAdapter.setCursor(cursor);
                offersAtNineAdapter.notifyDataSetChanged();
                break;
            case TEN_LOADER:
                offersAtTenAdapter.setCursor(cursor);
                offersAtTenAdapter.notifyDataSetChanged();
                break;
            case ELEVEN_LOADER:
                offersAtElevenAdaper.setCursor(cursor);
                offersAtElevenAdaper.notifyDataSetChanged();
                break;
            case THIRTEEN_LOADER:
                offersAtThirteenAdapter.setCursor(cursor);
                offersAtThirteenAdapter.notifyDataSetChanged();
                break;
            case FOURTEEN_LOADER:
                offersAtFourteenAdapter.setCursor(cursor);
                offersAtFourteenAdapter.notifyDataSetChanged();
                break;
            case FIFTEEN_LOADER:
                offersAtFifteenAdapter.setCursor(cursor);
                offersAtFifteenAdapter.notifyDataSetChanged();
                break;
            case SIXTEEN_LOADER:
                offersAtSixteenAdapter.setCursor(cursor);
                offersAtSixteenAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
