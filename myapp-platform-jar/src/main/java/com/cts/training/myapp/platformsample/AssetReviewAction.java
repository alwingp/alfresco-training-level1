package com.cts.training.myapp.platformsample;

import java.util.List;

import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;

public class AssetReviewAction extends ActionExecuterAbstractBase{

	NodeService nodeService;
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@Override
	protected void executeImpl(Action arg0, NodeRef actedOnNodeRef) {
		// TODO Auto-generated method stub
		if(nodeService.hasAspect(actedOnNodeRef, QName.createQName("http://www.mysys.com/model/content/1.0", "assetReview")))
		{
			//nodeService.setProperty(actedOnNodeRef, QName.createQName("mysys:assetReviewStatus"), "Production-Ready");
			nodeService.setProperty(actedOnNodeRef, QName.createQName("http://www.mysys.com/model/content/1.0", "assetReviewStatus"), "Production-Ready");
		}
	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
