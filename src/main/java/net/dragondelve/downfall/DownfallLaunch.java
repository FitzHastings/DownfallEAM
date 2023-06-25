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

package net.dragondelve.downfall;

/**
 * A simple class whose only purpose is to have a main method which calls the actual main method of the program
 * This enables building of jars that will properly include and reference the javaFX runtime components
 */
public class DownfallLaunch {
    /**
     * Calls the main method of
     * @param args program arguments that will be passed to DownfallMain where they will be ignored.
     */
    public static void main(String[] args) {
        DownfallMain.main(args);
    }
}
