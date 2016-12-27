package bonus.de.hska.klausurbonus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import bonus.de.hska.klausurbonus.model.domain.Offer;

public class MainActivity extends AppCompatActivity {

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
        offersAtEightThirty.setAdapter(offersAtEightThirtyAdapter = new OfferSummaryAdapter());

        offersAtNine = (RecyclerView) findViewById(R.id.offers_at_nine_recycler);
        offersAtNine.setHasFixedSize(true);
        offersAtNine.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtNine.setAdapter(offersAtNineAdapter = new OfferSummaryAdapter());

        offersAtTen = (RecyclerView) findViewById(R.id.offers_at_ten_recycler);
        offersAtTen.setHasFixedSize(true);
        offersAtTen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtTen.setAdapter(offersAtTenAdapter = new OfferSummaryAdapter());

        offersAtEleven = (RecyclerView) findViewById(R.id.offers_at_eleven_recycler);
        offersAtEleven.setHasFixedSize(true);
        offersAtEleven.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtEleven.setAdapter(offersAtElevenAdaper = new OfferSummaryAdapter());

        offersAtThirteen = (RecyclerView) findViewById(R.id.offers_at_thirteen_recycler);
        offersAtThirteen.setHasFixedSize(true);
        offersAtThirteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtThirteen.setAdapter(offersAtThirteenAdapter = new OfferSummaryAdapter());

        offersAtFourteen = (RecyclerView) findViewById(R.id.offers_at_fourteen_recycler);
        offersAtFourteen.setHasFixedSize(true);
        offersAtFourteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtFourteen.setAdapter(offersAtFourteenAdapter = new OfferSummaryAdapter());

        offersAtFifteen = (RecyclerView) findViewById(R.id.offers_at_fifteen_recycler);
        offersAtFifteen.setHasFixedSize(true);
        offersAtFifteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtFifteen.setAdapter(offersAtFifteenAdapter = new OfferSummaryAdapter());

        offersAtSixteen = (RecyclerView) findViewById(R.id.offers_at_sixteen_recycler);
        offersAtSixteen.setHasFixedSize(true);
        offersAtSixteen.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtSixteen.setAdapter(offersAtSixteenAdapter = new OfferSummaryAdapter());

        setData();
    }

    private void setData() {
        ArrayList<Offer> offersNine = new ArrayList<>();

        Offer offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Mathematik 3", "Denken", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Garten", "Handwerk/Kunst", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Klavier", "Musik", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Geschichte", "Sozialwissenschaften", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Basketball", "Sport/Tanz", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Physik", "Wissenschaften", "09:00", "143", "Herr Mustermann");
        offersNine.add(offer);

        offersAtNineAdapter.setData(offersNine);

        ArrayList<Offer> offersEightDirty = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Morgenkreise", "Sonstiges", "08:30", "143", "Herr Mustermann");
        offersEightDirty.add(offer);

        offersAtEightThirtyAdapter.setData(offersEightDirty);

        ArrayList<Offer> offersTen = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Band", "Musik", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Schmuck", "Handwerk/Kunst", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Schach", "Denken", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);

        offersAtTenAdapter.setData(offersTen);

        ArrayList<Offer> offersEleven = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Gemeinschaftskunde", "Sozialwissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Bio II", "Wissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Tanzen", "Sport/Tanz", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);

        offersAtElevenAdaper.setData(offersEleven);

        ArrayList<Offer> offersThirteen = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Geographie", "Sozialwissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Mathe Werkstatt", "Denken", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Sport", "Sport/Tanz", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Chemie I", "Wissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);

        offersAtThirteenAdapter.setData(offersThirteen);

        ArrayList<Offer> offersFourteen = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Schach", "Denken", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Band", "Musik", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Bio II", "Wissenschaften", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Tanzen", "Sport/Tanz", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Schmuck", "Handwerk/Kunst", "14:00", "143", "Herr Mustermann");
        offersFourteen.add(offer);

        offersAtFourteenAdapter.setData(offersFourteen);

        ArrayList<Offer> offersFifteen = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Chemie I", "Wissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Geographie", "Sozialwissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Mathe Werkstatt", "Denken", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Sport", "Sport/Tanz", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);

        offersAtFifteenAdapter.setData(offersFifteen);

        ArrayList<Offer> offersSixteen = new ArrayList<>();
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Geschichte", "Sozialwissenschaften", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Basketball", "Sport/Tanz", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.read_write_calc, null), "Physik", "Wissenschaften", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.math, null), "Mathematik 3", "Denken", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_elementary, null), "Garten", "Handwerk/Kunst", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);
        offer = new Offer(this.getResources().getDrawable(R.drawable.german_workshop, null), "Klavier", "Musik", "16:00", "143", "Herr Mustermann");
        offersSixteen.add(offer);

        offersAtSixteenAdapter.setData(offersSixteen);
    }

    private RecyclerView.LayoutManager createHorizontalLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }
}
