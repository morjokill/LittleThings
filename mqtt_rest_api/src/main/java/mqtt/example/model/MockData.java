package mqtt.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mqtt.example.container.Data;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@ToString
public class MockData implements Data {
    @JsonProperty("line")
    private String line;

    @Override
    public int[] getPixels() {
        return new int[0];
    }
}
