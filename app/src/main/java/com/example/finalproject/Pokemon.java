package com.example.finalproject;

public class Pokemon {

    String name, description, type1, type2;
    int id,  hp, atk, def, spAtk, spDef, speed, weight, height;

    public Pokemon() {
    }

    public Pokemon(String name, String description, String type1, String type2,
                   String evolution, int id, int hp, int atk, int def, int spAtk,
                   int spDef, int speed, int weight, int height) {
        // JSON Object

        // position 5
        this.height = height;

        // position 7(6?)
        this.id = id;

        // position 11(10?)
        this.name = name;

        // 14(13?) link to another json e.g :"https://pokeapi.co/api/v2/pokemon-species/2/
        // position 7 of second object for an array with flavor text, grab array position 1 object 1
        // .flavor_text
        this.description = description;

        // array 17(16?)
        this.type1 = type1;
        this.type2 = type2;
        // array at 16(15?)
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.spAtk = spAtk;
            this.spDef = spDef;
            this.speed = speed;

        // array at 18(17?)
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", id=" + id +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", speed=" + speed +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
