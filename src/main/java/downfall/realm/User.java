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

/**
 * Realm that is currently loaded and is being managed by the user
 * This class is non-instantiable use getInstance to get its only instance
 */
public class User extends Realm{
    private User() {
        super();
    }

    public static User instance = new User();

    /**
     * returns the only instance of User
     * @return the only instance of user
     */
    public static User getInstance() {
        return instance;
    }
}
