package bonus.de.hska.klausurbonus;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bonus.de.hska.klausurbonus.domain.model.Offer;
import bonus.de.hska.klausurbonus.view.offer.OfferResources;
import bonus.de.hska.klausurbonus.view.offer.OnOfferCardClickListener;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;

/**
 * Created by Walde on 26.11.16.
 */
public class OfferSummaryCursorAdapter extends RecyclerView.Adapter {

    private OnOfferCardClickListener onOfferCardClickListener;

    private Context context;

    private Cursor cursor;

    public OfferSummaryCursorAdapter(Context context, OnOfferCardClickListener onOfferCardClickListener) {
        this.onOfferCardClickListener = onOfferCardClickListener;
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        cursor.moveToPosition(position);
        final int id = cursor.getInt(cursor.getColumnIndex(OfferPlannerContract.OfferEntry._ID));
        String title = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TITLE));
        String category = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_CATEGORY));
        String room = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_ROOM));
        String time = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME));
        String teacher = cursor.getString(cursor.getColumnIndex(OfferPlannerContract.OfferEntry.COLUMN_NAME_TEACHER));

        final Offer offer = new Offer(title, category, time, room, teacher);

        OfferViewHolder offerHolder = (OfferViewHolder) holder;
        offerHolder.icon.setImageDrawable(OfferResources.getIconForOffer(context, offer));
        offerHolder.title.setText(offer.getTitle());
        offerHolder.card.setCardBackgroundColor(OfferResources.getColorForOffer(offer));

        offerHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOfferCardClickListener.onClickOfferCard(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cursor == null)
            return 0;

        return cursor.getCount();
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
