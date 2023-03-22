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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Building {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);

    private final BooleanProperty isOperating = new SimpleBooleanProperty(false);

    public IntegerProperty idProperty() {
        return id;
    }

    public BooleanProperty isOperatingProperty() {
        return isOperating;
    }

    @XmlElement(name="id")
    public int getId() {
        return id.get();
    }

    @XmlElement(name="is-operating")
    public boolean isOperating() {
        return isOperating.get();
    }

    public void setId(int id) {
        this.id.setValue(id);
    }

    public void setOperating(boolean isOperating) {
        this.isOperating.setValue(isOperating);
    }
}
