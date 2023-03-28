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

/**
 * A Tag is a String value that can be applied to Actors. This is used in certain scripts, events can determine access to special mechanics.
 * This class is fully annotated for serialization with JAXB
 */
@XmlRootElement(name = "tag")
public class Tag {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty tag = new SimpleStringProperty();
    private final BooleanProperty isFactional = new SimpleBooleanProperty();

    public Tag() {
        super();
        id.set(-1);
        tag.set("");
        isFactional.set(false);
    }

    public Tag(Integer id, String tag, Boolean isFactional) {
        super();
        this.id.set(id);
        this.tag.set(tag);
        this.isFactional.set(isFactional);
    }

    /**
     * returns the tag value of the tag so that it can be made human-readable.
     * @return Tag name of this instance.
     */
    @Override
    public String toString(){
        return this.tag.get();
    }

    /**
     * Lightweight accessor method.
     * @return unique tag identifier as a property.
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Lightweight accessor method
     * @return Tag title as a property
     */
    public StringProperty tagProperty() {
        return tag;
    }

    /**
     * Lightweight accessor method
     * @return Flag that determines if this tag is used to assign faction memberships to a realm, as a property.
     */
    public BooleanProperty isFactionalProperty() {
        return isFactional;
    }

    /**
     * Lightweight accessor method.
     * @return unique tag identifier.
     */
    @XmlElement(name="id")
    public Integer getId() {
        return id.get();
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
     * Lightweight accessor method.
     * @return Flag that determines if this tag is used to assign faction memberships to a realm.
     */
    @XmlElement(name="factional")
    public Boolean isFactional() {
        return isFactional.get();
    }

    /**
     * Lightweight mutator method.
     * @param id Unique tag identifier.
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     * Lightweight mutator method
     * @param tag Tag name
     */
    public void setTag(String tag) {
        this.tag.set(tag);
    }

    /**
     * Lightweight mutator method.
     * @param isFactional Flag that determines if this tag is used to assign faction memberships to a realm.
     */
    public void setFactional(Boolean isFactional) {
        this.isFactional.set(isFactional);
    }
}
