package bonus.de.hska.klausurbonus.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bonus.de.hska.klausurbonus.domain.model.Offer;
import bonus.de.hska.klausurbonus.persistence.contract.OfferPlannerContract;

/**
 * Created by Walde on 27.12.16.
 */
public class SampleDataProvider {

    List<List<Offer>> data = new ArrayList<>();

    public void fillDatabaseWithSampleData(SQLiteDatabase db) {
        initData();

        for (List<Offer> offerList: data) {
            for (Offer offer: offerList) {
                ContentValues values = new ContentValues();
                values.put(OfferPlannerContract.OfferEntry.COLUMN_NAME_TITLE, offer.getTitle());
                values.put(OfferPlannerContract.OfferEntry.COLUMN_NAME_CATEGORY, offer.getCategory());
                values.put(OfferPlannerContract.OfferEntry.COLUMN_NAME_ROOM, offer.getRoom());
                values.put(OfferPlannerContract.OfferEntry.COLUMN_NAME_TEACHER, offer.getTeacher());
                values.put(OfferPlannerContract.OfferEntry.COLUMN_NAME_TIME, offer.getTime());

                db.insert(OfferPlannerContract.OfferEntry.TABLE_NAME, null, values);
            }
        }
    }

    private void initData() {
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

        ArrayList<Offer> offersEightDirty = new ArrayList<>();
        offer = new Offer("Morgenkreise", "Sonstiges", "08:30", "143", "Herr Mustermann");
        offersEightDirty.add(offer);

        ArrayList<Offer> offersTen = new ArrayList<>();
        offer = new Offer("Band", "Musik", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer("Schmuck", "Handwerk/Kunst", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);
        offer = new Offer("Schach", "Denken", "10:00", "143", "Herr Mustermann");
        offersTen.add(offer);

        ArrayList<Offer> offersEleven = new ArrayList<>();
        offer = new Offer("Gemeinschaftskunde", "Sozialwissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer("Bio II", "Wissenschaften", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);
        offer = new Offer("Tanzen", "Sport/Tanz", "11:00", "143", "Herr Mustermann");
        offersEleven.add(offer);

        ArrayList<Offer> offersThirteen = new ArrayList<>();
        offer = new Offer("Geographie", "Sozialwissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Mathe Werkstatt", "Denken", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Sport", "Sport/Tanz", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);
        offer = new Offer("Chemie I", "Wissenschaften", "13:00", "143", "Herr Mustermann");
        offersThirteen.add(offer);

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

        ArrayList<Offer> offersFifteen = new ArrayList<>();
        offer = new Offer("Chemie I", "Wissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Geographie", "Sozialwissenschaften", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Mathe Werkstatt", "Denken", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);
        offer = new Offer("Sport", "Sport/Tanz", "15:00", "143", "Herr Mustermann");
        offersFifteen.add(offer);

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

        data.add(offersEightDirty);
        data.add(offersNine);
        data.add(offersTen);
        data.add(offersEleven);
        data.add(offersThirteen);
        data.add(offersFourteen);
        data.add(offersFifteen);
        data.add(offersSixteen);
    }
}
