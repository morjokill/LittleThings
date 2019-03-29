package mqtt.example.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mqtt.example.container.Data;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@ToString
public class Pixels implements Data {
    private int[] pixels;
}
