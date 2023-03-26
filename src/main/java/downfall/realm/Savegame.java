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

/**
 * a Save class that holds the information about a user's Realm and the Ruleset on which the Realm was created.
 */
@XmlRootElement(name="downfall-save")
public class Savegame {
    protected String pathToRules = "";
    protected Realm userRealm = new Realm();

    /**
     * Default constructor. Default values are "" pathToRules, and an empty Realm
     */
    public Savegame() {
        super();
    }

    /**
     *
     * @param pathToRules pathname to the rules that were used for this Save
     * @param userRealm user's realm data.
     */
    public Savegame(String pathToRules, Realm userRealm) {
        this.pathToRules = pathToRules;
        this.userRealm = userRealm;
    }

    /**
     * Lightweight accessor method.
     * @return pathname to the rules that were used for this Realm
     */
    @XmlElement(name = "path-to-rules")
    public String getPathToRules() {
        return pathToRules;
    }

    /**
     * Lightweight accessor method.
     * @return user's realm data.
     */
    @XmlElement(name = "user-realm")
    public Realm getUserRealm() {
        return userRealm;
    }

    /**
     * Lightweight mutator method.
     * @param pathToRules pathname to the rules that were used for this Save
     */
    public void setPathToRules(String pathToRules) {
        this.pathToRules = pathToRules;
    }

    /**
     * Lightweight mutator method.
     * @param userRealm user's realm data.
     */
    public void setUserRealm(Realm userRealm) {
        this.userRealm = userRealm;
    }
}
