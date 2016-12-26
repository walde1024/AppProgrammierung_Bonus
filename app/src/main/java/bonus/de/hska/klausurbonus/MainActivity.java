package bonus.de.hska.klausurbonus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        RecyclerView offersAtEightThirty = (RecyclerView) findViewById(R.id.offers_at_eight_thirty_recycler);
        offersAtEightThirty.setHasFixedSize(true);
        offersAtEightThirty.setLayoutManager(this.createHorizontalLayoutManager());
        offersAtEightThirty.setAdapter(new OfferSummaryAdapter());
    }

    private RecyclerView.LayoutManager createHorizontalLayoutManager() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        return layoutManager;
    }
}
