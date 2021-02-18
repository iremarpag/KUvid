package Domain.Storage;

import Domain.Game;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBManager {

    public void InsertingDocument(Game game) {


            // Creating a Mongo client
            MongoClient mongo = new MongoClient( "localhost" , 27017 );

            // Accessing the database
            MongoDatabase database = mongo.getDatabase("myDb");

            // Creating a collection
            database.createCollection("sampleCollection");
            System.out.println("Collection created successfully");

            // Retrieving a collection
            MongoCollection<Document> collection = database.getCollection("sampleCollection");
            System.out.println("Collection sampleCollection selected successfully");
            Document document = new Document("title", "MongoDB")
                    .append("shootedObjects", game.getShootedObjects())
                    .append("score", game.getScore())
                    .append("healthPoint", game.getHealth());


            //Inserting document into the collection
            collection.insertOne(document);

}
}