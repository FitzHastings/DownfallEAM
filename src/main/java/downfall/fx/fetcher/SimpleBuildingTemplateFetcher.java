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
