/**
 * @author Beatrice V.
 * @created 28.10.2020 - 13:39
 * @project Lab7
 */
import br.ufrgs.rmpestano.rsa.Encryption;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;

import java.util.Iterator;
import org.bson.Document;
public class DocumentManipulation {

    public void encryptFields(MongoCollection<Document> collection) {
        for (int i = 1; i <= collection.countDocuments(); i++) {
            String ipValue = collection.find(Filters.eq("id", i)).projection(Projections.include("ip_address")).first().getString("ip_address");
            collection.updateOne(Filters.eq("id", i), Updates.set("ip_address", Encryption.encrypt(ipValue)));

            String ibanValue = collection.find(Filters.eq("id", i)).projection(Projections.include("IBAN")).first().getString("IBAN");
            collection.updateOne(Filters.eq("id", i), Updates.set("IBAN", Encryption.encrypt(ibanValue)));

            System.out.println("Document updated successfully...");
        }
        readDB(collection);
    }

    public void decryptFields(MongoCollection<Document> collection) {
        for (int j = 1; j <= collection.countDocuments(); j++) {
            String ipValue = collection.find(Filters.eq("id", j)).projection(Projections.include("ip_address")).first().getString("ip_address");
            collection.updateOne(Filters.eq("id", j), Updates.set("ip_address", Encryption.decrypt(ipValue)));

            String ibanValue = collection.find(Filters.eq("id", j)).projection(Projections.include("IBAN")).first().getString("IBAN");
            collection.updateOne(Filters.eq("id", j), Updates.set("IBAN", Encryption.decrypt(ibanValue)));

            System.out.println("Document updated successfully...");
        }
        readDB(collection);
    }

    public void readDB(MongoCollection<Document> collection) {
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }
}