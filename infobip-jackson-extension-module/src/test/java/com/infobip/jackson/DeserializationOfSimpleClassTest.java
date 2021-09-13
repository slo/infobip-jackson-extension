package com.infobip.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class DeserializationOfSimpleClassTest extends TestBase {

    @Test
    public void  should_deserialize() throws JsonProcessingException {
        // given
        String json = "{\"name\": \"Honda\", \"color\": \"blueish\"}";

        // when
        Car output = objectMapper.readValue(json, Car.class);

        // then
        then(output.getColor().getColor()).isEqualTo("blueish");
    }

    @Test
    public void  should_deserialize_using_original_ObjectMapper() throws JsonProcessingException {
        // given
        String json = "{\"name\": \"Honda\", \"color\": \"blueish\"}";

        // when
        Car output = new ObjectMapper().readValue(json, Car.class);

        // then
        then(output.getColor().getColor()).isEqualTo("blueish");
    }

    @Test
    public void  should_serialize() throws JsonProcessingException {
        // given
        Car c = new Car("Honda", new CustomColor("greenish"));

        // when
        String output = objectMapper.writeValueAsString(c);

        // then
        then(output).contains("greenish");
    }

    @Test
    public void  should_serialize_using_original_ObjectMapper() throws JsonProcessingException {
        // given
        Car c = new Car("Honda", new CustomColor("greenish"));

        // when
        String output = new ObjectMapper().writeValueAsString(c);

        // then
        then(output).contains("greenish");
    }

}

class Car {

    String name;
    CustomColor color;

    public Car(String name, CustomColor color) {
        this.name = name;
        this.color = color;
    }

    public Car() {
    }

    public String getName() {
        return this.name;
    }

    public CustomColor getColor() {
        return this.color;
    }

}

class CustomColor {

    String color;

    public CustomColor(String color) {
        this.color = color;
    }

    @JsonValue
    public String getColor() {
        return color;
    }
}
