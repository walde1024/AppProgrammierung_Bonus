package bonus.de.hska.klausurbonus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;

public class OfferDetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView category;
    private TextView time;
    private TextView room;
    private TextView teacher;
    private ImageView icon;
    private FrameLayout iconFrame;

    private Offer offer;

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

        offer = (Offer) getIntent().getExtras().get(Offer.KEY);

        title.setText(offer.getTitle());
        category.setText(offer.getCategory());
        time.setText(offer.getTime());
        room.setText(offer.getRoom());
        teacher.setText(offer.getTeacher());
        icon.setImageDrawable(OfferResources.getIconForOffer(this, offer));
        iconFrame.setBackgroundColor(OfferResources.getColorForOffer(offer));
    }
}
