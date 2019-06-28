package com.cts.training.myapp.platformsample;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

public class TriggerOnCreateNode implements NodeServicePolicies.OnCreateNodePolicy{
	

	private PolicyComponent policyComponent;
	private Behaviour onCreateNode;
	private NodeService nodeService;
	
	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	public void setOnCreateNode(Behaviour onCreateNode) {
		this.onCreateNode = onCreateNode;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void init()
	{
		this.onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
		this.policyComponent.bindClassBehaviour(
		        QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"),
		        ContentModel.TYPE_CONTENT,
		        this.onCreateNode
		    );
	}
			
	public void onCreateNode(ChildAssociationRef childassocref) {
		// TODO Auto-generated method stub
		NodeRef createdNode=childassocref.getChildRef();
		String createdNodeName=(String) nodeService.getProperty(createdNode, ContentModel.PROP_NAME);
		if(createdNodeName.contains(".xls")||createdNodeName.contains(".doc")||createdNodeName.contains(".pdf")||createdNodeName.contains(".docx"))
		{
			//nodeService.setType(createdNode, QName.createQName("mysys:asset"));
			nodeService.setType(createdNode, QName.createQName("http://www.mysys.com/model/content/1.0", "asset"));
		}
	}
}
