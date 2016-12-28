package bonus.de.hska.klausurbonus.view.offer;

import android.content.Context;
import android.graphics.drawable.Drawable;

import bonus.de.hska.klausurbonus.R;
import bonus.de.hska.klausurbonus.domain.model.Offer;

/**
 * Created by Walde on 26.12.16.
 */
public class OfferResources {

    public static int getColorForOffer(Offer offer) {
        if (offer.getCategory().equals("Denken"))
            return 0xFF2196F3;

        if (offer.getCategory().equals("Handwerk/Kunst"))
            return 0xFFB71C1C;

        if (offer.getCategory().equals("Musik"))
            return 0xFFFFC107;

        if (offer.getCategory().equals("Sozialwissenschaften"))
            return 0xFF673AB7;

        if (offer.getCategory().equals("Sport/Tanz"))
            return 0xFF795548;

        if (offer.getCategory().equals("Deutsch und Fremdsprachen"))
            return 0xFF4CAF50;

        if (offer.getCategory().equals("Wissenschaften"))
            return 0xFFFFEB3B;

        return 0xFFFFFFFF;
    }

    public static Drawable getIconForOffer(Context context, Offer offer) {
        if (offer.getCategory().equals("Denken"))
            return context.getResources().getDrawable(R.drawable.math, null);

        if (offer.getCategory().equals("Handwerk/Kunst"))
            return context.getResources().getDrawable(R.drawable.german_elementary, null);

        if (offer.getCategory().equals("Musik"))
            return context.getResources().getDrawable(R.drawable.german_workshop, null);

        if (offer.getCategory().equals("Sozialwissenschaften"))
            return context.getResources().getDrawable(R.drawable.german_workshop, null);

        if (offer.getCategory().equals("Sport/Tanz"))
            return context.getResources().getDrawable(R.drawable.german_elementary, null);

        if (offer.getCategory().equals("Deutsch und Fremdsprachen"))
            return context.getResources().getDrawable(R.drawable.read_write_calc, null);

        if (offer.getCategory().equals("Wissenschaften"))
            return context.getResources().getDrawable(R.drawable.read_write_calc, null);

        return context.getResources().getDrawable(R.drawable.german_workshop, null);
    }
}
