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

package downfall.fx.fetcher;

import downfall.realm.template.VisualBuildingTemplate;
import downfall.util.Configurator;

/**
 * Simple implementation of Fetcher class that returns a new instance of VisualBuildingTemplate on request
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public class SimpleBuildingTemplateFetcher implements Fetcher<VisualBuildingTemplate> {
    /**
     * This method is used to return a new instance of VisualBuildingTemplate on request.
     * @return a new instance of VisualBuildingTemplate with its id set to be one larger than the last VisualBuildingTemplate in the currently selected rules
     */
    @Override
    public VisualBuildingTemplate retrieve() {
        VisualBuildingTemplate template = new VisualBuildingTemplate();
        //TODO:Replace this mess with a proper solution distributing IDs.
        if(Configurator.getInstance().getRules().getBuildingTemplates().size() > 1)
            template.setId(Configurator.getInstance().getRules().getBuildingTemplates().get(Configurator.getInstance().getRules().getBuildingTemplates().size()-1).getId()+1);
        else
            template.setId(1);
        template.setName("New Building");
        template.setDefConstructionCost(30);
        template.setDefConstructionTime(3);
        return template;
    }
}
