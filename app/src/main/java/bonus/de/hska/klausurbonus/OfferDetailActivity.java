package bonus.de.hska.klausurbonus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OfferDetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView category;
    private TextView time;
    private TextView room;
    private TextView teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        title = (TextView) findViewById(R.id.offer_detail_title);
        category = (TextView) findViewById(R.id.offer_detail_category);
        time = (TextView) findViewById(R.id.offer_detail_time);
        room = (TextView) findViewById(R.id.offer_detail_room);
        teacher = (TextView) findViewById(R.id.offer_detail_teacher);

        title.setText("Rechnen");
        category.setText("Mathematik");
        time.setText("8:30");
        room.setText("144");
        teacher.setText("Herr Mustermann");
    }
}
