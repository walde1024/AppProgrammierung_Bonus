package bonus.de.hska.klausurbonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OnOfferCardClickListener;

public class MainActivity extends AppCompatActivity implements OnOfferCardClickListener {

    private RecyclerView offersAtEightThirty;
    private RecyclerView offersAtNine;
    private RecyclerView offersAtTen;
    private RecyclerView offersAtEleven;
    private RecyclerView offersAtThirteen;
    private RecyclerView offersAtFourteen;
    private RecyclerView offersAtFifteen;
    private RecyclerView offersAtSixteen;

    private OfferSummaryAdapter offersAtEightThirtyAdapter;
    private OfferSummaryAdapter offersAtNineAdapter;
    private OfferSummaryAdapter offersAtTenAdapter;
    private OfferSummaryAdapter offersAtElevenAdaper;
    private OfferSummaryAdapter offersAtThirteenAdapter;
    private OfferSummaryAdapter offersAtFourteenAdapter;
    private OfferSummaryAdapter offersAtFifteenAdapter;
    private OfferSummaryAdapter offersAtSixteenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        offersAtEightThirty = (RecyclerView) findViewById(R.id.offers_at_eight_thirty_recycler);
        offersAtEightThirty.setHasFixedSize(true);
        offersAtEightThirty.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtEightThirty.setAdapter(offersAtEightThirtyAdapter = new OfferSummaryAdapter(this, this));

        offersAtNine = (RecyclerView) findViewById(R.id.offers_at_nine_recycler);
        offersAtNine.setHasFixedSize(true);
        offersAtNine.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtNine.setAdapter(offersAtNineAdapter = new OfferSummaryAdapter(this, this));

        offersAtTen = (RecyclerView) findViewById(R.id.offers_at_ten_recycler);
        offersAtTen.setHasFixedSize(true);
        offersAtTen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtTen.setAdapter(offersAtTenAdapter = new OfferSummaryAdapter(this, this));

        offersAtEleven = (RecyclerView) findViewById(R.id.offers_at_eleven_recycler);
        offersAtEleven.setHasFixedSize(true);
        offersAtEleven.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtEleven.setAdapter(offersAtElevenAdaper = new OfferSummaryAdapter(this, this));

        offersAtThirteen = (RecyclerView) findViewById(R.id.offers_at_thirteen_recycler);
        offersAtThirteen.setHasFixedSize(true);
        offersAtThirteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtThirteen.setAdapter(offersAtThirteenAdapter = new OfferSummaryAdapter(this, this));

        offersAtFourteen = (RecyclerView) findViewById(R.id.offers_at_fourteen_recycler);
        offersAtFourteen.setHasFixedSize(true);
        offersAtFourteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtFourteen.setAdapter(offersAtFourteenAdapter = new OfferSummaryAdapter(this, this));

        offersAtFifteen = (RecyclerView) findViewById(R.id.offers_at_fifteen_recycler);
        offersAtFifteen.setHasFixedSize(true);
        offersAtFifteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtFifteen.setAdapter(offersAtFifteenAdapter = new OfferSummaryAdapter(this, this));

        offersAtSixteen = (RecyclerView) findViewById(R.id.offers_at_sixteen_recycler);
        offersAtSixteen.setHasFixedSize(true);
        offersAtSixteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtSixteen.setAdapter(offersAtSixteenAdapter = new OfferSummaryAdapter(this, this));

        setData();
    }

    private void setData() {
        ArrayList<Offer> offersNine = new ArrayList<>();

        Offer offer = new Offer("Mathematik 3", "Denken", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer("Garten", "Handwerk/Kunst", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer("Klavier", "Musik", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer("Geschichte", "Sozialwissenschaften", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer("Basketball", "Sport/Tanz", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer("Physik", "Wissenschaften", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);

        offersAtNineAdapter.setData(offersNine);

        ArrayList<Offer> offersEightDirty = new ArrayList<>();
        offer = new Offer("Morgenkreise", "Sonstiges", "08:30", "143", "Herr Mustermann");
        offersEightDirty.add(offer);

        offersAtEightThirtyAdapter.setData(offersEightDirty);

        ArrayList<Offer> offersTen = new ArrayList<>();
        offer = new Offer("Band", "Musik", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer("Schmuck", "Handwerk/Kunst", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer("Schach", "Denken", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);

        offersAtTenAdapter.setData(offersTen);

        ArrayList<Offer> offersEleven = new ArrayList<>();
        offer = new Offer("Gemeinschaftskunde", "Sozialwissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer("Bio II", "Wissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer("Tanzen", "Sport/Tanz", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);

        offersAtElevenAdaper.setData(offersEleven);

        ArrayList<Offer> offersThirteen = new ArrayList<>();
        offer = new Offer("Geographie", "Sozialwissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Mathe Werkstatt", "Denken", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Sport", "Sport/Tanz", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Chemie I", "Wissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);

        offersAtThirteenAdapter.setData(offersThirteen);

        ArrayList<Offer> offersFourteen = new ArrayList<>();
        offer = new Offer("Schach", "Denken", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer("Band", "Musik", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer("Bio II", "Wissenschaften", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer("Tanzen", "Sport/Tanz", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer("Schmuck", "Handwerk/Kunst", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);

        offersAtFourteenAdapter.setData(offersFourteen);

        ArrayList<Offer> offersFifteen = new ArrayList<>();
        offer = new Offer("Chemie I", "Wissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Geographie", "Sozialwissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Mathe Werkstatt", "Denken", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Sport", "Sport/Tanz", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);

        offersAtFifteenAdapter.setData(offersFifteen);

        ArrayList<Offer> offersSixteen = new ArrayList<>();
        offer = new Offer("Geschichte", "Sozialwissenschaften", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer("Basketball", "Sport/Tanz", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer("Physik", "Wissenschaften", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer("Mathematik 3", "Denken", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer("Garten", "Handwerk/Kunst", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer("Klavier", "Musik", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);

        offersAtSixteenAdapter.setData(offersSixteen);
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
}
