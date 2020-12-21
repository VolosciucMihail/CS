import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        MongoCredential credential= MongoCredential.createCredential("sampleUser", "Persons", "password".toCharArray());
        System.out.println("Connected to the database successfully");

        MongoDatabase database = mongo.getDatabase("Persons");
        
        MongoCollection<Document> collection = database.getCollection("Persons");
        System.out.println("Collection myCollection selected successfully");

        DocumentManipulation doc = new DocumentManipulation();
        doc.encryptFields(collection);
    }
}
