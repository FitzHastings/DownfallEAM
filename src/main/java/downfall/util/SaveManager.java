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

package downfall.util;

/**
 * Interface for any class responsible for loading, saving and keeping track
 * of the last location to which the last load or to which last save was done.
 */
public interface SaveManager {
    /**
     * Loads and applies the savegame from last pathname used and attempts to find,
     * load and validate the rules that are referenced in the savegame.
     */
    void loadFromLast();

    /**
     * Loads and applies the savegame from a given pathname and attempts to find,
     * load and validate the rules that are referenced in the savegame.
     * @param pathname pathname to a savegame file.
     */
    void loadFrom(String pathname);

    /**
     * Formulates a new Savegame based on the state of the Configurator and then saves it
     * to the last pathname used.
     */
    void saveToLast();

    /**
     * Formulates a new Savegame ba
     * @param pathname pathname to a file in which the savegame data will be recorded.
     */
    void saveTo(String pathname);
}
