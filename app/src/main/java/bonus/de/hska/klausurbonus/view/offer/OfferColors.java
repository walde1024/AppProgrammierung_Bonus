package bonus.de.hska.klausurbonus.view.offer;

import bonus.de.hska.klausurbonus.model.domain.Offer;

/**
 * Created by Walde on 26.12.16.
 */
public class OfferColors {

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
}
