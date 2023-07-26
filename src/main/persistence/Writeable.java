// The following code is taken from the Writeable class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java

package persistence;

import org.json.JSONObject;

// stores object data in JSON format
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
