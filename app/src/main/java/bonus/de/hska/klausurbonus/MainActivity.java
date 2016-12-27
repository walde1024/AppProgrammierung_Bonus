package bonus.de.hska.klausurbonus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OnOfferCardClickListener;
import bonus.de.hska.klausurbonus.view.persistence.DatabaseReader;
import bonus.de.hska.klausurbonus.view.persistence.db.OfferPlannerDbHelper;

public class MainActivity extends AppCompatActivity implements OnOfferCardClickListener {

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
        offersAtEightThirty.setAdapter(offersAtEightThirtyAdapter = new OfferSummaryCursorAdapter(this, this, eightThirtyCursor));

        offersAtNine = (RecyclerView) findViewById(R.id.offers_at_nine_recycler);
        offersAtNine.setHasFixedSize(true);
        offersAtNine.setLayoutManager(this.createHorizontalLayoutManager());
        nineCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_NINE);
        offersAtNine.setAdapter(offersAtNineAdapter = new OfferSummaryCursorAdapter(this, this, nineCursor));

        offersAtTen = (RecyclerView) findViewById(R.id.offers_at_ten_recycler);
        offersAtTen.setHasFixedSize(true);
        offersAtTen.setLayoutManager(this.createHorizontalLayoutManager());
        tenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_TEN);
        offersAtTen.setAdapter(offersAtTenAdapter = new OfferSummaryCursorAdapter(this, this, tenCursor));

        offersAtEleven = (RecyclerView) findViewById(R.id.offers_at_eleven_recycler);
        offersAtEleven.setHasFixedSize(true);
        offersAtEleven.setLayoutManager(this.createHorizontalLayoutManager());
        elevenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_ELEVEN);
        offersAtEleven.setAdapter(offersAtElevenAdaper = new OfferSummaryCursorAdapter(this, this, elevenCursor));

        offersAtThirteen = (RecyclerView) findViewById(R.id.offers_at_thirteen_recycler);
        offersAtThirteen.setHasFixedSize(true);
        offersAtThirteen.setLayoutManager(this.createHorizontalLayoutManager());
        thirteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_THIRTEEN);
        offersAtThirteen.setAdapter(offersAtThirteenAdapter = new OfferSummaryCursorAdapter(this, this, thirteenCursor));

        offersAtFourteen = (RecyclerView) findViewById(R.id.offers_at_fourteen_recycler);
        offersAtFourteen.setHasFixedSize(true);
        offersAtFourteen.setLayoutManager(this.createHorizontalLayoutManager());
        fourteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_FOURTEEN);
        offersAtFourteen.setAdapter(offersAtFourteenAdapter = new OfferSummaryCursorAdapter(this, this, fourteenCursor));

        offersAtFifteen = (RecyclerView) findViewById(R.id.offers_at_fifteen_recycler);
        offersAtFifteen.setHasFixedSize(true);
        offersAtFifteen.setLayoutManager(this.createHorizontalLayoutManager());
        fifteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_FIFTEEN);
        offersAtFifteen.setAdapter(offersAtFifteenAdapter = new OfferSummaryCursorAdapter(this, this, fifteenCursor));

        offersAtSixteen = (RecyclerView) findViewById(R.id.offers_at_sixteen_recycler);
        offersAtSixteen.setHasFixedSize(true);
        sixteenCursor = DatabaseReader.getOffers(db, DatabaseReader.TIME_SIXTEEN);
        offersAtSixteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtSixteen.setAdapter(offersAtSixteenAdapter = new OfferSummaryCursorAdapter(this, this, sixteenCursor));
    }

    private RecyclerView.LayoutManager createHorizontalLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public void onClickOfferCard(Offer offer) {
        Intent intent = new Intent(this, OfferDetailActivity.class);
        intent.putExtra(Offer.KEY, offer);
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
}
