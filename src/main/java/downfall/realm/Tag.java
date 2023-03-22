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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A Tag is a String value that can be applied to Actors. This is used in certain scripts, events can determine access to special mechanics.
 * This class is fully annotated for serialization with JAXB
 */
@XmlRootElement(name = "tag")
public class Tag {
    private final StringProperty tag = new SimpleStringProperty();

    /**
     * Lightweight accessor method
     * @return Tag title as a property
     */
    public StringProperty tagProperty() {
        return tag;
    }

    /**
     * Lightweight accessor method
     * @return Tag title as value
     */
    @XmlElement(name="value")
    public String getTag() {
        return tag.get();
    }

    /**
     * Lightweight mutator method
     * @param tag Tag name
     */
    public void setTag(String tag) {
        this.tag.set(tag);
    }
}
