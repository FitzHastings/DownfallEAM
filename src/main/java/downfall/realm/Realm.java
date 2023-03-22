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

    public IntegerProperty diplomaticReputationProperty() {
        return diplomaticReputation;
    }

    public IntegerProperty powerProjectionsProperty() {
        return powerProjections;
    }

    public IntegerProperty legitimacyProperty() {
        return legitimacy;
    }

    public IntegerProperty prestigeProperty() {
        return prestige;
    }

    public IntegerProperty infamyProperty() {
        return infamy;
    }

    public DoubleProperty stabilityProperty() {
        return stability;
    }

    public StringProperty realmPathToGFXProperty() {
        return realmPathToGFX;
    }

    public StringProperty rulerPathToGFXProperty() {
        return rulerPathToGFX;
    }

    @XmlElement(name = "diplomatic-reputation")
    public int getDiplomaticReputation() {
        return diplomaticReputation.get();
    }

    @XmlElement(name = "power-projection")
    public int getPowerProjections() {
        return powerProjections.get();
    }

    @XmlElement(name = "legitimacy")
    public int getLegitimacy() {
        return legitimacy.get();
    }

    @XmlElement(name = "prestige")
    public int getPrestige() {
        return prestige.get();
    }

    @XmlElement(name = "infamy")
    public int getInfamy() {
        return infamy.get();
    }

    @XmlElement(name = "stability")
    public double getStability() {
        return stability.get();
    }

    @XmlElement(name = "realm-path-to-gfx")
    public String getRealmPathToGFX() {
        return realmPathToGFX.get();
    }

    @XmlElement(name = "ruler-path-to-gfx")
    public String getRulerPathToFX() {
        return rulerPathToGFX.get();
    }

    public void setDiplomaticReputation(int diplomaticReputation) {
        this.diplomaticReputation.set(diplomaticReputation);
    }

    public void setPowerProjections(int powerProjections) {
        this.powerProjections.set(powerProjections);
    }

    public void setLegitimacy(int legitimacy) {
        this.legitimacy.set(legitimacy);
    }

    public void setPrestige(int prestige) {
        this.prestige.set(prestige);
    }

    public void setInfamy(int infamy) {
        this.infamy.set(infamy);
    }

    public void setStability(double stability) {
        this.stability.set(stability);
    }

    public void setRealmPathToGFX(String realmPathToGFX) {
        this.realmPathToGFX.set(realmPathToGFX);
    }

    public void setRulerPathToGFX(String rulerPathToGFX) {
        this.rulerPathToGFX.set(rulerPathToGFX);
    }
}
