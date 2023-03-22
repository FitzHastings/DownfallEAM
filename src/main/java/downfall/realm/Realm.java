// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package downfall.realm;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * Realm is a data model class that represents an actor who possesses diplomatic attributes and is capable of having characters in its court.
 */
@XmlRootElement(name = "realm")
public class Realm extends Actor{
    public IntegerProperty diplomaticReputation = new SimpleIntegerProperty();
    public IntegerProperty powerProjections = new SimpleIntegerProperty();
    public IntegerProperty legitimacy = new SimpleIntegerProperty();
    public IntegerProperty prestige = new SimpleIntegerProperty();
    public IntegerProperty infamy = new SimpleIntegerProperty();
    public DoubleProperty stability = new SimpleDoubleProperty();
    public StringProperty realmPathToGFX = new SimpleStringProperty();
    public StringProperty rulerPathToGFX = new SimpleStringProperty();

    /**
     * Default constructor. Does not provide any default values
     */
    public Realm() {
        super();
    }

    /**
     *
     * @param id Unique realm identifier. Should be unique for every save file
     * @param name A human-readable name of the realm as a property.
     * @param treasury Amount of money in the treasury as a property.
     * @param stockpile List of all materials in realm's stockpile
     * @param ownedBuildings List of all owned buildings
     * @param tags List of all tags applied to the realm
     */
    public Realm(Integer id, String name, Integer treasury, ObservableList<Material> stockpile, ObservableList<Building> ownedBuildings, ObservableList<Tag> tags) {
        super(id, name, treasury, stockpile, ownedBuildings, tags);
    }

    /**
     *
     * @param id Unique realm identifier. Should be unique for every save file
     * @param name A human-readable name of the realm as a property.
     * @param treasury Amount of money in the treasury as a property.
     * @param stockpile List of all materials in realm's stockpile
     * @param ownedBuildings List of all owned buildings
     * @param tags List of all tags applied to the realm
     * @param diplomaticReputation a measure of diplomatic reputation of the Realm. Used when taking diplomatic actions.
     * @param powerProjection a measure of perceived power of the realm. Used when evaluating threats and taking DoW decisions
     * @param legitimacy a measure of legitimacy of the ruler. Affects unrest.
     * @param prestige a measure of a ruler's prestige. Expended and gained as a diplomatic resource
     * @param infamy a measure of the ruler's infamy. Affects unrest, used when taking diplomatic actions.
     * @param stability a measure of the realm's stability. affects unrest.
     * @param realmPathToGFX path to the image of a flag/coat of arms that represents the realm.
     * @param rulerPathToGFX path to the image of a flag/coat of arms that represents the ruling body.
     */
    public Realm(Integer id, String name, Integer treasury, ObservableList<Material> stockpile, ObservableList<Building> ownedBuildings, ObservableList<Tag> tags, Integer diplomaticReputation, Integer powerProjection, Integer legitimacy, Integer prestige, Integer infamy,Double stability, String realmPathToGFX, String rulerPathToGFX) {
        super(id, name, treasury, stockpile, ownedBuildings, tags);
        this.diplomaticReputation.set(diplomaticReputation);
        this.powerProjections.set(powerProjection);
        this.legitimacy.set(legitimacy);
        this.prestige.set(prestige);
        this.infamy.set(infamy);
        this.stability.set(stability);
        this.realmPathToGFX.set(realmPathToGFX);
        this.rulerPathToGFX.set(rulerPathToGFX);
    }

    /**
     * Lightweight accessor method
     * @return a measure of diplomatic reputation of the Realm. Used when taking diplomatic actions as a property.
     */
    public IntegerProperty diplomaticReputationProperty() {
        return diplomaticReputation;
    }

    /**
     * Lightweight accessor method
     * @return a measure of perceived power of the realm as a property. Used when evaluating threats and taking DoW decisions.
     */
    public IntegerProperty powerProjectionsProperty() {
        return powerProjections;
    }

    /**
     * Lightweight accessor method
     * @return a measure of legitimacy of the ruler as a property. Affects unrest.
     */
    public IntegerProperty legitimacyProperty() {
        return legitimacy;
    }

    /**
     * Lightweight accessor method
     * @return a measure of a ruler's prestige. Expended and gained as a diplomatic resource as a property.
     */
    public IntegerProperty prestigeProperty() {
        return prestige;
    }

    /**
     * Lightweight accessor method
     * @return a measure of the ruler's infamy as a property. Affects unrest, used when taking diplomatic actions.
     */
    public IntegerProperty infamyProperty() {
        return infamy;
    }

