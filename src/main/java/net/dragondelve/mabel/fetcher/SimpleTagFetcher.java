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

package net.dragondelve.mabel.fetcher;

import net.dragondelve.downfall.realm.Tag;
import net.dragondelve.downfall.util.Configurator;

/**
 * Simple implementation of Fetcher class that returns a new instance of Tag on request
 */
public final class SimpleTagFetcher implements Fetcher<Tag> {

    /**
     * This method is used to return a new instance of Tag on request.
     *
     * @return a new instance of Tag with its id set to be one larger than the last Tag in the currently selected rules
     */
    @Override
    public Tag retrieve() {
        Tag tag = new Tag();
        //TODO:Replace this mess with a proper solution distributing IDs.
        //if there are any Tags in the current rules
        if (Configurator.getInstance().getRules().getActorTags().size() > 1)
            //set the new instance's id to be equal of the last item in that list incremented by one
            tag.setId(Configurator.getInstance().getRules().getMaterialTemplates().get(Configurator.getInstance().getRules().getMaterialTemplates().size() - 1).getId() + 1);
        else
            tag.setId(1);
        tag.setTag("New Tag");
        return tag;
    }
}
