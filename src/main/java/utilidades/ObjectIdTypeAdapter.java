package utilidades;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ObjectIdTypeAdapter extends TypeAdapter<ObjectId> {
    @Override
    public void write(final JsonWriter out, final ObjectId value) throws IOException {
        out.beginObject()
           .name("$oid")
           .value(value.toString())
           .endObject();
    }

    @Override
    public ObjectId read(final JsonReader in) throws IOException {
        in.beginObject();
        assert "$oid".equals(in.nextName());
        String objectId = in.nextString();
        in.endObject();
        return new ObjectId(objectId);
    }
}