    /**
     * Lightweight accessor method
     * @return a measure of the realm's stability as a property. affects unrest.
     */
    public DoubleProperty stabilityProperty() {
        return stability;
    }

    /**
     * Lightweight accessor method
     * @return path to the image of a flag/coat of arms that represents the realm as a property.
     */
    public StringProperty realmPathToGFXProperty() {
        return realmPathToGFX;
    }

    /**
     * Lightweight accessor method
     * @return path to the image of a flag/coat of arms that represents the ruling body as a property.
     */
    public StringProperty rulerPathToGFXProperty() {
        return rulerPathToGFX;
    }

    /**
     * Lightweight accessor method
     * @return a measure of diplomatic reputation of the Realm. Used when taking diplomatic actions.
     */
    @XmlElement(name = "diplomatic-reputation")
    public int getDiplomaticReputation() {
        return diplomaticReputation.get();
    }

    /**
     * Lightweight accessor method
     * @return a measure of perceived power of the realm. Used when evaluating threats and taking DoW decisions.
     */
    @XmlElement(name = "power-projection")
    public int getPowerProjections() {
        return powerProjections.get();
    }

    /**
     * Lightweight accessor method
     * @return a measure of legitimacy of the ruler. Affects unrest.
     */
    @XmlElement(name = "legitimacy")
    public int getLegitimacy() {
        return legitimacy.get();
    }

    /**
     * Lightweight accessor method
     * @return a measure of a ruler's prestige. Expended and gained as a diplomatic resource
     */
    @XmlElement(name = "prestige")
    public int getPrestige() {
        return prestige.get();
    }

    /**
     * Lightweight accessor method
     * @return a measure of the ruler's infamy. Affects unrest, used when taking diplomatic actions.
     */
    @XmlElement(name = "infamy")
    public int getInfamy() {
        return infamy.get();
    }

    /**
     * Lightweight accessor method
     * @return a measure of the realm's stability. affects unrest.
     */
    @XmlElement(name = "stability")
    public double getStability() {
        return stability.get();
    }

    /**
     * Lightweight accessor method
     * @return path to the image of a flag/coat of arms that represents the realm.
     */
    @XmlElement(name = "realm-path-to-gfx")
    public String getRealmPathToGFX() {
        return realmPathToGFX.get();
    }

    /**
     * Lightweight accessor method
     * @return path to the image of a flag/coat of arms that represents the ruling body.
     */
    @XmlElement(name = "ruler-path-to-gfx")
    public String getRulerPathToFX() {
        return rulerPathToGFX.get();
    }

    /**
     * Lightweight mutator method
     * @param diplomaticReputation a measure of diplomatic reputation of the Realm. Used when taking diplomatic actions.
     */
    public void setDiplomaticReputation(int diplomaticReputation) {
        this.diplomaticReputation.set(diplomaticReputation);
    }

    /**
     * Lightweight mutator method
     * @param powerProjections a measure of perceived power of the realm. Used when evaluating threats and taking DoW decisions
     */
    public void setPowerProjections(int powerProjections) {
        this.powerProjections.set(powerProjections);
    }

    /**
     * Lightweight mutator method
     * @param legitimacy a measure of legitimacy of the ruler. Affects unrest.
     */
    public void setLegitimacy(int legitimacy) {
        this.legitimacy.set(legitimacy);
    }

    /**
     * Lightweight mutator method
     * @param prestige a measure of a ruler's prestige. Expended and gained as a diplomatic resource
     */
    public void setPrestige(int prestige) {
        this.prestige.set(prestige);
    }

    /**
     * Lightweight mutator method
     * @param infamy a measure of the ruler's infamy. Affects unrest, used when taking diplomatic actions.
     */
    public void setInfamy(int infamy) {
        this.infamy.set(infamy);
    }

    /**
     * Lightweight mutator method
     * @param stability a measure of the realm's stability. affects unrest.
     */
    public void setStability(double stability) {
        this.stability.set(stability);
    }

    /**
     * Lightweight mutator method
     * @param realmPathToGFX path to the image of a flag/coat of arms that represents the realm.
     */
    public void setRealmPathToGFX(String realmPathToGFX) {
        this.realmPathToGFX.set(realmPathToGFX);
    }

    /**
     * Lightweight mutator method
     * @param rulerPathToGFX path to the image of a flag/coat of arms that represents the ruling body.
     */
    public void setRulerPathToGFX(String rulerPathToGFX) {
        this.rulerPathToGFX.set(rulerPathToGFX);
    }
}
