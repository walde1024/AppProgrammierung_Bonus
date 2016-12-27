package bonus.de.hska.klausurbonus;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bonus.de.hska.klausurbonus.model.domain.Offer;
import bonus.de.hska.klausurbonus.view.offer.OfferColors;

/**
 * Created by Walde on 26.11.16.
 */
public class OfferSummaryAdapter extends RecyclerView.Adapter {

    private ArrayList<Offer> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Offer offer = data.get(position);

        OfferViewHolder offerHolder = (OfferViewHolder) holder;
        offerHolder.icon.setImageDrawable(offer.getIcon());
        offerHolder.title.setText(offer.getTitle());
        offerHolder.card.setCardBackgroundColor(OfferColors.getColorForOffer(offer));
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else
            return 0;
    }

    public void setData(ArrayList<Offer> data) {
        this.data = data;
    }

    private static class OfferViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;

        public TextView title;

        public CardView card;

        public OfferViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.offer_card_icon);
            title = (TextView) itemView.findViewById(R.id.offer_card_title);
            card = (CardView) itemView.findViewById(R.id.offer_card_card);
        }

    }
}
